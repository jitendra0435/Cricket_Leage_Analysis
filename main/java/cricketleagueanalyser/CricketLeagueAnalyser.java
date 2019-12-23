package cricketleagueanalyser;
import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {
    Map<String, IPLDAO> playerHashMap = new HashMap<>();
    Map<String,IPLDAO> mapAllrounder=null;
    Map<EnumField, Comparator<IPLDAO>> fieldNameComparatorMap = null;
    public enum Player {BATSMAN, BOWLER};
    private Player player;
    public CricketLeagueAnalyser() {
        mapAllrounder=new HashMap<>();
        this.fieldNameComparatorMap = new HashMap();
        this.fieldNameComparatorMap.put(EnumField.ECONOMY, Comparator.comparing(census -> census.economy, Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.STRIKERATES, Comparator.comparing(census -> census.batStrikeRate, Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.AVERAGE, Comparator.comparing(census -> census.average, Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.SixesAndFours, new SortedMethodContainer().reversed());
        this.fieldNameComparatorMap.put(EnumField.StrikeRateWithSixesAndFours, new SortedMethodContainer().reversed().thenComparing(cencus -> cencus.batStrikeRate));
        this.fieldNameComparatorMap.put(EnumField.STRIKERATESWITH4AND5WICKETS, new SortedMethodForWickets().reversed().thenComparing(cencus -> cencus.economy));

        Comparator<IPLDAO> averageComparator = Comparator.comparing(cencus -> cencus.average);
        this.fieldNameComparatorMap.put(EnumField.StrikeRateWithAvg,averageComparator.thenComparing(cencus ->cencus.batStrikeRate).reversed());

        Comparator<IPLDAO> averageComparatorForRuns = Comparator.comparing(cencus -> cencus.average);
        this.fieldNameComparatorMap.put(EnumField.MaxRunsWithAvg, averageComparatorForRuns.thenComparing(cencus->cencus.runs));

        Comparator<IPLDAO> wicketComparator = Comparator.comparing(cencus -> cencus.wickets);
        this.fieldNameComparatorMap.put(EnumField.MAXWICKETSWITHAVERAGE,wicketComparator.thenComparing(cencus -> cencus.average));

        Comparator<IPLDAO> comparing4 = Comparator.comparing(census -> census.average);
        this.fieldNameComparatorMap.put(EnumField.BESTAVGBAT_BALL, comparing4.thenComparing(census -> census.ballAverage).reversed());
    }

    public Map<String, IPLDAO> loadIPLCSV(Player player, String csvFilePath) throws CricketLeagueAnalyserException {
        IPLAdapter iplAdapter = IplAdapterFactory.getCensusData(player);
        playerHashMap = iplAdapter.loadCensusData(player, csvFilePath);
        return playerHashMap;
    }

    public String getSortedPlayerNameByField(EnumField field) throws CricketLeagueAnalyserException {
        if (playerHashMap == null || playerHashMap.size() == 0) {
            throw new CricketLeagueAnalyserException("No Data", CricketLeagueAnalyserException
                    .ExceptionType.CENSUS_FILE_PROBLEM);
        }
        ArrayList arrayList = playerHashMap.values().stream()
                .sorted(this.fieldNameComparatorMap.get(field))
                .collect(toCollection(ArrayList::new));
        return new Gson().toJson(arrayList);
    }

    public List<IPLDAO> sortByAvg(Map<String, IPLDAO> batsManAvg, Map<String, IPLDAO> ballAvg, EnumField field) {
            for (Map.Entry<String, IPLDAO> entry : batsManAvg.entrySet()
            ) {
                for (Map.Entry<String, IPLDAO> entry1 : ballAvg.entrySet()
                )
                    if (entry.getKey().equals(entry1.getKey())) {
                        mapAllrounder.put(entry.getKey(), new IPLDAO(entry.getValue()
                                .player, entry.getValue()
                                .average, entry.getValue().ballAverage));
                    }
            }
            List<IPLDAO> sortedPlayer = mapAllrounder.values().stream()
                    .sorted(fieldNameComparatorMap.get(field).reversed())
                    .collect(Collectors.toList());
            return sortedPlayer;
    }
}
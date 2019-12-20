package cricketleagueanalyser;
import com.google.gson.Gson;
import java.util.*;
import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {
    Map<String, IPLDAO> playerHashMap = new HashMap<>();
    Map<EnumField, Comparator<IPLDAO>> fieldNameComparatorMap = null;

    public enum Player {BATSMAN, BOWLER};
    private Player player;

    public CricketLeagueAnalyser() {

        this.fieldNameComparatorMap = new HashMap();
        this.fieldNameComparatorMap.put(EnumField.ECONOMY, Comparator.comparing(census -> census.economy, Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.STRIKERATES, Comparator.comparing(census -> census.batStrikeRate, Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.AVERAGE, Comparator.comparing(census -> census.average, Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.SixesAndFours, new SortedMethodContainer().reversed());
        this.fieldNameComparatorMap.put(EnumField.StrikeRateWithSixesAndFours, new SortedMethodContainer().reversed().thenComparing(cencus -> cencus.batStrikeRate));
        this.fieldNameComparatorMap.put(EnumField.STRIKERATESWITH4AND5WICKETS, new SortedMethodForWickets().reversed().thenComparing(cencus -> cencus.economy));

        Comparator<IPLDAO> averageComparator = Comparator.comparing(cencus -> cencus.average);
        Comparator<IPLDAO> strikingRateComparator = Comparator.comparing(cencus -> cencus.batStrikeRate);
        Comparator<IPLDAO> resultOfComparator = averageComparator.thenComparing(strikingRateComparator);
        this.fieldNameComparatorMap.put(EnumField.StrikeRateWithAvg, resultOfComparator.reversed());

        Comparator<IPLDAO> averageComparatorForRuns = Comparator.comparing(cencus -> cencus.average);
        Comparator<IPLDAO> maxRuns = Comparator.comparing(cencus -> cencus.runs);
        Comparator<IPLDAO> maxRunsWithAvg = averageComparatorForRuns.thenComparing(maxRuns);
        this.fieldNameComparatorMap.put(EnumField.MaxRunsWithAvg, maxRuns.reversed());
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
}
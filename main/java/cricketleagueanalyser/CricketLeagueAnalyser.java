package cricketleagueanalyser;
import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;
import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {
    Map<String, IPLDAO> playerHashMap = new HashMap<>();
    Map<EnumField, Comparator<IPLDAO>> fieldNameComparatorMap = null;

    public CricketLeagueAnalyser() {
        this.fieldNameComparatorMap=new HashMap();
        this.fieldNameComparatorMap.put(EnumField.STRIKERATES,Comparator.comparing(census->census.batStrikeRate,Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.AVERAGE,Comparator.comparing(census->census.average,Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.SixesAndFours,new SortedMethodContainer().reversed());
        this.fieldNameComparatorMap.put(EnumField.StrikeRateWithSixesAndFours, new SortedMethodContainer().reversed().thenComparing(cencus -> cencus.batStrikeRate));

        Comparator<IPLDAO> averageComparator = Comparator.comparing(cencus -> cencus.average);
        Comparator<IPLDAO> strikingRateComparator = Comparator.comparing(cencus -> cencus.batStrikeRate);
        Comparator<IPLDAO> resultOfComparator = averageComparator.thenComparing(strikingRateComparator);
        this.fieldNameComparatorMap.put(EnumField.StrikeRateWithAvg,resultOfComparator.reversed());

        Comparator<IPLDAO> averageComparatorForRuns = Comparator.comparing(cencus -> cencus.average);
        Comparator<IPLDAO> maxRuns = Comparator.comparing(cencus -> cencus.runs);
        Comparator<IPLDAO> maxRunsWithAvg = averageComparatorForRuns.thenComparing(maxRuns);
        this.fieldNameComparatorMap.put(EnumField.MaxRunsWithAvg, maxRuns.reversed());
    }

    public int loadIplMostRunCSV(String iplMostRunsCSVFilePath) throws CricketLeagueAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplMostRunsCSVFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLMostRunsCSV> censusList = csvBuilder.getCSVFileIterartor(reader, IPLMostRunsCSV.class);
            Iterable<IPLMostRunsCSV> indiaCensusCSVS = () -> censusList;
            StreamSupport.stream(indiaCensusCSVS.spliterator(), false)
                    .map(IPLMostRunsCSV.class::cast)
                    .forEach(csvCensus -> playerHashMap.put(csvCensus.player, new IPLDAO(csvCensus)));
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING);
        } catch (CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return playerHashMap.size();
    }

    public int loadIplMostWicketCSV(String iplMostWicketsCSVFilePath) throws CricketLeagueAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplMostWicketsCSVFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLMostWicketCSV> censusList = csvBuilder.getCSVFileIterartor(reader, IPLMostWicketCSV.class);
            Iterable<IPLMostWicketCSV> indiaCensusCSVS = () -> censusList;
            StreamSupport.stream(indiaCensusCSVS.spliterator(), false)
                    .map(IPLMostWicketCSV.class::cast)
                    .forEach(csvCensus -> playerHashMap.put(csvCensus.player, new IPLDAO(csvCensus)));
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING);
        } catch (CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return playerHashMap.size();
    }

    public String getSortedPlayerNameByField(EnumField field) throws CricketLeagueAnalyserException {
        if (playerHashMap == null || playerHashMap.size() == 0) {
            throw new CricketLeagueAnalyserException("No Data", CricketLeagueAnalyserException
                    .ExceptionType.CENSUS_FILE_PROBLEM);
        }
        ArrayList arrayList = playerHashMap.values().stream()
                .sorted(this.fieldNameComparatorMap.get(field))
                .collect(toCollection(ArrayList::new));
        String sortedStateCensus = new Gson().toJson(arrayList);
        return sortedStateCensus;
    }
}
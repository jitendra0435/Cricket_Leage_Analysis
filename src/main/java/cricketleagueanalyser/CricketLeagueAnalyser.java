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
    Map<String, IPLMostRuns2019DAO> playerHashmap = new HashMap<>();
    Map<EnumField,Comparator<IPLMostRuns2019DAO>> fieldNameComparatorMap=null;

    public CricketLeagueAnalyser() {
        this.fieldNameComparatorMap=new HashMap();
        this.fieldNameComparatorMap.put(EnumField.STRIKERATES,Comparator.comparing(census->census.StrikeRate,Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.AVERAGE,Comparator.comparing(census->census.Avg,Comparator.reverseOrder()));
        this.fieldNameComparatorMap.put(EnumField.SixesAndFours,new SortedMethodContainer().reversed());
    }

    public int loadIplMostRunCSV(String iplMostRunsCSVFilepath) throws CricketLeagueAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplMostRunsCSVFilepath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLMostRunsCSV> censusList = csvBuilder.getCSVFileIterartor(reader, IPLMostRunsCSV.class);
            Iterable<IPLMostRunsCSV> indiaCensusCSVS = () -> censusList;
            StreamSupport.stream(indiaCensusCSVS.spliterator(), false)
                    .map(IPLMostRunsCSV.class::cast)
                    .forEach(csvCensus -> playerHashmap.put(csvCensus.player, new IPLMostRuns2019DAO(csvCensus)));
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING);
        } catch (CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return playerHashmap.size();
    }

    public String getTopAverageBattingPlayerName(EnumField field) throws CricketLeagueAnalyserException {
        if (playerHashmap == null || playerHashmap.size() == 0) {
            throw new CricketLeagueAnalyserException("No Data", CricketLeagueAnalyserException
                    .ExceptionType.CENSUS_FILE_PROBLEM);
        }
        ArrayList arrayList = playerHashmap.values().stream()
                .sorted(this.fieldNameComparatorMap.get(field))
                .collect(toCollection(ArrayList::new));
        String sortedStateCensus = new Gson().toJson(arrayList);
        return sortedStateCensus;
    }
}
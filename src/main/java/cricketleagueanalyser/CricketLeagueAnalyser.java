package cricketleagueanalyser;
import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
    Map<String, IPLMostRuns2019DAO> playerHashmap = new HashMap<>();
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
}
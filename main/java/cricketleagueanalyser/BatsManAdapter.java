package cricketleagueanalyser;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsManAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLDAO> loadCensusData(CricketLeagueAnalyser.Player player, String csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IPLDAO> playerHashMap= super.loadCensusData(IPLMostRunsCSV.class,csvFilePath);
        return playerHashMap;
    }
    public int loadIndiaStateCodeData(String IndiaStateCodeCSV) throws CricketLeagueAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(IndiaStateCodeCSV));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLMostRunsCSV> stateCSVIterator = csvBuilder.getCSVFileIterartor(reader, IPLMostRunsCSV.class);
            Iterable<IPLMostRunsCSV> csvIterable = () -> stateCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> this.playerHashMap.get(csvState.player) != null)
                    .forEach(csvState -> this.playerHashMap.get(csvState.player));
            return this.playerHashMap.size();
        }catch (RuntimeException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING);
       } catch (IOException | CSVBuilderException e) {
           throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING);
        }
    }
}

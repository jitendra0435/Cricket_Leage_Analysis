package cricketleagueanalyser;

import com.bridgelabzs.CSVBuilderException;
import com.bridgelabzs.CSVBuilderFactory;
import com.bridgelabzs.ICSVBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {

    public abstract Map<String, IPLDAO> loadCensusData( CricketLeagueAnalyser.Player player,String csvFilePath) throws CricketLeagueAnalyserException;
    Map<String, IPLDAO> playerHashMap = new HashMap<>();

    public <E> Map<String,IPLDAO> loadCensusData(Class<E> censusCSVClass, String csvFilePath) throws CricketLeagueAnalyserException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createOpenCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader,censusCSVClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if (censusCSVClass.getName().equals("cricketleagueanalyser.IPLMostRunsCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLMostRunsCSV.class::cast)
                        .forEach(censusCSV -> playerHashMap.put(censusCSV.player, new IPLDAO(censusCSV)));
            } else if (censusCSVClass.getName().equals("cricketleagueanalyser.IPLMostWicketCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLMostWicketCSV.class::cast)
                        .forEach(censusCSV -> playerHashMap.put(censusCSV.player, new IPLDAO(censusCSV)));
            }
            return playerHashMap;
        } catch (RuntimeException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING);
        } catch (IOException | CSVBuilderException e) {
            throw new CSVBuilderException(e.getMessage(),CSVBuilderException.ExceptionType.NO_CENSUS_DATA);
        }
    }
}

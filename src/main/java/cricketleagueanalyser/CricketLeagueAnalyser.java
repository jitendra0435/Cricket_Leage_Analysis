package cricketleagueanalyser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
    public int loadIplMostRunCSV(String iplMostRunsCSVFilepath) throws CricketLeagueAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(iplMostRunsCSVFilepath));
            CsvToBeanBuilder<IPLMostRunsCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IPLMostRunsCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IPLMostRunsCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IPLMostRunsCSV> censusCSVIterator = csvToBean.iterator();
            Iterable<IPLMostRunsCSV> csvIterable = () -> censusCSVIterator;
            int numOfPlayers = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfPlayers;
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }
}


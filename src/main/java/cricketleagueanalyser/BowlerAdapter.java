package cricketleagueanalyser;
import com.bridgelabzs.CSVBuilderException;

import java.util.Map;

public class BowlerAdapter extends IPLAdapter {
    @Override
    public Map<String, IPLDAO> loadCensusData(CricketLeagueAnalyser.Player player, String csvFilePath) throws CricketLeagueAnalyserException {
        try {
            return loadCensusData(IPLMostWicketCSV.class, csvFilePath);
        } catch (CSVBuilderException e) {
            return null;
        }
    }
}

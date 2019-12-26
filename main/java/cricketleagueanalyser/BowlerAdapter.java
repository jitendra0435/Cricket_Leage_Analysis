package cricketleagueanalyser;
import java.util.Map;

public class BowlerAdapter extends IPLAdapter {
    @Override
    public Map<String, IPLDAO> loadCensusData(CricketLeagueAnalyser.Player player, String csvFilePath) throws CricketLeagueAnalyserException {
        return loadCensusData(IPLMostWicketCSV.class,csvFilePath);
    }
}

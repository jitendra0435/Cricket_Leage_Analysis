package cricketleagueanalyser;
import java.util.Map;;
public class BatsManAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLDAO> loadCensusData(CricketLeagueAnalyser.Player player, String csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IPLDAO> playerHashMap= super.loadCensusData(IPLMostRunsCSV.class,csvFilePath);
        return playerHashMap;
    }
}

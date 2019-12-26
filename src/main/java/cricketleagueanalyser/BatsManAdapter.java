package cricketleagueanalyser;

import com.bridgelabzs.CSVBuilderException;

import java.util.Map;;
public class BatsManAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLDAO> loadCensusData(CricketLeagueAnalyser.Player player, String csvFilePath) throws CricketLeagueAnalyserException{
        Map<String, IPLDAO> playerHashMap= null;
        try {
            playerHashMap = super.loadCensusData(IPLMostRunsCSV.class,csvFilePath);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return playerHashMap;
    }
}

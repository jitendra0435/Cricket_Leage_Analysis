package cricketleagueanalyser;
import com.bridgelabzs.CSVBuilderException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatsManMockitoTest {
    private static final String RUNS_FACT_SHEET = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    CricketLeagueAnalyser cricketLeagueAnalyser;

    @Mock
    BatsManAdapter batsManAdapter;
    Map<String,IPLDAO> batsManMap =null;

    @Rule
    public MockitoRule mockitoRule=MockitoJUnit.rule();

    @Before
    public void setData() throws CricketLeagueAnalyserException{
        batsManMap =new HashMap<>();
        batsManMap.put("David Warner",new IPLDAO("David Warner",692,63.3,1,123.0));
        batsManMap.put("KL Rahul",new IPLDAO("KL Rahul",593,55.2,1,98.23));
        batsManMap.put("Quinton de Kock",new IPLDAO("Quinton de Kock",529,35,0,101.23));
        BatsManAdapter batsManAdapter=mock(BatsManAdapter.class);
        when(batsManAdapter.loadCensusData(CricketLeagueAnalyser.Player.BATSMAN,RUNS_FACT_SHEET)).thenReturn(batsManMap);
        cricketLeagueAnalyser=new CricketLeagueAnalyser();
        cricketLeagueAnalyser.setAdapter(batsManAdapter);
    }

    @Test
    public void givenMethod_ShouldReturnNumberOfRecords() throws CricketLeagueAnalyserException {
        Map<String, IPLDAO> data = null;
        data = batsManAdapter.loadCensusData(CricketLeagueAnalyser.Player.BATSMAN,RUNS_FACT_SHEET);
        Assert.assertEquals(3,data.size());
    }

    @Test
    public void givenMethod_ShouldReturnTop_ScorerFromIPL2019() {
        try {
            batsManAdapter.loadCensusData(CricketLeagueAnalyser.Player.BATSMAN,RUNS_FACT_SHEET);
            String sortedIplData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.AVERAGE);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedIplData, IPLDAO[].class);
            Assert.assertEquals("David Warner ", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }


    @Test
    public void givenMethod_ShouldReturnWorstAverage_FromIPL2019() {
        try {
            batsManAdapter.loadCensusData(CricketLeagueAnalyser.Player.BATSMAN,RUNS_FACT_SHEET);
            String sortedIplData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.AVERAGE);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedIplData, IPLDAO[].class);
            Assert.assertEquals("Quinton de Kock", censusCSV[2].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenMethod_ShouldReturnTopStrikeRate_FromIPL2019() {
        try {
            batsManAdapter.loadCensusData(CricketLeagueAnalyser.Player.BATSMAN,RUNS_FACT_SHEET);
            String sortedIplData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.STRIKERATES);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedIplData, IPLDAO[].class);
            Assert.assertEquals("David Warner", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenMethod_ShouldReturnWorstStrikeRate_FromIPL2019() {
        try {
            batsManAdapter.loadCensusData(CricketLeagueAnalyser.Player.BATSMAN,RUNS_FACT_SHEET);
            String sortedIplData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.STRIKERATES);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedIplData, IPLDAO[].class);
            Assert.assertEquals("KL Rahul", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }
}


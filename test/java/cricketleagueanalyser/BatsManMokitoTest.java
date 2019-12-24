package cricketleagueanalyser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.Map;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatsManMokitoTest {
   @Mock
    BatsManAdapter batsManAdapter;
    IPLAdapter iplAdapter;
    Map<String,IPLDAO> dummyMap =null;

    @Rule
    public MockitoRule mockitoRule=MockitoJUnit.rule();
    private static final String IPL_MOST_RUNS_SHEET="/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Before
    public void setData(){
        IPLDAO iplDao=new IPLDAO();
        dummyMap.put("Marcus Stonnis",iplDao);
        dummyMap.put("Sunil Narine",iplDao);
        dummyMap.put("Chris Gayle",iplDao);
    }

    @Test
    public void givenMethod_ShouldReturnNumberOfRecords() throws CricketLeagueAnalyserException {
       IPLAdapter iplAdapter=mock(IplAdapterFactory.getCensusData(CricketLeagueAnalyser.Player.BATSMAN).getClass());
       CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(iplAdapter);
       when(iplAdapter.loadCensusData(CricketLeagueAnalyser.Player.BATSMAN,IPL_MOST_RUNS_SHEET)).thenReturn(dummyMap);
       Map<String,IPLDAO> mapData=cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL_MOST_RUNS_SHEET);
       Assert.assertEquals(3,mapData.size());
    }
}

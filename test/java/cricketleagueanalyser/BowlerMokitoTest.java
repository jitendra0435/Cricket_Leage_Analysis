package cricketleagueanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BowlerMokitoTest {
    @Mock
    BatsManAdapter batsManAdapter;
    IPLAdapter iplAdapter;
    Map<String, IPLDAO> dummyMap = null;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    private static final String IPL_MOST_WICKET_SHEET = "/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Before
    public void setData() {
        dummyMap = new HashMap<>();
        dummyMap.put("Lasith Malinga", new IPLDAO("B", 12, 55, 33, 55));
        dummyMap.put("Jasprit Bumrah", new IPLDAO("A", 30, 105, 55, 22));
        dummyMap.put("Tim Southee", new IPLDAO("Z", 66, 200, 69, 66));
    }

    @Test
    public void givenMethod_ShouldReturnNumberOfRecords() throws CricketLeagueAnalyserException {
        IPLAdapter iplAdapter = mock(IplAdapterFactory.getCensusData(CricketLeagueAnalyser.Player.BOWLER).getClass());
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(iplAdapter);
        when(iplAdapter.loadCensusData(CricketLeagueAnalyser.Player.BOWLER, IPL_MOST_WICKET_SHEET)).thenReturn(dummyMap);
        Map<String, IPLDAO> mapData = cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER, IPL_MOST_WICKET_SHEET);
        Assert.assertEquals(3, mapData.size());
    }
}

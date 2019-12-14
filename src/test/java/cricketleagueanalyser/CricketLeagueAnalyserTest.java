package cricketleagueanalyser;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {
    private static final String IPL2019_SHEET_MOSTRUNS = "/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenMethodFor_ReturningCorrectRecordsFrom_IPL2019MostRunsCSV(){
        int records= 0;
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            records = cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOSTRUNS);
            Assert.assertEquals(101,records);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }
}

package cricketleagueanalyser;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {
    private static final String IPL2019_SHEET_MOSTRUNS = "/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE_PATH="/./CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenMethodFor_ReturningCorrectRecordsFrom_IPL2019MostRunsCSV(){
        int records= 0;
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            records = cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOSTRUNS);
            Assert.assertEquals(100,records);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ifPassingInCorrectFilePathThrowsException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(WRONG_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING,e.type);
        }
    }

    @Test
    public void givenMethodFor_IfPassingFileContainIncorrectHeaderThrowsException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(WRONG_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING,e.type);
        }
    }
}

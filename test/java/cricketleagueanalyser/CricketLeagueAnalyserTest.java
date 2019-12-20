package cricketleagueanalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {
    private static final String IPL2019_SHEET_MOST_RUNS = "/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE_PATH = ".src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String INCORRECT_HEADER_FILE = "./src/test/resources/IPL2019FactsheetMostRunsInCorrectHeader.csv";

    private static final String IPL2019_SHEET_MOST_WICKETS = "/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenMethodFor_ReturningCorrectRecordsFrom_IPL2019MostRunsCSV(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            int records=cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOST_RUNS);
            Assert.assertEquals(100, records);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ifPassingInCorrectFilePath_ThrowsException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(WRONG_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING,e.type);
        }
    }

    @Test
    public void givenMethodFor_IfPassingFileContainIncorrectHeader_ThrowsException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(INCORRECT_HEADER_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING,e.type);
        }
    }

    @Test
    public void givenMethodFor_IfPassingFileContainInCorrectDelimeter_ThrowsException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(INCORRECT_HEADER_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING,e.type);
        }
    }

    @Test
    public void givenMethodFor_IfPassesEmptyFilePath_ThrowException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV("./home");
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING,e.type);
        }
    }

    @Test
    public void givenMethodFor_ReturningTopBattingStrikeRateFromIPL2019(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOST_RUNS);
            String sortedData=cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.STRIKERATES);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ReturningTopBattingAverageFromIPL2019(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOST_RUNS);
            String sortedData=cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.AVERAGE);
            IPLDAO[] cencusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("MS Dhoni",cencusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ReturningPlayerWho_hitsMax6sand4s(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOST_RUNS);
            String sortedData=cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.SixesAndFours);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Andre Russell",censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ReturningPlayerWhoHadMaximum_StrikeRateWithSixesAndFours(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOST_RUNS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.StrikeRateWithSixesAndFours);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Andre Russell",censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethoFor_ReturningGreatAverage_WithBestStrikeRate(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOST_RUNS);
            String sortedData=cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.StrikeRateWithAvg);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("MS Dhoni",censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ReturningMaximumRuns_WithBestAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOST_RUNS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.MaxRunsWithAvg);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("David Warner ", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ReturningCorrectRecordsFrom_IPL2019MostWicketCSV() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            int records = cricketLeagueAnalyser.loadIplMostWicketCSV(IPL2019_SHEET_MOST_WICKETS);
            Assert.assertEquals(99, records);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }
}

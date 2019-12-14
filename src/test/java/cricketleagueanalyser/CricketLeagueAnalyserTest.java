package cricketleagueanalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CricketLeagueAnalyserTest {
    private static final String IPL2019_SHEET_MOSTRUNS = "/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE_PATH="/./CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String INCORRECT_HEADER_FILE="/./CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostRunsInCorrectHeader.csv";

//    @Test
//    public void givenMethodFor_ReturningCorrectRecordsFrom_IPL2019MostRunsCSV(){
//        int records= 0;
//        try {
//            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
//            records=cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOSTRUNS);
//            Assert.assertEquals(101,records);
//        } catch (CricketLeagueAnalyserException e) {
//            e.printStackTrace();
//        }
//    }

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
            cricketLeagueAnalyser.loadIplMostRunCSV("");
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING,e.type);
        }
    }

    @Test
    public void givenMethodFor_sortPlayersByAverage(){
        try{
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            List<IPLMostRuns2019DAO> list=cricketLeagueAnalyser.loadIplMostRunCSV(IPL2019_SHEET_MOSTRUNS);
            String sortedData =cricketLeagueAnalyser.sortByAvg(list);
            IPLMostRunsCSV[] censusCSV=new Gson().fromJson(sortedData,IPLMostRunsCSV[].class);
            Assert.assertEquals(199812341,censusCSV[0].Average);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }
}

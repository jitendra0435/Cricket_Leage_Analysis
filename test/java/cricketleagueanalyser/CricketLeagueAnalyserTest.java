package cricketleagueanalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CricketLeagueAnalyserTest {
    private static final String IPL2019_SHEET_MOST_RUNS = "/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE_PATH = ".src/test/resources/IPL2019FactSheetMostRuns.csv";
    private static final String INCORRECT_HEADER_FILE = "./src/test/resources/IPL2019FactSheetMostRunsInCorrectHeader.csv";

    private static final String IPL2019_SHEET_MOST_WICKETS = "/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String WRONG_FILE_PATH_WICKET =" /home/admin1/Desktops/rc/test";
    private static final String INCORRECT_HEADER_FILE_FOR_WICKET = "/home/admin1/Desktop/CricketLeagueAnalyser/src/test/resources/INCORRECT_HEADER_FILE_FOR_WICKET.csv";


    @Test
    public void givenMethodFor_ReturningCorrectRecordsFrom_IPL2019MostRunsCSV(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            Map<String, IPLDAO> records=cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL2019_SHEET_MOST_RUNS);
            Assert.assertEquals(100, records.size());
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ifPassingInCorrectFilePath_ThrowsException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,WRONG_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenMethodFor_IfPassingFileContainIncorrectHeader_ThrowsException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,INCORRECT_HEADER_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenMethodFor_IfPassingFileContainInCorrectDeliMeter_ThrowsException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,INCORRECT_HEADER_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenMethodFor_IfPassesEmptyFilePath_ThrowException(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,"./home");
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenMethodFor_ReturningTopBattingStrikeRateFromIPL2019(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL2019_SHEET_MOST_RUNS);
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
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL2019_SHEET_MOST_RUNS);
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
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL2019_SHEET_MOST_RUNS);
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
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL2019_SHEET_MOST_RUNS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.StrikeRateWithSixesAndFours);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Andre Russell",censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ReturningGreatAverage_WithBestStrikeRate(){
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL2019_SHEET_MOST_RUNS);
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
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL2019_SHEET_MOST_RUNS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.MaxRunsWithAvg);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("David Warner ", censusCSV[98].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_ReturningCorrectRecordsFrom_IPL2019MostWicketCSV() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            Map<String, IPLDAO> records = cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,IPL2019_SHEET_MOST_WICKETS);
            Assert.assertEquals(99, records.size());
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_IfPassingInCorrectFilePath_ThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            Map<String, IPLDAO> records = cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,WRONG_FILE_PATH_WICKET);
            Assert.assertEquals(99, records.size());
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenMethodFor_IfPassingFileWithInCorrectHeader_ThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            Map<String, IPLDAO> records = cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,INCORRECT_HEADER_FILE_FOR_WICKET);
            Assert.assertEquals(99, records.size());
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenMethodFor_IfPassingFileWithInCorrectDeliMeter_ThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            Map<String, IPLDAO> records = cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,INCORRECT_HEADER_FILE_FOR_WICKET);
            Assert.assertEquals(99, records.size());
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenMethodFor__TopBowlingAverageFrom_IPL2019mostWicketCSV() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,IPL2019_SHEET_MOST_WICKETS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.AVERAGE);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Yusuf Pathan", censusCSV[98].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor__TopBowlingStrikeRateFrom_IPL2019mostWicketCSV() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,IPL2019_SHEET_MOST_WICKETS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.STRIKERATES);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Mayank Markande", censusCSV[98].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor__TopBowlingEconomyRateFrom_IPL2019mostWicketCSV() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,IPL2019_SHEET_MOST_WICKETS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.ECONOMY);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Shivam Dube", censusCSV[98].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor__TopBowlingStrikingRateWith4And5WicketFrom_IPL2019mostWicketCSV() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,IPL2019_SHEET_MOST_WICKETS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.STRIKERATESWITH4AND5WICKETS);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Ben Cutting", censusCSV[98].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor__TopBowlingAveragesRateWithBestStrikeRatesFrom_IPL2019mostWicketCSV() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,IPL2019_SHEET_MOST_WICKETS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.StrikeRateWithAvg);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Yusuf Pathan", censusCSV[98].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor__TopBowlingAverageWithMostWicketsFrom_IPL2019mostWicketCSV() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,IPL2019_SHEET_MOST_WICKETS);
            String sortedData = cricketLeagueAnalyser.getSortedPlayerNameByField(EnumField.MAXWICKETSWITHAVERAGE);
            IPLDAO[] censusCSV = new Gson().fromJson(sortedData, IPLDAO[].class);
            Assert.assertEquals("Imran Tahir ", censusCSV[98].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethodFor_SortedListOfBoth_ShouldReturnTOPBowlerAndBatsman_ByAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser= new CricketLeagueAnalyser();
            Map<String, IPLDAO> batsmanAvg = cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL2019_SHEET_MOST_RUNS);
            Map<String, IPLDAO> ballAvg = cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,IPL2019_SHEET_MOST_WICKETS);
            List<IPLDAO> ipldaoList = cricketLeagueAnalyser.sortByAvg(batsmanAvg, ballAvg,EnumField.BESTAVGBAT_BALL);
            Assert.assertEquals("Andre Russell", ipldaoList.get(0).player.trim());
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
        public void givenMessage_SortedListOfBoth_ShouldReturnAllRounderWithRunsAndWickets() {
            try {
                CricketLeagueAnalyser cricketLeagueAnalyser= new CricketLeagueAnalyser();
                cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BATSMAN,IPL2019_SHEET_MOST_RUNS);
                cricketLeagueAnalyser.sortPlayers(EnumField.ALLROUNDER);
                cricketLeagueAnalyser.loadIPLCSV(CricketLeagueAnalyser.Player.BOWLER,IPL2019_SHEET_MOST_WICKETS);
                cricketLeagueAnalyser.sortPlayers(EnumField.ALLROUNDER);
                IPLDAO ipldaoList = cricketLeagueAnalyser.allRounder(bat, ball);
                Assert.assertEquals("Andre Russell", ipldaoList.player.trim());
            } catch (CricketLeagueAnalyserException e) {
            }
        }
}

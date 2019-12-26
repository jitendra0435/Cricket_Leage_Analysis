package cricketleagueanalyser;

public class IplAdapterFactory {
    public static IPLAdapter getCensusData(CricketLeagueAnalyser.Player player) throws CricketLeagueAnalyserException {
        if (player.equals(CricketLeagueAnalyser.Player.BATSMAN)) {
            return  new BatsManAdapter();
        } else if (player.equals(CricketLeagueAnalyser.Player.BOWLER)) {
            return  new BowlerAdapter();
        }
        throw new CricketLeagueAnalyserException("wrong choice" ,CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING);
    }
}

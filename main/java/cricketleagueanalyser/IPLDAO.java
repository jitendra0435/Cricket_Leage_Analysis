package cricketleagueanalyser;

public class IPLDAO {
    public int position;
    public String player;
    public int matches;
    public int innings;
    public double notOut;
    public double runs;
    public int highestScore;
    public double average;
    public int ballPhase;
    public double ballStrikeRate;
    public double batStrikeRate;
    public int century;
    public int halfCentury;
    public int sixes;
    public int fours;
    public int bestBowlingIn;
    public int fourWickets;
    public int fiveWickets;
    public double overs;
    public double economy;
    public int wickets;
    public double ballAverage;

    public IPLDAO(String player, double average, double ballAverage) {
        this.player = player;
        this.average = average;
        this.ballAverage = ballAverage;
    }

    public IPLDAO(IPLMostRunsCSV iplMostRunCsv) {
        position = iplMostRunCsv.position;
        player = iplMostRunCsv.player;
        runs = iplMostRunCsv.runs;
        average = iplMostRunCsv.Average;
        batStrikeRate = iplMostRunCsv.StrikeRate;
        century = iplMostRunCsv.century;
        halfCentury = iplMostRunCsv.fifty;
        sixes = iplMostRunCsv.noOfSixs;
        fours = iplMostRunCsv.noOffours;
    }

    public IPLDAO(IPLMostWicketCSV iplMostWicketsCsv) {
        player = iplMostWicketsCsv.player;
        matches = iplMostWicketsCsv.matches;
        innings = iplMostWicketsCsv.innings;
        overs = iplMostWicketsCsv.overs;
        runs = iplMostWicketsCsv.runs;
        wickets = iplMostWicketsCsv.wickets;
        bestBowlingIn = iplMostWicketsCsv.bestBowlingIn;
        average = iplMostWicketsCsv.averageBall;
        economy = iplMostWicketsCsv.economy;
        ballStrikeRate = iplMostWicketsCsv.ballStrikeRate;
        fourWickets = iplMostWicketsCsv.fourWickets;
        fiveWickets = iplMostWicketsCsv.fiveWickets;
        ballAverage=iplMostWicketsCsv.averageBall;
    }

    public IPLDAO(String player,double average, int ballPhase, double ballStrikeRate, double ballAverage) {
        this.player=player;
        this.average = average;
        this.ballPhase = ballPhase;
        this.ballStrikeRate = ballStrikeRate;
        this.ballAverage = ballAverage;
    }
    public IPLDAO() {

    }
}

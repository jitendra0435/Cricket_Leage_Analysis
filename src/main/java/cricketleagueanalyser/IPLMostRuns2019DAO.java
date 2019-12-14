package cricketleagueanalyser;

public class IPLMostRuns2019DAO {
    public int position;
    public String player;
    public int runs;
    public int century;
    public int fifty;
    public int getNoOffours;
    public int getNoOfSixs;

    public IPLMostRuns2019DAO(IPLMostRunsCSV censusCSV) {
        this.position=censusCSV.position;
        this.player=censusCSV.player;
        this.runs=censusCSV.runs;
        this.century=censusCSV.century;
        this.fifty=censusCSV.fifty;
        this.getNoOffours=censusCSV.noOffours;
        this.getNoOfSixs=censusCSV.noOfSixs;

    }
}

package cricketleagueanalyser;

public class IPLMostRuns2019DAO {
    public int position;
    public String player;
    public int runs;
    public int century;
    public int fifty;
    public int getNoOffours;
    public int getNoOfSixs;
    public Double Avg;

    public IPLMostRuns2019DAO(IPLMostRunsCSV iplDataCSV) {
        this.position=iplDataCSV.position;
        this.player=iplDataCSV.player;
        this.runs=iplDataCSV.runs;
        this.century=iplDataCSV.century;
        this.fifty=iplDataCSV.fifty;
        this.getNoOffours=iplDataCSV.noOffours;
        this.getNoOfSixs=iplDataCSV.noOfSixs;
        this.Avg=iplDataCSV.Average;

    }
}

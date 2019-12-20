package cricketleagueanalyser;
import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV {

    @CsvBindByName(column = "POS")
    public int position;
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;
    @CsvBindByName(column = "Runs", required = true)
    public int runs;
    @CsvBindByName(column = "100", required = true)
    public int century;
    @CsvBindByName(column = "50", required = true)
    public int fifty;
    @CsvBindByName(column = "4s", required = true)
    public  int noOffours;
    @CsvBindByName(column = "6s", required = true)
    public int noOfSixs;

    @CsvBindByName(column = "Avg",required =true)
    public Double Average;

    @CsvBindByName(column = "SR",required =  true)
    public double StrikeRate;

    @Override
    public String toString() {
        return "IPLMostRunsCSV{" +
                "position=" + position +
                ", player='" + player + '\'' +
                ", runs=" + runs +
                ", century=" + century +
                ", fifty=" + fifty +
                ", noOffours=" + noOffours +
                ", noOfSixs=" + noOfSixs +
                ", Average=" + Average +
                ", StrikeRate=" + StrikeRate +
                '}';
    }
}

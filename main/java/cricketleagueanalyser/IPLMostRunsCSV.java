package cricketleagueanalyser;
import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV {

    @CsvBindByName(column = "POS")
    public int position;

    @CsvBindByName(column = "PLAYER")
    public String player;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "100")
    public int century;

    @CsvBindByName(column = "50")
    public int fifty;

    @CsvBindByName(column = "4s")
    public  int noOffours;

    @CsvBindByName(column = "6s")
    public int noOfSixs;

    @CsvBindByName(column = "Avg")
    public Double Average;

    @CsvBindByName(column = "SR")
    public double StrikeRate;

}

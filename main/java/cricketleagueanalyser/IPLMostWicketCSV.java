package cricketleagueanalyser;

import com.opencsv.bean.CsvBindByName;
public class IPLMostWicketCSV {

    @CsvBindByName(column = "PLAYER")
    public String player;

    @CsvBindByName(column = "Mat")
    public int matches;

    @CsvBindByName(column = "Inns")
    public int innings;

    @CsvBindByName(column = "Ov")
    public double overs;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "Wkts")
    public int wickets;

    @CsvBindByName(column = "BBI")
    public int bestBowlingIn;

    @CsvBindByName(column = "Avg")
    public double average;

    @CsvBindByName(column = "Econ")
    public double economy;

    @CsvBindByName(column = "SR")
    public double ballStrikeRate;

    @CsvBindByName(column = "4w")
    public int fourWickets;

    @CsvBindByName(column = "5w")
    public int fiveWickets;
}

package cricketleagueanalyser;
import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class CricketLeagueAnalyser {
    List<IPLMostRuns2019DAO> playersList=null;
    public CricketLeagueAnalyser() {
        this.playersList = new ArrayList<IPLMostRuns2019DAO>();
    }
    public List<IPLMostRuns2019DAO> loadIplMostRunCSV(String iplMostRunsCSVFilepath) throws CricketLeagueAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplMostRunsCSVFilepath))) {
            ICSVBuilder csvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLMostRunsCSV> iplFileIterator= csvBuilder.getCSVFileIterartor(reader,IPLMostRunsCSV.class);
            while(iplFileIterator.hasNext()){
                this.playersList.add(new IPLMostRuns2019DAO(iplFileIterator.next()));
            }
            return playersList;
        } catch (RuntimeException | IOException | CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(), CricketLeagueAnalyserException.ExceptionType.ERROR_WHILE_LOADING);
        }
    }

    public String sortByAvg(List<IPLMostRuns2019DAO> list) {
        Comparator<IPLMostRuns2019DAO>censusComparator=Comparator.comparing(census ->census.Avg);
        this.sort(censusComparator);
        String sortedStateCensusData=new Gson().toJson(playersList);
        return  sortedStateCensusData;
    }
    public void sort(Comparator<IPLMostRuns2019DAO>iplComparator){
        for(int i=0;i<playersList.size()-1;i++){
            for(int j=0;j<playersList.size()-i-1;j++){
                IPLMostRuns2019DAO census1=playersList.get(j);
                IPLMostRuns2019DAO census2=playersList.get(j+1);
                if(iplComparator.compare(census1,census2)>0){
                    playersList.set(j,census2);
                    playersList.set(j+1,census1);
                }
            }
        }
    }
}

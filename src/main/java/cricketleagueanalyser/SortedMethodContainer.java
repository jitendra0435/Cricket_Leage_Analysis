package cricketleagueanalyser;
import java.util.Comparator;

public class SortedMethodContainer implements Comparator<IPLMostRuns2019DAO> {
    @Override
    public int compare(IPLMostRuns2019DAO t1, IPLMostRuns2019DAO t2) {
        return ((t1.getNoOfSixs*6)+(t1.getNoOffours*4))-((t2.getNoOfSixs*6)+(t2.getNoOffours*4));
    }
}



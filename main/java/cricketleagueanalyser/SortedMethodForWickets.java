package cricketleagueanalyser;
import java.util.Comparator;

public class SortedMethodForWickets implements Comparator<IPLDAO> {
    @Override
    public int compare(IPLDAO t1, IPLDAO t2) {
        return ((t1.fiveWickets*5)+(t1.fourWickets*4))-((t2.fiveWickets*5)+(t2.fourWickets*4));
    }

}


package cricketleagueanalyser;
import java.util.Comparator;

public class SortedMethodContainer implements Comparator<IPLDAO> {
    @Override
    public int compare(IPLDAO t1, IPLDAO t2) {
        return ((t1.sixes*6)+(t1.fours*4))-((t2.sixes*6)+(t2.fours*4));
    }
}



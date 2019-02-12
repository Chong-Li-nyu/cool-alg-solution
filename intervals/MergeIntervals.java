package intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        List<Interval> ret = new ArrayList<>();
        for(Interval interval: intervals){
            if (ret.isEmpty() || ret.get(ret.size() - 1).end < interval.start){
                ret.add(interval); // empty or strictly smaller than the next start
            } else {
                Interval back = ret.get(ret.size() - 1);
                back.end = Math.max(interval.end, back.end);
            }
        }
        return ret;
    }
}

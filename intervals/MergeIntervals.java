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

/*
56. Merge Intervals
Medium

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
*/

class Solution {
    public int[][] mergeIntervals(int[][] arr) {
        // non-overlapping is e1 < s2
        // overlapping is e1 >= s2 and s1 <= e2
        // sort by start from small to big. so s2 >= start => e2 >= start.
        if(arr.length == 0) return arr;
        Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0]-o2[0];
				}
		});
        int start = arr[0][0];
        int end = arr[0][1];
        int index = 0;
        int[][] ret = new int[arr.length][2];
        for (int i = 1; i < arr.length; i ++){
            if (end < arr[i][0]) {
                ret[index][0] = start;
                ret[index++][1] = end;
                start = arr[i][0];
                end = arr[i][1];
            } else {
                end = Math.max(end, arr[i][1]);
            }
        } // 最后需要再次记录
        ret[index][0] = start;
        ret[index++][1] = end;
        return Arrays.copyOfRange(ret, 0, index);
    }
   
}

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

/*
https://leetcode.com/problems/insert-interval/
57. Insert Interval
Hard

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 找到合适的位置开始更新，最后记得放回这个区间
        int n = intervals.length ;
        int[][] ret = new int[ n + 1][2];
        int start = newInterval[0];
        int end = newInterval[1];
        int index = 0;
        
        for(int i = 0; i < n; i++){
            int curstart = intervals[i][0];
            int curend = intervals[i][1];
            if(start > curend){
                ret[index++] = intervals[i];
            } else {
                if(end < curstart) {
                    ret[index++] = new int[]{start, end};
                    // copy remaining intervals
                    for(int j = i; j < n; j++){
                        ret[index++] = intervals[j];
                    }
                    return Arrays.copyOfRange(ret, 0, index);
                } else { // start <= curend && end >= curstart
                    start = Math.min(start, curstart);
                    end = Math.max(end, curend);
                }
            }
        }
        ret[index++] = new int[]{start, end};
        return Arrays.copyOfRange(ret, 0, index);
    }
}

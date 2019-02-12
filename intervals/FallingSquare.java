package intervals;
import java.util.*;

/**
 * 1.保证map的intervals总是不相交的，如果相交，那么移除并把不相交的interval 再放回
 * 2.如何从相交的第一个interval开始iteration（设为x）呢？可以选择floorKey（floorKey前面的肯定不与x相交）,如果不存在floorKey
 *   则选择map的第一个key
 *   同时floorKey不一定与x相交，所以反过来想，排除不相交的情况：当t.e<=start continue; t.s>=end break;
 */
public class FallingSquare {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        int currMax = 0;
        TreeMap<Interval, Integer> map = new TreeMap<>();
        
        for(int[] p: positions){
            int start = p[0];
            int end = p[0] + p[1];
            int height = p[1];
            int base = 0;
            Interval t = map.floorKey(new Interval(start, end) );
            //intervals in front of t not intersected with (start, end) if t is not intersected with it.
            // because we make sure all intervals in map not intersected
            if(t==null && !map.isEmpty()) t = map.firstKey(); //first key might intersected with (start, end)
            
            //make sure selected from map
            for(; t != null; t = map.higherKey(t)){
                int s = t.start;
                int e = t.end;
                int h = map.get(t);
                if( s >= end ) break;  //end condition
                if( e <= start ) { // not intersected continue
                    continue;
                }
                //make sure t intersected with (start, end)
                map.remove(t);
                // if t has remaining part outside interval (start, end)
                if( s < start){
                    map.put(new Interval(s, start), h);
                }
                if( e > end){
                    map.put(new Interval(end, e), h);
                }
                base = Math.max(base, h);
            }
            height += base;
            map.put(new Interval(start, end), height);
            currMax = Math.max(currMax, height);
            ans.add(currMax);
        }
        return ans;
    }
    
    
}
//class Interval implements Comparable<Interval>{
//    int start;
//    int end;
//
//    public Interval(int s, int e){
//        start = s;
//        end = e;
//
//    }
//    @Override
//    public int compareTo(Interval i2){
//        return Integer.compare(this.start, i2.start);
//    }
//}

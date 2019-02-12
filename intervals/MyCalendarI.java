package intervals;
import java.util.*;
public class MyCalendarI {
    TreeMap<Integer, Integer> events = new TreeMap<Integer, Integer>();;
    public MyCalendarI() {
    }
    
    public boolean book(int start, int end) {
        Integer kf = events.floorKey(start);
        if(kf!= null && events.get(kf) > start ) return false;
        Integer kc = events.ceilingKey(start);
        if(kc != null && kc < end) return false;
        events.put(start, end);
        return true;
    }
}

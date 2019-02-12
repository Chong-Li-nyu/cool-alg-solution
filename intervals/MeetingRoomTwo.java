package intervals;
import java.util.*;

public class MeetingRoomTwo {
  public static void main(String[] args) {
    Interval[] intervals = new Interval[] {
        new Interval(1,5), new Interval(6,8), new Interval(3,7), new Interval(2,4), new Interval(6,9)
    };
    MeetingRoomTwo ins = new MeetingRoomTwo();
    int minRoom = ins.minMeetingRooms(intervals);
    System.out.println(minRoom);
    Map<Interval, List<Interval>> ret = ins.conflictMeeting(intervals);
    String format="%s: %s";
    for(Interval time: ret.keySet()) {
      List<Interval> l = ret.get(time);
      String[] vals = l.stream().map(Interval::toString).toArray(String[]::new);
      String val = String.join(", ", vals);
      String line = String.format(format,time,val );
      System.out.println(line);
    }
    
  }
  
  public int minMeetingRooms(Interval[] intervals) { 
    Arrays.sort(intervals,new Comparator<Interval>(){
        @Override
        public int compare(Interval i1, Interval i2){
            return Integer.compare(i1.start, i2.start); //increasing order
        }
    });
     PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>(){
         @Override
         public int compare(Interval i1, Interval i2){
             return Integer.compare(i1.end, i2.end);
         }
     });
    for(int i = 0; i<intervals.length ;i++){
        if(!pq.isEmpty() && pq.peek().end <= intervals[i].start)
            pq.poll();
        //has to put the interval in the priority queue because poll is optional when one meeting ends
        pq.offer(intervals[i]); //可能会推迟postpone最早的end时间，也有可能提前advance the earlist ending time
        
    }
    //有进有出，但是一定进，出的条件是earlist end time smaller than iterative interval's starting time
    //在最小的end结束之前，开始的meeting都需要不弹出的增长pq，求出峰值
    //之后有接着开始的，也只能poll offer配对操作，不改变q的size
    
     return pq.size();
 }
/**
 * 
 * @param intervals
 * @return 所有冲突的会议
 */
  public Map<Interval, List<Interval>> conflictMeeting(Interval[] intervals) {
    Map<Interval, List<Interval>> res = new LinkedHashMap<>();
    Arrays.sort(intervals,new Comparator<Interval>(){
        @Override
        public int compare(Interval i1, Interval i2){
            return Integer.compare(i1.start, i2.start); //increasing order
        }
    });
     PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>(){
         @Override
         public int compare(Interval i1, Interval i2){
             return Integer.compare(i1.end, i2.end);
         }
     });
    for(int i = 0; i<intervals.length ;i++){
      //pq 维护所有相交的intervals
        while(!pq.isEmpty() && pq.peek().end <= intervals[i].start) {
          pq.poll();
        }
        List<Interval> ivalue = new ArrayList<>(pq);

        for (Interval it : pq) {
//          if(!res.containsKey(it)) res.put(it, new ArrayList<Interval>());
          res.get(it).add(intervals[i]);
        }
        res.put(intervals[i], ivalue);
        pq.offer(intervals[i]);
        //此时pq中所有的start 小于 ith interval's start, aka max(start), (the intervals whose end <= start_i all polled)
        //也就是说pq所有的interval都和ith interval相交 ,满足 max(start) < min(end)
    }
    return res;
 }
  
  
  public Map<Interval, List<Interval>> conflictMap(Interval[] events){
    Map<Interval, List<Interval>> ret = new HashMap<>();
    PriorityQueue<Interval> pq=new PriorityQueue<>(new Comparator<Interval>() {
      @Override 
      public int compare(Interval i1, Interval i2) {
        return Integer.compare(i1.end, i2.end);
      }
    });
    Arrays.sort(events, new Comparator<Interval>() {
      @Override
      public int compare(Interval i1, Interval i2) {
        return Integer.compare(i1.start, i2.start);
      }
    });
    for(Interval it: events) {
      while(!pq.isEmpty() && pq.peek().end <= it.start) {
        pq.poll();
      }
      List<Interval> itValue = new ArrayList<>(pq);
      ret.put(it, itValue);
      for(Interval key: pq) {
        ret.get(key).add(it);
      }
      pq.offer(it);
    }
    
    return ret;
  }
  
}
class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
     public String toString() {
       return start+":"+end ;
     }
}

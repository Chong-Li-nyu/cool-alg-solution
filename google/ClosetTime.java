package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ClosetTime {
  //given 19:34, return 19:39
  //find which bit to change first
  public final int LARGEST_GAP = 24*60;
  int best = LARGEST_GAP;
  String closetTime = "";
  public static void main(String[] args) {
    ClosetTime ins = new ClosetTime();
    String next = ins.nextCloset2("13:59");
    
    System.out.println(next);
  }
  public String nextCloset(String time) {

    String[] sarr = time.split(":");

    int h = Integer.valueOf(sarr[0]) ;
    int m = Integer.valueOf(sarr[1]) ;
    while (true){
      m++;
      if (m == 60) {
        m = 0;
        h++;
        h %= 24;
      }
      // tell if contained in the string
      
      String newhm = String.format("%02d:%02d", h, m);
      boolean valid = true;
      for(int i = 0; i<5; i++) {
        if( time.indexOf( newhm.charAt(i) ) < 0 ) {
          valid = false;
          break; 
        }
      }
      if(valid) return newhm;
    }
  }
  public String nextCloset2(String time) {
    closetTime = time;
    String[] sarr = time.split(":");
    String hstr = sarr[0];
    String mstr = sarr[1];
    int h = Integer.valueOf(hstr);
    int m = Integer.valueOf(mstr);
    List<Integer> timearr = new ArrayList<Integer>();
    String hm = hstr+mstr;
    for(int i = 0;i<hm.length();i++) {
      timearr.add( (int)(hm.charAt(i) - '0') );
    }
    
    List<Integer> curr = new ArrayList<Integer>();
    dfs(0, timearr, toTime(h, m), curr);
    return closetTime;
  }
  public void dfs(int dep, List<Integer> timearr, int timemin, List<Integer> curr) {
    if(dep == 4) {
      int h = curr.get(0)*10+curr.get(1);
      int m = curr.get(2)*10+curr.get(3);
      if(h>23 || m>59) return ;
      int currmin = toTime(h, m);
      int gap = currmin- timemin;
      if(gap<=0) gap+= LARGEST_GAP;
      if(gap < best) {
        best = gap;
        closetTime = String.format("%02d:%2d", h, m);
      }
      return;
    }
    for(int i = 0; i<4; i++) {
      List<Integer> newcurr = new ArrayList<>(curr);
      newcurr.add( timearr.get(i) );
      dfs( dep + 1, timearr, timemin, newcurr);
    }
  }
  public int toTime(int h , int m) {
    return h*60 + m;
  }
  
  
  
  
  
}

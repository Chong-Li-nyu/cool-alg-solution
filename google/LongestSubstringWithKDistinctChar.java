package google;
import java.util.*;
public class LongestSubstringWithKDistinctChar {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if( k == 0 || s.equals("")) return 0;
    TreeMap<Integer, Character> last = new TreeMap<>();
    Map<Character, Integer> map = new HashMap<>(); 
    int l = 0;
    int max = Integer.MIN_VALUE;
    int n= s.length();
    for( int r = 0; r<n; r++){
        char cr = s.charAt(r);
        //size over k, remove one char(which is the smallest latest occurrence)
        while( !map.containsKey(cr) && map.size() == k){
            int smallest  = last.firstKey();
            char smallestChar = last.get(smallest);
            last.remove(smallest); //remove this occurence
            map.remove( smallestChar);
            l = smallest+1;
        }
        //常规更新
        if( map.containsKey(cr)){
            //update last occurrence
            last.remove(map.get(cr));
        }
        last.put(r, cr);
        map.put(cr, r);
        
        max = Math.max(max, r-l+1);
    }
    return max;
}
}

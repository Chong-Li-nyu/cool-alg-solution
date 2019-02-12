package google;
import java.util.*;
public class LongestSubstringWithoutRepeat {
  public static void main(String[] args) {
    LongestSubstringWithoutRepeat ins = new LongestSubstringWithoutRepeat();
    int res  = ins.lengthOfLongestSubstring("abcabcbb");
    System.out.println(res);
  }
  public int lengthOfLongestSubstring(String s) {
    if(s==null || s.length() == 0) return 0;
    int l = 0; 
    int r = 0;
    int n = s.length();
    int ret = 0;
    Map<Character, Integer> map = new HashMap<>();
    for( ; r< n; r++){
        //check each valid l and r, calculate new ret
        char rc = s.charAt(r);
        int find = 0;
        if((find=map.getOrDefault(rc, -1)) != -1){
            //only update l to bigger value, or we ignore the it, because only consider range(l~r)
            l = Math.max(l,find + 1);
        }
        ret = Math.max(ret, r-l +1);
        map.put(rc, r); //update in all case
    }
    return ret;
  }
  /*  public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length()== 0) return 0;
        //sliding window
        int l = 0;
        int r = 0;
        int n = s.length();
        //The problem is how to delete the repeating char when come across some repetitive char
        // find it and update l to find+1
        int ret = 0;
        Set<Character> set = new HashSet<>();

        for(; r<n; r++){
            char rc = s.charAt(r);
                //update l to find+1
            if(set.contains(rc)){
                ret = Math.max(ret, r-l); // exclusive r
                // important the +l, because create  a new substring
                int find = (s.substring(l,r)).indexOf(rc) + l; //find should have been the index of s
                //***but substring create another string and return a new index, so plus the cutted part length to get the index in s
                //update set 
                for(;l<=find; l++ ){ //don't delete char at find, because it is repetive
                    set.remove(s.charAt(l));
                }
            }
            set.add(rc);
        }

        ret = Math.max(ret, r-l); // at least calculate the substring length once.
        return ret;
    }*/
}

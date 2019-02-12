package google;
import java.util.*;
public class LongestSubstringAtMostKDistinctChar {
  public static void main(String[] args) {
    String s = "abcabcca";
    LongestSubstringAtMostKDistinctChar ins = new LongestSubstringAtMostKDistinctChar();
    System.out.println(ins.findLongestSubstringKDistinctChar(s, 2));
  }
  public String findLongestSubstringKDistinctChar(String s, int k) {
    if(s==null || s.length() == 0 ) return "";
    int n = s.length();
    // maintain a map record the last occurence of one char
    // when place the pointer to one bit behind the last char
    // we delete a char from current substring
    
    int l = 0;
    int r = 0;
    Map<Character, Integer> map = new HashMap<>();
    int maxLen = Integer.MIN_VALUE; 
    for(; r<n; r++) {
      char c = s.charAt(r);
      map.put(c, r); //update or add a new key/value pair : HASTODO. It will not influence previous char's last occurence when adding
      //a new char to the map. This adding changes the map's size from k to k+1
      if( map.size() == k+1) {
        while(l <= r) {
          //BUG: change l befor removing the last occurrence, so remove a new unchecked char
          char cl =s.charAt(l); //save the state and for future removing
          if( map.get(cl) == l++) { 
            map.remove(cl); 
            break;
          }
        }
      }
      
      //HAS-TO-DO
      maxLen= Math.max(maxLen, r-l+1);
    }
    return s.substring(l, l+maxLen);
  }
}

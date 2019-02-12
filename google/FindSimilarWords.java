package google;
import java.util.*;
public class FindSimilarWords {
  /*
   * Given a bunch of words, 'abc' 'xyz' has the same pattern
   * return the words having same pattern
   */
  public static void main(String[] args) {
    FindSimilarWords ins = new FindSimilarWords();
    List<String> words = new ArrayList<>();
    words.add("abc");
    words.add("bb");
    words.add("xyz");
    words.add("e");
    List<List<String>> res = ins.findSimilarWords(words);
    for(int i = 0 ;i < res.size(); i++)
      System.out.println(res.get(i).toString());
  }
  List<List<String>> findSimilarWords(List<String> words){
    int n = words.size();
    String[] i2s = new String[n];
    for(int i = 0; i<words.size(); i++) {
      i2s[i] = words.get(i);
    }
    int[] root = new int[n];
    Arrays.fill(root, -1);
    ArrayList<String>[] group = new ArrayList[n];
    
    
    for( int i = 0; i<n; i++) {
      int ri = find(i, root, i2s, group); //ri = i init
      if( ri < i) continue; 
      for(int j = i+1; j<n; j++) {
        if( hasSimilarPattern(i2s[ri], i2s[j])) {
          root[j] = ri; //assign j's root to ri
          group[ri].add(i2s[j]);
        }
      }
    }
    List<List<String>> res = new ArrayList<>();
    for(int i =0; i<n; i++) {
      if(group[i] == null) continue;
      res.add(group[i]);
    }
    return res;
  }
  private boolean hasSimilarPattern(String s1, String s2) {
    if(s1.length() != s2.length()) return false;
    if(s1.equals(s2)) return false;
    int diff = 0;
    for(int i = 0; i<s1.length(); i++) {
      if(diff == 0) {
        diff = s1.charAt(i) - s2.charAt(i);
        continue;
      }
      if(diff!= s1.charAt(i) - s2.charAt(i)) return false;
    }
    return true;
  }
  public int find(int p, int[] root, String[] i2s, ArrayList<String>[] group) {
    if(root[p] == -1) {
      root[p] = p;
      group[p] = new ArrayList<String>();
      group[p].add(i2s[p]); //add itself to the group
      return p;
    }
    while(p != root[p]) {
      p = root[p];
      root[p] = root[root[p]];
    }
    return p;
  }
}

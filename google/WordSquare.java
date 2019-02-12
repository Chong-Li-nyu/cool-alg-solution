package google;
import java.util.*;
public class WordSquare {
  List<List<String>> result = new ArrayList<>();
  public static void main(String[] args) {
    WordSquare ins = new WordSquare();
    String[] words = new String[] {
        "area","lead","wall","lady","ball"
    };
    System.out.println(ins.wordSquares(words));
  }
  public List<List<String>> wordSquares(String[] words) {
      // ["abat","baba","atan","atal"]
      
      int n = words.length;
      if( !allSameLength(words) ) return result;
      List<String> board = new ArrayList<>();
      List<Integer> used= new ArrayList<>();    
      dfs(board, words, used, 0, n);
      return result;
  }
  public void dfs(List<String> board, String[] words, List<Integer> used, int index, int n){
      if(index == n ) {
          result.add(new ArrayList<String>(board));
          return;
      }
      String prefix = getPrefix(board, index);
      for(int i = 0; i<n; i++){
          if(used.contains(i)) continue;
          if(!words[i].startsWith(prefix)) continue;
          used.add(i);
          board.add(words[i]);
          dfs(board, words, used, index+1, n);
          board.remove(words[i]);
          used.remove(i);
      }
  }
  public String getPrefix(List<String> board, int index){
      if(index == 0) return "";
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<index; i++){
          sb.append(board.get(i).charAt(index));
      }
      return sb.toString();
  }
  public boolean allSameLength(String[] words){
      int len = words.length;
      for(int i =0;i<words.length; i++){
          if( words[i].length() != len) return false;
      }
      return true;
  }
}

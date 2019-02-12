package DFS;
import java.util.*;
public class GenerateParenthethis {
public static void main(String[] args) {
  GenerateParenthethis ins = new GenerateParenthethis();
  System.out.println(ins.generateParen(3));
}
  public List<String> generateParen(int n){
    //2 : (()) ()()
    int count = 0; //cannot be smaller than 0 
    int left = n;
    int right = n;
    List<String> res = new ArrayList<>();
    dfs(left, right, count, "", res);
    return res;
  }
  public void dfs (int left, int right, int count, String p, List<String> res ) {
    if(count < 0) return;
    if( left < 0 || right < 0) return;
    if(left == 0 && right == 0) {
      res.add(p);
      return;
    }
    dfs(left-1, right, count +1, p+"(", res);
    dfs(left, right-1, count - 1, p+")", res);
  }
}

package RandomProblem;
import java.util.*;
public class CandyCrash {
  public int[][] generateBoard(int n , int[] candy){
    int[][] board = new int[n][n];
    for(int r = 0; r<n; r++) {
      for(int c = 0; c<n; c++) {
        int u = -1, l = -1;
        if(r >= 2)
          u = checkContinuousUp(board, r, c);
        if(c >= 2)
          l = checkContinuousLeft(board, r, c);
        Set<Integer> ex = new HashSet<>();
        if(u!= -1) ex.add(u);
        if(l!= -1) ex.add(l);
        board[r][c] = generate(candy, ex);
      }
    }
    return board;
  }
  public int checkContinuousUp(int[][] board, int r, int c) {
    if( board[r-1][c] == board[r-2][c] ) return board[r-1][c];
    return -1;
  }
  public int checkContinuousLeft(int[][] board, int r, int c) {
    if( board[r][c-1] == board[r][c-2] ) return board[r][c-1];
    return -1;
  }
  public static void main(String[] args) {
    int[] candy = new int[] {0,1,2,3,4};
    CandyCrash ins = new CandyCrash();
    int n = 8;
    int[][] board = ins.generateBoard(n, candy);
    for(int i = 0; i< n; i++) {
      System.out.println(Arrays.toString(board[i]));
    }
  }
  public static int generate(int[] candy, Set<Integer> ex) {
    //select one random valid number from candy(not in ex){
    int count = 0;
    int ret = -1;
    for(int i = 0; i<candy.length; i++) {
      if(ex.contains(i)) continue;
      count ++;
      double pick = Math.random() * count;
      if( pick  < 1 ) ret = i;
    }
    return ret;
  }
}

package google;

public class UniquePathSlant {
 //right, upright, downright
  //get unique path from left-up to right-up
  /*
  public int uniquePathNumber(int m , int n) {
    
    // the final answer is dp[m-1][0]
    // so the subproblem 
    int[][] opts= new int[][] {
      {0,1} , {1,1}, {-1,1}
    };
    int[][] dp = new int[m][n];
    
    for( int i = 0; i<m; i++) {
      for(int j = 0; j<n; j++) {
        for( int[] opt: opts) {
          if(i-opt[0]>=0 && j-opt[1]>= 0)
            dp[i][j] += dp[i-opt[0]][j-opt[1]];
        }
      }
    }
    dp[i][j] = dp[i][j-1] + dp[i+1][j-1] + dp[i-1][j-1]  
  }
  */
  //start from r,c the path number to [0][n-1]
  
  public static void main(String[] args) {
    UniquePathSlant ins = new UniquePathSlant();
    int[][] opts = new int[][] {
      {0,1},{1,1},{-1,1}
    };
    System.out.println(ins.uniquePath(0, 0, opts, 3, 4));
  }
  public int uniquePath(int r, int c, int[][] opts, int m, int n) {
    if(r == 0 && c == n-1) return 1;
    if( r<0 || c<0 || r>= m || c>=n) return 0;
    int sum = 0;
    for( int[] opt: opts) {
      sum += uniquePath(r+opt[0], c+opt[1], opts, m, n);
    }
    return sum;
  }
  
}

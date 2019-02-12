package google;

public class PickProbability {
  
  //有A，B两种汤，各5000，每次可以从下面4中里面取
//  (100，0) , (75,25), (50,50), (25,75)
//  length not exceed to 5000/25 = 200
  public static void main(String[] args) {
    PickProbability ins = new PickProbability();
    int[][] opts = new int[][] {
      new int[] {100,0}, new int[] {75,25}, new int[] {50,50}, new int[] {25,75}
    };
    

//    int A = 5000, B = 5000;
//    int A = 75, B = 125;
    /**
     * 1. {75,25} 1/4
     * 
     */
    
    
    int A = 200, B= 80;
    /**
     * 1. 100,0  -> 50, 50  or reverse order 
     * 2. 75,25 -> 75,25
     * 3/16 = 0.1875 correct
     */
    double[][] dp =new double[A+1][B+1];
    double res = ins.dfs(A, B, opts, dp);
    System.out.println(res);
    double res_dp = ins.pickUp(A, B, opts);
    System.out.println(res_dp);
    
  }
  public double dfs(int A, int B, int[][] opts, double[][] dp) { //A and B start from 5000, decrease 
    if( A == 0 && B > 0) return 1D;
    if( A < 0 || B < 0 ) return 0D;
    if(dp[A][B] != 0D) return dp[A][B];
    for(int[] pair : opts) {
      int a = pair[0], b = pair[1];
      dp[A][B] += dfs(A-pair[0], B-pair[1], opts, dp);
    }
    dp[A][B] /= 4;
    return dp[A][B];
  }
  
  //Wrong method
  //错的点在于，层与层之间会乱，在dp中有两层的结果，有三层的结果，但是他们不能加一样的权(1/4)
  //改正方法是：分开每一层的可能，使用三维数组
  public double pickUp(int A, int B, int[][] opts) {
    double[][][] dp = new double[10][A+1][B+1];
    dp[0][0][0] = 1D;
    double ret = 0D;
    // 把dp的ij放在外部loop，这样{opt1, opt2} {opt2,opt1}都能覆盖到
    for(int k = 1; k<10; k++) {
    for(int i = 0;i<= A; i+=25) {
      if(i%25 != 0) continue;
      for(int j = 0; j<B; j++) {  //not interested with B=5000
        if(j%25 != 0 ) continue;
        for(int[] pair: opts) {
          int pa = pair[0], pb = pair[1];
          if(i - pa >=0 && j-pb >= 0) {
            dp[k][i][j] += dp[k-1][i-pa][j-pb];            
          }
        }
        dp[k][i][j] /=4;
        if (i == A && j != B)
          ret += dp[k][i][j];
      }
    }  
    }
    return ret;
  }
  
} 
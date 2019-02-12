package DP;

import java.util.Arrays;

public class Knapsack {
  public static void main(String[] args) {
    int[] w = new int[] {2, 1, 5};
    int[] v = new int[] {2, 2, 4};
    int W = 7;
    Knapsack ins = new Knapsack();
    int[][] res= ins.knapsack(w, v, W);
    for(int i = 0; i<=w.length ;i++)
      System.out.println(Arrays.toString(res[i]));
//    System.out.println(res);
    
  }
  public int[][] knapsack(int[] w, int[] v, int W) {
    int n = w.length;
    int[][] dp = new int[n+1][W+1]; //dp[i][j] 表示前i个(w[0] ~ w[i-1])石头在总重量小于j 下的最大value
    //initialized with 0
    for(int i = 1; i<= n; i++) {
      for(int j = 1; j<= W; j++) {
        int weight = w[i-1];
        int value = v[i-1];
        int pickOrNot  = weight <= j? dp[i-1][j- weight] + value : -1; //for stone index i-1
        dp[i][j] = Math.max(dp[i-1][j], pickOrNot);
      }
    }
//    return dp[n][W];
    return dp;
  }
  /*
   *      int[] w = new int[] {2, 1, 5};
          int[] v = new int[] {2, 2, 4};
          int W = 6; 
          ans : 6,but 输出4
          问题在无法先更新dp[2] = 1, 导致dp[6] 无法更新
          带有W的限制，不能像普通子集求和一样倒过来做; 正着做又会出现用两次的情况
   */
  public int knapsackSimulatingSubsetSum(int[] w, int[] v, int W) {
    //total weight under W, maximum value required
    int sum = 0;
    for(int value: v) { 
      sum += value;
    }
    int[] dp = new int[sum+1];
    dp[0] = 0;
    //dp[i] is when value is i, the minimum weight 
    for(int i = 1; i<=sum; i++) dp[i] = Integer.MAX_VALUE;
    for(int i = 0; i<w.length; i++) {
      for(int k = sum; k>=0; k--) {
        if(dp[k] != Integer.MAX_VALUE) { //dp[k] already calculated
          if(dp[k] + w[i] < W) {
            int newval = k+v[i];
            dp[newval] = Math.min(dp[newval], dp[k] + w[i]);
          }
        }
      }
    }
    for(int i = sum; i>=0 ; i--) {
      if(dp[i] != Integer.MAX_VALUE) return i;
    }
    return 0;
//    return dp;
  }
}

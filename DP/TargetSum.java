package DP;
import java.util.Arrays;
public class TargetSum {
  public static void main(String[] args) {
    TargetSum ins = new TargetSum();
    int res = ins.findTargetSumWays(new int[] {1, 1,1,1,1}, 3);
    System.out.println(res);
  }
    public int findTargetSumWays(int[] nums, int S) {
        //更改一个正负号，会变化偶数个单位，不管加还是减，所以S只能是偶数才能(sum+S) % 2 == 0 才能变出来
        
        //dp use the sum as another index, 
        // the total sum of nums is N, possible biggest using all +
        
        //since sum can be [-N, N] , offset = N, shift to [0,2N]
        int n = nums.length ;
        int N = 0;
         for( int num: nums){
             N += num;
         }
        if( N < S|| (N+S) % 2 == 1) return 0; //S is too large
        int[][] dp = new int[n+1][2*N + 1];
        dp[0][0 + N] =  1; //use 0 element(s), sum to 0(+N) path number is 1
        for(int i = 1; i<= n; i++){
            for(int j = 0 ; j <= 2*N; j++){ //smallest is 0, largest is 2*N
                if( j-nums[i-1]>= 0) dp[i][j] += dp[i-1][j-nums[i-1]];  //since the i-1 index, we will not use nums[i] more than once
                if( j+nums[i-1] <= 2*N) dp[i][j] += dp[i-1][j+nums[i-1]];
            }   
        }
        return dp[n][S+N]; //plus the offset
    }

}

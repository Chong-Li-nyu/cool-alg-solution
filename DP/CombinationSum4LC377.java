package DP;

public class CombinationSum4LC377 {
  public int combinationSum4(int[] nums, int target) {
    // every number can used multiple times,
    // 1,2 and 2,1 are different, output the possible combination
    int[] dp = new int[target + 1];
    dp[0] = 1; // base case, dp[1] += dp[0] if there exist 1 in the given array
    //因为可以用任意多次，所以每一个数字应该被用很多次，那么所有的层都混在一起，dp数组可以从number开始更新到target
    //这个写法是不考虑sequence 的顺序，因为是每个数字只考虑一次，后面的数字一定在前面的数字之后
    // for(int num: nums){
    //     for(int i = num; i <= target; i++){
    //         dp[i] += dp[i - num];
    //     }
    // }
    
    //使用dp模拟记忆递归，先选一个数，然后只改变target as target - num，当做一个新的问题考虑，并记录结果待用
    
    for(int sum = 1; sum <= target; sum++){
      for(int num: nums){
        if(sum - num >= 0)
          dp[sum] += dp[sum - num]; //how to know dp[target - num], DO IT!!
      }
    }
    
    // for(int num: nums){
    //     if(target - num >= 0)
    //         dp[target] += dp[target - num]; //how to know dp[target - num], DO IT!!
    // }
    return dp[target];
  }
}

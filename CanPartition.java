/*
416. Partition Equal Subset Sum
Medium

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
*/
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num: nums) {
            sum += num;
        }
        if(sum %2 == 1) return false;
        int val = sum/2;
        boolean[] dp = new boolean[val + 1];
        dp[0] = true;
        Arrays.sort(nums);
        for(int num : nums){
            //只使用一次，反着更新
            for(int i = val; i > 0; i--){
                if(i-num >= 0 && dp[i-num]) dp[i] = true;
            }
        }
        return dp[val];
    }
}

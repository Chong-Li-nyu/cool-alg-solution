/*
You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.


Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

*/
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // 必去一个钱币开始dp
        //coins放在外loop
        for(int i = 0; i<coins.length; i++){ //只有一次机会access 它，得到的结果必定是从小到大排列
            //内循环计算取1，2，3，。。次coin[i]
            for(int c = coins[i] ; c <= amount; c ++){
                dp[c] += dp[c-coins[i]];
            }
        }
        return dp[amount];
    }
}

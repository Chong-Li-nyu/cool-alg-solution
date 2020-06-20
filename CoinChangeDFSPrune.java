class Solution {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] ans = new int[1];
        ans[0] = amount + 1;
        dfs(coins, amount, coins.length - 1, 0, ans);
        return ans[0] == amount + 1 ? -1 : ans[0];
    }
    public void dfs(int[] coins, int rem, int index, int count, int[] ans){
        if(rem == 0) {
            ans[0] = Math.min(ans[0], count);
            return;
        }
        if(index == 0){
            if(rem%coins[index] == 0)
                ans[0] = Math.min(ans[0], count + rem/coins[index]);
            return;
        } 
        int val = coins[index];
        for(int k = rem/val; k >=0 && count + k < ans[0]; k--){
            dfs(coins, rem - k*val, index-1, count+k, ans);
        }
    }
}

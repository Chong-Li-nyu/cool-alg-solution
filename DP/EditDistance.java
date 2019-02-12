package DP;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        // int[][] dp = new int[n+1][m+1];
        int[] dp = new int[m+1];
        // for(int i = 0; i <= n; i++){
        //     dp[i][0] = i;
        // }
        // for(int i = 0; i <= m; i++){
        //     dp[0][i] = i;
        // }
        for(int i = 0; i <= m; i++){
            dp[i] = i; // dp[0][i] = i;
        }
        for(int i = 1; i <= n; i++){
            char c1 = word1.charAt(i - 1);
            int i1j1 = dp[0]; //before update dp[0], for j loop initialize i1j1
            dp[0] = i; //dp[0], update for entrance of i'th round
            int temp;
            for(int j = 1; j <= m; j++){
                char c2 = word2.charAt(j - 1);
                // dp[j]对于是当前j轮，未更新是dp[i-1][j]
                // dp[j]对于下一轮，未更新是d[i-1][j-1]
                // dp[i][j] = Math.min( dp[i-1][j-1] + (c1==c2?0:1) ,
                //                     Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1));
                // d[j] 是d[i][j], 对下一轮是d[i][j-1]
                temp = dp[j];
                dp[j] = Math.min( i1j1 + (c1==c2?0:1),
                        Math.min(dp[j]+1, dp[j-1] + 1));

                i1j1 = temp;
            }
        }
        return dp[m];
        // return dp[n][m];
    }
}

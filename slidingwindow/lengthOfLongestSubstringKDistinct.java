/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String str, int k) {
        // sliding window, s, e.
        // diff = k, arr[128] from
        // when arr[i] 0 -> 1, diff--
        // diff == 0, calculate the curlen and keep result
        // when diff == -1, then move s to right until diff == 0
        if(k == 0) return 0;
        
        int s = 0; int e = 0;
        int n = str.length();
        int curlen = 0;
        int rem = k;
        
        int[] arr = new int[128];
        for(e = 0; e < n; e++){
            char eChar = str.charAt(e);
            if (arr[eChar]++ == 0) {
                rem--;
            }
            
            if(rem == 0){
                curlen = Math.max(curlen, e - s + 1);
            }
            
            while(rem < 0) {
                char sChar = str.charAt(s++);
                if (arr[sChar]-- == 1){
                    rem++;
                }
            }
            //卡点： 如果这里rem重新从-1升到0，表示新的可能的解，为什么不用在本次循环内判断呢。下个循化会更新e，这样会打破这个可能的解. 
           
        }
        if(rem > 0) curlen = Math.max(curlen, n); // !!!!
        return curlen;
    }
}

//因为这里的invalid上一个状态的valid substring的长度是 e-1 - s + 1. 至少需要移除一个左边的char才能重新 从invalid (e - s + 1)回到valid

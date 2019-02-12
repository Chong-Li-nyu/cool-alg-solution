package DP;

import java.util.Arrays;
import java.util.*;
//有bug
public class CombinationSumArraySizeK {
  public int combinationSumArraySizeK(int[] nums, int target, int k) {
    int[] ret = new int[1];
    
    dfs(nums, target, k, 0, ret);
    return ret[0];
  }
  public void dfs(int[] nums, int rem, int k, int index, int[] ret){
    if(rem == 0 && k == 0) {
      ret[0]++;
    }
    for(int i = index; i < nums.length; i++){
      if(k == 0 || rem - nums[i] < 0) continue;
      dfs(nums, rem - nums[i], k-1, i+1, ret);
    }
  }
  public static void main(String[] args) {
    int[] nums = new int[]{1,2,3,4,5};
    int target = 8;
    CombinationSumArraySizeK ins = new CombinationSumArraySizeK();
    System.out.println(ins.combinationSumArraySizeK(nums, 8, 3));
  }
}

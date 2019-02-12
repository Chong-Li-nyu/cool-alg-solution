package DP;

import java.io.*;
import java.util.*;

public class ContinuousSubarrayWithSumK {

    public static void main (String[] args) {
      ContinuousSubarrayWithSumK ins = new ContinuousSubarrayWithSumK();
      int[] various_nums = new int[] {2,1,-1,1, 3};
      int[] nums = new int[]{1 ,1 , 3, 2};
      int k = 4;
      System.out.println(ins.findLongestSubarrayWithSumK(various_nums, 3));
//      System.out.println(ins.findLongestSubarrayWithSumK2(nums, k));
    }
    public int findLongestSubarrayWithSumK(int[] nums, int k){
      int n = nums.length;
      int[] sums = new int[n];
      sums[0] = nums[0];
      Map<Integer, Integer> map = new HashMap<>();
      map.put(sums[0], 0);
      int res = 0;
      
      for(int i = 1; i < n; i++){
        sums[i] = sums[i-1] + nums[i];
        if(sums[i] == k){       
          res = Math.max(res, i + 1);
        }
        if(map.containsKey(sums[i] - k)) {
          int j = map.get(sums[i] - k); //the subarray is from j+1 to i
          res = Math.max(res, i - j);
        }
        if(!map.containsKey(sums[i])) {
          map.put(sums[i], i);
        }
      }
      return res;
    }
    
    public int findLongestSubarrayWithSumK2(int[] nums, int k){
      //check if max sum bigger than k 
      int left = 0;
      int sum = 0;
      int n = nums.length;
      int res = 0;
      for(int i = 0; i < n; i++){
        sum += nums[i];
        if(sum == k){
          res = Math.max(res, i - left + 1);
        }
        while(sum > k){
          //delete the left element
          sum -= nums[left];
          left++;
        } 
        if(sum == k){
          res = Math.max(res, i - left + 1);
        } 
      } 
      return res;
    }
  }

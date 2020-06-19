/*31. Next Permutation
Medium

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/
class Solution {
    public void nextPermutation(int[] nums) {
        // 1. find trailing decreasing subarry，递减的nums[i...n-1], nums[i-1] < nums[i]
        // 2. find some  i <= h <=j happens nums[x]> nums[t=i-1] && nums[x+1] < nums[t]
        // 3. swamp nums[t] and  nums[x] and reverse nums[i..j]
        if(nums.length==0 || nums.length==1) {
            return;
        }
        
        int n = nums.length; 
        
        int i = n-1;
        while(i>0 && nums[i-1] >= nums[i]) {
            i--; //最后一个满足条件的nums[ilast-1]是最大数，那么i-- exit,i变成了ilast -1,nums[i]最大
        } //now nums[i] is the biggest number in trailing subarray
        
        if(i==0) {
            reverse(nums, 0, n-1);
            return ;
        } 
        
        int t = i-1; // nums[t] is the smaller number than at least nums[i]
        //find a number from i to n-1, nums[x] > nums[t]
        int x = n-1;
        while(x >= i && nums[x] <= nums[t]) {
            x--;
        } 
        
        swap(nums, t, x); //使得整体变大了，但trailing subarray递减仍未变，只需reverse
        reverse(nums, i, n-1);
    }
    
    void swap(int[] nums, int i, int j) {
        
        int k = nums[i];
        nums[i]=nums[j];
        nums[j]=k;
        
    }
    
    void reverse(int[] nums, int i, int j) {
        
        while(i<j) {
            swap(nums, i ,j);
            i++;
            j--;
            
        }
        
    }
}

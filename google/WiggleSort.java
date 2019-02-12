package google;

public class WiggleSort {
  public static void main(String[] args) {
    WiggleSort ins = new WiggleSort();
    ins.wiggleSort(new int[] {3,5,2,1,6,4});
  }
    public void wiggleSort(int[] nums) {
        int n= nums.length;
        if(n<=1) return;
        for(int i = 1;i<n; i+=2){
            if(i+1 >= n){
                swap(nums, i-1, i);
                return;
            }
            swap(nums, i-1, i); //make sure nums[i-1] <= nums[i]

            if(nums[i] >= nums[i+1])
                swap(nums, i-1, i+1);
            else
                swap(nums, i+1, i);
        }
    }
    public void swap(int[] nums, int i, int j){
        if(nums[i] <= nums[j]) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

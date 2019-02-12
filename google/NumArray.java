package google;

import java.util.Arrays;

class NumArray {
    int[] arr;
    int[] P;
    int n;
    public static void main(String[] args) {
      NumArray ins = new NumArray(new int[] {1,2,3,4,5}); //[0, 1, 3, 3, 10, 5]
      System.out.println(Arrays.toString(ins.P));
    }
    public NumArray(int[] nums) {
        n = nums.length;
        arr = nums;
        P = new int[n+1];
        for(int i = 0; i < nums.length ; i++){
            updateDelta(i+1, nums[i]);
        }
    }
    
    public void update(int i, int val) {
        int delta = val - arr[i];
        updateDelta(i+1, delta); //update from i+1, if u want to update arr[i]
        arr[i] = val;
    }
    
    public int sumRange(int i, int j) {
      //querySum(j+1) return arr[0] - arr[j]
      //querySum(i) return arr[0] - arr[i-1]
        return querySum(j+1) - querySum(i);
    }
    
    public void updateDelta(int i , int delta){
        //update upward and + lowbit(i)
        // 110-> 1000
        int c = i;
        while( c <= n ){
            P[c] += delta;
            c += lowbit(c);
        }
    }
    
    public int querySum(int i){ //we don't change the index here, but at invocation
        // consume the bit, sum downwards
        int c = i;
        int sum = 0;
        while(c >= 1){
            sum += P[c];
            c -= lowbit(c);
        }
        return sum;
    }
    
    public int lowbit(int m){
        return m&(-m);
    }
}

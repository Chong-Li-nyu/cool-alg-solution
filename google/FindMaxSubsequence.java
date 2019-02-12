package google;
import java.util.*;

public class FindMaxSubsequence {
  public static void main(String[] args) {
    FindMaxSubsequence ins = new FindMaxSubsequence();
    int[] nums = new int[] {2,1,4,5,3};
    System.out.println(ins.findMaxSubseq(nums, 2));
  }
  public List<Integer> findMaxSubseq(int[] nums, int k) {
    // biggest subseq of length k
    /**
     * 2,6,4,5,3 , K = 2
     * return 65
     * 使用单调stack，递减因为是求最大，那么stack的头部是最大的
     * 遇到比st.peek() 大的元素就pop知道空或者 ith element < st.peek()
     * 
     */
    Stack<Integer> st = new Stack<>();
    if(nums.length < k) return null;
    int to_pop = nums.length - k;
    for(int i = 0; i<nums.length; i++) {
      while(!st.isEmpty() && nums[i] > st.peek() && to_pop-- > 0) {
        st.pop();
      }
      st.push(nums[i]); //stack 至少保留了k个元素，因为最多pop n-k个
    }
    List<Integer>  ret = new ArrayList<Integer>(st.subList(0, k));
    return ret;
  }
}

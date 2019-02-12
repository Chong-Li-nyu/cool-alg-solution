package BST;

public class MaxPathSum {
  
  public int maxPathSum(TreeNode root) {
    int[] res = new int[]{Integer.MIN_VALUE}; //reference
  maxSingle(root, res);
  return res[0];
}
/**
 * 
 * @param root
 * @return max single-side path sum, which must go through root and only one side of root
 * then we need the single-side path of root's left and right subtree
 * ??? how to calculate the path without passing root
 * --- in each level dfs, calculate local max sum, the global max sum must exist on some level
 * res[0] = Math.max(res[0], root.val+left+right);
 * 这一步和函数定义无关，是在求可能出现的path，这时可以同时取左右两边的path，与之前的单边和（单root）比较
 * 
 * 思考：可能出现在下面的某一层，所以要用递归，有了递归就能进入下面的层，
 * 所以在本层就能写出root.val+left+right.
 * 但是left和right是两个单边，所以递归的函数也是求单边的，所以单边返回的应该是更大的单边。
 * left可能是负数，所以需要和0 比较
 */
public int maxSingle(TreeNode root, int[] res){
    if(root == null) return 0;
    int left = Math.max(0,maxSingle(root.left,res) );
    int right = Math.max(0,maxSingle(root.right, res));

    res[0] = Math.max(res[0], root.val+left+right);
      
    return root.val+Math.max(left,right);
}
}

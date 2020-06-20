class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return helper(root, null, null);
    }
    // 确定上下限
    public boolean helper(TreeNode root, Integer min, Integer max){ 
        if(root == null) return true;
        if ( (min!= null && root.val <= min) || (max != null && root.val >=max) ) return false;
        if(!helper(root.left, min, new Integer(root.val))) return false;
        if(!helper(root.right, new Integer(root.val), max)) return false;
        return true;
    }
}

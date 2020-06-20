
class Solution {
    public void flatten(TreeNode root) {
        helper(root);
    }
    public TreeNode helper(TreeNode root) {
        
        /*
        考虑本层的流程
        1. 将非空的left subtree子问题求解，
        2. 需要返回left subtree的最后退出节点exit1，可以让exit1的right指向root.right, 同时root的right指向root.left。即把非空左子树插入到 root 和 root.right之间
        3. 递归右子树
        4. 同时因为step2要求返回退出节点，那么可能是 1）right exit2; 2）left exit1; 3）root;
        */
        if(root == null) return null;
        TreeNode temp = root.right;
        TreeNode exit1 = helper(root.left);
        if(exit1 == null){
            //nothing needed here
        } else{ 
            root.right = root.left; exit1.right = temp; root.left = null;
        }
        TreeNode exit2 = helper(temp);
        if(exit2 != null) {
            return exit2; 
        } else {
            return exit1 != null ? exit1 : root;  //preorder的退出节点
        }
        
    }
}

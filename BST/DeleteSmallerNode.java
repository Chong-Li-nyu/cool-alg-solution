package BST;

/** 
 * @author chong
 *
 */

public class DeleteSmallerNode {
  
  public TreeNode removeSmallThanTarget(TreeNode root, int t) {
    return rm(root, t);
  }
  
  public TreeNode rm(TreeNode root, int t) {
    if(root == null) return null;
    if(root.val == t ) {
      root.left = null;
      return root;
    }
    if(root.val > t) {
       root.left = rm(root.left, t);
       return root;
    }else { //when root.val <t, delete the root self also.
      return rm(root.right, t); //don't care about root and left anymore
    }
  }
  
  public static void main(String[] args) {
    DeleteSmallerNode ins = new DeleteSmallerNode();
    TreeNode root = new TreeNode(50);

    root.left = new TreeNode(new TreeNode(10), 20, new TreeNode( new TreeNode(27), 40, new TreeNode(45)));
    TreeNode newRoot = ins.removeSmallThanTarget(root, 30);
    System.out.println(newRoot.left.right.left==null);
  }
  
}

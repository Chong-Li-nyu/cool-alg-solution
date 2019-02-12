package BST;

public class checkEqualBST {
  public static void main(String[] args) {
    TreeNode r1 = new TreeNode(3);
    r1.left = new TreeNode(2);
    TreeNode r2 = new TreeNode(3);
    r2.right = new TreeNode(2);
    System.out.println(new checkEqualBST().check(r1, r2));
  }
  public boolean check(TreeNode r1, TreeNode r2) {
    if(r1 == null ) return r2 == null;
    else if(r2 == null) return false;
    if(r1.val == r2.val) {
      return ( (check(r1.left, r2.left) && check(r1.right, r2.right)) || 
          (check(r1.left, r2.right) && check(r1.right, r2.left)) );
    }else {
      return false;
    }
  }
}

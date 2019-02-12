package BST;

public class TreeNode{
  int order; // Used in KLargest.java
  int val;
  TreeNode left;
  TreeNode right;
  public TreeNode(int val) {
    
    this.val = val;
    left = null;
    right = null;
  }
  public TreeNode( TreeNode left, int val, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
  public static TreeNode nf( TreeNode left, int val, TreeNode right) {
    TreeNode r = new TreeNode(left, val, right);
    return r;
  }
  @Override 
  public String toString() {
    String l= this.left==null?"null ":String.valueOf(this.left.val);
    String r= this.right==null?"null ":String.valueOf(this.right.val);
    return String.valueOf ("l: "+l+", " + this.val+", r: "+r);
  }
}

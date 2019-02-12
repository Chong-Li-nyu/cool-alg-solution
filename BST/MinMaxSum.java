package BST;

public class MinMaxSum {
  public static void main(String[] args) {
    SerializeDeserializeBST ins = new SerializeDeserializeBST();
    TreeNode root = ins.deserialize( "4 2 1 # # 3 # # 8 6 5 # # 7 # # 9 # #" );
    MinMaxSum ins2 = new MinMaxSum();
    int res = ins2.minMaxSum(root, 4, 7);
    System.out.println(res);
    
    int leftsum = ins2.getAllGreaterSum(root.left, 2);
    System.out.println(leftsum);
  }
  public int minMaxSum(TreeNode root, int min, int max) {
    //all number bigger than min, all number smaller than max
    // must contain some complete subtree
    if( root == null) return 0;
    if (min == max) return min; //only one node 
    if( root.val > max ) return minMaxSum(root.left, min, max);
    else if (root.val < min) return minMaxSum(root.right, min, max);
    else if( root.val >= min && root.val <= max) {
      return getAllGreaterSum(root.left, min) + root.val + getAllLowerSum(root.right, max);
    }
    return -1;
  }
  public int getAllGreaterSum(TreeNode root, int min) {
    // get all parents in root subtree which are larger or equal than min
    if(root == null) return 0;
    if( root.val >= min ) 
      return root.val +  getAllGreaterSum(root.left, min) + getAllGreaterSum(root.right, min);
    else {
      return getAllGreaterSum(root.right, min);
    }
  }
  public int getAllLowerSum(TreeNode root, int max) {
    // get all parents in root subtree which are larger or equal than min
    if(root == null) return 0;
    if( root.val <= max) 
      return root.val + getAllLowerSum(root.left, max) + getAllLowerSum(root.right, max);
    else {
      return getAllLowerSum(root.left, max);
    }
  }
}

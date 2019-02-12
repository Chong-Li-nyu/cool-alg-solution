package BST;

public class KLargest {
  public int res;
  public int count = 1;
  /**
   * r.order is its order in inorder traversal
   * @param r: root node
   * @return number of all nodes in the tree represented by node r
   */
  public void dfs(TreeNode r, int k) {
    if(r!= null) {
      dfs(r.right, k);
      r.order = count++; //count starts from 1
      if(r.order == k) {
        res = r.val;
      }
      dfs(r.left, k);
    }
  }
  //WRONG solution
  public void dfswrong(TreeNode r, int count, int k) {
    if(r!= null) {
      dfswrong(r.right, count, k);
      r.order = count++; //count starts from 1
      if(r.order == k) {
        res = r.val;
      }
      dfswrong(r.left, count, k);
    }
  }
  public static void main(String[] args) {
    InorderPreorderRestore ins = new InorderPreorderRestore();
//    int[] preorder = new int[] {6,4,2,1,3,5,10,8,7,9};
//    int[] inorder = new int[] {1,2,3,4,5,6,7,8,9,10};
    int[] preorder = new int[] {2,1,4,3,5};
    int[] inorder = new int[] {1,2,3,4,5};
    TreeNode r = ins.restore(preorder, inorder);
    KLargest ins2 = new KLargest();
    ins2.dfs(r,2);
//    ins2.dfswrong(r, 1, 2); //output 1
    
    System.out.println(ins2.res); //strictly pass reconstructed tree
//    System.out.println(ins2.res);
  }
}


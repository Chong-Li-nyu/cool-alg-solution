package BST;
import java.util.*;
public class InsertNodeCompleteTree {
  public static void main(String[] args) {
    InsertNodeCompleteTree ins = new InsertNodeCompleteTree();
    
    int[] preorder = new int[] {4,2,1,3,6,5};
    int[] inorder = new int[] {1,2,3,4,5,6};    
    InorderPreorderRestore ins2 = new InorderPreorderRestore();
    TreeNode r = ins2.restore(preorder, inorder);
    
//    ins.insert(r, new TreeNode(8));
    ins.insert2(r,  new TreeNode(8));
    System.out.println(r.right.right.val);
  }
  
  public TreeNode insert2(TreeNode r, TreeNode in) {
    //if has unbalanced treenode, then it is the parent to have the inserted node
    //else all node has two children or none, find the first node which has no child
    // LAYER traversal using QUEUE

    if(r == null) return in;
    Queue<TreeNode> q = new LinkedList<>();
    q.add(r);
    TreeNode candidate = null;
    while(!q.isEmpty()) {
      TreeNode node = q.poll();
      if( node.left != null && node.right == null) {
        node.right = in;
        return r; //end here
      }else {
        if( node.left == null && node.right == null && candidate == null) {
          candidate = node; // no need to add its children anymore
        }
        else {
          q.add(node.left);
          q.add(node.right);
        }
      }
    }
    return r;
  }
  
  //插入的题或者删除的题都要返回node，方便递归进入下一层是，本层能够连接上
  // root = insert(root.left, in)  
  public TreeNode insert(TreeNode root, TreeNode in) {
    // biggest depth and smallest depth has difference.
    if( root == null) {
      root = in;
      return root;
    }
    
    int[] arr = depth(root);
    if(arr[0] == arr[1]) {
        root.left = insert(root.left, in); //insert to left subtree
    }else {
      int[] larr = depth(root.left);
      if( larr[0] == larr[1] )
        //We need a return type to chain the changed tree with root
        root.right = insert(root.right, in);
      else {
        root.left = insert(root.left, in);
      }
    }
    return root;
  }
  public int[] depth(TreeNode r) {
    if(r == null) return new int[] {0,0};
    int deepest = depth(r.left)[0] + 1;
    int shallow = depth(r.right)[1] + 1;
    return new int[] {deepest, shallow};
  }
}

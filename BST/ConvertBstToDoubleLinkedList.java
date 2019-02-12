package BST;

public class ConvertBstToDoubleLinkedList {
  public TreeNode prev = null;
  public TreeNode head = null; // new TreeNode(0);
  //the leftmost node be the head, right most be the tail,
  // use every node's left as prev, and right as next
  // head.prev = tail, tail.next = head;
  public static void main(String[] args) {
    InorderPreorderRestore ins = new InorderPreorderRestore();
    int[] preorder = new int[] {2,1,4,3,5};
    int[] inorder = new int[] {1,2,3,4,5};
    TreeNode r = ins.restore(preorder, inorder);
    
    TreeNode prev = null;
    ConvertBstToDoubleLinkedList ins2 = new ConvertBstToDoubleLinkedList();
    ins2.dfs(r);

    System.out.println(ins2.head.right);
    
  }
  
  public void dfs(TreeNode root) { //head will  bring out the head
    if(root == null) return;
    dfs(root.left);
    
    if(prev != null) {
      prev.right = root;
      root.left = prev; 
    }else { // prev ==null, at the leftmost node
      head = root;
    }
    //We make use of the last visited node is rightmost one
    // always concatenate the head and tail
    head.left = root;
    TreeNode rightchild = root.right;
    root.right = head;

    prev = root; // update prev
    
    dfs(rightchild);
  }
}

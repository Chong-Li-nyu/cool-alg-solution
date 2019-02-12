package BST;

public class DeleteNode {
  /**
   * deleteNode will delete the node with value key and return the 
   * 'newly created' tree (may be one subtree when another subtree is empty,
   *  or change the root.val return root itself)
   * 
   * we are not sure if deleteNode return a new node, or 
   * just change root.val as leftmost leaf's val in right subtree.
   * so always use root.left = delete(root.left, key)
   * @param root
   * @param key
   * @return
   */
  public TreeNode deleteNode(TreeNode root, int key) {
    if(root == null) return null;
    if(key < root.val )
        root.left  = deleteNode(root.left, key); 
    else if(key > root.val) root.right = deleteNode(root.right, key);
    else{
        // root is the node to be deleted
        if(root.left == null) return root.right;
        else if(root.right == null) return root.left;
        else{
            int newval = getLeftMost(root.right);
            root.val = newval;
            root.right = deleteNode(root.right, newval);
            return root;
        }
    } 
    return root;//compiler thing
}
  public int getLeftMost(TreeNode root){
      while(root.left!= null) root = root.left;
      return root.val;
  }
}
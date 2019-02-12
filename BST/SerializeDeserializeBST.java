package BST;
import java.util.*;
public class SerializeDeserializeBST {

  public String serialize(TreeNode root) {
        StringBuilder s = new StringBuilder();
        preOrder(root, s);
        return s.toString();
    }
    
    public void preOrder(TreeNode root, StringBuilder s) {
        if (root == null) {
            s.append("#").append(" ");
            return;
        }
        s.append(root.val).append(" ");
        preOrder(root.left, s);
        preOrder(root.right, s);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(" ");
        Queue<TreeNode> q = new LinkedList<>();
        for (String s: strs){
            if(s.equals("#")) q.offer(null);
            else 
                q.offer(new TreeNode(Integer.valueOf(s)));
        }
        return dfs(q);
    }
    public TreeNode dfs(Queue<TreeNode> q){
        if( q.isEmpty()) return null;
        TreeNode root = q.poll();
        if(root == null) return null;
        root.left = dfs(q);
        root.right = dfs(q);
        return root;
    }
  
}

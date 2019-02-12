package BST;
import java.util.*;

public class IterativeInorderPostOrder {

  public static void main(String[] args) {
    IterativeInorderPostOrder ins = new IterativeInorderPostOrder();
    TreeNode root = new TreeNode(6);
    root.left =new TreeNode (null, 3, new TreeNode(5)) ;
    root.right = new TreeNode(new TreeNode(7), 9, new TreeNode(10) );
    List<Integer> ans = ins.iterativeInorder(root);
    System.out.println(ans.toString());
    List<Integer> ansRecur = new ArrayList<>();
//    ins.inorderRecursion(root, ansRecur);

    ins.postorderInterative(root, ansRecur);
    System.out.println(ansRecur);
  }
  public void postorderInterative(TreeNode root, List<Integer> result) {
    /**
     *1. 构建left path to leftmost node
     *2. pop() 后不能直接访问，需要pop.right == null || lastvisited = pop.right
     *3. 否则递归访问右子树，这之前需要再次push保存根节点
     */
    Stack<TreeNode> st = new Stack<>();
    TreeNode p = root;
    TreeNode lastvisit = null;
    while( p!= null) {
      st.push(p);
      p = p.left;
    }
    
    
    while(!st.isEmpty()) {
      p = st.pop();
      if( p.right == null || lastvisit == p.right) {
        //can visit p as root node
        result.add(p.val);
        lastvisit = p; //唯一一次更新lastvisit
      }else { 
        st.push(p); //为了下一次访问完右子树后再访问根节点
        p = p.right;//这里访问到了right
        while(p!=null) {
          st.push(p);
          p = p.left;
        }
      }
    }
    
  }
  public void inorderRecursion(TreeNode root, List<Integer> result){
    if(root==null) return;
    inorderRecursion(root.left, result);
    result.add(root.val);
    inorderRecursion(root.right, result);
  }
  public List<Integer> iterativeInorder(TreeNode root) {
    // maintain the walked node on the path to the leftmost node
    Stack<TreeNode> st = new Stack<>();
    
    List<Integer> ans= new ArrayList<>();
    //construct initial left path stack
    TreeNode pointer = root;
    
    // pointer !=null 让空stack也能启动循环
    while(!st.isEmpty() || pointer != null) {
      while(pointer != null) {
        st.push(pointer);
        pointer = pointer.left;
      }
      pointer = st.pop();
      ans.add(pointer.val);
      pointer = pointer.right;
    }
    
    /*
    while(pointer!= null) {
      st.push(pointer);
      pointer = pointer.left;
    }
    while(!st.isEmpty()) {
      TreeNode pop = st.pop();
      ans.add(pop.val);
      pointer = pop.right;
      while( pointer != null) { //push the right subtree left path
        st.push(pointer);
        pointer = pointer.left;
      }
    }
    */
    return ans;
  }
}

package BST;

public class InorderPreorderRestore {
  //通过preorder恢复字数
  public TreeNode restore(int[] pre, int[] in){
    if(pre.length == 0) return null;
    int v = pre[0];
    if(pre.length == 1) {
      return new TreeNode(v);
    }
    TreeNode root  = new TreeNode(v);
    int indIn = find(in, v);
    int l = indIn; // 0 to v-1
    int r = in.length-l-1; // in.length-l-1
    int[] ls = new int[l]; 
    int[] rs = new int[r];
    System.arraycopy(pre, 1, ls, 0, l);
    System.arraycopy(pre, l+1, rs, 0, r);
    int[] ls2 = new int[l];
    int[] rs2 = new int[r];
    System.arraycopy(in, 0, ls2, 0, l);
    System.arraycopy(in, indIn+1, rs2,0,r);
    root.left = restore( ls, ls2);
    root.right  = restore(rs, rs2);
    return root;
  }
  public int find(int[] in, int v) {
    for(int i=0; i<in.length; i++) {
      if(in[i] == v) return i;
    }
    return 0;
  }
  public static void main(String[] args) {
    int[] preorder = new int[] {3,1,2};
    int[] inorder = new int[] {1,2,3};
    
    
    InorderPreorderRestore ins = new InorderPreorderRestore();
    TreeNode r = ins.restore(preorder, inorder);
    System.out.println(r.left.right.val == 2);
  }
}

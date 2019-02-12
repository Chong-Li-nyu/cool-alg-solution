package BST;
import java.util.*;
public class FindClosetLeaf {
  /**
   *  BFS 
   *  1.邻接节点 
   *  2.undirected graph 需要 visited 去重
   * @param root
   * @param k
   * @return leaf. root cannot be seen as leaf, so add itself as child
   */
  
  public int findClosestLeaf(TreeNode root, int k) {
    ArrayList<Integer>[] graph = new ArrayList[1001];
    dfs(root, root.val, graph) ; 
    //leaf node only has one neighbor
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> q = new LinkedList<>();
    q.offer(k);
    while(!q.isEmpty()){
        int pop = q.poll();
        visited.add(pop); //record visited nodes
        if(graph[pop].size() == 1) return pop; //root has at least two children
        for(int item : graph[pop])
            if(!visited.contains(item))
                q.offer(item);//next layer
    }
    return root.val; //default return root.val when only root
}
  /**
   * construct an undirected graph dfs format. BFS must use adjacency list
   * 
   * @param root
   * @param parent
   * @param graph
   */
public void dfs(TreeNode root, Integer parent, ArrayList<Integer>[] graph){
    if(root == null) return;
    //create its list first, because parent can be itself
    graph[root.val] = new ArrayList<Integer>(); 
    graph[parent].add(root.val); //for the up layer
    graph[root.val].add(parent); //child also need to point to root
    dfs(root.left, root.val, graph);
    dfs(root.right, root.val, graph); 
}
}
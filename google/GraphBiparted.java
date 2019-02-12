package google;
import java.util.*;
public class GraphBiparted {
  public boolean isBipartiteDFS(int[][] graph) {
      // next layer nodes, if the next layer dfs can access to nodes in the same layer, return false;
      Set<Integer> visited = new HashSet<>();
      HashSet<Integer>[] g = new HashSet[2];
      g[0] = new HashSet<Integer>();
      g[1] = new HashSet<Integer>();

      for(int i = 0; i<graph.length; i++){
          if(visited.contains(i)) continue;
      g[0].add(i);
          if(!dfs(i, g, 0, graph, visited)) return false;
      }
      return true;
  }
  public boolean dfs(int root, Set<Integer>[] g, int index, int[][] graph, Set<Integer> visited){
      if(graph[root].length == 0) return true;
      for(int adj: graph[root]){
          g[1-index].add(adj);
      }
      
      
      visited.add(root);
      
      for(int adj : graph[root]){
          if (g[index].contains(adj)) return false;
          if(visited.contains(adj)) continue;
          if(!dfs(adj, g, 1-index, graph, visited)) return false;
      }
      return true;
  }
  public boolean isBipartite(int[][] graph) {
    // graph[i] stores all adjs nodes , no direction
    //use BFS to check if node i adjs in the same group of i
    HashSet<Integer>[] g = new HashSet[2];
    for(int i = 0;i<2; i++){
      g[i] = new HashSet<Integer>();
    }
    Queue<Integer> q = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    //无向图检查所有的节点访问过没有
    for(int i =0; i<graph.length; i++) {
      if(visited.contains(i)) continue;
      q.offer(i);
      int index = 0;
      g[index].add(i);
      while(!q.isEmpty()) {
        int size = q.size();
        for(int k = 0; k<size; k++) {
          int pop = q.poll();
          if(visited.contains(pop)) continue; //in case of go back to upper layer
          visited.add(pop);
          for(int adj: graph[pop]) {
            //检查邻接点是否和自己在一个group
            if(g[index].contains(adj)) return false;
            g[1-index].add(adj);
            q.offer(adj); //visited 检查入口
          }
        }
        index = 1-index; //next layer belongs to another group
      }
    }
    return true;
  }
}

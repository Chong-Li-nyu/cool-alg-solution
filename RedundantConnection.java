class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        //acyclic graph, update both nodes' adjs list when dealing with edge
        //先检测 后更新图
        int n = edges.length;
        
        ArrayList<Integer>[] graph = new ArrayList[2*n]; //右边是运行时擦出类型，左边编译时，类型为ArrayList<Integer>
        for(int[] e : edges){
            int u =e[0];
            int v =e[1];
            
            Set<Integer> visited = new HashSet<>();
            if(hasPath(u, v, graph, visited)){
                return e;
            }
            if(graph[u] == null) graph[u] = new ArrayList<Integer>();
            if(graph[v] == null) graph[v] = new ArrayList<Integer>();
            graph[u].add(v);
            graph[v].add(u);
        }
        
        return new int[0];
    }
    public boolean hasPath(int u, int v, ArrayList<Integer>[] graph, Set<Integer> visited){
        if(u == v) return true;
        if(graph[u] == null) return false;
        
        visited.add(u);
        for(int adj: graph[u]) {
            if (visited.contains(adj)){ //双向边避免往回搜索
                continue;
            }
            if(hasPath(adj, v, graph, visited)) return true;
        }
        return false;
    }
}

class Solution {
    //本质是是否存在奇数个节点构成的环路
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // -1 and 1
        for(int i = 0; i< graph.length; i++){
            //上过色的都是合法的，因为不合法会在dfs时返回
            if(color[i] == 0) { //未上色表示与之前的节点都不相连，因为dfs已经把相连节点上色
               if(!dfs(i, 1, graph, color)) return false;
            } 
        }
        return true;
    }
    public boolean dfs(int cur,  int targetColor, int[][] graph, int[] color){
        //当前同时要检查上色是否正确，以及paint uncolored node to targetColor
        if (color[cur] == targetColor) return true; //避免了子节点上色之后递归重复访问父亲节点
        color[cur] = targetColor; 
        for(int adj: graph[cur]){
            if(color[adj] == targetColor || !dfs(adj, -targetColor, graph, color)) {
                return false;
            }
        }
        return true;
    }
}

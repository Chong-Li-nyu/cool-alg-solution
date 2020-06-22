class Solution {
    public int[] findOrder(int n, int[][] preq) {
        if (n==0) return new int[0];
        int[] indegree = new int[n];
        ArrayList<Integer>[] graph = new ArrayList[n];
       
        for(int[] pair : preq){
            int first = pair[0];
            int second = pair[1];
            if(graph[second] == null) graph[second] = new ArrayList<Integer>();
            graph[second].add(first);
            indegree[first]++;
        }
        // Initialize queue putting all starting node
        Queue<Integer> q = new LinkedList<>();
        for(int i =0 ;i<n;i++){
            if (indegree[i] == 0) q.offer(i);
        }
        
        int index = 0;
        int[] ret = new int[n];
        while(!q.isEmpty()){
            int cur = q.poll();
            ret[index++] = cur; //how to tell cycle in BFS
            if(graph[cur] == null) continue;
            for(int adj: graph[cur]){
                //不用查重，因为有indegree数组保证。有环那么indegree会>0无法入队
                // if(q.contains(adj)){
                //     return new int[0];
                // }
                if(--indegree[adj] == 0) q.offer(adj);
            }
        }
        return index < n ? new int[0] : ret;
    }
}

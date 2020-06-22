/*
210. Course Schedule II
Medium

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
*/
class Solution {
    public int[] findOrder(int n, int[][] preq) {
        // BFS. [1,0] indegree[1]++
        if (n==0) return new int[0];
        List<List<Integer>> adjs = new ArrayList<>();
        for(int i = 0;i<n; i++){
            adjs.add(new ArrayList<Integer>());
        }
        for (int[] pair: preq){
            adjs.get(pair[1]).add(pair[0]);
        }

        List<Integer> visited = new ArrayList<>();
        int[] status = new int[n];
        for(int i = 0; i < n; i++){ //保证不相连的分量都能被访问到,不用担心开始节点的顺序，因为visited.add(0,s);
            if (dfs(i, status, adjs, visited)) return new int[0];
        }
        int[] ret = new int[n];
        for(int i = 0;i<n; i++){
            ret[i] = visited.get(i);
        }
        return ret; 
    }
    
    //有环返回真
    public boolean dfs(int s, int[] status, List<List<Integer>> adjs, List<Integer> visited){
        if(status[s]==1) return true; // 1 means visiting, haven't exit
        if(status[s]==2) return false; //2 means visited, 访问过的节点不会被重复添加到visited
        
        status[s] = 1;
        for(int adj: adjs.get(s)){
            if(dfs(adj, status, adjs, visited)) return true;
        }
        status[s] = 2;
        visited.add(0, s); //add to the first position
        return false;
    }
}

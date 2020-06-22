/*
332. Reconstruct Itinerary
Medium

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.
    One must use all the tickets once and only once.

Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

*/
/*
topo sort. Postorder add reversely to the result. 
1. 因为可能有环，所以要postorder添加起点，同时要删除在访问next前删除edge避免重复访问
2. 分两种情况讨论，adjacent中环是先出现还是后出现，如果先出现那么就能正常的回溯顺下来；如果环在lexically后面出现，那么由于是postorder reversely add，
带环的那个dfs仍然是优先访问的（可以理解右子树的结果仍然在左子树之前
3. 访问adj的时候需要while循环一个一个的访问，不能使用for，避免修改循环对象错误。可食用pq代替adj list
*/
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        int n = tickets.size();
        Map<String, List<String>> graph = new HashMap<>();
        for(int i = 0; i<n; i++){
            String start = tickets.get(i).get(0);
            String end = tickets.get(i).get(1);
            if(!graph.containsKey(start)) graph.put(start, new ArrayList<String>());
            graph.get(start).add(end);
        }
        for(List<String> adjs: graph.values()){
            Collections.sort(adjs);
        }
        List<String> ret = new ArrayList<String>();
        dfs(graph, "JFK", ret);
        return ret;
    }
        
    // before enter a subnode, disable this edge to avoid revisiting in a cycle
    // postorder reversely add to result
    public void dfs(Map<String, List<String>> graph, String cur, List<String> visited){
        if(!graph.containsKey(cur)) {
            visited.add(0, cur);
            return;
        }
        List<String> adjs = graph.get(cur);
        while(!adjs.isEmpty()){
            String next = adjs.remove(0);
            dfs(graph, next, visited);
        }
        visited.add(0, cur);
    }
}

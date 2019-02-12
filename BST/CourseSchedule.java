package BST;

import java.util.*;

public class CourseSchedule {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // BFS, any loop in the directed graph
            List<Integer>[] graph = new List[numCourses];
            int[] inDegree = new int[numCourses];
            for(int[] edge: prerequisites){
                int src = edge[1];
                int out = edge[0];
                inDegree[out] += 1;
                if (graph[src] == null){
                    graph[src] = new ArrayList<Integer>();
                }
                graph[src].add(out);
            }

            Queue<Integer> q = new LinkedList<>();
            Set<Integer> inQ = new HashSet<>(); //inQ can convert to visited
            for (int i = 0; i<numCourses; i++){
                if(inDegree[i] == 0){
                    //保证所有入度为0的起点都在q中
                    q.add(i); //start from those points
                    inQ.add(i);
                }
            }
            int visitedCount = 0;
            while (!q.isEmpty()){
                int curr = q.poll();
                visitedCount++;
                if(graph[curr] == null) continue;
                for(int neighbor : graph[curr]){
                    if(inQ.contains(neighbor)) continue;
                    inDegree[neighbor]--;
                    if(inDegree[neighbor] == 0){
                        // 由于这里有inDegree  == 0的入队限制，所以不可能被重复入队
                        q.add(neighbor);
                        inQ.add(neighbor);
                    }
                }
            }
            return visitedCount == numCourses;
        }
}

package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleTopo {
  public int[] findOrder(int N, int[][] prerequisites) {
    ArrayList<Integer>[] adj = new ArrayList[N];
     for(int i=0; i<N; i++){
         adj[i] = new ArrayList<Integer>();
     }
     int[] degree = new int[N];
     for( int[] pair: prerequisites){
         int p1 = pair[0];
         int p2 = pair[1];
         degree[p1]++;
         adj[p2].add(p1);
     }
    Queue<Integer> q = new LinkedList<>();
     for(int i=0;i<N; i++){
         if(degree[i] == 0){
             q.offer(i);
         }
     }
    int count = 0;
     int[] res = new int[N];
     while(!q.isEmpty()){
         int u = q.poll();
         res[count++] = u;
         for( int v: adj[u] ){
            if(--degree[v] == 0)
                q.offer(v);
         }
     }
     if(count != N) return new int[0];
     return res;
 }
}

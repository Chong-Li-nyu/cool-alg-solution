package graph;

import java.util.*;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Topo {
  public Integer[] topoSort(int[][] graph,int N) throws Exception{
    int[] indegree = new int[N];
//    PriorityQueue<Node> pq = new PriorityQueue<Node>(
//        new Comparator<Node>(){
//          @Override
//          public int compare(Node n1, Node n2) {
//            return Integer.compare(n1.indegree, n2.indegree);
//          }
//        }
//    );
    List<Integer> res = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();
    for(int[] l : graph){
        for(int v: l){
            indegree[v] ++;
        }
    }
    for(int i = 0; i<N; i++) {
      if(indegree[i] == 0)
      q.offer(i); //only offer, not visit
    }
    int count = 0;
    while(!q.isEmpty()) {
      int u = q.poll();
      res.add(u); // visit the node starts
      count ++;
      for(int des : graph[u]) {
        if( --indegree[des] == 0)
          q.offer(des);
      }
    }
    if(count != N) throw new Exception();
    Integer[] resarr= new Integer[N];
    return res.toArray(resarr);
  }
  public static void main(String[] args) {
    int[][] graph = new int[5][];
    graph[0] =  new int[] {1,2};
    graph[1] = new int[] {2,3};
    graph[2] = new int[] {3,4};
    graph[3] = new int[] {2,4};
    graph[4] = new int[0];
    Topo t = new Topo();
    try {
      Integer[] res = t.topoSort(graph, 5);
      System.out.println(Arrays.toString(res));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

package google;
import java.util.*;
public class SolutionBlossom {
    public static void main(String[] args) {
      SolutionBlossom s = new SolutionBlossom();
      int[] P = new int[] {3,1,5,4,2};
      int K = 3;
      int res = s.solution2(P, K);
      System.out.println(res);
    }

    public int[] s;
    public int[] rank;
    
    int groupsize  = 0;
     
    public int solution2(int[] P, int K) {
      int n = P.length;
      boolean graph[][] = new boolean[n+1][n+1];
      int day = -1;
      for( int i =0; i<n; i++) {
        // use dfs to get the size 
        int index = P[i];
        graph[index][index] = true;
        int left = index-1;
        int right = index+1;
        if(left>=1 && graph[left][left]) {
          graph[index][left] = true;
          graph[left][index] = true;
        }
        if(right<=n && graph[right][right]) {
          graph[index][right] = true;
          graph[right][index] = true;
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> sizes = new HashSet<>();
        for(int k = 0; k<=n; k++) {
          if( graph[k][k] ) {
            groupsize = 0;
            dfs(k, graph, n, visited);
            sizes.add(groupsize);
            if(sizes.contains(K)) {
              day = i+1;
              break;
            }
          }
        }

      }
      return day;
    }
    public void dfs(int index, boolean[][] graph, int n, Set<Integer> visited) {
      if(visited.contains(index)) return;
      groupsize ++; //index 
      visited.add(index); //object is reference, can be seen by all recursion function
      for(int i = 0; i<=n; i++) {
          if(i == index) continue;
          if( graph[index][i] ) {
            dfs(i, graph, n, visited);
          }
      }
    }
    public int solution(int[] P, int K) {
        // write your code in Java SE 8
        int n = P.length;
        s = new int[n+1];
        for(int i = 1; i<= n; i++)
            s[i] = i;
//        rank = new int[n+1];
        int[] gsize = new int[n+1]; // init 0

        int day = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< n; i++){
            int ind = P[i];
            gsize[ind] = 1;
            int left = ind-1;
            int right = ind+1;
            if( left >=1 && gsize[ find(left) ] > 0){
                int temp = gsize[find(left)];
                gsize[find(left)] = 0;
                map.remove(find(left));
                
                union(ind, left);
                gsize[find(ind)] += temp; // only bind the group size on the root index
            }
            if( right <= n && gsize[ find(right) ] > 0){
              int temp = gsize[find(right)];
              gsize[find(right)] = 0; //before union, reset right group size
              map.remove(find(right)); 
              
              union(ind, right);
              gsize[find(ind)] += temp;
            }
            map.put(find(ind), gsize[find(ind)]);

            for( int j=1; j<=n; j++){
                if( gsize[find(j)] == K){
                    day = i+1;
                    break;
                }
            }
            for(int v: map.values()) {
              if(v == K) {
                day = i+1;
                break;
              }
            }
        }
        return day;
    }
    public int find(int p){
        if(p==s[p]) return p;
        else 
            return find(s[p]);
    }
    public void union(int p, int q ){
        int proot = find(p);
        int qroot = find(q);
        if(proot != qroot)
          s[qroot] = proot; //always fold right to left
        
    }
//    public void unionWithSize(int p, int q) {
//      
//    }

  
  
}

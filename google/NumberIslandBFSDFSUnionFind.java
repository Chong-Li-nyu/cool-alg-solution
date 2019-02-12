package google;
import java.util.*;
public class NumberIslandBFSDFSUnionFind {
  /*  
  public int numIslands(char[][] grid) {
      int R= grid.length;
      if( R == 0) return 0;
      int C = grid[0].length;
      int count =0;
      int bitmask = 0xFFFF;
      for( int i = 0; i<R; i++){
          for(int j = 0; j<C; j++){
              if(grid[i][j] == '1' ){
                  //start a BFS and check the boundary before each offer operation
                  Queue<Integer> q = new LinkedList<>();
                  Set<Integer> set = new HashSet<>();
                  int key = (i<<16) + j;
                  q.offer(key);
                  count ++;
                  
                  while(!q.isEmpty()){
                      int curr = q.poll();
                      if( set.contains(curr) ) continue;
                      
                      set.add(curr);
                      // int r = curr / 10000, c = curr%10000;
                      
                      int c = curr & bitmask;
                      int r = curr >> 16; //abandon lower 16 bits
                      grid[r][c] = '0';
                      check(q, r+1, c, R, C, grid, set);
                      check(q, r-1, c, R, C, grid, set);
                      check(q, r, c+1, R, C, grid, set);
                      check(q, r, c-1, R, C, grid, set);
                  }
                  
              }
          }
      }
      return count;
  }
  
  public void check(Queue<Integer> q, int r, int c, int R, int C, char[][] grid, Set<Integer> set){
      int key = (r<<16) + c;
      if( r<0 || r>= R || c<0||c >= C || grid[r][c] == '0' || set.contains(key)) return;
      q.offer(key);
  }
*/
  
  /*
  //DFS 
  public int numIslands(char[][] grid){
      int R= grid.length;
      if( R == 0) return 0;
      int C = grid[0].length;
      int count =0;

      for( int i = 0; i<R; i++){
          for(int j = 0; j<C; j++){
              if( grid[i][j] == '1'){
                  count ++; //at entering time
                  dfs(i,j, grid, R, C);
              }
          }
      }  
      return count;
  }
  public void dfs(int r, int c, char[][] grid,  int R, int C){
      if( r< 0 || c<0|| r>= R|| c>=C || grid[r][c] == '0') return; 
      grid[r][c] = '0';
      dfs(r-1, c, grid, R, C);
      dfs(r+1, c, grid, R, C);
      dfs(r, c-1, grid, R, C);
      dfs(r, c+1, grid, R, C);
  } 
  */
  
  //Union find可以求得group的个数和每个group包含哪些index
  //如果更新matrix里面的element，可以直接找到group删除/插入那个element
  Map<Integer, Set<Integer>> map = new HashMap<>(); 
  public int numIslands(char[][] grid){
      int R = grid.length;
      if( R == 0) return 0;
      int C = grid[0].length;
      int count = 0;
      int bitmask = 0xFFFF;
      for(int i = 0; i<R; i++){
          for(int j = 0; j<C; j++){
              if( grid[i][j] == '1'){
                  // we only check the up and left 1. because they have been visited
                  int key = (i<<16) + j;
                  Set<Integer> kset = new HashSet<>();
                  kset.add(key);
                  map.put(key, kset);
                  int lkey = -1;
                  if( (lkey = checkLU(i, j-1, R, C, grid)) != -1 ){
                      union(lkey, key);
                  }
                  int ukey = -1;
                  if( (ukey = checkLU(i-1, j, R, C, grid)) != -1 ){
                      union(key, ukey);
                  }
              }
          }
      }
      return map.size();
  }
  public int find(int key){
      //find which group it belongs to
      for(Map.Entry<Integer, Set<Integer>> entry : map.entrySet()){
          if((entry.getValue()).contains(key)){
              return entry.getKey();
          }
      }
      return -1;
  }
  public void union(int p, int q){
      int pr = find(p);
      int qr = find(q);
      if(pr == qr) return;
      //put q's group elements to p' group
      Set<Integer> s1 = map.get(pr);
      Set<Integer> s2 = map.get(qr);
      s1.addAll(s2);
      map.put(pr, s1);
      map.remove(qr);
  }
  public int checkLU(int r, int c, int R, int C, char[][] grid){
      if(r<0||c<0|| r>=R || c>=C || grid[r][c] != '1') return -1;
      return ((r<<16)+c);
  }
}


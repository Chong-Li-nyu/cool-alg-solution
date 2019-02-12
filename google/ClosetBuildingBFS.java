package google;
import java.util.*;
public class ClosetBuildingBFS {

  /**
   * 0是空地，1是现有的buildings,2是障碍物，找出一个0，修building，要求到所有的buildings距离之和最近
   * 
   * 从所有的1 出发，BFS，更新每一个0的distance sum，queue里面只保留0，
   * 
   * 1 0
   * 0 1
   * 同时因为是在矩阵，add twice but only visit once
   * 
   * 
   * 1 0 0 1 0
   * 只考虑能够被所有1 reachable的0，所以需要计数数组，记录被访问的次数，如果不存在这样的0，返回-1
   * 
   * 
   */
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n =0;
        if (m!=0)
         n = grid[0].length;
        int[][] count = new int[m][n];
        int[][] dist = new int[m][n];
        int oneCount = 0;
        for(int i = 0; i<m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    oneCount++;
                    int key = i * (m+n) + j;
                    
                    Queue<Integer> q = new LinkedList<>();
                    Set<Integer> set = new HashSet<>();
                    q.offer(key);
                    int level = -1;
                    while(!q.isEmpty()){
                        int size = q.size();
                        level ++; //starting from layer 0 also as the distance.
                        for(int k = 0 ; k<size; k++){
                            int pop = q.poll();
                            if(  set.contains(pop) ) continue;
                            
                            set.add(pop); //visited pop

                            int r = pop / (m+n);
                            int c = pop % (m+n);
                            count[r][c] ++;
                            dist[r][c] += level;
                            offerValidZeros(r-1,c , q, m, n, set, grid);
                            offerValidZeros(r+1,c , q, m, n, set, grid);
                            offerValidZeros(r,c-1 , q, m, n, set, grid);
                            offerValidZeros(r,c+1 , q, m, n, set, grid);

                        }
                    }  
                }
            }
        }
        // check the smallest dist zero in dist array
        int ret = Integer.MAX_VALUE;
        for(int i =0;i<m;i++){
            for(int j = 0; j<n;j++){
                if( grid[i][j] == 0 && count[i][j] == oneCount){
                    ret = Math.min(ret, dist[i][j]);
                }
            }
        }
        return ret==Integer.MAX_VALUE? -1 : ret;
    }
    
    public void offerValidZeros( int r, int c, Queue<Integer> q, int m, int n, Set<Integer> set, int[][] grid){
        int key = r*(m+n) + c;
        if( r < 0 || c< 0||r >= m || c>= n || grid[r][c] != 0 || set.contains(key)) return  ;
        q.offer( key );
    }
}

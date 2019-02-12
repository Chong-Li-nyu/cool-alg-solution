package google;
import java.util.*;
public class ShortestDistanceToAllBuildings {
  public static void main(String[] args) {
    int[][] grid = new int[][]{
      new int[]{1,0,2,0,1},new int[]{0,0,0,0,0},new int[]{0,0,1,0,0}
    };
    ShortestDistanceToAllBuildings ins = new ShortestDistanceToAllBuildings();
    int res = ins.shortestDistance(grid);
    System.out.println(res);
  }
  public Set<Integer> countOnes = new HashSet<>();
  public int factor = 0;
  public int shortestDistance(int[][] grid) {
    int R = grid.length;
    if(R==0) return -1;
    int C = grid[0].length;

    int[][] dist = new int[R][C];
    int[][] touch = new int[R][C];
    factor = Math.max(R,C);
    int ret = Integer.MAX_VALUE;
    List<int[]> buildings = new ArrayList<>();
    for(int i = 0; i<R; i++)
        for(int j=0; j<C; j++)
            if(grid[i][j] == 1)
                buildings.add(new int[]{i,j});
    
    for(int i = 0; i<buildings.size(); i++){
        
        int[] b= buildings.get(i);
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer( b[0]*factor + b[1] );
        countOnes.clear();
        countOnes.add(b[0]*factor + b[1]);
        int level = -1;
        
        while(!q.isEmpty()){
            int size = q.size();
            level++; //start from 0
            for(int j = 0; j<size; j++){
                int curr = q.poll();
                int r = curr/factor, c = curr%factor;
                if(visited.contains(curr)) continue;
                visited.add(curr);
                if(level > 0){
                    touch[r][c] ++;
                    dist[r][c] += level;
                    if( i == buildings.size()-1 && touch[r][c]==buildings.size() ){
                        ret = Math.min(ret, dist[r][c]);
                    }
                } 
                checkOffer(r+1, c, R, C,grid, q, visited, level+1);
                checkOffer(r-1, c, R, C,grid, q, visited, level+1);
                checkOffer(r, c+1, R, C,grid, q, visited, level+1);
                checkOffer(r, c-1, R, C,grid, q, visited, level+1);
            }
        }
        if(countOnes.size()!= buildings.size()) return -1;
    }
    return ret==Integer.MAX_VALUE? -1: ret;
}
    public void checkOffer(int r, int c, int R, int C,int[][] grid, Queue<Integer> q, Set<Integer> visited, int level){
        int next = r * factor + c;
        if(r<0||r>=R || c<0|| c>= C|| visited.contains(next) || grid[r][c] == 2) return;
        if(grid[r][c] == 1 ) {
            if (level > 1 && !countOnes.contains(next)){
                countOnes.add(next);
                visited.add(next);
                return;  
            }
        }else
            q.add(next);
    }

}

package DFS;
import java.util.*;
public class LongestIncreasingPathInMatrix {
  public static void main(String[] args) {
    LongestIncreasingPathInMatrix ins = new LongestIncreasingPathInMatrix();
    int[][] matrix =   
        new int[][] { 
          new int[]{9,9,4},
          new int[]{6,6,8},
          new int[]{2,1,1}
    };
    List<Integer> path = ins.longestIncreasingPathRecovery(matrix);
    System.out.println(path);
  }
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int R = matrix.length;
        int C = 0;
        if(R > 0) C = matrix[0].length;
        
        int[][] cache = new int[R][C];
        int ret = 1;
        for(int i = 0; i<R; i++){
            for(int j = 0; j< C; j++){
                ret = Math.max(ret , dfs(i, j, /*prev*/-1, R, C, matrix, cache));
            }
        }
        return ret;
    }
    public List<Integer> longestIncreasingPathRecovery(int[][] matrix) {
      if(matrix.length == 0) return null;
      int R = matrix.length;
      int C = 0;
      if(R > 0) C = matrix[0].length;
      
      ArrayList<Integer>[][] cache = new ArrayList[R][C];
      List<Integer> ret = new ArrayList<>();
      for(int i = 0; i<R; i++){
          for(int j = 0; j< C; j++){
            List<Integer> newPath = dfsWithPath(i, j, /*prev*/-1, R, C, matrix, cache);
            if( ret.size() < newPath.size()  )
              ret = newPath;
          }
      }
      return ret;
  }
    public int dfs(int r, int c, int prev, int R, int C, int[][] matrix, int[][] cache){
        if(r<0|| c<0|| r>= R||c>=C) return 0;
        
        if(matrix[r][c] <= prev) return 0; 
        
        if(cache[r][c] != 0 ) return cache[r][c];
        prev = matrix[r][c]; //first element prev is MIN_VALUE
        int up = dfs(r+1, c, prev, R, C, matrix, cache);
        int down = dfs(r-1, c, prev, R, C, matrix, cache);
        int left = dfs(r, c-1, prev, R, C, matrix, cache);
        int right = dfs(r, c+1, prev, R, C, matrix, cache);

        int result = 1 + Math.max( up, Math.max(down, Math.max(left, right)) );
        cache[r][c] = result;
        return result;
    }
    
    public List<Integer> dfsWithPath(int r, int c , int prev, int R, int C, int[][] matrix, List<Integer>[][] cache){
      List<Integer> result = new ArrayList<>();
      if(r<0|| c<0|| r>= R||c>=C) return result; 
      
      if(matrix[r][c] <= prev) return  result; 
      
      if(cache[r][c] != null ) return cache[r][c];
      
      prev = matrix[r][c];
      
      List<Integer> up = dfsWithPath(r-1, c, prev, R, C, matrix, cache);
      List<Integer> down = dfsWithPath(r+1, c, prev, R, C, matrix, cache);
      List<Integer> left = dfsWithPath(r, c-1, prev, R, C, matrix, cache);
      List<Integer> right = dfsWithPath(r, c+1, prev, R, C, matrix, cache);
      
      result.add(matrix[r][c]);
      List<Integer> maxList = compareList(up, compareList(down, compareList(left, right)));
      
      result.addAll(maxList);
      return result;
    }
    public List<Integer> compareList(List<Integer> l1, List<Integer> l2){
      if( l1.size() > l2.size()) {
        return l1;
      }else {
        return l2;
      }
    }
    
    
    
    
}
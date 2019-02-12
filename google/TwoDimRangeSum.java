package google;
/**
 * 二维矩阵的query sum
 * 向两个方向传播，两层循环
 * 
 */
public class TwoDimRangeSum {
  int R;
  int C;
  int[][] mat;
  int[][] P;
  public static void main(String[] args) {
    int[][] matrix = new int[][] {
      {3,0,1,4,2},
      {5,6,3,2,1},
      {1,2,0,1,5}
    };
    TwoDimRangeSum ins =  new TwoDimRangeSum(matrix);
    int res = ins.sumRegion(0,1,2,3);
    System.out.println(res);
  }
  public TwoDimRangeSum(int[][] matrix) {
    R = matrix.length;
    C = 0;
    if( R != 0) 
      C=matrix[0].length;
    mat = matrix;
    P = new int[R+1][C+1];
    for(int i = 0; i<R;i++) {
      for(int j = 0; j<C; j++) {
        updateDelta(i+1, j+1, matrix[i][j]); //call with plus 1
      }
    }
  }
  public void  update (int row, int col, int val) {
    int delta = val - mat[row][col];
    updateDelta(row+1, col+1, delta);
    mat[row][col] = val;
  }
  
  public int sumRegion(int row1, int col1, int row2, int col2) {
    return querySum(row2+1, col2+1) - querySum(row1, col2+1) - 
        querySum(row2+1, col1) + querySum(row1, col1);
  }
  
  public void updateDelta(int row , int col, int delta) {
    for(int i = row; i<= R; i+=lowbit(i)) {
      for(int j = col; j<=C; j+= lowbit(j)) {
        P[i][j] += delta;
      }
    }
  }
  public int querySum(int row, int col) {
    int sum = 0;
    for( int i = row; i>=1; i-=lowbit(i)) {
      for( int j= col; j>=1; j -= lowbit(j)) {
        sum+=P[i][j];
      }
    }
    return sum;
  }
  public int lowbit(int m) {
    return m&(-m);
  }
}

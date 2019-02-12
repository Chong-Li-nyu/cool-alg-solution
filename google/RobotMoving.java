package google;

import java.awt.Robot;

public class RobotMoving {
  int dir;
  int r;
  int c;
  int h;
  int w;
  int hits = 0;
  boolean[][] cleaned;
  int[][] board;
  final int[][] dirs = new int[][] {  {0,1}, {1, 0}, {0,-1}, {-1,0} };
  //RDLU
  public RobotMoving() {
    dir = 0;
    r = 0;
    c = 0;
  }
  public RobotMoving(int r, int c, int dir, int h, int w) {
    this.r = r;
    this.c = c;
    this.dir = dir;
    this.h = h;
    this.w = w;
    cleaned = new boolean[h][w];
    board = new int[h][w];
  }
  
  public boolean move(boolean back) {
    //move a step in dir
    int nextr = r + dirs[dir][0];
    int nextc = c + dirs[dir][1];
    if(nextr < 0||nextc < 0|| nextr >= h|| nextc >= w || (!back && cleaned[nextr][nextc] )) {
      return false;
    }

    r = nextr; 
    c = nextc;
    return true;

  }
  public void turnLeft(int k) {
    // turn left k times
    dir = dir - k;
    dir %= 4;
    if( dir<0 ) dir += 4; 
  }
  public void turnRight(int k) {
    dir = dir + k;
    dir %= 4;
  }
  // from its current position, clean the board and return the start position
  void dfs() {
    cleaned[r][c] = true;
    hits++;
    for(int i = 0; i<4; i++) {
      turnRight(i);
      boolean tryMove = move(false);
      if(!tryMove) {
        turnLeft(i);
        continue;
      }
      //reach next position
      dfs();
      turnRight(2);
      move(true/*back*/);
      turnLeft(i + 2);
    }
  }
  public static void main(String[] args) {
    RobotMoving ins = new RobotMoving(0,0, 0/*dir*/, 5, 5);
    ins.dfs();
    System.out.println(ins.hits);
  }
}

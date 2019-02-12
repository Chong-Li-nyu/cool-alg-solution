package amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ShortestPath {
    int shortestPath(int[][] board){
        // 给定int[row][col]
        // 左上角为起点，一定为1. 矩阵里面某个位置有一个9。 求1走到9 的最短路径长度。 走的规则是 遇到0，就断了，遇到1可以继续走。
        int row = board.length;
        int col = board[0].length;
        int factor = Math.max(row, col) + 1;

//        int code = row * Math.max(row, col) + col;
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> toBeVisited = new HashSet<>();
        // 已经在队列中将要被polled访问的(或者已经访问过的，因为不删除set里面的entry)，只需要记录(x<<10)+y; 而queue则不用，存取int[]
        // 和二分图中，保证每条边的的节点分别属于两个group，group内的没有edge。在加入队列的时候涂上反色，涂过正确反色的可以忽略
        int[] start = new int[]{0,0};
        q.offer(start);
        toBeVisited.add(code(start));
        int length = 1;
        int layer = 0;

        int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
        while(!q.isEmpty()){
            length = q.size();
            for(int i = 0; i < length; i++){
                int[] point = q.poll();
                if (board[point[0]][point[1]] == 9) return layer;
                for(int[] xy : dirs){
                    int newx = point[0] + xy[0];
                    int newy = point[1] + xy[1];
                    int[] newpoint = new int[]{newx, newy};

                    if(newx < 0 || newy < 0 || newx >= row || newy >= col || board[newx][newy] == 0) continue;
                    if (toBeVisited.contains(code(newpoint))) continue;
                    // if(board[newx][newy] == 1 or 9
                    q.offer(newpoint);
                    toBeVisited.add(code(point));
                }
            }
            layer++;
        }
        return -1;
    }

    public int code(int[] point){
        return (point[0] << 15) + point[1];
    }

    public static void main(String[] args) {
        ShortestPath ins = new ShortestPath();
        int[][] board = new int[][]{
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {0, 1, 1, 1},
                {9, 1, 1, 0}
        };
        System.out.println(ins.shortestPath(board));
    }

}

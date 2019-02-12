package amazon;

import java.util.*;

public class DistanceToZero {
    public List<List<Integer>> distanceToZero( List<List<Integer>> points, int n ){
        List<List<Integer>> ret = new ArrayList<>();
        TreeSet<Location> ts = new TreeSet<>();

        for (List<Integer> point: points){
            int x = point.get(0);
            int y = point.get(1);
            int sq_dist = x*x + y*y;
            ts.add(new Location(point, sq_dist));
        }
        for(int i = 0; i< n; i++){
            Location loc = ts.pollFirst();
            ret.add(loc.location);
        }
        return ret;
    }
    public List<List<Integer>> distanceToZero2( List<List<Integer>> points, int n ){
        List<List<Integer>> ret = new ArrayList<>();
        PriorityQueue<Location> pq = new PriorityQueue<>();

        for (List<Integer> point: points){
            int x = point.get(0);
            int y = point.get(1);
            int sq_dist = x*x + y*y;
            pq.offer(new Location(point, sq_dist));
        }
        for(int i = 0; i< n; i++){
            Location loc = pq.poll();
            ret.add(loc.location);
        }
        return ret;
    }


    public static void main(String[] args) {
        DistanceToZero dtz = new DistanceToZero();
        Integer[][] ps = new Integer[][]{{0,1},{1,2}, {2,1}, {2, -1}};
        List<List<Integer>> points = new ArrayList<List<Integer>>();
        for(Integer[] p: ps){
            points.add(Arrays.asList(p));
        }
        List<List<Integer>> ret  = dtz.distanceToZero2(points, 3);
        System.out.println(ret);
    }
    class Location implements Comparable<Location>{
        List<Integer> location;
        int dist;

        public Location(List<Integer> location, int dist){
            this.location = location;
            this.dist = dist;
        }
        @Override
        public int compareTo(Location other){
//            if (this.dist == other.dist){
//                return -1;
//            }
            return this.dist - other.dist;
        }
    }
}

package intervals;

import java.util.*;

//考虑到ds既要能够提供max，又能删除特定的key，treeMap<h, 出现times>，访问顺序由排序的event控制
public class Skyline {
    public List<int[]> getSkyline(int[][] buildings) {
        //扫描线算法，每个building收集entrance和exit的Event, Events 放在Data structure中排序，
        //按顺序逐个处理每个Event，并考虑是否收集当前的Event.x, 和either ds.max or e.h
        // ds既要能够提供max，又能删除特定的key，treeMap<h, 出现times>访问顺序由排序的event控制
        // 所有的Event L的h都要被加入到ds中去，作为可能出现的结果；
        // 所有的Event R的h都要从ds中移除（移除特定的值）
        // L : if(e.h > ds.max()) ret.add(new int[]{e.x, e.h});
        // ds.add(e.h);
        // R : ds.remove(e.h);
        // if (e.h > ds.max()) ret.add(new int[]{e.x, ds.max()});
        List<Event> le = new ArrayList<>();
        for (int[] building: buildings){
            int h = building[2];
            le.add(new Event('L', building[0], h));
            le.add(new Event('R', building[1], h));
        }
        Collections.sort(le);
        List<int[]> ret = new ArrayList<>();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (Event e: le){
            if(e.type == 'L'){
                if (e.h > (tm.isEmpty() ? 0 : tm.lastKey()))
                    ret.add(new int[]{e.x, e.h});
                if (tm.containsKey(e.h)) {tm.put(e.h, tm.get(e.h) + 1);}
                else {tm.put(e.h, 1);}
            }
            if(e.type == 'R'){
                if(tm.get(e.h) - 1 == 0) tm.remove(e.h);
                else tm.put(e.h, tm.get(e.h) - 1);
                if (e.h > (tm.isEmpty() ? 0 : tm.lastKey()))
                    ret.add(new int[]{e.x, (tm.isEmpty() ? 0 : tm.lastKey())});
            }
        }
        return ret;

    }
    //Event 排序的3种特殊情况
    //1. 两个event，L and R with same x, first consider L（entrance event）
    //2. 同为L event，same x，先考虑h高的，只有最高的天际线的左边加入结果
    //3. 同为R event，same x，先考虑h低的
    static class Event implements Comparable<Event>{
        char type; //L or R, L<R
        int x;
        int h;
        @Override
        public int compareTo(Event other){
            if(this.x != other.x) return this.x - other.x;
            else{
                if(this.type != other.type) return (int)(this.type - other.type);
                else{
                    // x same, type same
                    if(this.type == 'L') return other.h - this.h;
                    if(this.type == 'R') return this.h - other.h;
                }
            }
            return 0;
        }
        public Event(char type, int x, int h){
            this.type = type;
            this.x = x;
            this.h = h;
        }
    }
}
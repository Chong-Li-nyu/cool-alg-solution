package DFS;

import java.util.*;
public class FrogJump {
  public static void main(String[] args) {
    int[] stones = {0,1,2,3,4,8,9,11};
    FrogJump ins = new FrogJump();
    System.out.println(ins.canCross(stones));
  }
    public boolean canCross(int[] stones) {
        Map<Integer, Boolean> cache = new HashMap<>();
        Map<Integer, Integer> posMap = new HashMap<>();
        for(int i = 0; i < stones.length; i++){
            posMap.put(stones[i], i);
        }
        return dfs(stones, 0, 0, cache, posMap);
    }
    public boolean dfs(int[] stones, int prevStep, int index, Map<Integer, Boolean> cache, Map<Integer, Integer> posMap){
        //use k jump to jump to stones[index]
        int key = (index << 15) + prevStep;
        if( index == (stones.length - 1) ){
            cache.put(key, true);
            return true;
        } 
        
        if( cache.containsKey(key) ) return cache.get(key);
        int currPos = stones[index];
        int[] nexts = new int[]{-1, 0, 1};
        for (int next: nexts){
            int step = prevStep+next;
            if(step <= 0) continue;
            if(!posMap.containsKey(currPos + step)) continue;
            if(dfs(stones, step, posMap.get(currPos + step), cache, posMap)) {
                cache.put(key, true);
                return true;
            }
        }
        cache.put(key, false);
        return false;
    }
    
    public boolean dfs2(int[] stones, int k, int index, Map<Integer, Boolean> cache){
      //use k jump to jump to stones[index]
      int key = (index << 15) + k;
      if( index == (stones.length - 1) ){
          cache.put(key, true);
          return true;
      } 
      
      if( cache.containsKey(key) ) return cache.get(key);
      int next = Arrays.binarySearch(stones, stones[index] + k+1);
      if( next >= 0 && dfs2(stones, k+1, next, cache)) {
        cache.put(key, true);
        return true;
      }
      next = Arrays.binarySearch(stones, stones[index] + k);
      if( next >= 0 && dfs2(stones, k, next, cache)) {
        cache.put(key, true);
        return true;
      }
      next = Arrays.binarySearch(stones, stones[index] + k-1);
      if( next > index && next >= 0 && dfs2(stones, k-1, next, cache)) {
        cache.put(key, true);
        return true;
      }
      cache.put(key, false);
      return false;
    }
}

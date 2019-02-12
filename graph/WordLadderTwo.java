package graph;
import java.util.*;

class WordLadderTwo{
    public List<List<String>> res = new ArrayList<>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if ( !wordList.contains(endWord)) return res;
        Set<String> set = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        
        Map<String, List<String>> parent = new HashMap<>();
        for(String s: set){
            parent.put(s, new ArrayList<String>());
        }
        Map<String, Integer> dist = new HashMap<>();
        q.offer(beginWord);
        int level = 0;
        boolean found = false;
        while(!q.isEmpty() && !found){
            int count = q.size();
            for(int i=0; i<count; i++){
                String pop = q.poll();
                if(pop.equals(endWord)) {
                  found = true;
                  break; // the endWord layer still exist endWord from other path
                }
                  char[] arr = pop.toCharArray();
                  for(int j = 0; j<arr.length; j++){
                      char oldchar = arr[j];
                      for(char c = 'a'; c<='z'; c++){
                          if(c == arr[j]) continue;
                          arr[j] = c;
                          String next = String.valueOf(arr);
   //当第一次得到next，就设定了最小的level(remove next)，这样在接下来的level碰到next就不用考虑，因为dist.getOrDefault(next, -1) > level
                  if( set.contains(next) || (dist.getOrDefault(next, -1) == level && !parent.get(next).contains(pop))){   
                              set.remove(next);
                              dist.put(next, level);
                              List<String> l = parent.get(next);
                              l.add(pop); 
                              q.offer(next);
                          }
                          arr[j] = oldchar;
                      }
                  }
            }
            level ++;
        }
        dfsPath(endWord, beginWord, parent, new ArrayList<String>(), level);
        return res;
    }

    public void dfsPath(String curr, String beginWord, Map<String, List<String>> parent, List<String> path, int level) {
      List<String> l = new ArrayList<String>(path);
      l.add(0,curr);
      if (l.size() == level) {
        if(curr.equals(beginWord))
          res.add(l);
        return;
      }
      if(!parent.containsKey(curr)) return;
      for(String next : parent.get(curr)) {
        dfsPath(next, beginWord, parent, l, level);
      }
    }
    public boolean isAdj(String w, String adj){
        int count = 0;
        for(int i = 0; i <w.length(); i++){
            if(w.charAt(i) != adj.charAt(i)) count++;
        }
        return count == 1;
    }
  }
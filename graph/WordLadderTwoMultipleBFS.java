package graph;
import java.util.*;
public class WordLadderTwoMultipleBFS {
  public List<List<String>> findLadders(String start, String end, List<String> wordList) {
    Set<String> dict = new HashSet(wordList);
    if (!dict.contains(end)) return new ArrayList();

    Set<String> set1 = new HashSet();
    Set<String> set2 = new HashSet();
    set1.add(start); //one bfs from start, another from end
    set2.add(end);
    
    // we use a map to help construct the final result
    Map<String, List<String>> map = new HashMap();
    // build the map, start from 2 ends to avoid creating unnecessary paths 
    buildMap(dict, set1, set2, map, false);
    List<List<String>> res = new ArrayList();
    List<String> sol = new ArrayList(Arrays.asList(start));
    // recursively build the final result
    generateList(start, end, map, sol, res);
    return res;
}

boolean buildMap(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
    if (set1.isEmpty()) 
        return false;
    if (set1.size() > set2.size()) 
        // make sure set1 is always smaller than  or equal to set2 
        return buildMap(dict, set2, set1, map, !flip);
    // remove words on current both ends from the dict
    dict.removeAll(set1);
    dict.removeAll(set2);

    boolean done = false;
    // set for the next level, no need to consider those nodes that have been processed already. 
    Set<String> nextLevelSet = new HashSet();
    // for each string in set1 (smaller set)
    for (String str : set1) {
        for (int i = 0; i < str.length(); i++) {
            char[] chars = str.toCharArray();
            // change one character for every position, O(26) time
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chars[i] = ch;
                String newWord = new String(chars);
                // make sure we construct the tree in the correct direction
                // flip false means start->end
                // flip true means end->start
                String key = flip ? newWord : str;
                String val = flip ? str : newWord; 
                List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList();   
                if (set2.contains(newWord)) {
                    done = true; 
                    list.add(val);
                    map.put(key, list);
                } 
                if (!done && dict.contains(newWord)) {
                    nextLevelSet.add(newWord);
                    list.add(val);
                    map.put(key, list);
                }
            }
        }
    }
    // early terminate if done is true
    return done || buildMap(dict, set2, nextLevelSet, map, !flip);
}

// backtracking 
void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
    if (start.equals(end)) {
        res.add(new ArrayList(sol));
        return;
    }
    // need this check in case the diff between start and end happens to be one
    // e.g "a", "c", {"a", "b", "c"}
    if (!map.containsKey(start)) return;
    for (String word : map.get(start)) {
        sol.add(word);
        generateList(word, end, map, sol, res);
        sol.remove(sol.size() - 1);
    }
}
}

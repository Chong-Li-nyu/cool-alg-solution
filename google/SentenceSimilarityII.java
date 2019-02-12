package google;
import java.util.*;
// union-find dealing with the transitive of similar words
// return find(map.get(key))
public class SentenceSimilarityII {
  public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
    if(words1.length != words2.length ) return false;
    Map<String, String> map = new HashMap<>();
    for(String[] p : pairs){
        String r1 = find(map, p[0]), r2 = find(map, p[1]);
        if( !r1.equals(r2) ) {
            map.put(r1, r2); //union r1 and r2
        }
        
    }
    for(int i = 0;i<words1.length; i++){
        if(words1[i].equals(words2[i])) continue;
        if( !find(map, words1[i]).equals(find(map, words2[i])) ) return false;
    }
    return true;
}
// make put and find operation together. initiate the s[] in one loop
public String find(Map<String, String> map , String key){
    if( !map.containsKey(key) ) {
        map.put(key, key); //initiate the root as itself
        return key;
    }else{
        if( key.equals(map.get(key)) ) return map.get(key);
        else return find(map, map.get(key)) ;  //find(p) until map.get(p) == p  
    }
}
}

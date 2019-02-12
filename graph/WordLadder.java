package graph;

import java.util.*;

public class WordLadder {
  
  public static void main(String[] args) {
    WordLadder ins = new WordLadder();
    String beginWord = "hit";
    String endWord = "cog";
    String[] words =new String[] { "hot","dot","dog","lot","log","cog"};
    List<String> wordList = Arrays.asList(words);
    int result = ins.ladderLength(beginWord, endWord, wordList);
    System.out.println(result);
  }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // use level order traversal
        if(!wordList.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();

        Set<String> wordSet = new HashSet<String>(wordList);
        
        int count=0;
        q.offer(beginWord);
        while(!q.isEmpty()){
            ++count;
            int size = q.size();
            

            for(int i = 0; i<size; i++){
                String w = q.poll();
                if(w.equals(endWord)) return count;
                char[] arr = w.toCharArray();
                for(int k = 0; k<w.length(); k++){
                    char hold = arr[k];
                    for(char c = 'a'; c<='z'; c++){
                        if(c!= hold){
                          arr[k] = c;
                          String next = String.valueOf(arr);
                          if( wordSet.contains(next) ){
                            wordSet.remove(next);
                            q.offer(next);
                          }
                        }
                    }
                    arr[k] = hold;
                }
                
            }
            
        }
        return 0;
    }
}

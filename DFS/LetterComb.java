package DFS;
import java.util.*;

public class LetterComb {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0 ) return new ArrayList<String>();
        ArrayList<String>[] map = new ArrayList[10];
        for(int i = 2; i<10; i++){
            map[i] = new ArrayList<String>();
        }
        int index = 2;
        char c= 'a';
        while( index <= 9){
            int count = 3;
            if(index == 7 || index == 9) count = 4;
            for(int i = 0; i<count ;i++){
                map[index].add( String.valueOf(c++) );
            }
            index ++;
        }
        char[] charr = digits.toCharArray();
        int[] arr = new int[digits.length()];
        for(int i = 0; i< arr.length; i++){
            arr[i] = charr[i]-'0';
        }
        return dfs(0, arr, map);
    }
    public List<String> dfs(int index, int[] arr, ArrayList<String>[] map){
        List<String> result = new ArrayList<String>();

        if(index == arr.length -1){
            return map[arr[index]];
        }
        int number = arr[index];
        List<String>  l = map[number];
        List<String> resNext = dfs(index+1, arr, map); 

            for(String prev: l){
                for(String s: resNext){
                    result.add(prev + s);
                }
            }
        
        return result;
    }
}
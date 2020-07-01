class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (s1,s2) -> s1.length() - s2.length());
        
        Map<String, Integer> dp = new HashMap<>();
        int ret = 0;
        for(String w: words){
            dp.put(w, 1); //intial value can be 1
            for(int i = 0; i < w.length(); i++){ //remove charAt(i)
                String s = w.substring(0,i) + w.substring(i+1, w.length());
                if(dp.containsKey(s)){
                    dp.put(w, Math.max(dp.get(w), dp.get(s) + 1));
                }
            }
            ret = Math.max(ret, dp.get(w));
        }
        return ret;
    }
}

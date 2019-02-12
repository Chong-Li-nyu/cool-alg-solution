package slidingwindow;

class MinimumSlidingWindow {
    public String minWindow(String s, String t) {
        //返回from position and len
        //t: count = 0, count from t.length() to 0
        //j: left pointer, i: right pointer
        //i stops at possible solution to find best j solution by checking count == 0 when j++
        //sliding window的关键在于如何在找到possible solution()
        //再次出发后续的搜索，所以要尝试手动打破满足的条件，将左指针j手动往右移动,移除这个左边的char
        //但是只有在(++map[s.charAt(j++)] > 0)才count++
        //tmap = new int[128], 记录所有的t中char的个数
        int count = t.length();
        int[] tmap = new int[128];
        for(char c: t.toCharArray()){
            tmap[c]++;
        }

        int minLen = Integer.MAX_VALUE;
        int from = 0;
        for(int i = 0, j = 0; i < s.length(); i++){
            //try to find char in t and decrease count to 0
            //0 to -1 doesn't make count--, because it add redundant char
            if (--tmap[s.charAt(i)] >= 0) count--;
            while (count == 0){
                if(i - j + 1 < minLen) {
                    minLen = i-j+1;
                    from = j; //keep j as possible solution
                }
                //try to break or optimize left pointer j
                // updated map[j] > 0, then we need to add back count
                if (++tmap[s.charAt(j++)] > 0) count++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(from, from + minLen);
    }

    public static void main(String[] args) {
        MinimumSlidingWindow ins = new MinimumSlidingWindow();
        String ret = ins.minWindow("ADOBECODEBANC",
                "ABC");
        System.out.println(ret);
    }
}

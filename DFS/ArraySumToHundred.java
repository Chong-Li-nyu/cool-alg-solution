package DFS;
import java.util.*;
public class ArraySumToHundred {
// given 123..9 , 返回所有表达式，结果为100
  public static void main(String[] args) {
    ArraySumToHundred ins = new ArraySumToHundred();
    List<String> result3 = ins.sumToOneHundread("123456789", 100);
    List<String> result2 = ins.dfs2("123456789", 100);
    List<String> test = new ArrayList<>();
    System.out.println(test==null);
    String t = "ha";
    System.out.println(t.substring(2).equals(""));
    System.out.println(result2);
    System.out.println(result3);
  }
  public List<String> result = new ArrayList<>();
  public List<String> sumToOneHundread(String s, int dsum) {
    dfs(0,0,"",s, dsum);
    return result;
//    return dfs2(s, dsum);
  }
  //子问题是，prev取多少位，决定了下一个index
  //而且每个子问题和prev 的关系可以是+/-
  //include the state in dfs signature, when ending at leaf node and state is valid
  //change the result
  //保存中间状态，知道最后再决定输不输出
  public void dfs(int index, int currval, String currexp, String s, int dsum) {
    if (index == s.length()) {
      if(currval == dsum) {
        result.add(currexp);
      }
      return;
    }
    for( int i = index+1; i<=s.length(); i++) {
      int prev = Integer.valueOf(s.substring(index, i) );
      //第一个数不能为-的bug
      if(currexp.equals("")) {
        dfs(i, currval + prev, s.substring(index, i), s, dsum);
      }else {
        dfs(i, currval + prev, currexp +"+"+s.substring(index, i), s, dsum);
        dfs(i, currval - prev, currexp +"-"+s.substring(index, i), s, dsum);
      }
    }
  }
  //返回之后的表达式，以及value，把问题直接交个后面的递归，这一层只是简单的考虑一个值
  
  public List<String> dfs2( String s, int dsum){
    List<String> result = new ArrayList<>();
    if( s.equals("")) {
      if(dsum == 0) {
        return result;
      }else 
        return null;
    }

    // keep at least one char for prev, when prev == "" ,it has been check by the previous part
    for(int i = 1; i<= s.length(); i++) {
      String prev = s.substring(0,i); // prev  at least one char, can be whole string s
      int prevVal = Integer.valueOf(prev); 
      String tail = s.substring(i); //tail can be empty
      if(tail.equals("")) {
        List<String> resNext = dfs2(tail, dsum - prevVal);
        if(resNext!=null) {
          result.add(prev);
        }
        continue;
      }
      List<String> resPlus = dfs2(tail, dsum - prevVal); //prev + tail = dsum
      List<String> resMinus = dfs2(tail, prevVal - dsum); //prev - tail = dsum
      if(resPlus!=null) {
        for(String item : resPlus) {
          result.add(prev +"+"+ item);
        }
      }
      if(resMinus != null) {
        for(String item : resMinus) {
          result.add(prev +"-"+ item);
        }
      }
    }
    return result;
  }

}

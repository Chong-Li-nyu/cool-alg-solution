package google;

import java.util.*;
/**
 * 
 * @author chong
 * e.g.
 * a(b(c){2}){2}d-> a(cc){2}
 */
public class DecodeStringCompression {
  public static void main(String[] args) {
    DecodeStringCompression ins = new DecodeStringCompression();
    String res = ins.decode("a(b(c){2}){2}d");
    System.out.println(res);
  }
  public String decode(String comp) {
    char arr[] = comp.toCharArray();
    Stack<Character> st = new Stack<>();
    int num = 0;
    for(int i = 0; i< arr.length; i++) {
      char c = arr[i];
      if( Character.isDigit(c)) {
        num = num*10+ (c-'0');
      }else if(Character.isLetter(c) || c == '(' || c== ')') {
        st.push(c);
      }else if( c == '{'){
        num = 0; //reset the num
      }else if(c =='}') {
        //pop a pair of () and times num
//        num * chars in ()
        times(st, num);
      }
    }
    StringBuilder sb = new StringBuilder();
    while(!st.isEmpty())
      sb.insert(0, st.pop());
    return sb.toString();
  }
  
  public void times(Stack<Character> st, int num) {
    List<Character> list= new ArrayList<>();
    st.pop(); //pop )
    while( !st.isEmpty() &&  st.peek() !=  '(') {
      list.add(0, st.pop());
    }
    st.pop(); // pop (
    for(int i = 0; i<num; i++) {
      st.addAll(list);
    }
  }
  /*
  public String decode(String comp) {

    Stack<String> st = new Stack<>();
    int i = 0;
    while(i < comp.length()) {
      char c = comp.charAt(i);
      if( c == '{' ) {
        int end = comp.indexOf("}", i); //definitely find }
        int len = Integer.valueOf( comp.substring(i+1, end ) );
        String pop = st.pop();
        
        StringBuilder sb = new StringBuilder();
        
        for(int k = 0; k<len; k++)
            sb.append(pop);
        st.push(sb.toString());
        
        i = end + 1;
      }else {
        int nextBracket = comp.indexOf('(', i);
        String letters = comp.substring(i, nextBracket) ;
        i = nextBracket + 1;
        if(!letters.equals(""))
          st.push( letters);
//        st.push("("); //don't need explicitly insert, because already separate
      }
    }
    */
}

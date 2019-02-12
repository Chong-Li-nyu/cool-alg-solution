package stack;

import java.util.*;
public class Calculator {
    public int calculate(String s) {
        //先操作所有的 乘除操作，再全部加起来
        //使用stack的时候，都会有一个imaginary entry被首先放到stack中
        //比如maximum histogram中，先放一个-1进去
        int num = 0;
        char sign = '+'; //imaginary sign，决定了第一个操作数一定是push to stack
        s = s.replace(" ","");
        s = s + '^';
        Stack<Integer> st = new Stack<>();
        //两个sign中间的数能够确定，当第二个sign出现时，
        //第一个sign决定了这个数是入stack，还是作为第二个操作数与还是于stack里面的数求值
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = 10*num + (c-'0');
            } else{ // the ending mark is for trigger this else branch
                if(sign == '+'){
                    st.push(num);
                } else if(sign == '-'){
                    st.push(-num);
                } else if(sign == '*'){// * num anotherSign
                    st.push(st.pop() * num);
                } else if(sign == '/'){
                    st.push(st.pop() / num);
                }
                sign = c;
                num = 0;
            }
        }
        int ret = 0;
        while(!st.isEmpty()){
            ret += st.pop();
        }
        return ret;
    }

}
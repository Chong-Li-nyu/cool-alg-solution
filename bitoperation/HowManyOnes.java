package bitoperation;

public class HowManyOnes {
  public static void main(String[] args) {
    HowManyOnes h = new HowManyOnes();
//    h.shiftRight(0);
    int res = h.findOnes(0xF); //
    System.out.println(res);
  }
  public int findOnes(int num) {
    // given 7, return 2, because 2^3 - 2^0 = 7, two numbers(both power of 2)
    // return how many (power of 2) in binary representation? But allow minus
    // 把num向右移动，if(end with 0) go on
    // else 1.end with 11, +1, and shift two bit go on
          //2.end with 01, then -1, shift two bit
    // return the count of +1 and -1 operation
    //当需要用二进制考虑数字的时候，向右移动
    int count = 0;
    while( num!= 0 ) {
      if( (num & 1) == 0) {
        //end with 0
        num >>= 1;
      }else {
        if( (num & 3) == 3) { //end with 11
          num+=1;
        }else if( (num & 1) == 1) { //end with 01
            num -=1;
        }
        count++;
        num >>=2; //if no more bit to shift right?
      }
    }
    return count;
  }
  public void shiftRight(int num) {
    
    num >>=2;
    System.out.println(num);
  }
}

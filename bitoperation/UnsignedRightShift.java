package bitoperation;

public class UnsignedRightShift {
  /*
   *>> and >>>
   * The unsigned right shift operator ">>>" shifts a zero into the leftmost position, while the leftmost 
   * position after ">>" depends on sign extension.
   */
  public static void main(String[] args) {
    int a = -10;
    int fanma = ~10;
    int b = a>>1;
    
    int b2 = a>>>1;
    int b3 = b2<<1;
    
    int c = a<<1;

    
    System.out.printf("a: %d, b: %d \n" ,a, b);
    System.out.println("10 invert: " + fanma);
    System.out.println("b2 unsigned right shift: " + b2);
    System.out.println("b3 restore b2: " + b3);
    
    System.out.println("c: " + c);
    
    
    long l = -120;
    int hashcodeOfL = (int)(l ^ (l>>>32));
    System.out.println("l: "+l);
    System.out.println("l binary: "+Long.toBinaryString(l));
    System.out.println("l hashcode (32bit):" + hashcodeOfL);
    System.out.println("l high 32: "+Long.toBinaryString((l>>>32)));
    System.out.println("l hashcode binary: "+Integer.toBinaryString(hashcodeOfL));
  }
}

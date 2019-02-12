package google;

public class StringInsertAChar {
  public char findInsertedChar(String s, String p) {
    if(s== null || s.length() == 0) return p.charAt(0);
    int l = 0; 
    int r = p.length() - 1;
    while( l< r) {
      int m = l + (r-l)/2;
      //check substring to index m
      if( s.substring(l, m+1).equals(p.substring(l, m+1))) {
        l = m+1; // go to unknow part
      }else {
        r = m; 
      }
    }
    return p.charAt(r);
  }
  public static void main(String[] args) {
    StringInsertAChar ins = new StringInsertAChar();
    String s= "abbbbbbb";
    StringBuilder sb = new StringBuilder(s);
    String p = sb.insert(4, 'x').toString();
    
    System.out.println(ins.findInsertedChar(s, p));
  }
}

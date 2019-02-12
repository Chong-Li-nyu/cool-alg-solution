package google;

public class Fibo {
//  if( n<=1 ) return n;
  public static void main(String[] args) {
    Fibo ins = new Fibo();
    System.out.println(ins.dpFibo(10));
    System.out.println(ins.dpMemFibo(10));
  }

  
  public int dpFibo(int n) {
    if(n<= 1)   return n;
    int[] dp = new int[n+1];
    dp[0] = 0; dp[1] = 1;
    for(int i = 2; i<=n ;i++) {
      dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
  }
  public int dpMemFibo(int n) {
    if(n<=1) return n;
    int b1 = 0, b2 = 1; 
    int ans = 1;
    for( int i = 2; i <= n; i++ ) {
      ans = b1+b2;
      b1 = b2;
      b2 = ans;
    }
    return ans;
  }
}

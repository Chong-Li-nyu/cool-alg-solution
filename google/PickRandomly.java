package google;

public class PickRandomly {
  public static void main(String[] args) {
    PickRandomly ins = new PickRandomly();
    int[] arr = new int[] {1,3,4,5};

    for(int i = 0; i<13; i++) {
      int res = ins.pickRandomly(arr, i);
      System.out.print(res+ " ");
    }
  }
  /*
   * given arr [1,3,4,5], write an API return a number in it with probability
   * of value/sum, when return 3, with 3/13 probability
   */
  public int pickRandomly(int[] arr, int Random ) {
    //create a range of 13, each value is represented by a sub-interval
    // given the random number, to see which range it falls
    // represent all range using partial sum
    int n = arr.length;
    int[] partialSum = new int[n+1];
    partialSum[0] = 0; 
    for(int i = 1; i<=n; i++) {
      partialSum[i] = partialSum[i-1] + arr[i-1];
    }
    //[0,1,4,8,13], the number is the upperbound of a range(exclusive)
//    index = 3, [8,13)
    int l = 0; 
    int r = n;
    

    while( r-l > 1) { //should return l = 3, r = 4;
      int mid  = l+ (r-l)/2; //2
      if( partialSum[mid] <=  Random ) {
        l = mid;
      }else {
        r = mid;
      }
    }
    return arr[l];
  }
}

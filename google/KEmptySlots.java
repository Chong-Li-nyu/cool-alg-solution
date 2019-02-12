package google;

public class KEmptySlots {
  public static void main(String[] args) {
    int[] flowers = new int[] {1,3,2};
    int k = 1;
    KEmptySlots ins = new KEmptySlots();
    System.out.println(ins.daySepKSlots(flowers, 3, 1));
  }
  public int daySepKSlots(int[] flowers,int n, int k) {
    int nb = n / (k+1) +1 ;  //because 3/2 = 1, maxindex = 1, so size = 2
    int[][] buckets = new int[nb][2];

    for( int i = 0; i<nb; i++) {
      buckets[i][0] = Integer.MAX_VALUE; //record min flower index
      buckets[i][1] = Integer.MIN_VALUE; 
    }
    for(int i = 0; i < flowers.length; i++) {
      int f =  flowers[i];
      int bind = f /(k+1) ;
      if( f > buckets[bind][0] && f< buckets[bind][1] )
        continue;
      
      if( f < buckets[bind][0]) {
        buckets[bind][0] = f ;
        if( bind >0 && f - buckets[bind-1][1] == k+1 ) {
          return i+1;
        }
      }
      if( f > buckets[bind][1]) {
        buckets[bind][1] = f;
        if( bind < nb-1 && buckets[bind+1][0] - f == k+1) {
          return i+1;
        }
      }
      
    }
    return -1; //cannot get the day
  }
}


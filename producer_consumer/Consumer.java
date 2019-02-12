package producer_consumer;

import java.util.List;

public class Consumer {
    List<Integer> l;
    final int size;
    public Consumer(List<Integer> l, int size){
        this.l = l;
        this.size = size;
    }
    public int get(){
        synchronized (l){
            while(l.isEmpty()){
                try {
                    l.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int ret = l.remove(0);
            System.out.printf("Consumer: %d\n", ret);
            return ret;
        }
    }
}

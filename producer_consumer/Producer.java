package producer_consumer;

import java.util.*;

public class Producer {
    List<Integer> l;
    final int size;
    public Producer(List<Integer> l, int size){
        this.l = l;
        this.size = size;
    }
    public void put(int x) {
        synchronized (l) {
            while (l.size() == size) { ////用while而不用if的原因，这样每个线程在wait等待醒来后都必须再次判断条件
                /* no place to put x */
                try {
                    l.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            l.add(x);
            System.out.printf("Producer: %d\n", x);
            l.notify();  //单独写notify，默认是在this object上面，所以是对l的锁进行判断
        }
    }
}

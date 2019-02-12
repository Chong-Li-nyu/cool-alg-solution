package producer_consumer;
import java.util.*;
public class Pro_Con {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        int size = 3;
        Thread t_pro = new Thread(new Runnable() {
            @Override
            public void run(){
                Producer p = new Producer(l, size);
                while( true ){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    p.put(new Random().nextInt(10));
                }

            }
        });
        t_pro.start();

        Thread t_con = new Thread(new Runnable() {
            @Override
            public void run() {
                Consumer c = new Consumer(l,size);
                while(true){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.get();
                }
            }
        });
        t_con.start();
    }
}

package multithread;
import java.util.*;

/*
What we usually want is a work queue combined with a fixed group of worker threads,
 which uses wait() and notify() to signal waiting threads that new work has arrived.

 Worker thread(PoolWorker here) will wait on the work queue object, and when the workqueue is empty,
 wait in while loop; otherwise call removeFirst() to get a Runnable from the work queue

  The work queue is generally implemented as some sort of linked list with
  an associated monitor object. Listing 1 shows an example of a simple pooled work queue.
   This pattern, using a queue of Runnable objects, is a common convention for schedulers and
   work queues,
although there is no particular need imposed by the Thread API to use the Runnable interface.
 */

public class WorkQueue
{
    private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedList<Runnable> queue;

    public WorkQueue(int nThreads)
    {
        this.nThreads = nThreads;
        queue = new LinkedList();
        threads = new PoolWorker[nThreads];

        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable r) {
        synchronized(queue) {
            queue.addLast(r);
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {
        public void run() {
            Runnable r;

            while (true) {
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        try
                        {
                            queue.wait();
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }

                    r = (Runnable) queue.removeFirst();
                    /*
                    Runnable here can be written as :
                    Runnable runA = new Runnable(){
                        @Override
                        public void run(){
                            // task code
                        }
                    }
                     */

                }

                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    r.run();
                }
                catch (RuntimeException e) {
                    // You might want to log something here
                }
            }
        }
    }
}
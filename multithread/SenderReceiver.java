package multithread;
/*
let’s go through a simple Sender–Receiver application – that will make use of the wait() and notify() methods to set up synchronization between them:
* The Sender is supposed to send a data packet to the Receiver
* The Receiver cannot process the data packet until the Sender is finished sending it
* Similarly, the Sender mustn’t attempt to send another packet unless the Receiver has already processed the previous packet
不要继续发包，如果receiver hasn’t processed 之前的packet
Sender must not send another packet unless the receiver has processed the previous packet
 */
public class SenderReceiver {

    class Data{
        private String packet;
        // the Data instance is a resource, multithread can access this class.
        // True if receiver should wait
        // False if sender should wait
        private boolean transfer = true;

        public synchronized void send(String packet) {
            while (!transfer) {
                try {
                    wait();
                } catch (InterruptedException e)  {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted" + e.getStackTrace());
                }
            }
            transfer = false;

            this.packet = packet; // important STEP, give the packet a value
            this.notifyAll();
        }

        public synchronized String receive() {
            while (transfer) {
                try {
                    this.wait();
                } catch (InterruptedException e)  {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted" + e.getStackTrace());
                }
            }
            transfer = true;

            notifyAll();
            return packet;
        }
    }
}

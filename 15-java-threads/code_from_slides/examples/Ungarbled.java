class Counter {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }
    public synchronized void decrement() {
        count--;
    }
    public synchronized int getCount() {
        return count;
    }
}
public class Ungarbled implements Runnable {
    private static Counter counter = new Counter();
    
    public void run() {
        for(int i=0; i<50000; ++i) {
            if(i%2 == 0) counter.increment();
            else counter.decrement();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for(int i=0; i<10; ++i) {
            threads[i] = new Thread(new Garbled());
            threads[i].start();
        }
        for(int i=0; i<10; ++i) {
            threads[i].join();
        }
        System.out.println("Should be 0: " + counter.getCount());
    }
}

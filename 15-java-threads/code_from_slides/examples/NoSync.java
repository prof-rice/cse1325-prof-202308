public class NoSync implements Runnable {
    private final static int numThreads = 50;
    private final static int numDecrements = 5000;
    private static int counter = numThreads * numDecrements;
    
    // Our thread simply decrements counter numDecrements times
    @Override
    public void run() {
        for(int i=0; i<numDecrements; ++i) {
            --counter;
        }
    }
    
    // Main creates and starts numThreads threads.
    //   When they've all exited, it prints counter.
    public static void main(String[] args) 
            throws InterruptedException {
            
        // Create array to reference the threads
        Thread[] threads = new Thread[numThreads];
        
        // Start a thread for each array element
        for(int i=0; i<numThreads; ++i) {
            threads[i] = new Thread(new NoSync());
            threads[i].start();  // Start decrementing
        }
        
        // Join all threads
        for(int i=0; i<numThreads; ++i)
            threads[i].join();
        
        // Show counter - is it actually 0?
        System.out.println("Should be 0: " + counter);
    }
}

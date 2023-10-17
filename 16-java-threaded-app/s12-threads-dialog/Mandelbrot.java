import complex.Complex;
import java.util.concurrent.locks.ReentrantLock;

public class Mandelbrot {
    public final static int MAX_COLOR = 255;

    public Mandelbrot(BitMap canvas, int width, int height, int icount, int threads) {
        while(running > 0) {
            abort = true; // cancel the current calculations
            try {
                Thread.sleep(100);
            } catch(Exception e) {
            }
        }
        abort = false;

        this.canvas = canvas;
        this.width  = width;
        this.height = height;
        this.ratio  = (double) height / (double) width;
        this.icount = icount;
        this.zoom   = 0.001;   //  0.001
        this.xOffset = -1.786; // -1.786
        this.yOffset = 0;      //  0.0
        this.mutex = new ReentrantLock();
        
        threadManager = new Thread(() -> {calculateImageViaPool(threads);});
        threadManager.start();
    }
    public Mandelbrot(Canvas canvas, int threads) {
        this(canvas, canvas.getWidth(), canvas.getHeight(), 255, threads);
    }
   public Mandelbrot(Canvas canvas) {
        this(canvas, canvas.getWidth(), canvas.getHeight(), 255, 1);
    }
    public void setView(double xOffset, double yOffset, double zoom) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zoom = zoom;
    }
    public void calculateImageViaPool (int numThreads) {
        synchronized (this) {running++;}
        Thread[] threads = new Thread[numThreads];
        for(int i=0; i<numThreads; ++i) {
            Runnable runnable = new Runnable() {
                public void run() {
                    int row = 0;
                    while(!abort) { 
                       // mutex ensures only 1 thread accesses nextY at a time
                       // A ReentrantLock allows the same thread to re-lock it
                       //   as long as it has one unlock for every lock.
                       try {
                            mutex.lock();
                            row = nextY++;
                        } finally {
                            mutex.unlock();
                        }
                        if(row >= height) break;
                        calculateRow(row);
                    }
                }
            };
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Interrupted Exception");
            }
        }
        synchronized (this) {running--;}
    }
    protected void calculateRow   (int y) {
        for(int x=0; x<width; ++x) calculatePoint(x, y);
    }
    protected void calculatePoint (int x, int y) {
        Complex point = new Complex((((double) x / (double) width) - 0.5) * zoom  + xOffset, 
                                    (((double) y / (double) height) - 0.5) * zoom * ratio + yOffset);
        Complex z = new Complex();
        int iterations = 0;
        while(z.abs() < 2.0 && iterations++ <= icount) z = z.times(z).plus(point);
        int color = (iterations < icount) ? (MAX_COLOR*iterations)/icount : 0;
        if(canvas != null) canvas.setPoint(x, y, color);
    }
    
    private int width;    // image x size (adjust for runtime)
    private int height;   // image y size (adjust for runtime)
    private int icount;   // iteration count
    private int nextY;    // used by thread pool
    private ReentrantLock mutex; // used to synchronize thread pool
    
    private double zoom;  // how large to make the image 
    private double ratio; // handle non-square images
    private double xOffset; // scroll image left to right
    private double yOffset; // scroll image up to down 
    
    private BitMap canvas;  // listener for setting pixels
    
    private Thread threadManager; // Generates threads to generate Mandelbrot
    
    private static int running = 0; // # instances calculating Mandelbrot
    private static boolean abort = false; // True if current work should be discontinued
}


import complex.Complex;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.SwingUtilities;

public class Mandelbrot implements Runnable {
    public final static int MAX_COLOR = 255;

    public Mandelbrot(BitMap canvas, int width, int height, int icount, int threads) {
        this.canvas = canvas;
        this.width  = width;
        this.height = height;
        this.ratio  = (double) height / (double) width;
        this.icount = icount;
        this.zoom   = 0.001;   //  0.001
        this.xOffset = -1.786; // -1.786
        this.yOffset = 0;      //  0.0
        this.colors = new int[width][height];
        this.mutex = new ReentrantLock();
        this.threads = threads;

        // SwingUtilities.invokeLater(this);
        m = new Thread(() -> {
          try {
            System.out.println("Pre-Mandelbrot");
            Thread.sleep(1000);
            System.out.println("Initiating Mandelbrot");
            calculateImageViaPool(threads); 
          } catch(Exception e) {
            System.err.println("Mandlebrot failed: " + e);
          }
        });
        m.start();
        
        System.out.println("Mandelbrot constructed");
    }
    public Mandelbrot(Canvas canvas, int threads) {
        this(canvas, 1000, 1000, 255, threads);
    }
    public void run() {
        try {
            System.out.println("Pre-Mandelbrot");
            Thread.sleep(1000);
            System.out.println("Initiating Mandelbrot");
            calculateImageViaPool(threads); 
        } catch(Exception e) {
            System.err.println("Mandlebrot failed: " + e);
        }
    }

    public void calculateImageViaPool (int numThreads) {
        Thread[] threads = new Thread[numThreads];
        for(int i=0; i<numThreads; ++i) {
            Runnable runnable = new Runnable() {
                public void run() {
                    int row = 0;
                    while(true) {
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
                        //System.out.print(".");
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
                System.out.println("Interrupted Exception");
            }
        }
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
        colors[x][y] = (iterations < icount) ? (MAX_COLOR*iterations)/icount : 0;
        if(canvas != null) canvas.setPoint(x, y, colors[x][y]);
    }

    private int width;    // image x size (adjust for runtime)
    private int height;   // image y size (adjust for runtime)
    private int icount;   // iteration count
    private int nextY;    // used by thread pool
    
    private int threads;
    private ReentrantLock mutex; // used to synchronize thread pool
    
    private double zoom;  // how large to make the image 
    private double ratio; // handle non-square images
    private double xOffset; // scroll image left to right
    private double yOffset; // scroll image up to down 
    
    private int[][] colors; // array containing color values at y*width+x
    private BitMap canvas;  // listener for setting pixels
    
    private Thread m;
}


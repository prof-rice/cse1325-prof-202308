class Complex {
    Complex(double x, double y) {this.x = x; this.y = y;}
    Complex() {this(0.0, 0.0);}
    Complex add(Complex c) {return new Complex(x + c.x, y + c.y);}
    Complex mult(Complex c) {return new Complex((x * c.x) - (y * c.y),
                                                (x * c.y) + (y * c.x));
    }
    double pabs() {return x*x + y*y;}
    double abs()  {return Math.sqrt(pabs());}
    
    private double x;
    private double y;
}

public class Mandelbrot {
    public final static int MAX_COLOR = 255;

    public static void main(String[] args) {
        Mandelbrot m = new Mandelbrot();
        System.out.println(m);
    }
    public Mandelbrot(int width, int height, int icount) {
        this.width  = width;
        this.height = height;
        this.icount = icount;
        this.zoom   = 150;
        this.colors = new int[width][height];
        calculateImage();
    }
    public Mandelbrot() {
        this(1000, 1000, 255);
    }
    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public void calculateImage () {
        for(int y=0; y<height; ++y) calculateRow(y);
    }
    protected void calculateRow   (int y) {
        for(int x=0; x<width; ++x) calculatePoint(x, y);
    }
    protected void calculatePoint (int x, int y) {
        Complex point = new Complex(((double) x / (double) width)  - 1.5, 
                                    ((double) y / (double) height) - 0.5);
        Complex z = new Complex();
        int iterations = 0;
        while(z.pabs() < 4.0 && iterations++ <= icount) z = z.mult(z).add(point);
        colors[x][y] = (iterations < icount) ? (MAX_COLOR*iterations)/icount : 0;
    }
/*
   protected void calculatePoint (int x, int y) {
        double zx = 0;
        double zy = 0;
        double cX = (x - width /2) / zoom;
        double cY = (y - height/2) / zoom;
        double temp = 0;
        int iter = icount;
        while (zx * zx + zy * zy < 4 && iter > 0) {
            temp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = temp;
            iter--;
        }
        colors[x][y] = iter;
    }
*/

    @Override
    public String toString() {
        StringBuffer result =
            new StringBuffer("P3\n" + width + ' ' + height 
                              + ' ' + icount + '\n');
        for(int y=0; y<height; ++y) {
            for(int x=0; x<width; ++x) {
                result.append("" + colors[x][y] + " 0 0\n");
            }
        }
        return result.toString();
    }
    
    private int width;    // image x size (adjust for runtime)
    private int height;   // image y size (adjust for runtime)
    private int icount;   // iteration count
    
    private double zoom;  // how large to make the image
    
    private int[][] colors;   // array containing color values at y*width+x
}


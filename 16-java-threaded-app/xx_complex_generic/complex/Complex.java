package complex;

public class Complex<E extends Number> {
    public Complex(E x, E y) {this.x = x; this.y = y;}
    public Complex() {this(new E(), new E());}
    
    // Getters
    public E getReal() {return x;}
    public E getImaginary() {return y;}
    
    // Unary math    
    public double abs()  {return Math.sqrt((Double) (x*x + y*y));}  // Math.sqrt(x*x + y*y)
    public double theta() {return Math.atan2((Double) y,(Double) x);} // Angle
    public Complex scale(E alpha) {return new Complex(x*alpha, y*alpha);}
    public Complex conjugate() {return new Complex(x,-y);}
    public Complex reciprocal() {
        E scale = x*x + y*y;
        return new Complex(x/scale, -y/scale);
    }

    // Binary math
    public Complex plus  (Complex c) {return new Complex(x + c.x, y + c.y);}
    public Complex minus (Complex c) {return new Complex(x - c.x, y - c.y);}
    public Complex times (Complex c) {return new Complex((x * c.x) - (y * c.y),
                                                (x * c.y) + (y * c.x));
    }
    public Complex divide(Complex c) {return times(c.reciprocal());}

    // Trigonometry
    public Complex exp() {return new Complex(Math.exp(x) * Math.cos(y), 
                                             Math.exp(x) * Math.sin(y));
    }
    public Complex sin() {return new Complex(Math.sin(x) * Math.cosh(y), 
                                             Math.cos(x) * Math.sinh(y));
    }
    public Complex cos() {return new Complex(Math.cos(x) * Math.cosh(y), 
                                            -Math.sin(x) * Math.sinh(y));
    }
    public Complex tan() {return sin().divide(cos());}


    // Standard utility
    @Override
    public String toString() {
        return "" + x + ((y>=0) ? "+" : "") + y + "i";
    }
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        Complex c = (Complex) o;
        return (x == c.x) && (y == c.y);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31*hash + (int) (10000*x);
        hash = 31*hash + (int) (10000*y);
        return hash;
    }
       
    private E x;
    private E y;
}



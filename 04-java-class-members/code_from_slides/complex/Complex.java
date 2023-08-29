public class Complex {
    // Constructor invoked by e.g., Complex c = new Complex(3.0, 4.0);
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    // Constructor invoked by e.g., Complex c = new Complex();
    // Shows constructor chaining to previous constructor
    public Complex() {
        this(0.0, 0.0);
    }
    // Destructors are automatic in Java - never needed
    
    // toString is "special" - it is invoked automatically
    //   any time a Complex object is used in a string context, e.g., 
    //   Complex c = new Complex(3.0, 4.0);
    //   String s = "The answer is " + c; // c.toString() is called
    @Override
    public String toString() {
        return "" + real + "+" + imaginary + "i";
    }
    
    private double real;      // These attribute can't be accessed
    private double imaginary; //   outside of class Complex
}
    

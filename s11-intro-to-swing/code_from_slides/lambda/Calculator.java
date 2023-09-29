// This is another example of lambdas.

public class Calculator {
    // Here's our interface, which does some kind of operation
    // (to be defined by the lambda) to our two integer parameters
    interface IntegerMath {
        int operation(int a, int b);   
    }

    // Give a + b (where + is any IntegerMath operation, our operateBinary 
    //   method accepts a and b and the operation to be performed,
    //   returning the result.
    // The third parameter may be of ANY class type as long as that class
    //   implements IntegerMath. 
    // When we use a lambda, Java creates an anonymous Java class for us
    //   that ONLY implements IntegerMath, with an implied declaration
    //   of an IntegerMath.operation whose parameter list is to the left
    //   of -> and whose body contains ONLY the code to the right of ->.
    public int operateBinary(int a, int b, IntegerMath im) {
        return im.operation(a, b);
    }
 
    public static void main(String... args) {
        Calculator calc = new Calculator();

        IntegerMath addition = (a, b) -> a + b;    // Define addition
        IntegerMath subtraction = (a, b) -> a - b; // define subtraction

        // Use our lambda definitions to perform some math
        System.out.println("40 + 2 = " + calc.operateBinary(40, 2, addition));
        System.out.println("20 - 10 = " + calc.operateBinary(20, 10, subtraction));
        
        // We can also include the lambda right in the method call!
        System.out.println("1 + 1 = " + calc.operateBinary(1, 1, (a, b) -> a + b));
    }
    
    // Lambdas are VERY useful for creating our user interfaces, because
    //   (as with attomenu and Swing) we can provide an implementation
    //   of the interface to specify code for the method to be called
    //   when a menu is selected or a widget is clicked.
}

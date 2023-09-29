import javax.swing.*;

public class HelloJava extends JFrame{
    public HelloJava() {
        // Close if X is clicked in title bar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton button = new JButton("Hello, Java!");
        button.setBounds(20,20,120, 30);  // position & size
		
        add(button);      // Add the button to the JFrame
		
        setSize(160,110); // Set the JFrame size
        setLayout(null);  // setBounds avoids layout manager need
        setVisible(true); // Make the JFrame visible
    }

    public static void main(String[] args) {
        new HelloJava();  // Instance and run the JFrame
    }
}

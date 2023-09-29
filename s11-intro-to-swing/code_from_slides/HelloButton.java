import javax.swing.JFrame;
import javax.swing.JButton;

public class HelloButton extends JFrame{
    public HelloButton() {
    
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
        new HelloButton();  // Instance and run the JFrame
    }
}

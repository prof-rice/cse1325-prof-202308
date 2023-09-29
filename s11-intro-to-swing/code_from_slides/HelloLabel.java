import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloLabel extends JFrame{
    public HelloLabel() {
        // Close if X is clicked in title bar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and set position / size of label
        JLabel label = new JLabel("Hello, Java!");
        label.setBounds(20,20,100, 30); 
        add(label);      // Add the label to the JFrame
		
        setSize(160,110); // Set the JFrame size
        setLayout(null);  // setBounds avoids layout manager need
        setVisible(true); // Make the JFrame visible
    }
    
    public static void main(String[] args) {
        new HelloLabel();  // Instance and run the JFrame
    }
}

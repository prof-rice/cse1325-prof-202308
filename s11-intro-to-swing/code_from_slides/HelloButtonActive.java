import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelloButtonActive extends JFrame implements ActionListener {
    public HelloButtonActive() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on X click
        
        JButton button = new JButton("Hello, Java!");
        button.setBounds(20,20,120, 30);  // position & size
        button.addActionListener(this);   // call actionPerformed on click
        add(button);                      // Add the button to the JFrame
		
        setSize(160,110); // Set the JFrame size
        setLayout(null);  // setBounds avoids layout manager need
        setVisible(true); // Make the JFrame visible
    }

    // Define an action listener that prints to the console
    @Override
    public void actionPerformed(ActionEvent event) {
        String str = event.getActionCommand();  // Button text
        System.out.println(str);
    }
    
    public static void main(String[] args) {
       new HelloButtonActive();   // Instance and run the JFrame
    }
}

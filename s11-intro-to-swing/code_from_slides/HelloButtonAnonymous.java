import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelloButtonAnonymous extends JFrame {
    public HelloButtonAnonymous() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on X click
        
        JButton button = new JButton("Hello, Java!");
        button.setBounds(20,20,120, 30);  // position & size
		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
                String str = event.getActionCommand();  // Button text
                System.out.println(str);
            }
        });
        add(button);                      // Add the button to the JFrame
		
        setSize(160,110); // Set the JFrame size
        setLayout(null);  // setBounds avoids layout manager need
        setVisible(true); // Make the JFrame visible
    }

    public static void main(String[] args) {
       new HelloButtonAnonymous();   // Instance and run the JFrame
    }
}

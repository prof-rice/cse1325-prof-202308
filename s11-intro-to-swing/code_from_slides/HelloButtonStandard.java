import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class HelloButtonStandard extends JFrame {
    public HelloButtonStandard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        JButton button = new JButton("Hello, Java!");
        button.setBounds(20,20,120, 30); 
		button.addActionListener(
		    event -> onHelloButtonClick(event)); // delegate to Observer method!
        add(button);
		
        setSize(160,110); 
        setLayout(null);
        setVisible(true);
    }
    // Here's the Observer method. It runs via the above lambda
    //   whenever the button is clicked. Sign up / call back!
    public void onHelloButtonClick(ActionEvent event) {
        System.out.println(event.getActionCommand());
    }

    public static void main(String[] args) {
       new HelloButtonStandard();   // Instance and run the JFrame
    }
}

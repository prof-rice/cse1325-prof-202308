// Swing is much more advanced, so don't worry if this makes little since right now
// In a few weeks, all will be clear and understandable!

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TestJavaSwing extends JFrame {
    private enum Grades {A, B, C, D, F, I};
    public TestJavaSwing() {
        // Configure the window to exit if the close button (x) is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Create a button that, when clicked, calls onButtonClick()
        JButton button = new JButton("Click me!");
        //button.setBounds(20,20,120, 30);  // position & size
		button.addActionListener(
		    event -> onButtonClick());    // Call onButtonClick when clicked
        add(button);                      // Add the button to the main window (JFrame)
		
		// Size and run the main window
        setSize(160,110); // Set the JFrame size
        setVisible(true); // Make the JFrame visible (run the window!)
    }
    
    // This method (an observer, or action listener in Java terms)
    //   will run whenever the button is clicked
    public void onButtonClick() {
        // Create an empty combobox (also called a drop-down list)
        JComboBox<Object> comboEnum = new JComboBox<>();
        
        // The combobox will list the string representation of
        //   the Grades.values() array - the enum values
        comboEnum.setModel(new DefaultComboBoxModel<Object>(Grades.values()));
        
        // The confirmation dialog usually takes a String prompt
        //   as the 2nd parameter, but it can actually accept
        //   ANY object. So we show the combobox and run the dialog.
        // QUESTION_MESSAGE as parameter 5 makes the dialog icon a ?
        // With OK_CANCEL_OPTION as parameter 4, it will return 
        //   OK_OPTION or CANCEL_OPTION. See your other options at
        //   https://docs.oracle.com/en/java/javase/16/docs/api/java.desktop/javax/swing/JOptionPane.html
        // We could use a message dialog, but that offers only
        //   an OK button. We usually prefer OK and CANCEL when
        //   accepting user data such as with a widget.
        int button = JOptionPane.showConfirmDialog(this, 
                comboEnum,
                "Which grade would you like in CSE1325?", 
                JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        
        // Parse the result and display in a message dialog
        String result = "";
        if(button == JOptionPane.CANCEL_OPTION) result = "Cancel was clicked!";
        else result = "Here's hoping for your " + comboEnum.getSelectedItem().toString() + "!";
        JOptionPane.showMessageDialog(this, result);
    }

    public static void main(String[] args) {
        // Instance and (automatically) run an TestJavaSwing object. 
        new TestJavaSwing();
    }
}

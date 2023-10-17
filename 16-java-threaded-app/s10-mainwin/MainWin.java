import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class MainWin extends JFrame {
    public MainWin() {
        setTitle("Mandelbrot Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas(1000, 1000);
        add(canvas);
        pack();
        setVisible(true);
        SwingUtilities.invokeLater(() -> new Mandelbrot(canvas));
        System.out.print("C");
    }    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWin());
    }
}


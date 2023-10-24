import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

public class Canvas extends JPanel implements BitMap {
    private BufferedImage image;
    private final int width;

    public Canvas(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000,1000);
    }

    @Override
    public void setPoint(int x, int y, int color) {
        image.setRGB(x, y, color);
        //if(x == width-1) 
        repaint(x,y,1,1);
        //SwingUtilities.invokeLater(() -> repaint()); 
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        System.out.print("."); System.out.flush();    
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, null, null);
    }
}

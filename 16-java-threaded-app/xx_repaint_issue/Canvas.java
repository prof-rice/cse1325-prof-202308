import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

public class Canvas extends JPanel implements BitMap {
    private BufferedImage image;

    public Canvas(int width, int height) {
        super(false); // disable double buffering
        this.width = width;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.color = Color.BLUE;
        //System.out.println(isDoubleBuffered());
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000,1000);
    }

    @Override
    public void setPoint(int x, int y, int color) {
        try {
            int c = this.color.getRed()   * color / 255 * 0X10000
                  + this.color.getGreen() * color / 255 * 0X100
                  + this.color.getBlue()  * color / 255;
            image.setRGB(x, y, c);
            //repaint();
            repaint(0,y,1000,1);
            //SwingUtilities.invokeLater(() -> repaint()); 
            /*
            try {
                if(x == width-1) {
                    System.out.print("p");
                    paintImmediately(0,y,1000,1);
                    Thread.sleep(100);
                }
            } catch(Exception e) {
            }
            */
        } catch(Exception e) {
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, null, null);
        //System.out.println("|");
    }

    private Color color;
    private final int width;
}

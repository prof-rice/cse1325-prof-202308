import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;;
import java.io.IOException;

// import javax.swing.SwingUtilities;

public class Canvas extends JPanel implements BitMap {
    private BufferedImage image;
    private final int width;

    public Canvas(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.color = Color.BLUE;
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000,1000);
    }

    @Override
    public void setPoint(int x, int y, int color) {
        int c = this.color.getRed()   * color / 255 * 0X10000
              + this.color.getGreen() * color / 255 * 0X100
              + this.color.getBlue()  * color / 255;
        image.setRGB(x, y, c);
        //if(x == width-1) 
        repaint(x,y,1,1);
        //SwingUtilities.invokeLater(() -> repaint()); 
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, null, null);
    }

    // ////////////////////////////////////////////////////////////////
    // Attributes, Getters and Setters
    // File I/O members
    public static String id() {return "Mandelbrot";}

    private File filename;        // Filename to which this Canvas will be saved
    public File filename() {return filename;}
    public void filename(File filename) {this.filename = filename;}
    
    private boolean isDirty;      // True if we have unsaved data
    public boolean isDirty() {return isDirty;}
    
    private Color color;          // Color for upcoming lines to be drawn
    public Color color() {return color;}
    public void color(Color newColor) {color = newColor;}

}

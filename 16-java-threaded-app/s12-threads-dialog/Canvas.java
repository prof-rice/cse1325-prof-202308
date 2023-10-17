import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;;
import java.io.IOException;

public class Canvas extends JPanel implements BitMap, ComponentListener {
    public Canvas(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.color = Color.BLUE;

        // Listen for componentResized events to change size of Mandelbrot
        this.addComponentListener(this); // generates size exception
    }

    public void clear() {
        image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
        revalidate();
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, width);
    }

    // BitMap implementation
    @Override
    public void setPoint(int x, int y, int color) {
        try {
            int c = this.color.getRed()   * color / 255 * 0X10000
                  + this.color.getGreen() * color / 255 * 0X100
                  + this.color.getBlue()  * color / 255;
            image.setRGB(x, y, c);
            repaint(x,y,1,1);
        } catch(Exception e) {
        }
    }

    // ComponentListener implementation
    @Override
    public void componentResized(ComponentEvent ce) {
        
        int size = Math.min(this.getHeight(), this.getWidth());
        if(size != width) {
            width = size;
            //System.out.println("Canvas resized to " + width);
            image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
            revalidate();
        }
    };
    @Override
    public void componentHidden(ComponentEvent e) { }
    @Override
    public void componentMoved(ComponentEvent e) { }
    @Override
    public void componentShown(ComponentEvent e) { }
    
    // Repaint code
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, null, null);
    }

    // ////////////////////////////////////////////////////////////////
    // Attributes, Getters and Setters

    // Image data    
    private BufferedImage image;
    private int width;

    private Color color;          // Color for image
    public Color color() {return color;}
    public void color(Color newColor) {color = newColor;}

    // File I/O members
    public static String id() {return "Mandelbrot";}

    private File filename;        // Filename to which this Canvas will be saved
    public File filename() {return filename;}
    public void filename(File filename) {this.filename = filename;}
    
    private boolean isDirty;      // True if we have unsaved data
    public boolean isDirty() {return isDirty;}
}

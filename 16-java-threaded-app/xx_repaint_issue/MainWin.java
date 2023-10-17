import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import javax.swing.SwingUtilities;

import javax.imageio.ImageIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

import java.util.Arrays;

public class MainWin extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWin());
    }

    public MainWin() {
        super("Test");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(1000, 1000);
        threads = 1;
        
        setVisible(true);

        canvas = new Canvas(1000, 1000);
        add(canvas, BorderLayout.CENTER);
        
        // Generate an initial Mandelbrot
        try {
            SwingUtilities.invokeLater(() -> new Mandelbrot(canvas, threads));
        } catch(Exception e) {
            System.err.println("Mandelbrot construction aborted: " + e);
        }
        /*
        SwingUtilities.invokeLater(() -> {
            try {
                while(true) {
                    Thread.sleep(1000);
                    System.out.print("T");
                    canvas.paintImmediately(0,0,1000,1000);
                }
            } catch (Exception e) {
                System.err.println("Repaint thread crash: " + e);
            }
        });
        */
        System.out.println("End of MainWin constructor");

    }
    
    private Canvas canvas;
    private int threads;
    
}

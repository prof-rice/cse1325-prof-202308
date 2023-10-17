import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSlider;
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
    public static final String TITLE = "CSE1325 Mandelbrot";
    public static final String MAGIC_COOKIE = "𝓒𝓼𝓮 ₁₃₂₅ ℳℬ";
    public static final String VERSION = "0.10.0";
    public static final String FILE_VERSION = "0.0";
    public static final String EXTENSION = "mb";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWin(TITLE));
    }

    public MainWin(String title) {
        super(title);

        setSize(1000, 1000);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        // File menu
        
        JMenu     file       = new JMenu("File");
        
        // File > New
        JMenuItem newp       = new JMenuItem("New");        
        newp.addActionListener((ActionEvent event) -> onNewClick());
        file.add(newp);
        
        // File > Open
        JMenuItem open       = new JMenuItem("Open");        
        open.addActionListener((ActionEvent event) -> onOpenClick());
        file.add(open);
        
        // File > Save
        JMenuItem save       = new JMenuItem("Save");        
        save.addActionListener((ActionEvent event) -> onSaveClick());
        file.add(save);
        
        // File > Save As
        JMenuItem saveAs     = new JMenuItem("Save As");        
        saveAs.addActionListener((ActionEvent event) -> onSaveAsClick());
        file.add(saveAs);
        
        // File > Export
        JMenu     export     = new JMenu("Export As"); 
        String[] names = ImageIO.getWriterFormatNames(); // All supported formats
        Arrays.sort(names);   // Humans like alphabetical order!
        for (String name : names) {
            if(name.equals(name.toLowerCase())) {  // Only accept those in lower case
                JMenuItem extension = new JMenuItem(name);
                extension.addActionListener(
                    (ActionEvent event) -> onExportClick(name));
                export.add(extension);
            }
        } 
        file.add(export);       
                
        // File > Quit
        JMenuItem quit       = new JMenuItem("Quit");        
        quit.addActionListener((ActionEvent event) -> onQuitClick());
        // Make clicking the 'x' the same as File > Quit
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onQuitClick(); // Call this when 'x' is clicked
            }
        });
        file.add(quit);
        
        menubar.add(file);
        
/*        // Edit menu
        
        JMenu     edit       = new JMenu("Edit");
        
        // Edit > Undo
        JMenuItem undo       = new JMenuItem("Undo");        
        undo.addActionListener((ActionEvent event) -> onUndoClick());
        edit.add(undo);
        
        // Edit > Redo
        JMenuItem redo       = new JMenuItem("Redo");        
        redo.addActionListener((ActionEvent event) -> onRedoClick());
        edit.add(redo);
        
        menubar.add(edit);
*/        
                
        // Pen menu
        
        // Pen > Color
        JMenu     pen         = new JMenu("Pen");
        JMenuItem color       = new JMenuItem("Color");        
        color.addActionListener((ActionEvent event) -> onColorClick());
        pen.add(color);
/*        
        // Pen > Width
        JMenuItem width       = new JMenuItem("Width");        
        width.addActionListener((ActionEvent event) -> onWidthClick());
        pen.add(width);

        // Pen > Dash
        JMenu dash            = new JMenu("Dash");
        for(int i=0; i<dashedLines.length; ++i) {
            JMenuItem dashPattern = new JMenuItem(dashedLines[i]);
            final int index = 1;  // "local variables referenced from a lambda expression 
                                  //  must be final or effectively final"
            dashPattern.addActionListener((ActionEvent event) -> onDashClick(index));
            dash.add(dashPattern);
        }        
        pen.add(dash);
        
        // Separator
        pen.add(new JSeparator());
        
        // Pen > Segment
        JMenuItem segment     = new JMenuItem("Single Lines");        
        segment.addActionListener((ActionEvent event) -> onSegmentClick());
        pen.add(segment);
       
        // Pen > Contiguous
        JMenuItem contiguous  = new JMenuItem("Connected Lines");        
        contiguous.addActionListener((ActionEvent event) -> onContiguousClick());
        pen.add(contiguous);
       
        // Pen > Freehand
        JMenuItem freehand  = new JMenuItem("Freehand Lines");        
        freehand.addActionListener((ActionEvent event) -> onFreehandClick());
        pen.add(freehand);
*/       
        menubar.add(pen);
        
        setJMenuBar(menubar);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("Paint Controls");

        // New button
        JButton newButton  = 
            new JButton(new ImageIcon("resources/new_file.png"));
        newButton.setActionCommand("New");
        newButton.setToolTipText("Create a new painting");
        toolbar.add(newButton);
        newButton.addActionListener((ActionEvent event) -> onNewClick());
        
        // Open button
        JButton openButton  = 
            new JButton(new ImageIcon("resources/open.png"));
        openButton.setActionCommand("Open");
        openButton.setToolTipText("Open a painting from a file");
        toolbar.add(openButton);
        openButton.addActionListener((ActionEvent event) -> onOpenClick());
        
        // Save button
        JButton saveButton  = 
            new JButton(new ImageIcon("resources/save.png"));
        saveButton.setActionCommand("Save");
        saveButton.setToolTipText("Save the painting to the current file");
        toolbar.add(saveButton);
        saveButton.addActionListener((ActionEvent event) -> onSaveClick());

        // Save As button
        JButton saveAsButton  = 
            new JButton(new ImageIcon("resources/save_as.png"));
        saveAsButton.setActionCommand("Save As");
        saveAsButton.setToolTipText("Save the painting to a different file");
        toolbar.add(saveAsButton);
        saveAsButton.addActionListener((ActionEvent event) -> onSaveAsClick());
/*
        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(25));
  
        // Undo button
        JButton undoButton  = 
            new JButton(new ImageIcon("resources/undo.png"));
        undoButton.setActionCommand("Undo");
        undoButton.setToolTipText("Remove the most recently added shape");
        toolbar.add(undoButton);
        undoButton.addActionListener((ActionEvent event) -> onUndoClick());

        // Redo button
        JButton redoButton  = 
            new JButton(new ImageIcon("resources/redo.png"));
        redoButton.setActionCommand("Redo");
        redoButton.setToolTipText("Restore the most recently removed shape");
        toolbar.add(redoButton);
        redoButton.addActionListener((ActionEvent event) -> onRedoClick());
*/
        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(25));
  
        // Create the Color button
        JButton colorButton  = 
            new JButton(new ImageIcon("resources/color.png"));
        colorButton.setActionCommand("SelectPenColor");
        colorButton.setToolTipText("Select the pen color");
        toolbar.add(colorButton);
        colorButton.addActionListener((ActionEvent event) -> onColorClick());
/*
        // Create the Width button
        JButton widthButton  = 
            new JButton(new ImageIcon("resources/width.png"));
        widthButton.setActionCommand("SelectPenWidth");
        widthButton.setToolTipText("Select the pen width");
        toolbar.add(widthButton);
        widthButton.addActionListener((ActionEvent event) -> onWidthClick());
        
        // Create the Dash button with dropdown (this is tricky!)
        // First, we must manually create the dropdown menu contents
        dashPopupMenu = new JPopupMenu();
        for(int i=0; i<dashedLines.length; ++i) {
            final int index = i; // "local variables referenced from an inner class 
                                 //  must be final or effectively final"
            dashPopupMenu.add(new JMenuItem(new AbstractAction(dashedLines[i]) {
                public void actionPerformed(ActionEvent e) {
                    onDashClick(index);
                }
            }));
        }
        // Then, when the button is clicked, we show the popup menu under the mouse
        JButton dashButton  = 
            new JButton(new ImageIcon("resources/dash.png"));
        dashButton.setActionCommand("SelectDashPattern");
        dashButton.setToolTipText("Select the dash pattern");
        toolbar.add(dashButton);
        dashButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                dashPopupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        
       

        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(25));
  
        ButtonGroup penModeGroup = new ButtonGroup();
        
        // Create the Segment toggle button (acts like a radio button)
        segmentButton  = 
            new JToggleButton(new ImageIcon("resources/segment.png"), true);
        segmentButton.setActionCommand("SelectSegment");
        segmentButton.setToolTipText("Draw single lines (click-click)");
        penModeGroup.add(segmentButton);
        toolbar.add(segmentButton);
        segmentButton.addActionListener((ActionEvent event) -> onSegmentClick());
        
        // Create the Contiguous toggle button (acts like a radio button)
        contiguousButton  = 
            new JToggleButton(new ImageIcon("resources/contiguous.png"));
        contiguousButton.setActionCommand("SelectContiguous");
        contiguousButton.setToolTipText("Draw connected lines (click-click-click…)");
        penModeGroup.add(contiguousButton);
        toolbar.add(contiguousButton);
        contiguousButton.addActionListener((ActionEvent event) -> onContiguousClick());
        
        // Create the Freehand toggle button (acts like a radio button)
        freehandButton  = 
            new JToggleButton(new ImageIcon("resources/scribble.png"));
        freehandButton.setActionCommand("SelectFreehand");
        freehandButton.setToolTipText("Draw freehand (click-drag)");
        penModeGroup.add(freehandButton);
        toolbar.add(freehandButton);
        freehandButton.addActionListener((ActionEvent event) -> onFreehandClick());
        
*/
        getContentPane().add(toolbar, BorderLayout.PAGE_START);
      
        // /////////////////////////// ////////////////////////////////////////////
        // C A N V A S
        // Provide a JPanel to act as the Canvas
        canvas = new Canvas(1000, 1000);
        add(canvas, BorderLayout.CENTER);
        
        // Make everything in the JFrame visible
        setVisible(true);

        // Generate an initial Mandelbrot
        SwingUtilities.invokeLater(() -> new Mandelbrot(canvas, 32));

    }
    
    // Listeners
    protected void onNewClick() {
        if(okToExit()) {
            if(canvas != null) {remove(canvas); canvas = null;}  // Don't delete twice
            canvas = new Canvas(1000, 1000);
            add(canvas, BorderLayout.CENTER);
            revalidate();
            repaint();
//            onSegmentClick();
        }
    }

    protected void onOpenClick() {
        // Don't discard unsaved data without permission
        if(!okToExit()) return;  

        // Identify a default filename
        File filename = new File("untitled." + EXTENSION);;

/*        // If canvas exists, get filename from it instead, then remove it
        if(canvas != null) {
            filename = canvas.filename();
            remove(canvas); // Don't delete twice
            canvas = null;
        }
*/
        // Create a file chooser
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter mbFiles = new FileNameExtensionFilter("Mandelbrot files", EXTENSION);
        fc.addChoosableFileFilter(mbFiles);
        fc.setFileFilter(mbFiles);  // Show CSE1325 Mandelbrot files only by default
        
        // Run the dialog and return the button clicked
        int result = fc.showOpenDialog(this);
        
        // If the user clicked OK, set the filename; if Cancel, just return
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();
        } else if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
         
        // Preset error message (what a pessimist!)
        // This allows us to capture the line number in our data file
        //   in the event of an error via LineNumberReader
        String errorMessage = "Unable to open " + filename;
        try (LineNumberReader br = new LineNumberReader(new FileReader(filename))) {
            try {
                // Verify that this is indeed a CSE1325 Paint-generated file
                String magicCookie = br.readLine();
                if(!magicCookie.equals(MAGIC_COOKIE)) 
                    throw new IOException("Not a " + TITLE + " file");
                    
                // Verify that the file format is known (i.e., version is <= to our version)
                String fileVersion = br.readLine();
                if(fileVersion.compareTo(FILE_VERSION) > 0) 
                    throw new IOException("Incompatible file format version " + fileVersion);
                
                // Verify that a canvas was saved (written by Canvas.save)
                String programName = br.readLine();
                if(!programName.equals(Canvas.id())) // Was this added by Canvas.save?
                    throw new IOException("Missing '" + Canvas.id() + "' ID");
                
/*                // Instance a new Canvas from the file stream
                canvas = new Canvas(br, fileVersion);
                add(canvas, BorderLayout.CENTER);
                revalidate();
                repaint();
*/                
            // On a read error from opened file, capture the line number and rethrow
            } catch (Exception e) {
                if (br != null) 
                    errorMessage = " Unable to read " + filename 
                                 + " at line " + br.getLineNumber();
                throw e;
            }
        // On any error (file open OR read), report to the user and clean up
        } catch (Exception e) {
            JDialog dialog = new JOptionPane(
                errorMessage + '\n' + e, 
                JOptionPane.ERROR_MESSAGE,
                JOptionPane.DEFAULT_OPTION).createDialog("Failed"); 
            dialog.setAlwaysOnTop(true); 
            dialog.setVisible(true);        // Show the error dialog
            dialog.dispose();               // Delete the dialog from memory

            canvas = null;  // Discard possibly damaged Canvas
            onNewClick();   // Create a new, clean Canvas
        }
    }

    protected void onSaveClick() {
        // Get canvas' current filename
        File filename = canvas.filename();
        
        // Open that file for writing
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');  // Save a cookie (to recognize Paint files)
            bw.write(FILE_VERSION + '\n');  // Save the file version (for later use)
//            canvas.save(bw);                // Save the painting
            
        // If an exception occurred, report it to the user
        } catch (Exception e) {
            JDialog dialog = 
                new JOptionPane("Unable to open " + filename + '\n' + e, 
                    JOptionPane.ERROR_MESSAGE,
                    JOptionPane.DEFAULT_OPTION).createDialog("Failed"); 
            dialog.setAlwaysOnTop(true); 
            dialog.setVisible(true);        // Show the error dialog
            dialog.dispose();               // Delete the dialog from memory
        }
    }

    protected void onSaveAsClick() {
        // Identify a default filename
        File filename = canvas.filename();

        // Create a file chooser
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter paintFiles = new FileNameExtensionFilter("Paint files", EXTENSION);
        fc.addChoosableFileFilter(paintFiles);
        fc.setFileFilter(paintFiles);  // Show CSE1325 Paint files only by default
        
        // Run the dialog and return the button clicked
        int result = fc.showSaveDialog(this);
        
        // If user clicked OK, get the filename
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();

            // If the extension is missing / incorrect, append it
            if(!filename.getPath().toUpperCase().endsWith(EXTENSION.toUpperCase()))
                filename = new File(filename.getPath() + '.' + EXTENSION);

            // Set canvas' current filename to this, then save to current filename
            canvas.filename(filename);
            onSaveClick();
        }
    }
    
    protected void onExportClick(String extension) {
        // Identify a default filename
        File filename = new File("untitled." + extension);

        // Create a file chooser
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter pngFiles = new FileNameExtensionFilter(extension + " files", extension);
        fc.addChoosableFileFilter(pngFiles);
        fc.setFileFilter(pngFiles);
        
        // Run the dialog and return the button clicked
        int result = fc.showSaveDialog(this);
        
        // If user clicked OK, get the filename
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();

            // If the extension is missing / incorrect, append it
            if(!filename.getPath().toLowerCase().endsWith(extension.toLowerCase()))
                filename = new File(filename.getPath() + '.' + extension);

            // Tell Canvas to export itself
            try {
//                canvas.export(filename, extension);
            } catch(Exception e) {
                JDialog dialog = 
                    new JOptionPane("Unable to export " + filename + '\n' + e, 
                        JOptionPane.ERROR_MESSAGE,
                        JOptionPane.DEFAULT_OPTION).createDialog("Failed"); 
                dialog.setAlwaysOnTop(true); 
                dialog.setVisible(true);        // Show the error dialog
                dialog.dispose();               // Delete the dialog from memory
            }
        }
    }
    protected void onQuitClick() {   // Exit the program
        if(okToExit()) dispose(); //System.exit(0);
    }
    
    protected void onUndoClick() {   // Remove the newest active shape
//        canvas.undo(); 
    }
    
    protected void onRedoClick() {   // Restore the oldest inactive shape
//        canvas.redo(); 
    }
    
    protected void onColorClick() {   // Select a new pen color
        Color newColor = JColorChooser.showDialog(this, "Choose a color", canvas.color());
        if(newColor != null) canvas.color(newColor);
        new Mandelbrot(canvas, 32);
    }
    
/*    protected void onWidthClick() {   // Select a new pen width
        // Configure a slider to select the pen width
        JSlider slider = new JSlider(1, 25, (int) canvas.width());
        slider.setMajorTickSpacing(3);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);

        // Use the slider as the "message" in an Option Dialog
        int choice = JOptionPane.showOptionDialog(this, 
            slider,
            "Select Line Width", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null,
            null, 
            null);

        if(choice == JOptionPane.OK_OPTION) {
            canvas.width((float) slider.getValue());
        }    
     }
    
    protected void onDashClick(int index) {   // Select a new dash pattern
        canvas.dash(index);
    }

    protected void onSegmentClick() {   // Draw single lines
        canvas.penMode(Canvas.PenMode.SEGMENT);
        segmentButton.setSelected(true); // This does NOT trigger an event
    }

    protected void onContiguousClick() {   // Draw connected lines
        canvas.penMode(Canvas.PenMode.CONTIGUOUS);
        contiguousButton.setSelected(true); // This does NOT trigger an event
    }

    protected void onFreehandClick() {   // Draw connected lines
        canvas.penMode(Canvas.PenMode.FREEHAND);
        freehandButton.setSelected(true); // This does NOT trigger an event
    }
*/
    protected boolean okToExit() {  // Returns true if OK to exit
        if (canvas == null) return true;     // OK to exit if no Canvas
        if (!canvas.isDirty()) return true;  // OK to exit if canvas already saved
        int choice = JOptionPane.showOptionDialog(this,
            "Save before discarding canvas?",
            "Unsaved Data",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            null,
            null); 
        if (choice == JOptionPane.YES_OPTION) {
            onSaveClick();
            return !canvas.isDirty(); // OK to exit only is saved successfully
        } 
        if (choice == JOptionPane.NO_OPTION) {
            return true;     // OK to discard data and exit
        } 
        return false;  // Exit is not approved
    }       
   
    private Canvas canvas;
    
//    private JToggleButton segmentButton;
//    private JToggleButton contiguousButton;
//    private JToggleButton freehandButton;
//    private JPopupMenu dashPopupMenu;
//    private final String[] dashedLines = 
//        {"Solid Line", "Dashed Line", "Dotted Line", "Dash-Dot Line"};
}

package attomenu;

/*
Copyright 2023 by George F. Rice
DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.

This file is part of Console Menu.

Console Menu is free software: you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

Foobar is distributed in the hope that it will be useful, but WITHOUT 
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
for more details.

You should have received a copy of the GNU General Public License 
along with Console Menu. If not, see <https://www.gnu.org/licenses/>.
*/

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import java.io.File;
import java.io.FilenameFilter;

/**
 * A simple multi-level console menu system using the Observer pattern.
 * <p>
 * Each {@link MenuItem} specifies the text for a menu entry, the Runnable object
 * (a lambda is often preferred) to invoke when that menu entry is selected,  
 * an optional key character for selecting that MenuItem from the keyboard,
 * and optional help text to print when requested.
 * <p>
 * Menu uses the sequential non-negative integers, the key character specified to MenuItem,
 * or both for selecting a MenuItem using console input. If {@link SHOW_CHAR} is false,
 * only integer keys are used. If {@link SHOW_INT} is false, only the key character
 * is used (but if not available, an integer key will be substituted). If both are true,
 * character keys will be used when available along with integer keys.
 * <p>
 * The Menu can be runOnce() for a single selection and response, or simply run() 
 * for many selections and responses until the user selects {@link EXIT_CHAR} 
 * ('x' by default) to exit that Menu.
 * <p>
 * Menu also provides static {@link select(Object,Object[])} and {@link select(Object,List)} 
 * methods for selecting one element from an array or List of objects, and a static 
 * {@link selectFile(Object, File, FilenameFilter)} method 
 * to select a file or directory. These static select methods always use integer keys.
 * <p>
 * If HELP_CHAR is not a space, the user will be prompted to type it for help. If typed
 * alone at a command prompt, help provided in the Menu constructor will be displayed.
 * If followed by a space and a menu key, any help provided with that MenuItem
 * will be displayed instead. If no help was provided for that MenuItem, then the
 * message will be "No help available for 'key'."
 * <p>
 * Menu is philisophically similar to Swing's JMenu, using MenuItem instead of JMenuItem
 * for the menu elements.
 * <p>
 * See the Pizza Pearl example application in package pizza for ideas.
 *
 * @author             Professor George F. Rice
 * @version            1.1
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class Menu {
    /**
     * The char command to exit the menu ('x' is default, ' ' is not permitted).
     * 
     * @since 1.1
     */
    public static char EXIT_CHAR = 'x';
    /**
     * The char command to display help ('?' is default, ' ' offers no help).
     * 
     * @since 1.1
     */
    public static char HELP_CHAR = '?';
    /**
     * Enables integer key in menus (always shown if no character key is available).
     * 
     * @since 1.1
     */
    public static boolean SHOW_INT = true;
    /**
     * Enables character key in menus when available.
     * 
     * @since 1.1
     */
    public static boolean SHOW_CHAR = true;
    /**
     * Constructs a new Menu with all menu items and associated callbacks.
     * 
     * @param title      Printed before the menu items (if not null)
     * @param data       Printed after the menu items and before the prompt (if not null)
     * @param help       Printed on request (if not null) to help the user
     * @param menuItems  List of menu item text and observer / callbacks
     * @since 1.0
     * @throws IllegalArgumentException if EXIT_CHAR is set to a space
     */
    public Menu(Object title, Object data, String help, MenuItem... menuItems) {
        if(EXIT_CHAR == ' ') 
            throw new IllegalArgumentException("EXIT_CHAR cannot be ' '");
        this.title = title;
        this.data = data;
        this.help = help;
        this.menuItems = menuItems;
    }
    /**
     * Constructs a new Menu with all menu items and associated callbacks.
     * 
     * @param title      Printed before the menu items (if not null)
     * @param data       Printed after the menu items and before the prompt (if not null)
     * @param menuItems  List of menu item text and observer / callbacks
     * @since 1.0
     * @throws IllegalArgumentException if EXIT_CHAR is set to a space
     */
    public Menu(Object title, Object data, MenuItem... menuItems) {
        this(title, data, null, menuItems);
    }
    /**
     * Displays the menu and executes the callback for one selection by the user.
     * 
     * @return null if selected action succeeded, user input if an error occurred
     */
    public String runOnce() {
        System.out.print(this);  // Print the menu and get the user's selection
        String input = System.console().readLine().trim();
        System.out.println();
        if(input.isEmpty()) return " "; // Invalid input
        
        Help helpRequested = Help.NONE;  // Check if help is requested
        if(input.charAt(0) == HELP_CHAR) {
            if(input.length() == 1) {
                helpRequested = Help.MAIN;
            } else {
                helpRequested = Help.SELECTION;
                input = input.substring(1).trim(); // Parse the selection requested
            }
        }
        final int INVALID = -1;  // Must be <0 to trigger exception below
        int index = INVALID;     // Assume an invalid selection  until proven otherwise
        try {                    // Try to accept numeric input
            index = Integer.parseInt(input);
        } catch(Exception e) {
        }
        char c = input.charAt(0);
        if(index == INVALID && SHOW_CHAR) { // If not, check for a char key
            for(int i=0; i<menuItems.length; ++i) {
                if(c == menuItems[i].getKey()) {
                    index = i;
                    break;
                }
            }
        }
        try {
            if(helpRequested.equals(Help.NONE)) {
                menuItems[index].run();
                input = null;       // Null indicates a valid response was handled
            } else {
                String helpText = (help != null) ? help : "No help available.";
                if (helpRequested.equals(Help.SELECTION)) {
                    if(c == EXIT_CHAR) helpText = "This will exit the menu.";
                    else if (c == HELP_CHAR) helpText = "This will show help.";
                    else helpText = menuItems[index].getHelp();
                }
                if(helpText == null) System.out.println("No help for '" + input + "'.\n");
                else System.out.println("\n" + helpText + "\n\n");
                input = null;       // Null indicates a valid response was handled
            }
        } catch(Exception e) {
        }
        return input;
    }
    /**
     * The main loop that repeatedly calls runOnce until the user enters EXIT_CHAR.
     */
    public void run() {
        while(true) {
            String input = runOnce(); // Print menu and get response
            if(input != null) {       // If not null, response was undefined
                if(Character.toLowerCase(input.charAt(0)) == EXIT_CHAR) break;
                System.err.println("Invalid menu selection: " + input + "\n");
            }
        }
    }
    // <E> means to accept a List containing any type!
    // If user exits without selection, returns -1
    /**
     * Provides a menu containing all list members so the user can select one.
     * 
     * Calls toArray on the list and passes the array to {@link #select(Object,Object[])}.
     * 
     * @param <E>    The type of elements in list
     * @param title  Displayed above the menu (if not null)
     * @param list   The List of elements to offer in the menu
     * @return       The index of the selected element, or -1 if no selection
     */
    public static <E> int select(Object title, List<E> list) {
        return select(title, list.toArray(new String[list.size()]));
    }
    /**
     * Provides a menu containing all array members so the user can select one.
     * 
     * The menu items correspond to the index of elements. If the user enters
     * an out of bounds index or an invalid character command, an error message 
     * is displayed followed by the prompt. If the user exits, -1 is returned
     * to signal that the user declined to select an element.
     * 
     * 
     * @param title  Displayed above the menu (if not null)
     * @param list   The array of elements to offer in the menu
     * @return       The index of the selected element, or -1 if no selection
     */
    public static int select(Object title, Object[] list) {
        if(list.length < 2) return list.length-1; // trivial selections
        String menu = buildMenu(title.toString(), null, list);
        int selection = -1;
        while(true) {
            System.out.println(menu);
            String input = System.console().readLine();
            try {
                if(input != null) {
                    if(Character.toLowerCase(input.charAt(0)) == EXIT_CHAR) {
                        selection = -1;
                    } else {
                        selection = Integer.parseInt(input);
                        if(selection < 0 || selection >= list.length)
                            throw new ArrayIndexOutOfBoundsException();
                    }
                    break;
                }
            } catch(Exception e) {
                System.err.println("Invalid selection: " + input);
            }
        }
        return selection;
    }
    /**
     * Navigates the filesystem so the user can select a file or directory.
     * 
     * The subdirectories (with a trailing '/') and files in the starting directory
     * that match filter are listed in case-insensitive sort order along with 
     * 4 additional options:
     * (0) .. changes to the parent directory
     * (1) .  returns a File object for the current folder
     * (2) +  prompts for a new subdirectory path, and creates and changes to it
     * (3) ++ prompts for a new filename and returns a File object for it
     * 
     * Otherwise, if a subdirectory is selected it is opened and the method repeats.
     * If a file is selected it is returned. If the user enters an out of bounds index 
     * or an invalid char command, an error message is displayed followed by the prompt. 
     * If the user enters EXIT_CHAR, null is returned to signal that the user declined to 
     * select a file or directory.
     * 
     * @param title    Displayed above the menu (if not null)
     * @param starting The first directory to be listed, or user.home if null
     * @param filter   A FilenameFilter object (filter hidden files if null)
     * @return         The selected File object, or null if no file was selected
     */
    public static File selectFile(Object title, File starting, FilenameFilter filter) {
        if(title == null) title = "";
        File current = (starting != null) ? starting : new File(System.getProperty("user.home"));
        if(filter == null) filter = (dir, name) -> name.charAt(0) != '.';
        int selection = -1;
        boolean done = false; // used to explicitly select a directory
        while(!done && current != null && current.isDirectory()) {
            try {
                File[] files = current.listFiles(filter);
                Arrays.sort(files, (a,b) -> {
                    if( a.isDirectory() && !b.isDirectory()) return -1;
                    if(!a.isDirectory() &&  b.isDirectory()) return  1;
                    return a.getName().compareToIgnoreCase(b.getName());
                });
                String[] filenames = new String[files.length+4];
                filenames[0] = ".. (go up one directory)";
                filenames[1] = ".  (select this directory)";
                filenames[2] = "+  (create new directory and open it)";
                filenames[3] = "++ (specify a new filename here)";
                for(int i=0; i<files.length; ++i) 
                    filenames[i+4] = files[i].getName() + 
                        (files[i].isDirectory() ? File.separator : "");
                selection = select("\n" + title + "\nAt " + current.getPath(), filenames);
                switch(selection) {
                    case -1: current = null; break; // User canceled the selection
                    case  0: current = current.getParentFile(); break; // Up one directory
                    case  1: done = true; break;    // User selected this directory
                    case  2: String sub = System.console().readLine("Enter new subdirectory path: ");
                             File subs = new File(current, sub);
                             subs.mkdirs();  // try to create subdirectories
                             current = subs; // it worked! Switch to it now
                             break;
                    case  3: String file = System.console().readLine("Enter new filename: ");
                             current = new File(current, file);
                             break;
                    default: current = files[selection-4];
                }
            } catch (Exception e) {
                System.err.println("#### Error: " + e);
            }        
        }
        return current;
    }

    /**
     * @param title  Printed before the menu items (if not null)
     * @param data   Printed after the menu items and before the prompt (if not null)
     * @param items  Array of elements to include in the menu
     * @return       String representation of the constructed menu
     */
    private static String buildMenu(Object title, Object data, Object[] items) {
        StringBuilder sb = new StringBuilder(title.toString() + "\n\n");
        for(int i = 0; i < items.length; ++i) {
            String key = "";                        // Begin building the selection key(s)
            char c = ' ';                           // Default char key is ' ' (none)
            if(SHOW_CHAR && items[i] instanceof MenuItem) {
                c = ((MenuItem) items[i]).getKey(); // Get char key (' ' if not specified)
            }
            if(SHOW_INT || c == ' ') {              // Add the index
                key += i;
                if(c != ' ') key += ',';            // Add , if both index & char
            }
            if(c != ' ') key += c;                  // Add char key if available
            sb.append(key + ") " + items[i].toString() + '\n');
        }
        if(data != null) sb.append("\n\n" + data + "\n\n");
        String parenthetical = "('" + EXIT_CHAR + "' to exit";
        if(HELP_CHAR != ' ') parenthetical += ", '" + HELP_CHAR + " [key]' for help";
        sb.append("\nSelection" + parenthetical + "): ");
        return sb.toString();
    }
    /**
     * Formats this menu for presentation to the user.
     */
    @Override
    public String toString() {
        return buildMenu(title, data, menuItems);
    }
    /**
     * Returns the hash code value for this menu.
     *
     * @returns a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, data, menuItems);
    }
    /**
     * Compares the specified object with this menu for equality.
     * <p> 
     * Returns true if and only if the specified object is also a Menu
     * and the title, data, and all menuItems are equal and in the same order.
     * 
     * @param obj  The object to be compared for equality with this Menu
     * @return     True if the specified object is equal to this Menu
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Menu other = (Menu) obj;
        return title.equals(other.title) && 
               data.equals(other.data) &&
               Arrays.equals(menuItems, other.menuItems);
    }
    private Object title;          // Displayed above the menu
    private Object data;           // Displayed between menu and prompt,
                                   //   usually the data object being built by the menu.
    private String help;           // Helpful information for the user
    private MenuItem[] menuItems;  // The menu text and associated callback for each element
    
    private enum Help {NONE, MAIN, SELECTION}; // Tracks level of help requested

}

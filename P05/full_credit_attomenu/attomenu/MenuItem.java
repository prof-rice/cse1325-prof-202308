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

import java.util.Objects;

/**
 * Associates menu text with a response when selected.
 * <p>
 * A character key may be optionally specified for selecting this MenuItem object.
 * If omitted, a numeric key will be generated instead. In addition, a String 
 * containing help text may be provided to be shown to the user on request.
 * <p>
 * MenuItem is philisophically similar to Swing's {@link javax.swing.JMenuItem}, except that
 * MenuItem relies on {@link java.lang.Runnable} rather than {@link java.awt.event.ActionListener}
 * for the response.
 * <p>
 * When printed, MenuItem prints the menuText. Calling {@link run()} invokes the menuResponse.
 *
 * @author             Professor George F. Rice
 * @version            1.1
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 * @see Menu
 */
public class MenuItem implements Runnable {
    /**
     * Initializes a MenuItem object with an optional character key.
     *
     * @param menuText      Text to display when the menu is printed
     * @param menuResponse  Object that delegates to the menu item response
     * @param key           Alternate character to select this menu item
     * @param help          Optional help text
     * @since               1.1
     */
    public MenuItem(Object menuText, Runnable menuResponse, char key, String help) {
        this.menuText = menuText;
        this.menuResponse = menuResponse;
        this.key = key;
        this.help = help;
    }
    /**
     * Initializes a MenuItem object with optional help text.
     *
     * @param menuText      Text to display when the menu is printed
     * @param menuResponse  Object that delegates to the menu item response
     * @param help          Optional help text
     * @since               1.1
     */
    public MenuItem(Object menuText, Runnable menuResponse, String help) {
        this(menuText, menuResponse, ' ', help);
    }
    /**
     * Initializes a MenuItem object with optional help text.
     *
     * @param menuText      Text to display when the menu is printed
     * @param menuResponse  Object that delegates to the menu item response
     * @param key           Alternate character to select this menu item
     * @since               1.1
     */
    public MenuItem(Object menuText, Runnable menuResponse, char key) {
        this(menuText, menuResponse, key, null);
    }
    /**
     * Initializes a MenuItem object with no character key.
     *
     * @param menuText      Text to display when the menu is printed
     * @param menuResponse  Object that delegates to the menu item response
     * @since               1.0
     */
    public MenuItem(Object menuText, Runnable menuResponse) {
        this(menuText, menuResponse, ' ', null);
    }
    /**
     * Returns the alternate char to select this MenuItem (space if unset)
     * 
     * @since               1.1
     */
    public char getKey() {
        return key;
    }
    /**
     * Returns the help text, or null if not set.
     * 
     * @since               1.1
     */
    public String getHelp() {
        return help;
    }
    /**
     * Returns the result of the menuText toString() method.
     */
    @Override
    public String toString() {
        return menuText.toString();
    }
    /**
     * Calls the menuResponse for this menu item.
     */
    @Override
    public void run() {
        menuResponse.run();
    }
    
    private Object menuText;        // The text displayed to the user
    private Runnable menuResponse;  // run() is called on this object when selected
    private char key;               // Alternate character to select this menu item
    private String help;            // Help text

    /**
     * Returns the hash code value for this menuItem.
     *
     * @returns a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(menuText, menuResponse);
    }
    /**
     * Compares the specified object with this menuItem for equality. 
     * <p>
     * Returns true if and only if the specified object is also a MenuItem
     * and the menuText and menuResponse are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MenuItem other = (MenuItem) obj;
        return menuText.equals(other.menuText) && 
               menuResponse.equals(menuResponse);
    }

}

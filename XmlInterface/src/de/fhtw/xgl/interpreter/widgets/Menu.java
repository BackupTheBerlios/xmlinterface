/*
 * Created on 03.06.2004
 *
 */
package de.fhtw.xgl.interpreter.widgets;

import de.fhtw.xgl.interpreter.Widget;

/**
 * Represents the GUI-element Menu.
 * 
 * Implementations of this interface could be written in Swing, AWT, SWT or whatever.
 * The standard-implementation contains a Swing-implementation for this control.
 * 
 * @author Sebastian Heide
 *
 */
public interface Menu extends Widget
{

	/**
	 * Adds a Menu to the <code>MenuBar</code>.
	 * 
	 * @param mnu
	 */
	public void addMenu(Menu mnu);
	
	/**
	 * Returns the <code>Menu</code>-object with the index.
	 * 
	 * @param index of the Menu
	 * @return Menu-object at index
	 */
	public Menu getMenuAtIndex(int index);
	
	/**
	 * Removes the <code>Menu</code> from the MenuBar.
	 * 
	 * @param mnu Menu to remove
	 */
	public void removeMenu(Menu mnu);
	
	/**
	 * Returns the number of menus added to this MenuBar-object.
	 * 
	 * @return number of menus
	 */
	public int getMenuCount();

}

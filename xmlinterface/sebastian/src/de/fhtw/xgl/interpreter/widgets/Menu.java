/*
 * Created on 03.06.2004
 *
 */
package de.fhtw.xgl.interpreter.widgets;

/**
 * Represents the GUI-element Menu.
 * 
 * Implementations of this interface could be written in Swing, AWT, SWT or whatever.
 * The standard-implementation contains a Swing-implementation for this control.
 * 
 * @author Sebastian Heide
 *
 */
public interface Menu extends MenuItem
{

	/**
	 * Adds a Menu to the <code>MenuBar</code>.
	 * 
	 * @param mnu
	 */
	public void addMenu(MenuItem mnu);
	
	/**
	 * Returns the <code>MenuItem</code>-object with the index.
	 * 
	 * @param index of the Menu
	 * @return Menu-object at index
	 */
	public MenuItem getMenuAtIndex(int index);
	
	/**
	 * Removes the <code>MenuItem</code> from the MenuBar.
	 * 
	 * @param mnu Menu to remove
	 */
	public void removeMenu(MenuItem mnu);
	
	/**
	 * Returns the number of menus added to this MenuBar-object.
	 * 
	 * @return number of menus
	 */
	public int getMenuCount();

}

/*
 * Created on 24.05.2004
 *
 */
package de.fhtw.xgl.interpreter.widgets;

import de.fhtw.xgl.interpreter.Widget;

/**
 * Represents the GUI-element Container.
 * 
 * A container ist a widget which can contain other widgets and can be visible or
 * invisible.  
 * 
 * Implementations of this interface could be written in Swing, AWT, SWT or whatever.
 * The standard-implementation contains a Swing-implementation for this control.
 * 
 * @author Sebastian Heide
 *
 */
public interface Container extends Widget
{
	
	/**
	 * Adds a <code>Widget</code> to this container.
	 * The size and location of this <code>Widget</code> is defined
	 * by itself via the methods setSize(int w, int h) and 
	 * setLocation(int x, int y).
	 * 
	 * @param w the Widget to add
	 */
	public void addWidget(Widget w);

	/**
	 * Removes a <code>Widget</code> from this container.
	 * 
	 * @param w the Widget to remove
	 */
	public void removeWidget(Widget w);
	
	/**
	 * Returns the number of <code>Widget</code>-objects of this container.  
	 * 
	 * @return number of Widgets
	 */
	public int getWidgetCount();
	
	/**
	 * Returns the <code>Widget</code> with specified index.
	 * 
	 * @param index
	 * @return Widget-object at index
	 */
	public Widget getWidget(int index);

}

/*
 * Created on 24.05.2004
 *
 */
package de.fhtw.xgl.interpreter.widgets;

import de.fhtw.xgl.interpreter.widgets.Container;

/**
 * Represents the GUI-element Window of Frame or Form or whatever.
 * 
 * Implementations of this interface could be written in Swing, AWT, SWT or whatever.
 * The standard-implementation contains a Swing-implementation for this control.
 * 
 * @author Sebastian Heide
 *
 */
public interface Window extends Container
{
	/**
	 * <code>title</code>-attribute of a window-widget
	 */
	public final static String ATTRIBUTE_TITLE = "title";

	/**
	 * Sets the window's title-attribute to the given String.
	 * 
	 * @param title String to set
	 */
	public void setTitle(String title);

}

/*
 * Created on 24.05.2004
 *
 */
package de.fhtw.xgl.interpreter.widgets;

import de.fhtw.xgl.interpreter.Widget;

/**
 * Represents the GUI-element Button.
 * 
 * Implementations of this interface could be written in Swing, AWT, SWT or whatever.
 * The standard-implementation contains a Swing-implementation for this control.
 * 
 * @author Sebastian Heide
 *
 */
public interface Button extends Widget
{
	/**
	 * <code>text</code>-attribute of a window-widget
	 */
	public final static String ATTRIBUTE_TEXT = "text";
	
	/**
	 * Sets the button's text-attribute to the given String.
	 * 
	 * @param text String to set
	 */
	public void setText(String text);

}

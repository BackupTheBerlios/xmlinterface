/*
 * Created on 24.05.2004
 *
 */
package de.fhtw.xgl.interpreter.widgets;

import de.fhtw.xgl.interpreter.Widget;

/**
 * Represents the GUI-element Textfield.
 * 
 * Implementations of this interface could be written in Swing, AWT, SWT or whatever.
 * The standard-implementation contains a Swing-implementation for this control.
 * 
 * @author Sebastian Heide
 *
 */
public interface Textfield extends Widget
{
	/**
	 * <code>text</code>-attribute of a window-widget
	 */
	public final static String ATTRIBUTE_TEXT = "text";
	/**
	 * <code>editable</code>-attribute of a window-widget
	 */
	public final static String ATTRIBUTE_EDITABLE = "editable";
	/**
	 * <code>scrollable</code>-attribute of a textfield-widget
	 */
	public final static String ATTRIBUTE_SCROLLABLE = "scrollable";
	
	/**
	 * Indicates that scrolling is disabled.
	 */
	public final static int SCROLL_NO = 0;
	/**
	 * Indicates that scrolling is enabled.
	 */
	public final static int SCROLL_BOTH = 1;
	/**
	 * Indicates that horizontal scrolling is enabled.
	 */
	public final static int SCROLL_HORIZ = 2;
	/**
	 * Indicates that vertical scrolling is enabled.
	 */
	public final static int SCROLL_VERT = 3;

	/**
	 * Sets the textfield's text-attribute to the given String.
	 * 
	 * @param text String to set
	 */
	public void setText(String text);

	/**
	 * Defines whether the textfield is editable or not.
	 * 
	 * <ul>
	 *   <li>true - textfield is editable</li>
	 *   <li>false - textfield is not editable</li>
	 * </ul>
	 * 
	 * @param value editable or not
	 */
	public void setEditable(boolean value);

	/**
	 * Defines whether the textfield is scrollable or not.
	 * 
	 * <ul>
	 *   <li>SCROLL_NO</li>
	 *   <li>SCROLL_BOTH</li>
	 *   <li>SCROLL_HORIZ</li>
	 *   <li>SCROLL_VERT</li>
	 * </ul>
	 * 
	 * @param mode a bitwise mask of textfield scroll constants
	 */
	public void setScrollable(int mode);

}

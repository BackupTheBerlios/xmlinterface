/*
 * Created on 24.05.2004
 *
 */
package de.fhtw.xgl.interpreter.widgets;

import de.fhtw.xgl.interpreter.Widget;

/**
 * Represents the GUI-element Checkbox.
 * 
 * Implementations of this interface could be written in Swing, AWT, SWT or whatever.
 * The standard-implementation contains a Swing-implementation for this control.
 * 
 * @author Sebastian Heide
 *
 */
public interface Checkbox extends Widget
{
	/**
	 * <code>checked</code>-attribute of a checkbox-widget
	 */
	public final static String ATTRIBUTE_CHECKED = "checked";
	/**
	 * <code>text</code>-attribute of a checkbox-widget
	 */
	public final static String ATTRIBUTE_TEXT = "text";
	
	/**
	 * Sets the checkbox's checked-attribute to the given value.
	 * 
	 * @param value boolean-value to set
	 */
	public void setChecked(boolean value);
	
	/**
	 * Returns if checkbox is checked.
	 * 
	 * @return boolean-value
	 */
	public boolean isChecked();
	
	/**
	 * Sets the checkbox's text-attribute to the given value.
	 * 
	 * @param text String to set
	 */
	public void setText(String text);
	
	/**
	 * Returns the checkbox's text.
	 * 
	 * @return String-value
	 */
	public String getText();

}

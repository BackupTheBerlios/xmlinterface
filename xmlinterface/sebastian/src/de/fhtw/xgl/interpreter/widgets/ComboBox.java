/*
 * Created on 07.06.2004
 *
 */
package de.fhtw.xgl.interpreter.widgets;

import de.fhtw.xgl.interpreter.Widget;

/**
 * Represents the GUI-element Combobox.
 * 
 * Implementations of this interface could be written in Swing, AWT, SWT or whatever.
 * The standard-implementation contains a Swing-implementation for this control.
 * 
 * @author Sebastian Heide
 *
 */
public interface Combobox extends Widget
{
	/**
	 * <code>option</code>-attribute of a combobox-widget
	 */
	public final static String ATTRIBUTE_OPTION = "option";
	
	/**
	 * Adds an option to the option-list of this combobox.
	 * 
	 * @param option String to set
	 */
	public void addOption(String option);
	
	/**
	 * Returns the number of options availbable at this combobox.
	 * 
	 * @return number of options
	 */
	public int getOptionCount();
	
	/**
	 * Returns the option at the given index.
	 * 
	 * @param index the option's index
	 * @return text of this option
	 */
	public String getOptionAt(int index);
	
	/**
	 * Removes the option with at the given index.
	 * 
	 * @param index the option's index
	 */
	public void removeOptionAt(int index);

}

/*
 * Created on 20.06.2004
 *
 */
package de.fhtw.xgl.interpreter.widgets;

import de.fhtw.xgl.interpreter.Widget;

/**
 * Represents the GUI-element MenuItem.
 * 
 * Implementations of this interface could be written in Swing, AWT, SWT or whatever.
 * The standard-implementation contains a Swing-implementation for this control.
 * 
 * @author Sebastian Heide
 *
 */
public interface MenuItem extends Widget
{
	/**
	 * <code>text</code>-attribute of a menuitem-widget
	 */
	public final static String ATTRIBUTE_TEXT = "text";
	/**
	 * <code>mnemonic</code>-attribute of a menuitem-widget
	 */
	public final static String ATTRIBUTE_MNEMONIC = "mnemonic";
	
	/**
	 * Sets the menuitem's text-attribute to the given String.
	 * 
	 * @param text String to set
	 */
	public void setText(String text);

	/**
	 * Returns the menuitem's text.
	 * 
	 * @return text of the menuitem
	 */
	public String getText();
	
	/**
	 * Sets the menuitem's mnemonic to the given value.
	 * 
	 * @param mnemonic String-representation of a mnemonic e.g. "Alt+H"
	 */
	public void setMnemonic(String mnemonic);
	
	/**
	 * Returns the menuitem's mnemonic.
	 * 
	 * @return String-representation of the mnemonic
	 */
	public String getMnemonicString();

}

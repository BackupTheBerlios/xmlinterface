/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.interpreter;

/**
 * @author Administrator
 *
 */
public interface Callback
{
	
	public String WINDOW_CALLBACK_MAXIMIZE = "maximizeEvent"; 
	public String WINDOW_CALLBACK_MINIMIZE = "minimizeEvent"; 
	public String WINDOW_CALLBACK_CLOSE = "closeEvent"; 
	public String MOUSE_EVENT = "mouseEvent"; 
	
	/**
	 * Returns the callback's name.
	 * 
	 * @return String representing the name.
	 */
	public String getName();

	/**
	 * Returns the callback's type.
	 * 
	 * @return String representing the type.
	 */
	public String getType();
	
	/**
	 * Returns the source of this callback-object.
	 * 
	 * @return Widget
	 */
	public Widget getSource();

}

/*
 * Created on 06.06.2004
 *
 */
package de.fhtw.xgl.interpreter;

/**
 * Defines the connection with the application logic.
 * 
 * A class connecting GUI and logic must implement this interface.<br>
 * GUI-events are delivered through the method handleCallback(Widget widget).
 * 
 * @author Sebastian Heide
 *
 */
public interface CallbackHandler
{
	
	/**
	 * Handels the callback triggered by the specified <code>Widget</code>.
	 * 
	 * @param widget the callback's source
	 */
	public void handleCallback(Widget widget);
	
}

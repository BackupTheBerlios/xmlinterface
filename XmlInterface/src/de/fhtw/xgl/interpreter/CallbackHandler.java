/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.interpreter;

/**
 * @author Administrator
 *
 */
public interface CallbackHandler
{
	
	/**
	 * Main method of CallBackHandler which handles all the events of this GUI.
	 * 
	 * @return
	 */
	void handleCallback(Callback callback);

}

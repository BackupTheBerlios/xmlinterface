/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.example;

// AWT-iimports
import java.awt.Frame;
// Project-imports
import de.fhtw.xgl.interpreter.Callback;
import de.fhtw.xgl.interpreter.CallbackHandler;
import de.fhtw.xgl.interpreter.swing.SwingInterpreter;
import de.fhtw.xgl.interpreter.swing.Window;

/**
 * @author Administrator
 *
 */
public class ExampleCallbackHandler implements CallbackHandler
{

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.CallbackHandler#handleCallBack(de.fhtw.xgl.interpreter.Callback)
	 */
	public void handleCallback(Callback callback)
	{
		if (callback.getSource().getType().equals(SwingInterpreter.WIDGET_TYPE_WINDOW))
		{
			handleWindowCallback(callback);
		}
		else if (callback.getSource().getType().equals(SwingInterpreter.WIDGET_TYPE_BUTTON))
		{
			handleButtonCallback(callback);
		}
	}
	
	private void handleWindowCallback(Callback callback)
	{
		Window w = (Window)callback.getSource();
		if (callback.getType().equals(Callback.WINDOW_CALLBACK_CLOSE))
		{
			w.setVisible(false);
			System.exit(0);
		}
		else if (callback.getType().equals(Callback.WINDOW_CALLBACK_MAXIMIZE))
		{
			w.setState(Frame.MAXIMIZED_BOTH);
		}
		else if (callback.getType().equals(Callback.WINDOW_CALLBACK_MINIMIZE))
		{
			w.setState(Frame.ICONIFIED);
		}
	}
	
	private void handleButtonCallback(Callback callback)
	{
		System.exit(0);
	}

}

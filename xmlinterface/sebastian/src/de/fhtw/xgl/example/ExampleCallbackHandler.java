/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.example;

// AWT-iimports
//import java.awt.Frame;
// Project-imports
import de.fhtw.xgl.interpreter.swing.SwingInterpreter;
import de.fhtw.xgl.interpreter.swing.widgets.SwingWindow;
import de.fhtw.xgl.interpreter.swing.widgets.SwingButton;
import de.fhtw.xgl.interpreter.CallbackHandler;
import de.fhtw.xgl.interpreter.Widget;

/**
 * @author Administrator
 *
 */
public class ExampleCallbackHandler implements CallbackHandler
{

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.CallbackHandler#handleCallBack(de.fhtw.xgl.interpreter.Callback)
	 */
	public void handleCallback(Widget w)
	{
		if (w.getType().equals(SwingInterpreter.WIDGET_TYPE_WINDOW))
		{
			SwingWindow win = (SwingWindow)w;
			handleWindowCallback(win);
		}
		else if (w.getType().equals(SwingInterpreter.WIDGET_TYPE_BUTTON))
		{
			SwingButton cbt = (SwingButton)w;
			handleButtonCallback(cbt);
		}
	}
	
	private void handleWindowCallback(SwingWindow w)
	{
		System.exit(0);
//		SwingWindow w = (SwingWindow)callback.getSource();
//		if (callback.getType().equals(Callback.WINDOW_CALLBACK_CLOSE))
//		{
//			w.setVisible(false);
//			System.exit(0);
//		}
//		else if (callback.getType().equals(Callback.WINDOW_CALLBACK_MAXIMIZE))
//		{
//			w.setState(Frame.MAXIMIZED_BOTH);
//		}
//		else if (callback.getType().equals(Callback.WINDOW_CALLBACK_MINIMIZE))
//		{
//			w.setState(Frame.ICONIFIED);
//		}
	}
	
	private void handleButtonCallback(SwingButton cbt)
	{
		System.exit(0);
	}

}

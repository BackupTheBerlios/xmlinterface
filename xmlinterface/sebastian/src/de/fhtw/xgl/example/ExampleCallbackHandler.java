/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.example;

// Project-imports
import de.fhtw.xgl.interpreter.CallbackHandler;
import de.fhtw.xgl.interpreter.Widget;

/**
 * 
 * 
 * @author Sebastian Heide
 *
 */
public class ExampleCallbackHandler implements CallbackHandler
{
	
	private TestGUIApp tga = null;
	
	public ExampleCallbackHandler(TestGUIApp tga)
	{
		this.tga = tga;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.CallbackHandler#handleCallBack(de.fhtw.xgl.interpreter.Callback)
	 */
	public void handleCallback(Widget w)
	{
		switch (w.getCallbackID())
		{
			case 0:
			case 1:
				System.exit(0);
				break;
			case 11:
				tga.store();
				break;
			default :
				javax.swing.JOptionPane.showMessageDialog(null, "Callback [" + Integer.toString(w.getCallbackID()) + "] ausgelöst!");
				break;
		}
	}

}

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

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.CallbackHandler#getCallbackName(int)
	 */
	public String getCallbackName(int callbackId)
	{
		String name = "";
		switch (callbackId)
		{
			case 0:
				name = "Fenster schließen";
				break;
			case 1:
				name = "Programm beenden";
				break;
			case 2:
				name = "Hilfe/Inhalt";
				break;
			case 3:
				name = "Hilfe/Suchen";
				break;
			case 4:
				name = "Hilfe/Über";
				break;
			case 5:
				name = "Textfeld";
				break;
			case 6:
				name = "Geschlecht/männlich";
				break;
			case 7:
				name = "Geschlecht/weiblich";
				break;
			case 8:
				name = "Beruf";
				break;
			case 9:
				name = "Abschicken";
				break;
			case 10:
				name = "Zurücksetzen";
				break;
			case 11:
				name = "Oberfläche speichern";
				break;
		}
		return name;
	}

}

/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.example;

// Project-imports
import de.fhtw.xgl.interpreter.CallbackHandler;
import de.fhtw.xgl.interpreter.Interpreter;
import de.fhtw.xgl.interpreter.Widget;
import de.fhtw.xgl.interpreter.swing.widgets.SwingTextfield;
import de.fhtw.xgl.interpreter.swing.widgets.SwingCheckbox;

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
			case 9 :
				Interpreter i = tga.getInterpreter();
				Widget w1 = i.getWidgetById(5);
				String message = "Das Formular wurde mit folgenden Werten abgeschickt:\n";
				SwingTextfield t = (SwingTextfield)w1;
				message += "Name: " + t.getText() + "\n";
				w1 = i.getWidgetById(6);
				t = (SwingTextfield)w1;
				message += "Alter: " + t.getText() + "\n";
				w1 = i.getWidgetById(7);
				SwingCheckbox c = (SwingCheckbox)w1;
				if (!c.isChecked())
				{
					w1 = i.getWidgetById(8);
					c = (SwingCheckbox)w1;
					if (c.isChecked()) message += "Geschlecht: weiblich";
				}
				else
					message += "Geschlecht: männlich";
				javax.swing.JOptionPane.showMessageDialog(null, message);
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
				name = "Name";
				break;
			case 6:
				name = "Alter";
				break;
			case 7:
				name = "Geschlecht männlich";
				break;
			case 8:
				name = "Geschlecht weiblich";
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

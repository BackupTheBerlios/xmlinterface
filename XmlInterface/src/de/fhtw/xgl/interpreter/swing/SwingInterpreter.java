/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.interpreter.swing;

// XML-imports
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
// Project-imports
import de.fhtw.xgl.interpreter.CallbackHandler;
import de.fhtw.xgl.interpreter.Callback;
import de.fhtw.xgl.interpreter.XglInterpreterException;

/**
 * @author Administrator
 *
 */
public class SwingInterpreter implements de.fhtw.xgl.interpreter.Interpreter
{
	
	private CallbackHandler cbh = null;
	
	/**
	 * Constructs a standard-interpreter without a CallbackHandler.  
	 *
	 */
	public SwingInterpreter()
	{
		this(null);
	}
	
	/**
	 * Constructs a standard-interpreter without a CallbackHandler.  
	 * 
	 * @param cbh CallbackHanlder-object
	 */
	public SwingInterpreter(CallbackHandler cbh)
	{
		this.cbh = cbh;
	}
	
	public void sendCallback(Callback callback)
	{
		if (cbh != null) cbh.handleCallback(callback);
	}
	
	public void parseDocument(Document doc) throws XglInterpreterException
	{
		if (!doc.getFirstChild().getNodeName().equals(XML_ROOT_ELEMENT))
			throw new XglInterpreterException("Root-Element('" + XML_ROOT_ELEMENT + "') not found! Element: '" + doc.getFirstChild().getNodeName() + "'");
		NodeList nl = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < nl.getLength(); i++)
		{
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
			{
				Window w = new Window(nl.item(i), this);
				w.setVisible(true);
			}
		}
	}
		
		
}

/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.interpreter.swing;

// XML-imports
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
// Util-imports
import java.util.HashMap;
// Project-imports
import de.fhtw.xgl.interpreter.XglInterpreterException;
import de.fhtw.xgl.interpreter.CallbackHandler;
import de.fhtw.xgl.interpreter.swing.widgets.SwingWindow;
import de.fhtw.xgl.interpreter.Widget;


/**
 * @author Administrator
 *
 */
public class SwingInterpreter implements de.fhtw.xgl.interpreter.Interpreter
{
	
	private CallbackHandler cbh = null;
	
	private HashMap widgets = null;
	
	/**
	 * Constructs a standard-interpreter without a CallbackHandler.  
	 *
	 */
	public SwingInterpreter()
	{
		this(null);
	}

	/**
	 * Constructs a <code>SwingInterpreter<code> without the given CallbackHandler.  
	 *
	 */
	public SwingInterpreter(CallbackHandler cbh)
	{
		this.cbh = cbh;
		widgets = new HashMap();
	}
	
	public void handleEvent(Widget w)
	{
		cbh.handleCallback(w);
	}
	
	public void parseDocument(Document doc) throws XglInterpreterException
	{
		if (!doc.getFirstChild().getNodeName().equals(XML_ROOT_ELEMENT))
			throw new XglInterpreterException("Root-Element('" + XML_ROOT_ELEMENT + "') not found! Element: '" + doc.getFirstChild().getNodeName() + "'");
		NodeList nl = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < nl.getLength(); i++)
		{
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE && nl.item(i).getNodeName().equals(XML_ELEMENT_WIDGETS))
			{
				Node n = nl.item(i);
				NodeList nl1 = n.getChildNodes();
				for (int j = 0; j < nl1.getLength(); j++)
				{
					if (nl1.item(j).getNodeType() == Node.ELEMENT_NODE)
					{
						SwingWindow w = new SwingWindow(nl1.item(j), this);
						w.setVisible(true);
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Interpreter#setCallbackHandler(de.fhtw.xgl.interpreter.CallbackHandler)
	 */
	public void setCallbackHandler(CallbackHandler cbh)
	{
		this.cbh = cbh;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Interpreter#getCallbackHandler()
	 */
	public CallbackHandler getCallbackHandler()
	{
		return cbh;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Interpreter#getWidgetById(java.lang.String)
	 */
	public Widget getWidgetById(int id)
	{
		return (Widget)widgets.get(new Integer(id));
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Interpreter#addWidget(java.lang.String, de.fhtw.xgl.interpreter.Widget)
	 */
	public void addWidget(int id, Widget w)
	{
		widgets.put(new Integer(id), w);
	}
		
		
}

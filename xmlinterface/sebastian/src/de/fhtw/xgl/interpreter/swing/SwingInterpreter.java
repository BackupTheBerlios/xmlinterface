/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.interpreter.swing;

// XML-imports
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
// Util-imports
import java.util.HashMap;
// AWT-imports
import java.awt.Component;
import java.awt.event.KeyEvent;
// Project-imports
import de.fhtw.xgl.interpreter.XglInterpreterException;
import de.fhtw.xgl.interpreter.CallbackHandler;
import de.fhtw.xgl.interpreter.swing.widgets.SwingWindow;
import de.fhtw.xgl.interpreter.swing.guiEditor.GuiEditor;
import de.fhtw.xgl.interpreter.Widget;


/**
 * @author Administrator
 *
 */
public class SwingInterpreter 
	implements 
		de.fhtw.xgl.interpreter.Interpreter,
		java.awt.event.KeyListener
{
	
	private CallbackHandler cbh = null;
	private SwingWindow parentWindow = null;
	private boolean magic;
	private HashMap attributes = null;
	
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
	 * Constructs a <code>SwingInterpreter</code> without the given CallbackHandler.  
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
		// retrieve all childnodes of the document
		NodeList nodes = doc.getChildNodes();
		Node rootNode = null;
		attributes = new HashMap();
		// iteration through the childnodes to get the first element-node (root-widget)
		for (int k = 0; k < nodes.getLength(); k++)
		{
			// only read if no root-widget is set by now
			if (rootNode == null)
			{
				rootNode = nodes.item(k);
				if (rootNode.getNodeType() == Node.ELEMENT_NODE)
				{
					// if wrong root-element in xml-tree
					if (!rootNode.getNodeName().equals(XML_ROOT_ELEMENT))
						throw new XglInterpreterException("Root-Element('" + XML_ROOT_ELEMENT + "') not found! Element: '" + doc.getFirstChild().getNodeName() + "'");
					// read attrbiutes
					if (doc.getFirstChild().hasAttributes())
					{
						for (int i = 0; i < doc.getFirstChild().getAttributes().getLength(); i++)
						{
							Node attr = doc.getFirstChild().getAttributes().item(i);
							if (attr.getNodeType() == Node.ATTRIBUTE_NODE)
							{
								if (attr.getNodeName().equals(XML_ATTRIBUTE_MAGIC))
								{
									magic = new Boolean(attr.getNodeValue()).booleanValue();
								}
								// for unknown attributes
								else attributes.put(attr.getNodeName(), attr.getNodeValue());
							}
						}
					}
					// read children
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
									// first must be window.widget
									parentWindow = new SwingWindow(nl1.item(j), this);
									parentWindow.setVisible(true);
								}
							}
						}
					}
				}
			}
		}
	}
//
//	Action magicAction = new AbstractAction() {
//		public void actionPerformed(ActionEvent e) {
//			//do nothing
//		}
//	};
	
	public Node storeDocument(Document doc)
	{
		Element el = doc.createElement(XML_ROOT_ELEMENT);
		
		// write the 'magic'-attribute
		el.setAttribute(XML_ATTRIBUTE_MAGIC, Boolean.toString(magic));
		
		// write unknown attributes
		String key = null;
		for (int i = 0; i < attributes.size(); i++)
		{
			key = attributes.keySet().iterator().next().toString();
			el.setAttribute(key, attributes.get(key).toString());
		}

		// write the widgets
		Element elWidgets = doc.createElement(XML_ELEMENT_WIDGETS);
		elWidgets.appendChild(parentWindow.store(doc));
		el.appendChild(elWidgets);

		return el;
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
		Component c = (Component)w;
		c.addKeyListener(this);
		widgets.put(new Integer(id), w);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e)
	{
		System.out.println("key");
		if (e.isShiftDown() && e.isAltDown() && e.getKeyChar() == 'F')
		{
			GuiEditor ge = new GuiEditor(parentWindow);
			e.consume();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e)
	{
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e)
	{
	}
		
		
}

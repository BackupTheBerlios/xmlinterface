/*
 * Created on 06.05.2004
 *
 */
package de.fhtw.xgl.interpreter.swing;

// AWT-imports
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
// Swing-imports
import javax.swing.JPanel;
// XML-imports
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
// Project-imports
import de.fhtw.xgl.interpreter.Interpreter;
import de.fhtw.xgl.interpreter.Callback;
import de.fhtw.xgl.interpreter.Widget;

/**
 * @author Administrator
 *
 */
public class Container 	extends 
							JPanel 
						implements 
							Widget, 
							ComponentListener
{
	
	private Interpreter interpreter = null;
	private Callback[] callbacks = null;
	
	public Container()
	{
		this(null);
	}
	
	public Container(Node node)
	{
		this(node, null);
	}
	
	public Container(Node node, Interpreter i)
	{
		super(null);
		setInterpreter(i);
		load(node);
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#load(org.w3c.dom.Node)
	 */
	public boolean load(Node node)
	{
		if (node == null) return false;
		// reference to all childnodes
		NodeList nodeList = node.getChildNodes();
		Node child = null;
		Node attr = null;
		// iterate through all attributes
		for (int i = 0; i < node.getAttributes().getLength(); i++)
		{
			
			attr = node.getAttributes().item(i);
			if (attr.getNodeType() == Node.ATTRIBUTE_NODE)
			{
				if (attr.getNodeName().equals(XML_ATTRIBUTE_NAME))
				{
					this.setName(attr.getNodeValue());
				}
				else if (attr.getNodeName().equals(XML_ATTRIBUTE_TYPE))
				{
					// exit if wrong widget-type
					if (!attr.getNodeValue().equals(getType())) return false;
				}
			}
		}
		// iterate through all attributes
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			child = nodeList.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE)
			{
				if (child.getNodeName().equals(SwingInterpreter.XML_ELEMENT_WIDGETS))
				{
					loadWidgets(child);
				}
				else if (child.getNodeName().equals(SwingInterpreter.XML_ELEMENT_PROPERTIES))
				{
					loadProperties(child);
				}
				else if (child.getNodeName().equals(SwingInterpreter.XML_ELEMENT_EVENTS))
				{
					loadEvents(child);
				}
			}
		}
		return true;
	}
	
	/**
	 * loads all the object's properties from the given XML-node
	 * @param node property-node
	 */
	private void loadProperties(Node node)
	{
		int width = 0;
		int height = 0;
		int xCoord = 0;
		int yCoord = 0;
		NodeList nodeList = node.getChildNodes();
		Node child = null;
		Node attr = null;
		for (int i = 0; i < nodeList.getLength(); i ++)
		{
			child = nodeList.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE)
			{
				if (child.getAttributes() != null)
					for (int j = 0; j < child.getAttributes().getLength(); j++)
					{
						attr = child.getAttributes().item(j);
						if (attr.getNodeType() == Node.ATTRIBUTE_NODE)
						{
							if (attr.getNodeName().equals("name"))
							{
								if (attr.getNodeValue().equals(ATTRIBUTE_WIDTH))
									width = new Integer(child.getFirstChild().getNodeValue()).intValue();
								if (attr.getNodeValue().equals(ATTRIBUTE_HEIGHT))
									height = new Integer(child.getFirstChild().getNodeValue()).intValue();
								if (attr.getNodeValue().equals(ATTRIBUTE_X_COORD))
									xCoord = new Integer(child.getFirstChild().getNodeValue()).intValue();
								if (attr.getNodeValue().equals(ATTRIBUTE_Y_COORD))
									yCoord = new Integer(child.getFirstChild().getNodeValue()).intValue();
							} // if attr = "name"
						} // if attr = ATTRIBUTE_NODE
					} // Attributes iteration
			} // if Node = ELEMENT_NODE
		} // NodeList iteration
		setBounds(xCoord, yCoord, width, height);
	}
	
	private void loadWidgets(Node node)
	{
		NodeList nodeList = node.getChildNodes();
		Node child = null;
		Node attr = null;
		for (int i = 0; i < nodeList.getLength(); i ++)
		{
			child = nodeList.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE)
			{
				if (child.getAttributes() != null)
					for (int j = 0; j < child.getAttributes().getLength(); j++)
					{
						attr = child.getAttributes().item(j);
						if (attr.getNodeType() == Node.ATTRIBUTE_NODE)
						{
							if (attr.getNodeName().equals("type"))
							{
								if (attr.getNodeValue().equals(Interpreter.WIDGET_TYPE_TEXTFIELD))
									add(new Textfield(child, interpreter));
								if (attr.getNodeValue().equals(Interpreter.WIDGET_TYPE_BUTTON))
									add(new Button(child, interpreter));
								if (attr.getNodeValue().equals(Interpreter.WIDGET_TYPE_CONTAINER))
									add(new Container(child, interpreter));
							} // if attr = "name"
						} // if attr = ATTRIBUTE_NODE
					} // Attributes iteration
			} // if Node = ELEMENT_NODE
		} // NodeList iteration
	}
	
	private void loadEvents(Node node)
	{
		String eventName = "";
		String eventType = "";
		NodeList nodeList = node.getChildNodes();
		Node child = null;
		Node attr = null;
		for (int i = 0; i < nodeList.getLength(); i ++)
		{
			child = nodeList.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE)
			{
				if (child.getAttributes() != null)
					for (int j = 0; j < child.getAttributes().getLength(); j++)
					{
						attr = child.getAttributes().item(j);
						if (attr.getNodeType() == Node.ATTRIBUTE_NODE)
						{
							if (attr.getNodeName().equals("name"))
								eventName = attr.getNodeValue();
							if (attr.getNodeName().equals("type"))
								eventType = attr.getNodeValue();
						} // if attr = ATTRIBUTE_NODE
					} // Attributes iteration
				addCallback(new SwingCallback(eventName, eventType, this));
			} // if Node = ELEMENT_NODE
			eventName = "";
			eventType = "";
		} // NodeList iteration
	}
	
	/**
	 * Adds a callback to the windows callbacks-list
	 * 
	 * @param callback to add
	 */
	private void addCallback(Callback callback)
	{
		if (callbacks == null)
		{
			callbacks = new Callback[1];
			callbacks[0] = callback;
		}
		else
		{
			Callback[] c = new Callback[callbacks.length + 1];
			for (int i = 0; i < callbacks.length; i++)
				c[i] = callbacks[i];
			c[c.length - 1] = callback;
			callbacks = c;
		}
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#store(org.w3c.dom.Document)
	 */
	public Node store(Document doc)
	{
		Element el = doc.createElement(XML_NODE_NAME);

		// set the widget's attributes
		el.setAttribute(XML_ATTRIBUTE_TYPE, getType());
		el.setAttribute(XML_ATTRIBUTE_NAME, getName());
		
		// not yet implemented
		el.setAttribute(XML_ATTRIBUTE_UI_TYPE, "");

		el.appendChild(storeProperties(doc));

		Element elWidgets = doc.createElement(Interpreter.XML_ELEMENT_WIDGETS);
		Button b = new Button();
		for (int i = 0; i < getComponentCount(); i++)
		{
			if (getComponent(i).getClass().getName().startsWith("de.fhtw.xgl.interpreter.swing"))
			{
				Widget w = (Widget)getComponent(i);
				elWidgets.appendChild(w.store(doc));
			}
		}
		el.appendChild(elWidgets);

		return el;
	}

	private Node storeProperties(Document doc)
	{
		Element el = doc.createElement(SwingInterpreter.XML_ELEMENT_PROPERTIES);

		Element elProperty = doc.createElement(SwingInterpreter.XML_ELEMENT_PROPERTY);
		elProperty.setAttribute("name", ATTRIBUTE_X_COORD);
		elProperty.appendChild(doc.createTextNode(new Integer(getX()).toString()));
		el.appendChild(elProperty);

		elProperty = doc.createElement(SwingInterpreter.XML_ELEMENT_PROPERTY);
		elProperty.setAttribute("name", ATTRIBUTE_Y_COORD);
		elProperty.appendChild(doc.createTextNode(new Integer(getY()).toString()));
		el.appendChild(elProperty);

		elProperty = doc.createElement(SwingInterpreter.XML_ELEMENT_PROPERTY);
		elProperty.setAttribute("name", ATTRIBUTE_WIDTH);
		elProperty.appendChild(doc.createTextNode(new Integer(getWidth()).toString()));
		el.appendChild(elProperty);

		elProperty = doc.createElement(SwingInterpreter.XML_ELEMENT_PROPERTY);
		elProperty.setAttribute("name", ATTRIBUTE_HEIGHT);
		elProperty.appendChild(doc.createTextNode(new Integer(getHeight()).toString()));
		el.appendChild(elProperty);
		
		return el;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#getType()
	 */
	public String getType()
	{
		return Interpreter.WIDGET_TYPE_CONTAINER;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#getComponent()
	 */
	public Component getComponent()
	{
		return this;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
	 */
	public void componentResized(ComponentEvent e)
	{
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
	 */
	public void componentMoved(ComponentEvent e)
	{
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
	 */
	public void componentShown(ComponentEvent e)
	{
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
	 */
	public void componentHidden(ComponentEvent e)
	{
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#setInterpreter(de.fhtw.xgl.interpreter.Interpreter)
	 */
	public void setInterpreter(Interpreter i)
	{
		interpreter = i;
	}

}

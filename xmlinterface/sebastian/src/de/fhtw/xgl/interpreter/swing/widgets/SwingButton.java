/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.interpreter.swing.widgets;

// XML-imports
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
// Swing-imports
import javax.swing.JButton;
// Project-imports
import de.fhtw.xgl.interpreter.widgets.Button;
import de.fhtw.xgl.interpreter.Interpreter;
import de.fhtw.xgl.interpreter.swing.SwingInterpreter;

/**
 * @author Administrator
 *
 */
public class SwingButton extends 
						JButton 
					implements 
						Button,
						ActionListener
{
	
	private Interpreter interpreter = null;
	private int id = 0;
	private int callbackID = CALLBACK_ID_UNDEFINED;
	
	public SwingButton()
	{
		super();
	}
	
	public SwingButton(String text)
	{
		super(text);
	}
	
	public SwingButton(Node node)
	{
		this(node, null);
	}
	
	public SwingButton(Node node, Interpreter i)
	{
		setInterpreter(i);
		load(node);
		addActionListener(this);
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
				if (attr.getNodeName().equals(XML_ATTRIBUTE_ID))
				{
					id = Integer.parseInt(attr.getNodeValue());
				}
				else if (attr.getNodeName().equals(XML_ATTRIBUTE_TYPE))
				{
					// exit if wrong widget-type
					if (!attr.getNodeValue().equals(getType())) return false;
				}
				else if (attr.getNodeName().equals(XML_ATTRIBUTE_CALLBACK_ID))
				{
					callbackID = Integer.parseInt(attr.getNodeValue());
				}
			}
		}
		if (interpreter != null) interpreter.addWidget(id, this);
		// iterate through all attributes
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			child = nodeList.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE)
			{
				if (child.getNodeName().equals(SwingInterpreter.XML_ELEMENT_WIDGETS))
				{
					// not yet implemented
				}
				else if (child.getNodeName().equals(SwingInterpreter.XML_ELEMENT_PROPERTIES))
				{
					loadProperties(child);
				}
				else if (child.getNodeName().equals(SwingInterpreter.XML_ELEMENT_EVENTS))
				{
//					loadEvents(child);
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
								if (attr.getNodeValue().equals(ATTRIBUTE_TEXT))
									setText(child.getFirstChild().getNodeValue());
							} // if attr = "name"
						} // if attr = ATTRIBUTE_NODE
					} // Attributes iteration
			} // if Node = ELEMENT_NODE
		} // NodeList iteration
		setBounds(xCoord, yCoord, width, height);
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#store(org.w3c.dom.Document)
	 */
	public Node store(Document doc)
	{
		Element el = doc.createElement(XML_NODE_NAME);

		// set the widget's attributes
		el.setAttribute(XML_ATTRIBUTE_ID, new Integer(getId()).toString());
		el.setAttribute(XML_ATTRIBUTE_TYPE, getType());
		if (callbackID != CALLBACK_ID_UNDEFINED) el.setAttribute(XML_ATTRIBUTE_CALLBACK_ID, new Integer(getCallbackID()).toString());
		
		// not yet implemented
		el.setAttribute(XML_ATTRIBUTE_UI_TYPE, "");

		el.appendChild(storeProperties(doc));

		return el;
	}
	
	/**
	 * stores all the object's properties into the XML-node
	 * @param doc the node's XML-document
	 * @return XML-node-object
	 */
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

		elProperty = doc.createElement(SwingInterpreter.XML_ELEMENT_PROPERTY);
		elProperty.setAttribute("name", ATTRIBUTE_TEXT);
		if (getText() != null)
			elProperty.appendChild(doc.createTextNode(getText()));
		else
			elProperty.appendChild(doc.createTextNode(""));
		el.appendChild(elProperty);
		
		return el;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#getType()
	 */
	public String getType()
	{
		return SwingInterpreter.WIDGET_TYPE_BUTTON;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#getComponent()
	 */
	public Component getComponent()
	{
		return this;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#setInterpreter(de.fhtw.xgl.interpreter.Interpreter)
	 */
	public void setInterpreter(Interpreter i)
	{
		interpreter = i;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (callbackID != CALLBACK_ID_UNDEFINED && interpreter != null) interpreter.handleEvent(this);
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#getId()
	 */
	public int getId()
	{
		return id;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#setCallbackID(int)
	 */
	public void setCallbackID(int id)
	{
		callbackID = id;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#getCallbackID()
	 */
	public int getCallbackID()
	{
		return callbackID;
	}

}

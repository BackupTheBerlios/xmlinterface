/*
 * Created on 07.06.2004
 *
 */
package de.fhtw.xgl.interpreter.swing.widgets;

import javax.swing.JMenu;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import de.fhtw.xgl.interpreter.Interpreter;
import de.fhtw.xgl.interpreter.widgets.Menu;
import de.fhtw.xgl.interpreter.widgets.MenuItem;

import javax.swing.JMenuItem;

/**
 * @author Sebastian Heide
 *
 */
public class SwingMenu extends JMenu implements Menu
{
	
	private Interpreter interpreter = null;
	private int id = 0;
	private int callbackID = CALLBACK_ID_UNDEFINED;
	
	public SwingMenu(String text)
	{
		this(null, null);
	}
	
	public SwingMenu(Node node, Interpreter interpreter)
	{
		super();
		this.interpreter = interpreter;
		load(node);
	}
	
	public SwingMenu(Node node)
	{
		this(node, null);
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
				if (child.getNodeName().equals(Interpreter.XML_ELEMENT_WIDGETS))
				{
					loadWidgets(child);
				}
				else if (child.getNodeName().equals(Interpreter.XML_ELEMENT_PROPERTIES))
				{
					loadProperties(child);
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
								if (attr.getNodeValue().equals(ATTRIBUTE_TEXT))
									setText(child.getFirstChild().getNodeValue());
								else if (attr.getNodeValue().equals(ATTRIBUTE_MNEMONIC))
									setMnemonic(child.getFirstChild().getNodeValue());
							} // if attr = "name"
						} // if attr = ATTRIBUTE_NODE
					} // Attributes iteration
			} // if Node = ELEMENT_NODE
		} // NodeList iteration
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
								if (attr.getNodeValue().equals(Interpreter.WIDGET_TYPE_MENU))
									addMenu(new SwingMenu(child, interpreter));
								else if (attr.getNodeValue().equals(Interpreter.WIDGET_TYPE_MENU_ITEM))
									addMenu(new SwingMenuItem(child, interpreter));
							} // if attr = "name"
						} // if attr = ATTRIBUTE_NODE
					} // Attributes iteration
			} // if Node = ELEMENT_NODE
		} // NodeList iteration
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

		Element elWidgets = doc.createElement(Interpreter.XML_ELEMENT_WIDGETS);
		for (int i = 0; i < getMenuCount(); i++)
		{
			MenuItem m = getMenuAtIndex(i);
			elWidgets.appendChild(m.store(doc));
		}
		el.appendChild(elWidgets);

		return el;
	}
	
	/**
	 * stores all the object's properties into the XML-node
	 * @param doc the node's XML-document
	 * @return XML-node-object
	 */
	private Node storeProperties(Document doc)
	{
		Element el = doc.createElement(Interpreter.XML_ELEMENT_PROPERTIES);

		Element elProperty = doc.createElement(Interpreter.XML_ELEMENT_PROPERTY);
		elProperty.setAttribute("name", ATTRIBUTE_TEXT);
		elProperty.appendChild(doc.createTextNode(getText()));
		el.appendChild(elProperty);

		if (getMnemonicString() != null)
		{
			elProperty = doc.createElement(Interpreter.XML_ELEMENT_PROPERTY);
			elProperty.setAttribute("name", ATTRIBUTE_MNEMONIC);
			elProperty.appendChild(doc.createTextNode(getMnemonicString()));
			el.appendChild(elProperty);
		}
		
		return el;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#getType()
	 */
	public String getType()
	{
		return Interpreter.WIDGET_TYPE_MENU;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#setInterpreter(de.fhtw.xgl.interpreter.Interpreter)
	 */
	public void setInterpreter(Interpreter i)
	{
		interpreter = i;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.Menu#addMenu(de.fhtw.xgl.interpreter.widgets.Menu)
	 */
	public void addMenu(MenuItem mnu)
	{
		add((JMenuItem)mnu);	
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.Menu#getMenuAtIndex(int)
	 */
	public MenuItem getMenuAtIndex(int index)
	{
		return (MenuItem)getItem(index);
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.Menu#removeMenu(de.fhtw.xgl.interpreter.widgets.Menu)
	 */
	public void removeMenu(MenuItem mnu)
	{
		this.remove((JMenuItem)mnu);
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.Menu#getMenuCount()
	 */
	public int getMenuCount()
	{
		return getMenuComponentCount();
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

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.MenuItem#setMnemonic(java.lang.String)
	 */
	public void setMnemonic(String mnemonic)
	{
		char m = mnemonic.charAt(mnemonic.length() - 1);
		setMnemonic((int)m);
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.MenuItem#getMnemonicString()
	 */
	public String getMnemonicString()
	{
		// null, wenn kein Mnemonic gesetzt!
		if (getMnemonic() == 0)
			return null;
		String m = "Alt+";
		m += (char)getMnemonic();
		return m;
	}

}

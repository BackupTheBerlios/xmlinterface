/*
 * Created on 07.06.2004
 *
 */
package de.fhtw.xgl.interpreter.swing.widgets;

import javax.swing.JMenuBar;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import de.fhtw.xgl.interpreter.Interpreter;
import de.fhtw.xgl.interpreter.Widget;
import de.fhtw.xgl.interpreter.widgets.Menu;
import de.fhtw.xgl.interpreter.widgets.MenuBar;

/**
 * Represents the Swing-Instance of <code>{@link MenuBar MenuBar}</code>.
 * 
 * @author Administrator
 *
 */
public class SwingMenuBar extends JMenuBar implements MenuBar
{
	
	private Interpreter interpreter = null;
	private int id = 0;
	private int callbackID = 0;
	
	public SwingMenuBar()
	{
		this(null);
	}
	
	public SwingMenuBar(Node node)
	{
		this(node, null);
	}
	
	public SwingMenuBar(Node node, Interpreter interpreter)
	{
		super();
		this.interpreter = interpreter;
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
				}
			}
		}
		return true;
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
		el.setAttribute(XML_ATTRIBUTE_CALLBACK_ID, new Integer(getId()).toString());
		
		// not yet implemented
		el.setAttribute(XML_ATTRIBUTE_UI_TYPE, "");

//		el.appendChild(storeProperties(doc));

		Element elWidgets = doc.createElement(Interpreter.XML_ELEMENT_WIDGETS);
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
							} // if attr = "name"
						} // if attr = ATTRIBUTE_NODE
					} // Attributes iteration
			} // if Node = ELEMENT_NODE
		} // NodeList iteration
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#getType()
	 */
	public String getType()
	{
		return Interpreter.WIDGET_TYPE_MENU_BAR;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#setInterpreter(de.fhtw.xgl.interpreter.Interpreter)
	 */
	public void setInterpreter(Interpreter i)
	{
		interpreter = i;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.MenuBar#addMenu(de.fhtw.xgl.interpreter.widgets.Menu)
	 */
	public void addMenu(Menu mnu)
	{
		SwingMenu m = (SwingMenu)mnu;
		add(m);
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.MenuBar#getMenuAtIndex(int)
	 */
	public Menu getMenuAtIndex(int index)
	{
		return (Menu)getMenu(index);
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

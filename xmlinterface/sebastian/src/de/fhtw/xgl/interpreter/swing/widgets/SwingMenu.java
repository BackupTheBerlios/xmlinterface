/*
 * Created on 07.06.2004
 *
 */
package de.fhtw.xgl.interpreter.swing.widgets;

import javax.swing.JMenu;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import de.fhtw.xgl.interpreter.Interpreter;
import de.fhtw.xgl.interpreter.widgets.Menu;

/**
 * @author Administrator
 *
 */
public class SwingMenu extends JMenu implements Menu
{
	
	private Interpreter interpreter = null;
	private int id = 0;
	private int callbackID = 0;
	
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
		return false;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#store(org.w3c.dom.Document)
	 */
	public Node store(Document doc)
	{
		// TODO Auto-generated method stub
		return null;
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
	public void addMenu(Menu mnu)
	{
		
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.Menu#getMenuAtIndex(int)
	 */
	public Menu getMenuAtIndex(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.Menu#removeMenu(de.fhtw.xgl.interpreter.widgets.Menu)
	 */
	public void removeMenu(Menu mnu)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.Menu#getMenuCount()
	 */
	public int getMenuCount()
	{
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#getCallbackID()
	 */
	public int getCallbackID()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}

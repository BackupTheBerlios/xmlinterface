/*
 * Created on 07.06.2004
 *
 */
package de.fhtw.xgl.interpreter.swing.widgets;

import javax.swing.JMenuBar;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import de.fhtw.xgl.interpreter.Interpreter;
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

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Widget#load(org.w3c.dom.Node)
	 */
	public boolean load(Node node)
	{
		// TODO Auto-generated method stub
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
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.widgets.MenuBar#removeMenu(de.fhtw.xgl.interpreter.widgets.Menu)
	 */
	public void removeMenu(Menu mnu)
	{
		// TODO Auto-generated method stub
	}
	
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

}

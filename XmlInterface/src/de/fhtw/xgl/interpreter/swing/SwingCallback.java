/*
 * Created on 06.05.2004
 *
 */
package de.fhtw.xgl.interpreter.swing;

import de.fhtw.xgl.interpreter.Callback;
import de.fhtw.xgl.interpreter.Widget;

/**
 * @author Administrator
 *
 */
public class SwingCallback implements Callback
{
	
	private String name = null;
	private String type = null;
	private Widget widget = null;
	
	public SwingCallback()
	{
		this("", "");
	}
	
	public SwingCallback(String name, String type)
	{
		this.name = name;
		this.type = type;
	}

	public SwingCallback(String name, String type, Widget w)
	{
		this.name = name;
		this.type = type;
		this.widget = w;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Callback#getName()
	 */
	public String getName()
	{
		return name;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Callback#getType()
	 */
	public String getType()
	{
		return type;
	}

	/* (non-Javadoc)
	 * @see de.fhtw.xgl.interpreter.Callback#getSource()
	 */
	public Widget getSource()
	{
		return widget;
	}
	
	public String toString()
	{
		String str = "SwingCallback[";
		str += "name=" + getName() + ",";
		str += "type=" + getType() + ",";
		str += "widget=" + widget.toString() + "]";
		return str;
	}

}

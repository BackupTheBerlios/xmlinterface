package de.fhtw.xgl.interpreter.swing.guiEditor;
import java.awt.*;
import java.awt.event.*;

class Context extends PopupMenu
{
  public Menu sub;
  public MenuItem mi;
  ActionListener listener;

	public Context(ActionListener lst)
	{
		listener = lst;
	}
	
	public void addItem(String item)
	{
		mi = new MenuItem(item);
	    mi.addActionListener(listener);
	    add(mi);
	}
	
	public void addSubItem(Component currentCMP)
	{
		mi = new MenuItem(currentCMP.getName());
		mi.setLabel(currentCMP.getName());
		mi.setActionCommand("add:"+currentCMP.getName()+":"+currentCMP.getClass().getName());
	    mi.addActionListener(listener);
	    sub.add(mi);
	}
}

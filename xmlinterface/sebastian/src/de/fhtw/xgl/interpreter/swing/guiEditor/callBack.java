package de.fhtw.xgl.interpreter.swing.guiEditor;
/*
 * Created on 20.06.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Stefan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class callBack {
	String cId, label;
	public callBack(String cId, String label)
	{
		this.cId = cId;
		this.label = label;
	}
	
	public String toString()
	{
		return this.label;
	}
}

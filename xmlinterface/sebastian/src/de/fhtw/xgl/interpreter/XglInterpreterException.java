/*
 * Created on 06.05.2004
 *
 */
package de.fhtw.xgl.interpreter;

/**
 * Indicates that an Exception appeared during the parsing of the XML-structure.
 * 
 * Mostly this Exception is caused by a wrong XML-structure. 
 * 
 * @author Sebastian Heide
 *
 */
public class XglInterpreterException extends Exception
{
	
	/**
	 * Constructs an XglInterpreterException with the message msg.
	 * 
	 * @param msg error-message to display
	 */
	public XglInterpreterException(String msg)
	{
		super(msg);
	}
	
}

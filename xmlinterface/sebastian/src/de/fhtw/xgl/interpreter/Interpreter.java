/*
 * Created on 06.05.2004
 *
 */
package de.fhtw.xgl.interpreter;

import org.w3c.dom.Document; 

/**
 * @author Administrator
 *
 */
public interface Interpreter
{

	/**
	 * Represents the root-element of the xgl-language.
	 */	
	public static final String XML_ROOT_ELEMENT = "xgl-gui";
	/**
	 * Represents the <code>widget</code>-tag of the xml-file.
	 */
	public static final String XML_ELEMENT_WIDGET = "widget";
	/**
	 * Represents the <code>widgets</code>-tag of the xml-file.
	 */
	public static final String XML_ELEMENT_WIDGETS = "widgets";
	/**
	 * Represents the <code>property</code>-tag of the xml-file.
	 */
	public static final String XML_ELEMENT_PROPERTY = "property";
	/**
	 * Represents the <code>properties</code>-tag of the xml-file.
	 */
	public static final String XML_ELEMENT_PROPERTIES = "properties";
	/**
	 * Represents the <code>event</code>-tag of the xml-file.
	 */
	public static final String XML_ELEMENT_EVENT = "event";
	/**
	 * Represents the <code>events</code>-tag of the xml-file.
	 */
	public static final String XML_ELEMENT_EVENTS = "events";
	/**
	 * Represents the type-name of widget-type <code>window</code>
	 */
	public static final String WIDGET_TYPE_WINDOW = "window";
	/**
	 * Represents the type-name of widget-type <code>menuBar</code>
	 */
	public static final String WIDGET_TYPE_MENU_BAR = "menuBar";
	/**
	 * Represents the type-name of widget-type <code>menu</code>
	 */
	public static final String WIDGET_TYPE_MENU = "menu";
	/**
	 * Represents the type-name of widget-type <code>button</code>
	 */
	public static final String WIDGET_TYPE_BUTTON = "button";
	/**
	 * Represents the type-name of widget-type <code>textfield</code>
	 */
	public static final String WIDGET_TYPE_TEXTFIELD = "textfield";
	/**
	 * Represents the type-name of widget-type <code>container</code>
	 */
	public static final String WIDGET_TYPE_CONTAINER = "container";
	
	public void sendCallback(Callback callback);

	public void parseDocument(Document doc) throws XglInterpreterException;

}

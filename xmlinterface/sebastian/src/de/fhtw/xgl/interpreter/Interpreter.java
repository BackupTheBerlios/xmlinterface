/*
 * Created on 06.05.2004
 *
 */
package de.fhtw.xgl.interpreter;

import org.w3c.dom.Document;

import de.fhtw.xgl.interpreter.CallbackHandler;

/**
 * The Interpreter is the core-class of the XML-gui.
 * 
 * An interpreter is used to transform an XML-structure into a GUI.<br>
 * To read an XML-GUI-file and transform it into a GUI do the following:
 * 
 * <pre><code>
	FileInputStream reader = new FileInputStream("gui.xml");
	
	SwingInterpreter interpreter = new SwingInterpreter(new ExampleCallbackHandler());

	DocumentBuilderFactory dbFactory = 
		DocumentBuilderFactory.newInstance();
	dbFactory.setNamespaceAware(true);
	DocumentBuilder builder = dbFactory.newDocumentBuilder();
	Document document = builder.parse(reader);
	interpreter.parseDocument(document);
 * </code></pre>
 * 
 * @author Sebastian Heide
 *
 */
public interface Interpreter
{

	/**
	 * Represents the root-element of the xgl-language.
	 */	
	public static final String XML_ROOT_ELEMENT = "xmlgui";
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
	
	/**
	 * This method is called by <code>Widget<code>-objects to send an event to the <code>CallbackHandler</code>.
	 * 
	 * @param widget
	 */
	public void handleEvent(Widget widget);

	/**
	 * Parses the given XML-document-structure and builds the Gui-structure.
	 * 
	 * The document must be a valid XML-document and a valid GUI-structure.
	 * A Window will be created after the process and the GUI becomes visible.
	 * 
	 * @param doc XML-document with the GUI-structure
	 * @throws XglInterpreterException
	 */
	public void parseDocument(Document doc) throws XglInterpreterException;
	
	/**
	 * Sets the CallbackHandler for this Interpreter (of not done through the constructor).
	 * 
	 * @param cbh CallbackHandler
	 */
	public void setCallbackHandler(CallbackHandler cbh);

	/**
	 * Returns the CallbackHandler connected with this Interpreter.
	 * 
	 * @return CallbackHandler that handles GUI-events
	 */
	public CallbackHandler getCallbackHandler();
	
	/**
	 * Returns the <code>Widget</code>-object with the specified ID.
	 * 
	 * @param Id ID of the Widget 
	 * @return the Widget-object
	 */
	public Widget getWidgetById(int id);
	
	/**
	 * This method should not be invoked directly. It adds a widget to the Interpreter's widget-list.
	 * 
	 * Widgets use this method to make themself known to this Interpreter-object.
	 * 
	 * @param id the widget's id
	 * @param w the widget itself
	 */
	public void addWidget(int id, Widget w);

}

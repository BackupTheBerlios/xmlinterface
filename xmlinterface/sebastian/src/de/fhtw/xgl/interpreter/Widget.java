/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.interpreter;

// XML-imports
import org.w3c.dom.Node;
import org.w3c.dom.Document;

/**
 * Basic GUI-element. All widgets are derived from this interface.
 * 
 * @author Sebastian Heide
 *
 */
public interface Widget
{
	
	/**
	 * XML-node-name of this object.
	 */
	public static final String XML_NODE_NAME = "widget";
	/**
	 * XML-attribute <code>callbackID</code> of this object.
	 */
	public static final String XML_ATTRIBUTE_CALLBACK_ID = "callbackID";
	/**
	 * XML-attribute <code>type</code> of this object.
	 */
	public static final String XML_ATTRIBUTE_TYPE = "type";
	/**
	 * XML-attribute <code>uiType</code> of this object.
	 */
	public static final String XML_ATTRIBUTE_UI_TYPE = "uiType";
	/**
	 * XML-attribute <code>id</code> of this object.
	 */
	public static final String XML_ATTRIBUTE_ID = "id";
	/**
	 * <code>width</code>-attribute of a widget
	 */
	public final static String ATTRIBUTE_WIDTH = "width"; 
	/**
	 * <code>height</code>-attribute of a widget
	 */
	public final static String ATTRIBUTE_HEIGHT = "height"; 
	/**
	 * <code>xCoord</code>-attribute of a widget
	 */
	public final static String ATTRIBUTE_X_COORD = "xCoord"; 
	/**
	 * <code>yCoord</code>-attribute of a widget
	 */
	public final static String ATTRIBUTE_Y_COORD = "yCoord";
	/**
	 * <code>Widget</code>-objects without a defined callback-ID return this constant value as callback-ID
	 */ 
	public final static int CALLBACK_ID_UNDEFINED = -1;
	
	/**
	 * Constructs the widget from the Node-object.
	 * 
	 * @param node XML-Node containing the widget
	 * @return true if loading was successful
	 */
	public boolean load(Node node);
	
	/**
	 * Stores the widget into an XML-Node
	 * 
	 * @param doc XML-Document of the node-object
	 * @return XML-Node of the Widget
	 */
	public Node store(Document doc);
	
	/**
	 * Returns the type of the widget-object.
	 * 
	 * @return String representing the widget's type
	 */
	public String getType();
	
	/**
	 * Sets the Interpreter of this widget.
	 * 
	 * @param i Interpreter to set
	 */
	public void setInterpreter(Interpreter i);
	
	/**
	 * Sets the widget's size.
	 * 
	 * @param w width
	 * @param h height
	 */
	public void setSize(int w, int h);

	/**
	 * Sets the widget's location relative to it's container.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void setLocation(int x, int y);
	
	/**
	 * Returns the widget's id.
	 * 
	 * @return id of the widget
	 */
	public int getId();
	
	/**
	 * Sets the widget's CallbackID.
	 * 
	 * @param id callbackID to set
	 */
	public void setCallbackID(int id);

	/**
	 * Returns the widget's callbackIDd.
	 * 
	 * @return callbackID of the widget
	 */
	public int getCallbackID();

}

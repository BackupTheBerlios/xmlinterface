/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.interpreter;

// XML-imports
import org.w3c.dom.Node;
import org.w3c.dom.Document;
// AWT-imports
import java.awt.Component;

/**
 * @author Administrator
 *
 */
public interface Widget
{
	
	/**
	 * XML-node-name of this object.
	 */
	public static final String XML_NODE_NAME = "widget";
	/**
	 * XML-attribute <code>name</code> of this object.
	 */
	public static final String XML_ATTRIBUTE_NAME = "name";
	/**
	 * XML-attribute <code>type</code> of this object.
	 */
	public static final String XML_ATTRIBUTE_TYPE = "type";
	/**
	 * XML-attribute <code>uiType</code> of this object.
	 */
	public static final String XML_ATTRIBUTE_UI_TYPE = "uiType";

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
	 * @return
	 */
	public Node store(Document doc);
	
	/**
	 * Returns the type of the widget-object.
	 * 
	 * @return String representing the widget's type
	 */
	public String getType();
	
	/**
	 * Returns the <code>Component<code>-object for the widget
	 * 
	 * @return Component-object for widget
	 */
	public Component getComponent();
	
	public void setInterpreter(Interpreter i);
	

}

/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.example;

import de.fhtw.xgl.interpreter.swing.SwingInterpreter;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;

// XML-In/Output
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;

/**
 * Testclass fpr the SwingInterpreter of the XGL language.
 * 
 * @author Sebastian Heide
 *
 */
public class TestGUIApp
{
	
	/*
	 * The standard-inputfile, if no filename is delivered 
	 */
	public final static String inputFile =
		"xmlgui.xml";
		
	private SwingInterpreter interpreter;
	
	public TestGUIApp()
	{
		this(inputFile);
	}
	
	public TestGUIApp(String file)
	{
		try
		{
			FileInputStream reader = new FileInputStream(file);
			
			// create interpreter and add sample-callbackhandler
			interpreter = new SwingInterpreter(new ExampleCallbackHandler(this));

			DocumentBuilderFactory dbFactory = 
				DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document document = builder.parse(reader);
			interpreter.parseDocument(document);
		}
		catch (org.xml.sax.SAXParseException eSAX)
		{
			javax.swing.JOptionPane.showMessageDialog(null, eSAX.getLocalizedMessage(), "Fehler in der XML-Datei", javax.swing.JOptionPane.ERROR_MESSAGE);
			eSAX.printStackTrace();
		}
		catch (de.fhtw.xgl.interpreter.XglInterpreterException eInt)
		{
			javax.swing.JOptionPane.showMessageDialog(null, eInt.getLocalizedMessage(), "Fehler in der XML-Datei", javax.swing.JOptionPane.ERROR_MESSAGE);
			eInt.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void store()
	{
		try
		{
			File f = new File(inputFile);
			if (f.exists()) f.renameTo(new File(inputFile + ".bak"));
			f = new File(inputFile);
			FileOutputStream fOut = new FileOutputStream(f);
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.newDocument();
			doc.appendChild(interpreter.storeDocument(doc));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//			OutputKeys.OMIT_XML_DECLARATION
			StreamResult sr = new StreamResult(fOut);
			transformer.transform(new DOMSource(doc), sr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

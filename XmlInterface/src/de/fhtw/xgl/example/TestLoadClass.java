/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.example;

import de.fhtw.xgl.interpreter.swing.SwingInterpreter;
import java.io.FileInputStream;

// XML-Output
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author Administrator
 *
 */
public class TestLoadClass
{
	public static void main(String[] args)
	{
		try
		{
			FileInputStream reader = new FileInputStream("examples\\test.xml");
			
			// create interpreter and add sample-callbackhandler
			SwingInterpreter interpreter = new SwingInterpreter(new ExampleCallbackHandler());

			DocumentBuilderFactory dbFactory = 
				DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document document = builder.parse(reader);
			interpreter.parseDocument(document);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

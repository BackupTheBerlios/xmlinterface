/*
 * Created on 05.05.2004
 *
 */
package de.fhtw.xgl.example;

import de.fhtw.xgl.interpreter.swing.widgets.SwingWindow;
import de.fhtw.xgl.interpreter.swing.widgets.SwingButton;
import de.fhtw.xgl.interpreter.swing.widgets.SwingTextfield;

// XML-Output
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;

/**
 * @author Administrator
 *
 */
public class TestStoreClass
{
	
	public static void main(String[] args)
	{
		SwingWindow sww = new SwingWindow();
		sww.setTitle("Test");
		sww.setSize(800, 600);
		sww.setLocation(100, 100);
		SwingTextfield t = new SwingTextfield("Testtext");
		t.setSize(780, 300);
		t.setLocation(1, 1);
		SwingButton b = new SwingButton("Button1");
		b.setLocation(1, 305);
		b.setSize(100, 20);
		sww.getContentPane().add(t);
		sww.getContentPane().add(b);
		sww.setVisible(true);
		try
		{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.newDocument();
			doc.appendChild(sww.store(doc));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StreamResult sr = new StreamResult(System.out);
			transformer.transform(new DOMSource(doc), sr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

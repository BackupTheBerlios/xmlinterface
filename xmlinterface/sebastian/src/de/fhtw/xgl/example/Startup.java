/*
 * Created on 23.06.2004
 *
 */
package de.fhtw.xgl.example;

/**
 * @author Administrator
 *
 */
public class Startup
{

	public static void main(String[] args)
	{
		if (args.length > 0)
		{
			TestGUIApp t = new TestGUIApp(args[0]); 
		}
		else
		{
			TestGUIApp t = new TestGUIApp();
		}
	}

}

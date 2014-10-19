package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import Server.Filiale;
import Server.Spiel;

public class SpielTest extends Spiel
{
	static Socket[] sock = new Socket[2];
	static String[] n = new String[]{"Anton","Berta"};
	public SpielTest() throws ParserConfigurationException, SAXException, IOException			
	{		
		super(sock, n);
		// TODO Auto-generated constructor stub
	}

}

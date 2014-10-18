package Client;

import Server.QuartalsBericht;

public class JahresBerichtAlle {
	private int jahr;
	private QuartalsBericht[] berichte;
	private int anzBerichte = 0;
	
	
	public String toString(int jahr, int quartal){
		return "test";
	}
	
	public void add(QuartalsBericht bericht){
		
	}
	
	private JahresBerichtAlle(String[] qb)
	{		
		berichte = new QuartalsBericht[qb.length];
		for (int i = 0; i < qb.length; i++)
		{
			berichte[i] = QuartalsBericht.readFromString(qb[i]);
		}
	}
	public JahresBerichtAlle(int AnzPlayer)
	{
		berichte = new QuartalsBericht[AnzPlayer];
	}
	public void setQuartalsbericht(String b)
	{
		berichte[anzBerichte] = QuartalsBericht.readFromString(b);
		anzBerichte++;
	}
	public QuartalsBericht getQuartalsBericht(int spieler)
	{		
		return berichte[spieler]; //Funktioniert so nicht
	}
	
	
}

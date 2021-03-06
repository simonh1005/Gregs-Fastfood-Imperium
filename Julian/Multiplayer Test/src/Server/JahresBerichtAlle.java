package Server;

public class JahresBerichtAlle
{
	private int jahr;
	private QuartalsBericht[] berichte;
	private int anzBerichte = 0;

	private JahresBerichtAlle(String[] qb)
	{		
		berichte = new QuartalsBericht[qb.length];
		for (int i = 0; i < qb.length; i++)
		{
			berichte[i] = QuartalsBericht.readFromString(qb[i]);
		}
	}
	public JahresBerichtAlle(int AnzPlayer, int jahr)
	{
		berichte = new QuartalsBericht[AnzPlayer];
		this.jahr = jahr;
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

	public String toString()
	{
		return "test";
	}
}

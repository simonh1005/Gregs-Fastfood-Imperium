package Server;

public class VerbrauchT
{
	private int[] verbrauchA = new int[3];
	private int[] verbrauchB = new int[3];
	private int[] verbrauchC = new int[3];
	private int[] verbrauchD = new int[3];

	public VerbrauchT(int[] verbrauchA, int[] verbrauchB, int[] verbrauchC,
			int[] verbrauchD)
	{
		this.verbrauchA = verbrauchA;
		this.verbrauchB = verbrauchB;
		this.verbrauchC = verbrauchC;
		this.verbrauchD = verbrauchD;
	}
	public VerbrauchT()
	{
		this.verbrauchA = new int[]{0,0,0};
		this.verbrauchB = new int[]{0,0,0};
		this.verbrauchC = new int[]{0,0,0};
		this.verbrauchD = new int[]{0,0,0};
	}
	public int[] getZutat(int zutat)
	{
		switch (zutat)
		{
		case 1:
			return verbrauchA;

		case 2:
			return verbrauchB;

		case 3:
			return verbrauchC;

		case 4:
			return verbrauchD;

		}
		return null;
	}

	public void setZutat(int zutat, int[] werte)
	{
		switch (zutat)
		{
		case 1:
			verbrauchA = werte;
			break;
		case 2:
			verbrauchB = werte;
			break;
		case 3:
			verbrauchC = werte;
			break;
		case 4:
			verbrauchD = werte;
			break;
		}
	}

	public void sub(VerbrauchT verbrauch)
	{
		int[] tmp = verbrauch.getZutat(1);
		for (int i = 0; i < 3; i++)
		{
			verbrauchA[i] -= tmp[i];
		}
		tmp = verbrauch.getZutat(2);
		for (int i = 0; i < 3; i++)
		{
			verbrauchB[i] -= tmp[i];
		}
		tmp = verbrauch.getZutat(3);
		for (int i = 0; i < 3; i++)
		{
			verbrauchC[i] -= tmp[i];
		}
		tmp = verbrauch.getZutat(4);
		for (int i = 0; i < 3; i++)
		{
			verbrauchD[i] -= tmp[i];
		}
	}

	public void sum(VerbrauchT verbrauch)
	{
		int[] tmp = verbrauch.getZutat(1);
		for (int i = 0; i < 3; i++)
		{
			verbrauchA[i] += tmp[i];
		}
		tmp = verbrauch.getZutat(2);
		for (int i = 0; i < 3; i++)
		{
			verbrauchB[i] += tmp[i];
		}
		tmp = verbrauch.getZutat(3);
		for (int i = 0; i < 3; i++)
		{
			verbrauchC[i] += tmp[i];
		}
		tmp = verbrauch.getZutat(4);
		for (int i = 0; i < 3; i++)
		{
			verbrauchD[i] += tmp[i];
		}
	}
	
	public VerbrauchT getCopy()
	{
		return new VerbrauchT(verbrauchA,verbrauchB,verbrauchC,verbrauchD);
	}
	public double getDurchschnitt()
	{
		double schnitt = 0;
		for (int i = 0; i < 3; i++)
		{
			schnitt+= verbrauchA[i];
		}
		for (int i = 0; i < 3; i++)
		{
			schnitt+= verbrauchB[i];
		}
		for (int i = 0; i < 3; i++)
		{
			schnitt+= verbrauchC[i];
		}
		for (int i = 0; i < 3; i++)
		{
			schnitt+= verbrauchD[i];
		}
		schnitt /= 12;
		return schnitt;
	}
}

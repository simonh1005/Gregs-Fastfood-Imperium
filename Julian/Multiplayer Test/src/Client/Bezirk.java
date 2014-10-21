package Client;

public class Bezirk
{
	private String name;
	private int einwohner;
	private int[] boni = new int[3];
	private int freieKundschaft;
	private int maxFilialen;
	private int bID;
	private Spieler parent;

	private Filiale[] Filialen;
	private int[] maxKundenVomServer = new int[42]; // Noch vom Server kriegen
													// als int[]

	public Bezirk(int id, String name, int einwohner, int maxFilialen,
			int[] boni)
	{
		this.bID = id;
		this.name = name;
		this.einwohner = einwohner;
		this.maxFilialen = maxFilialen;
		this.boni = boni;
		this.Filialen = new Filiale[maxFilialen];
	}

	public void setParent(Spieler parent)
	{
		this.parent = parent;
	}

	public String toHTML() // Labels können nur Zeilenumbrüche in HTML
	{
		return "<HTML><BODY>Bezirk: " + name + "<br> Einwohnerzahl: "
				+ einwohner + "<br> Boni: Noch nicht erforscht</HTML></BODY>";
	}

	public double verkaufen(int fid)
	{ 
		double verkaufsPreisF = 0;
		for (int i = 0; i < Filialen.length; i++)
		{
			if (Filialen[i].getfID() == fid)
			{
				verkaufsPreisF = Filialen[fid].verkaufen();
			}
		}
		return verkaufsPreisF;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getEinwohner()
	{
		return einwohner;
	}

	public void setEinwohner(int einwohner)
	{
		this.einwohner = einwohner;
	}

	public int[] getBoni()
	{
		return boni;
	}

	public void setBoni(int[] boni)
	{
		this.boni = boni;
	}

	public int getFreieKundschaft()
	{
		return freieKundschaft;
	}

	public void setFreieKundschaft(int freieKundschaft)
	{
		this.freieKundschaft = freieKundschaft;
	}

	public int getMaxFilialen()
	{
		return maxFilialen;
	}

	public void setMaxFilialen(int maxAnzFilialen)
	{
		this.maxFilialen = maxAnzFilialen;
	}

	public int getbID()
	{
		return bID;
	}

	public void setbID(int bID)
	{
		this.bID = bID;
	}

	public Filiale getFiliale(int fid)
	{

		return Filialen[fid]; // Filiale 0
	}

	public void setFiliale(Filiale filiale, int fid)
	{
		Filialen[fid] = filiale;
	}

}

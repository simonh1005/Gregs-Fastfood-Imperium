package Server;

public class Bezirk
{
	private String name;
	private int einwohner;
	private double[] boni = new double[3]; //Burger;Pizza;Wurst
	private int freieKundschaft;
	private int maxFilialen;
	private Filiale[] filialen;
	private int id;
	private int filialenGesetzt = 0;

	public Bezirk(int id, String name, int einwohner, int maxFilialen, double[] boni)
	{
		this.id = id;
		this.name = name;
		this.einwohner = einwohner;
		this.maxFilialen = maxFilialen;
		filialen = new Filiale[maxFilialen];
		this.boni = boni;
	}
	
	public int[] calcKundschaft()
	{
		int[] kundschaft = new int[maxFilialen];	
		double durchschnittspreis =0;
		double durchschnittsquali = 0;
		for (int i = 0; i < filialen.length; i++)
		{
			durchschnittsquali += filialen[i].getQualitaet();
			double bonus = boni[filialen[i].getTyp()];		
			durchschnittspreis += filialen[i].getProduktPreis();
			
		}
		durchschnittspreis /= filialen.length;
		durchschnittsquali /= filialen.length;
		for (int i = 0; i < filialen.length; i++)
		{
			double pricefaktor = 0;
			double qualifaktor = 0;
			if (filialen[i].getProduktPreis() < durchschnittspreis)
			{
				pricefaktor = 0.2;
			}
			else if(filialen[i].getProduktPreis() > durchschnittspreis)
			{
				pricefaktor = -0.05;
			}
			
			if (filialen[i].getQualitaet() < durchschnittsquali)
			{
				qualifaktor = 0.2;
			}
			else if(filialen[i].getQualitaet() > durchschnittsquali)
			{
				qualifaktor = -0.05;
			}
			kundschaft[i] = Math.min((int)((einwohner/filialen.length)* pricefaktor*qualifaktor), filialen[i].getAbfertigungsmenge());
		}
		
		return kundschaft;
	}

	public Filiale getFiliale(int fid)
	{
		try
		{
			return filialen[fid %(id*10)]; 
		} catch (Exception e)
		{
			// TODO: handle exception
		}
			return filialen[fid]; //Filiale 0
	}
	public void addNewFiliale(String name, int kaufpreis)
	{
		if (filialenGesetzt < maxFilialen)
		{
			filialen[filialenGesetzt] = new Filiale(this.id*10+filialenGesetzt, name, kaufpreis);
			filialenGesetzt++;
		}
		//ansonsten evtl Fehler werfen
	}
}

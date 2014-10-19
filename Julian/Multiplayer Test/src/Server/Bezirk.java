package Server;

public class Bezirk
{
	private String name;
	private int einwohner;
	private int[] boni = new int[3]; //Burger;Pizza;Wurst
	private int freieKundschaft;
	private int maxFilialen;
	private Filiale[] filialen;
	private int id;
	private int filialenGesetzt = 0;

	public Bezirk(int id, String name, int einwohner, int maxFilialen, int[] boni)
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
		for (int i = 0; i < filialen.length; i++)
		{
			int qualität = filialen[i].getQualitaet();
			int bonus = boni[filialen[i].getTyp()];			
			kundschaft[i] = Math.min((einwohner/filialen.length)* bonus*(100+qualität)/100, filialen[i].getAbfertigungsmenge()); //Pseudo Formel, könnte iwie hinhauen :-)
		}
		return new int[]{1,1};
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

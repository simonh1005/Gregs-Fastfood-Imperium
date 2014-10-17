package Server;

public class Filiale
{
	private int fID;
	private int typ;
	private int bezirk;
	private int mitarbeiter;
	private double produktPreis;
	private String besitzer;
	private String name;
	private int filialnummer;
	private int absatz;
	private double wert;
	private int groeße;
	private int qualitaet;
	private double kaufPreis;
	private double verkaufsPreis;

	public Filiale(int id, String name, int kaufpreis)
	{
		this.fID = id;
		this.name = name;
		this.kaufPreis = kaufpreis;
		
	}

	public int getTyp()
	{
		return typ;
	}

	public double getProduktPreis()
	{
		return produktPreis;
	}

	public int getQualitaet()
	{
		return qualitaet;
	}

	private int getMAANz()
	{
		return mitarbeiter;
	}

	public int getAbfertigungsmenge()
	{
		return mitarbeiter*500;
	}

	private int getMaxKunden() // Maximale abfertigungsmenge,hngt
	{
		return absatz;

	}

	public void mitarbeiterzahlAendern(int zahl)
	{
		mitarbeiter = zahl;
	}

	public VerbrauchT getVerbrauch(int maxZutaten, int maxKunden)
	{
		VerbrauchT test = new VerbrauchT();
		return test;
	}

	public double getEinnahmen(int maxZutaten, int maxKunden)
	{
		return 1.45;
	}

	// public VerbrauchT getMaxVerbrauch() Wird das überhaupt benötigt?
	// {
	// switch (typ)
	// {
	// case 1:
	//
	// break;
	//
	// default:
	// break;
	// }
	// VerbrauchT test = new VerbrauchT();
	// return test;
	// }
}

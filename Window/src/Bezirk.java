public class Bezirk
{
	int id;
	String name;
	int einwohner;
	int maxFilialen;
	String boni;	

	public Bezirk(int id, String name, int einwohner, int maxFilialen, String boni)
	{
		this.id = id;
		this.name = name;
		this.einwohner = einwohner;
		this.maxFilialen = maxFilialen;
		this.boni = boni;
	}

	public String toString()
	{
		return "Bezirk: " + name + "\r\n Einwohnerzahl: " + einwohner + "\r\n Boni: Noch nicht erforscht";
	}
	public String toHTML() //Labels k�nnen nur Zeilenumbr�che in HTML
	{
		return "<HTML><BODY>Bezirk: " + name + "<br> Einwohnerzahl: " + einwohner + "<br> Boni: Noch nicht erforscht</HTML></BODY>";
	}
}


package Server;

public class QuartalsBericht
{
	private String spieler;
	private double filialen;
	private double rohstoffe;
	private double bank;
	private double eK;
	private double fK;

	public QuartalsBericht(String spieler, double filialen, double rohstoffe,
			double bank, double eK, double fK)
	{
		this.spieler = spieler;
		this.filialen = filialen;
		this.rohstoffe = rohstoffe;
		this.bank = bank;
		this.eK = eK;
		this.fK = fK;
	}

	public static QuartalsBericht readFromString(String bericht)// "Format: Bericht;spieler;filialen;rohstoffe;bank;EK;FK"
	{
		try
		{
			String[] vars = bericht.split(";");
			if (vars[0].equals("Bericht") && (vars.length == 7))
			{
				return new QuartalsBericht(vars[0], Double.parseDouble(vars[1]), Double.parseDouble(vars[2]), Double.parseDouble(vars[3]), Double.parseDouble(vars[4]), Double.parseDouble(vars[5]));
	
			}
		} catch (Exception e)
		{

		}
		return null;
	}

	public String toString(int jahr, int quartal)
	{
		return "Bericht;" + spieler + ";" + filialen + ";" + rohstoffe + ";"
				+ bank + ";" + eK + ";" + fK;
	}
}

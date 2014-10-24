package Client;


public class QuartalsBericht {
	private int jahr;
	private int quartal;
	private double filialen;
	private double rohstoffe;
	private double bank;
	private double EK;
	private double FK;
	private String spieler;
	
	
	public QuartalsBericht(String spieler, double filialen, double rohstoffe,
			double bank, double EK, double FK)
	{
		this.spieler = spieler;
		this.filialen = filialen;
		this.rohstoffe = rohstoffe;
		this.bank = bank;
		this.EK = EK;
		this.FK = FK;
	}
	
	public String toString(int jahr, int quartal){
		return "Bericht;" + spieler + ";" + filialen + ";" + rohstoffe + ";"
				+ bank + ";" + EK + ";" + FK;
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
	
}

package Client;



public class Marktpreis {
	private double qualitaet1;
	private double qualitaet2;
	private double qualitaet3;
	private double discount;
	private String name;
	private int id;
	
	public Marktpreis(double qualitaet1, double qualitaet2, double qualitaet3,
			String name, int id)
	{
		super();
		this.qualitaet1 = qualitaet1;
		this.qualitaet2 = qualitaet2;
		this.qualitaet3 = qualitaet3;
		this.name = name;
		this.id = id;
	}

//	public double verbrauchZuPreis(VerbrauchT verbrauch){		// �berhaupt notwendig oder durch einkaufen schon abgedeckt, da ja schon menge und Zutat geschickt wird?
//		double test = 1.45;
//		return test;
//	}
	
	public double getPrice(int menge, int qualitaet){	//Wie ist das hier implementiert serverseitig?
		double[] tmp = new double [4];//Server.Marktpreis.getPreise();
		double preis = tmp[qualitaet];
		return preis;
	}
	
	
}

package Client;



public class Marktpreis {
	private double qualitaet1;
	private double qualitaet2;
	private double qualitaet3;
	private double discount;
	private String name;
	private int id;
	
	private void einkaufen(int menge, int qualitaet, int zutatID){
		Server.Marktpreis.einkaufen(menge, qualitaet);
		getPrice(menge, qualitaet);
	}
	
	public double verbrauchZuPreis(VerbrauchT verbrauch){		// ???
		double test = 1.45;
		return test;
	}
	
	public double getPrice(int menge, int qualitaet){
		double[] tmp = Server.Marktpreis.getPreise();
		double preis = tmp[qualitaet];
		return preis;
	}
	
	
}

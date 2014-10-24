package Client;

public class Marktpreis {
	private double qualitaet1;
	private double qualitaet2;
	private double qualitaet3;
	private double discount;
	private String name;
	private int id;
	private static Marktpreis[] preise = new Marktpreis[4];

	public Marktpreis(double qualitaet1, double qualitaet2, double qualitaet3,
			String name, int id) {
		super();
		this.qualitaet1 = qualitaet1;
		this.qualitaet2 = qualitaet2;
		this.qualitaet3 = qualitaet3;
		this.name = name;
		this.id = id;
		preise[id] = this;
	}

	public double getPrice(int menge, int qualitaet) { 
		double[] tmp = getPreise();
		double preis = tmp[qualitaet-1];
		return preis *menge;
	}

	public static void readFromString(String preis){
		String[] tmp = preis.split(">");	//<einkauf> , 1,1,1  2,2,2  3,3,3  4,4,4
		String[] z11 = tmp[1].split(",");	//1,1,1  2,2,2  3,3,3  4,4,4
		
		int z;
		
		for (int i = 0; i < 4; i++) {
			z = i*3;
			preise[i].setPreise(new double[]{Double.parseDouble(z11[z]),Double.parseDouble(z11[z+1]),Double.parseDouble(z11[z+2])});
		}
	}

	public double[] getPreise()
	{
		return new double[] { qualitaet1, qualitaet2, qualitaet3 };

	}

	public void setPreise(double[] preise) {
		qualitaet1 = preise[0];
		qualitaet2 = preise[1];
		qualitaet3 = preise[2];
	}
	
	public static void setMarktPreis(int id, String name, double qualitaet1,double qualitaet2, double qualitaet3)
	{
		preise[id] = new Marktpreis(qualitaet1, qualitaet2, qualitaet3, name, id);
	}

	public static Marktpreis[] getMarktPreise() {
		
		return preise;
	}

}

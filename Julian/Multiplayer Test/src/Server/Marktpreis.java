package Server;

import Server.VerbrauchT;

public class Marktpreis
{
	private static int id;
	private String name;
	private static double qualitaet1;
	private static double qualitaet2;
	private static double qualitaet3;
	private static VerbrauchT verbrauchAlt;
	private static VerbrauchT verbrauchAkt;
	private static Marktpreis[] preise = new Marktpreis[4];
	private static double teuerungsFaktor = 0.02; // z.Bsp durch inflation,
													// steigende nachfrage usw.;
													// gilt für alle produkte in
													// allen Qualitäten

	private Marktpreis(int id, String name, double qualitaet1,
			double qualitaet2, double qualitaet3)
	{
		super();
		this.id = id;
		this.name = name;
		this.qualitaet1 = qualitaet1;
		this.qualitaet2 = qualitaet2;
		this.qualitaet3 = qualitaet3;
	}

	public static void calcMarktPreis()
	{
		VerbrauchT nachfrageEntwicklung = verbrauchAkt.getCopy();
		nachfrageEntwicklung.sub(verbrauchAlt);
		double schnitt = nachfrageEntwicklung.getDurchschnitt();
		for (int i = 0; i < preise.length; i++)
		{
			double[] zutatPreise = preise[i].getPreise();
			int[] zutatNachfrage = nachfrageEntwicklung.getZutat(i);
			for (int j = 0; j < 3; i++)
			{
				double ZutatTeuerung = zutatNachfrage[i] / schnitt
						* teuerungsFaktor + 1;
				zutatPreise[i] *= ZutatTeuerung;
			}
			preise[i].setPreise(zutatPreise);
		}
	}

	public static void setMarktPreis(int id, String name, double qualitaet1,double qualitaet2, double qualitaet3)
	{
		preise[id] = new Marktpreis(id, name, qualitaet1, qualitaet2, qualitaet3);
	}

	public static Marktpreis getMarktPreis(int id)
	{
		return preise[id];
	}
	public static void einkaufen(int menge, int qualitaet) // Verbrauch erhöht sich in
													// Echtzeit; auswirkungen
													// durch Neuberechnung erst
													// am Rundenende
	{
		VerbrauchT tmp = new VerbrauchT();
		int[] einkauf = new int[] { 0, 0, 0 };
		einkauf[qualitaet] = menge;
		tmp.setZutat(id, einkauf);
		verbrauchAlt.sum(tmp);
	}

	public static double[] getPreise() // Werden am Rundenende an die Clients
								// weitergereicht
	{
		return new double[] { qualitaet1, qualitaet2, qualitaet3 };

	}

	public void setPreise(double[] preise)
	{
		qualitaet1 = preise[0];
		qualitaet2 = preise[1];
		qualitaet3 = preise[2];
	}
}

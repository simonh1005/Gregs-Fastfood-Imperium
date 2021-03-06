package Client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Spieler
{
	private int mitarbeiterPool;
	private int freieMitarbeiter;
	private String name;
	private double kontostand;
	private VerbrauchT vorraete;
	private VerbrauchT vorratsBedarfSoll;
	private VerbrauchT vorratsBedarfIst;
	private double monatlicheKosten;
	private double liquiditaet = 100;
	private int liquiditaetCounter = 0;
	private SpielLogik parent;
	private Socket socket;
	private Marktpreis[] preise = new Marktpreis[4];		
	
	private final double mitarbeiterLohn = 6.50; // Konstanter Lohn

	private Bezirk[] bezirke = new Bezirk[8];

	public Spieler(SpielLogik parent, String name, Socket socket)
	{
		this.parent = parent;
		this.name = name;
		this.socket = socket;
	}

	public void sendToServer(String msg)
	{
		PrintStream raus;
		try
		{
			raus = new PrintStream(socket.getOutputStream());
			raus.println(msg);
		} catch (IOException e)
		{ // keine Pr�fung, ob Nachricht wirklich ankommt, da Fehler sehr
			// unwahrsceinlich und Nachricht nicht kritisch
		}
	}

	public int mitarbeiterzahlVeraendern(int menge)
	{ // Menge kann z.B. +3 oder -3 sein.
		mitarbeiterPool = mitarbeiterPool + menge;
		return mitarbeiterPool;
	}

	public void ausscheidenPruefen()
	{
		if (liquiditaet < 100)
		{
			liquiditaetCounter++;
		}

		if (liquiditaet > 100)
		{
			liquiditaetCounter--;
		}

		if (liquiditaetCounter == -4)
		{ // 3xQuartale negative Liquidit�t
			parent.spielBeenden();
		}
	}
	
	public boolean liquiditaetPruefen(double kontostand, double preis){
		boolean l = false;
		
		if (kontostand > preis) {
			l = true;
		} else {
			l = false;
		}
		
		return l;
	}

	public void MAAnzAendern(int zahl, int fid){
		if ((freieMitarbeiter - zahl) > 0) {
			freieMitarbeiter += bezirke[fid / 10].getFiliale(fid % 10).mitarbeiterzahlAendern(zahl);	
		}
	}
	
	public void filialeVerkaufen(int fid){
		kontostand += bezirke[fid / 10].getFiliale(fid % 10).verkaufen();
	}
	
	

	public void filialeEroeffnen(int fid, int groe�e, int typ,
			String nameBesitzer, int qualitaet)
	{
		
		if (liquiditaetPruefen(kontostand, bezirke[fid / 10].getFiliale(fid % 10).getKaufPreis()) == true) {
			bezirke[fid / 10].getFiliale(fid % 10).eroeffnen(groe�e, typ,
					nameBesitzer, qualitaet);
			sendToServer("<newFil>" + fid + "," + typ + "," + groe�e);
			sendToServer("<FilUpd>" + name + "," + qualitaet);
		}

	}

	public void einkaufen(int menge, int qualit�t, int id)
	{ 
		int[] werte = vorraete.getZutat(id);
		werte[qualit�t-1] += menge;
		double preis = preise[id].getPrice(menge, qualit�t) * menge;
		if (liquiditaetPruefen(kontostand, preis) == true) {
			vorraete.setZutat(id, werte);
			kontostand = kontostand - preis ;
			sendToServer("<einkauf>" + menge + "," + qualit�t + "," + id); //<einkauf>int menge, int qualit�t, int id
		}
		
	}

	public  int getfreieMitarbeiter()
	{
		return freieMitarbeiter;
	}

	public double getMonatlicheKosten()
	{
		return monatlicheKosten;
	}

	public void setMonatlicheKosten()
	{
		double mitarbeiterKosten = mitarbeiterPool * mitarbeiterLohn;
		double betriebsKosten = 0;
		double monatKosten;

		for (Bezirk b : bezirke)
		{
			for (int i = 0; i < b.getMaxFilialen(); i++)
			{
				if (b.getFiliale(i).getBesitzer().equals(name))
				{
					betriebsKosten += b.getFiliale(i).getBetriebsKostenF();
				}
			}
		}
		monatKosten = mitarbeiterKosten + betriebsKosten;

		monatlicheKosten = monatKosten;
	}

	public double getLiquiditaet()
	{
		return liquiditaet;
	}

	public void setLiquiditaet()
	{
		liquiditaet = (kontostand / monatlicheKosten) * 100;
	}

	public Marktpreis[] getPreise() {
		return preise;
	}

	public void setPreise(Marktpreis[] preise) {
		this.preise = preise;
	}

	public Bezirk[] getBezirke()
	{
		return bezirke;
	}

	public void setBezirke(Bezirk[] bezirke)
	{
		this.bezirke = bezirke;
	}

	public VerbrauchT getVorraete()
	{
		return vorraete;
	}

	public void setVorraete(VerbrauchT vorraete)
	{
		this.vorraete = vorraete;
	}

	public void berechneEinnahmen()
	{
		VerbrauchT sollVerbauch = new VerbrauchT();
		for (Bezirk b : bezirke)
		{
			for (int i = 0; i < b.getMaxFilialen(); i++)
			{
				sollVerbauch.sum(b.getFiliale(i).getSollVerbrauch());

			}
		}
		double umsatz = 0;
		if (vorraete.groe�erGleichAls(sollVerbauch))
		{
			for (Bezirk b : bezirke)
			{
				for (int i = 0; i < b.getMaxFilialen(); i++)
				{
					umsatz += b.getFiliale(i).getEinnahmen();
				}
			}
			vorraete.sub(sollVerbauch);
		} else
		{
			for (Bezirk b : bezirke)
			{
				for (int i = 0; i < b.getMaxFilialen(); i++)
				{
					VerbrauchT tmp = sollVerbauch;
					tmp.div(b.getFiliale(i).getSollVerbrauch()); // Prozentualler
																	// Anteil
																	// der
																	// Filiale
					tmp.mult(vorraete);
					tmp.div(100);
					umsatz += b.getFiliale(i).getEinnahmen(tmp);
				}
			}
			vorraete = new VerbrauchT();
		}
		kontostand += umsatz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void mitarbeiterEinstellen(int menge){
		mitarbeiterPool += menge;
	}
	
	
	
	
}

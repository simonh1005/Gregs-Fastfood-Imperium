package Client;

public class Spieler {
	private int mitarbeiterPool;
	private static int freieMitarbeiter;
	private String name;
	private double kontostand;
	private VerbrauchT vorraete;
	private VerbrauchT vorratsBedarfSoll;
	private VerbrauchT vorratsBedarfIst;
	private double monatlicheKosten;
	private double liquiditaet;
	private int liquiditaetCounter = 0;

	private final double mitarbeiterLohn = 6.50; // Konstanter Lohn
	
	private Bezirk[] Bezirke = new Bezirk[8];			//wie die fids auf bezirke verteilen?
	private Filiale[] Filialen = new Filiale[42];		//noch ersetzen
	
	public int mitarbeiterzahlVeraendern(int menge) { // Menge kann z.B. +3 oder -3 sein. 
		mitarbeiterPool = mitarbeiterPool + menge;
		return mitarbeiterPool;
	}

	public void liquiditaetPruefen() {
		if (liquiditaet < 100) {
			liquiditaetCounter++;
		}
		
		if (liquiditaet > 100) {
			liquiditaetCounter--;
		}
		
		if (liquiditaetCounter == -3) {		// 3xQuartale negative Liquidit‰t
			SpielLogik.spielBeenden();
		}
	}

	public void forschungBetreiben() {
		// Entf‰llt
	}

	
	public void filialeEroeffnen(int fid, int groeﬂe, int typ, String nameBesitzer) {
		Filialen[fid].setfID(fid);
		Filialen[fid].setGroeﬂe(groeﬂe);
		Filialen[fid].setTyp(typ);
		Filialen[fid].setBesitzer(nameBesitzer);

	}

	
	public void einkaufen(VerbrauchT einkauf) {
		// noch zu implementieren		an Server
	}

	private void berechneRohstoffverbrauch(boolean ist) {
		// noch zu implementieren		Was soll die machen?
	}

	
	public static int getfreieMitarbeiter() {
		return freieMitarbeiter;
	}

	public double getMonatlicheKosten() {
		return monatlicheKosten;
	}

	public void setMonatlicheKosten() {
		double mitarbeiterKosten = mitarbeiterPool * mitarbeiterLohn;
		double betriebsKosten = 0;
		double monatKosten;

		for (int j = 0; j < 42; j++) { // Filialen durchgehen

			if (Filialen[j].getBesitzer().equals(name)) {
				betriebsKosten = betriebsKosten + Filialen[j].getBetriebsKostenF();
			}

		}
		
		monatKosten = mitarbeiterKosten + betriebsKosten;
		
		monatlicheKosten = monatKosten;
	}

	public double getLiquiditaet() {
		return liquiditaet;
	}

	public void setLiquiditaet() {
		liquiditaet = (kontostand / monatlicheKosten) * 100;
	}

	public Bezirk[] getBezirke() {
		return Bezirke;
	}

	public void setBezirke(Bezirk[] bezirke) {
		Bezirke = bezirke;
	}

	public VerbrauchT getVorraete() {
		return vorraete;
	}

	public void setVorraete(VerbrauchT vorraete) {
		this.vorraete = vorraete;
	}
	
	
	

}

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
	
	private Bezirk[] Bezirke = new Bezirk[8];	
	
	
	
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
		
		if (liquiditaetCounter == -3) {		// 3xQuartale negative Liquidität
			SpielLogik.spielBeenden();
		}
	}
	
	public void filialeEroeffnen(int fid, int groeße, int typ, String nameBesitzer, int bID) {
		Bezirke[bID].getFilialen(fid).setfID(fid);
		Bezirke[bID].getFilialen(fid).setGroeße(groeße);
		Bezirke[bID].getFilialen(fid).setTyp(typ);
		Bezirke[bID].getFilialen(fid).setBesitzer(nameBesitzer);

	}

	
	public void einkaufen(VerbrauchT einkauf) {
		// noch zu implementieren		an Server / Mit Simon drüber reden
	}

	private void berechneRohstoffverbrauch(boolean ist) {
		// noch zu implementieren		Was soll die machen? drüber sprechen
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
			for (int i = 0; i < Bezirke.length; i++) {
				if (Bezirke[i].getFilialen(j).getBesitzer().equals(name)) {
					betriebsKosten = betriebsKosten + Bezirke[i].getFilialen(j).getBetriebsKostenF();
				}	
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

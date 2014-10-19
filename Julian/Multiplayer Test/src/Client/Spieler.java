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
		Filiale.setFid(fid);
		Filiale.setGroeﬂe(groeﬂe);
		Filiale.setTyp(typ);
		Filiale.setBesitzer(nameBesitzer);

	}

	
	public void einkaufen(VerbrauchT einkauf) {
		// noch zu implementieren
	}

	private void berechneRohstoffverbrauch(boolean ist) {
		// noch zu implementieren
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

			if (Filiale.getFid() == j && Filiale.getBesitzer().equals(name)) {
				betriebsKosten = betriebsKosten + Filiale.getBetriebsKostenF();
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
	
	

}

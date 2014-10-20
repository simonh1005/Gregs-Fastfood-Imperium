package Client;

public class Bezirk {
	private String name;
	private int einwohner;
	private int[] boni = new int[3];
	private int freieKundschaft;
	private int maxFilialen;
	private int bID;

	private Filiale[] Filialen = new Filiale[42];
	private int[] maxKundenVomServer = new int[42]; // Noch vom Server kriegen als int[]

	public Bezirk(int id, String name, int einwohner, int maxFilialen,
			int[] boni) {
		this.bID = id;
		this.name = name;
		this.einwohner = einwohner;
		this.maxFilialen = maxFilialen;
		this.boni = boni;
	}

	public double getEinnahmen(int fid) { // Einnahmen weiter implementieren
		VerbrauchT vorrat = SpielLogik.getSpieler().getVorraete();
		VerbrauchT verbrauch = Filialen[fid].getVerbrauch(maxKundenVomServer[fid]);
		
		int[] voz1 = vorrat.getZutat(1);
		int[] voz2 = vorrat.getZutat(2);
		int[] voz3 = vorrat.getZutat(3);
		int[] voz4 = vorrat.getZutat(4);
		
		int[] vez1 = verbrauch.getZutat(1);
		int[] vez2 = verbrauch.getZutat(2);
		int[] vez3 = verbrauch.getZutat(3);
		int[] vez4 = verbrauch.getZutat(4);
		
		// vorrat / verbrauch = maxZutaten; <---- Stimmt das so?
		
		
		// hier weiter...
		
		
		
		int maxZutaten = 20;
		double einnahmen = Filialen[fid].getEinnahmen(maxZutaten,
				maxKundenVomServer[fid]);
		return einnahmen;
	}

	public String toHTML() // Labels k�nnen nur Zeilenumbr�che in HTML
	{
		return "<HTML><BODY>Bezirk: " + name + "<br> Einwohnerzahl: "
				+ einwohner + "<br> Boni: Noch nicht erforscht</HTML></BODY>";
	}

	public VerbrauchT getVerbrauch(int fid, int maxZutaten) {
		VerbrauchT verbrauch = Filialen[fid].getVerbrauch(maxZutaten); // Evtl.
																		// Fehler
																		// die
																		// maxZutaten?
		return verbrauch;
	}

	public void setAbsaetze(int absatz, int fid) {
		Filialen[fid].setAbsatz(absatz);
	}

	public void filialeEroeffnen(int fid, int groe�e, int typ, String besitzer,int qualitaet) {
		Filialen[fid].eroeffnen(groe�e, typ, besitzer,qualitaet);
	}

	public void setFilialAttribute(int fid, int mitarbeiter,
			double produktPreis, int groe�e, int qualitaet) {
		Filialen[fid].setfID(fid);
		Filialen[fid].setMitarbeiter(mitarbeiter);
		Filialen[fid].setProduktPreis(produktPreis);
		Filialen[fid].setGroe�e(groe�e);
		Filialen[fid].setQualitaet(qualitaet);
	}

	public double verkaufen(int fid) {
		double verkaufsPreisF = 0;
		for (int i = 0; i < Filialen.length; i++) {
			if (Filialen[i].getfID() == fid) {
				verkaufsPreisF = Filialen[fid].verkaufen();
			}
		}
		return verkaufsPreisF;
	}

	public void setProduktPreis(int fid, double preis) {
		for (int i = 0; i < Filialen.length; i++) {
			if (Filialen[i].getfID() == fid) {
				Filialen[i].setProduktPreis(preis);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEinwohner() {
		return einwohner;
	}

	public void setEinwohner(int einwohner) {
		this.einwohner = einwohner;
	}

	public int[] getBoni() {
		return boni;
	}

	public void setBoni(int[] boni) {
		this.boni = boni;
	}

	public int getFreieKundschaft() {
		return freieKundschaft;
	}

	public void setFreieKundschaft(int freieKundschaft) {
		this.freieKundschaft = freieKundschaft;
	}

	public int getMaxFilialen() {
		return maxFilialen;
	}

	public void setMaxFilialen(int maxAnzFilialen) {
		this.maxFilialen = maxAnzFilialen;
	}

	public int getbID() {
		return bID;
	}

	public void setbID(int bID) {
		this.bID = bID;
	}

	public Filiale getFilialen(int fid) {
		try {
			return Filialen[fid % (bID * 10)];
		} catch (Exception e) {
		}
		return Filialen[fid]; // Filiale 0
	}

	public void setFilialen(Filiale[] filialen) {
		Filialen = filialen;
	}

}

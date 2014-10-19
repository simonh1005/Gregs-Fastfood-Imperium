package Client;


public class Bezirk {
	private String name;
	private int einwohner;
	private int[] boni = new int[3];
	private int freieKundschaft;
	private int maxFilialen;
	private static int bID;
	
	private Filiale[] Filialen = new Filiale[42];
	private int maxKundenVomServer = 20; // Noch vom Server kriegen
	
	
	public Bezirk(int id, String name, int einwohner, int maxFilialen, int[] boni)
	{
		this.bID = id;
		this.name = name;
		this.einwohner = einwohner;
		this.maxFilialen = maxFilialen;
		this.boni = boni;
	}
	public double getEinnahmen(int fid){
		VerbrauchT vorrat = SpielLogik.getSpieler().getVorraete();
		VerbrauchT verbrauch = Filialen[fid].getVerbrauch(maxKundenVomServer);
		// hier weiter...
		int maxZutaten = 20;
		double einnahmen = Filialen[fid].getEinnahmen(maxZutaten, maxKundenVomServer);
		return einnahmen;
	}
	public String toHTML() //Labels können nur Zeilenumbrüche in HTML
	{
		return "<HTML><BODY>Bezirk: " + name + "<br> Einwohnerzahl: " + einwohner + "<br> Boni: Noch nicht erforscht</HTML></BODY>";
	}
	public VerbrauchT getVerbrauch(int maxZutaten){
		VerbrauchT test = new VerbrauchT();
		return test;
	}
	
	public void setAbsaetze(int absatz, int fid){
		Filialen[fid].setAbsatz(absatz);
	}
	
	public void filialeEroeffnen(int filialnummer){
		// noch zu implementieren
	}
	
	public void setFilialAttribute(int fid, int mitarbeiter, double produktPreis, int groeße, int qualitaet){
		Filialen[fid].setfID(fid);
		Filialen[fid].setMitarbeiter(mitarbeiter);
		Filialen[fid].setProduktPreis(produktPreis);
		Filialen[fid].setGroeße(groeße);
		Filialen[fid].setQualitaet(qualitaet);
	}
	
	public double verkaufen(int fid){
		if (Filialen[fid].getfID() == fid) {
			Filialen[fid].verkaufen();
		}
		return 1.45;
	}
	
	public void setProduktPreis(int fid, double preis){
		if (Filialen[fid].getfID() == fid) {
			Filialen[fid].setProduktPreis(preis);
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

	public static int getbID() {
		return bID;
	}

	public void setbID(int bID) {
		this.bID = bID;
	}
	public Filiale[] getFilialen() {
		return Filialen;
	}
	public void setFilialen(Filiale[] filialen) {
		Filialen = filialen;
	}
	
	
	
	
}

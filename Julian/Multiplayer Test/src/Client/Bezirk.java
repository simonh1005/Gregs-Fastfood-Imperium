package Client;

public class Bezirk {
	private String name;
	private int einwohner;
	private int[] boni = new int[3];
	private int freieKundschaft;
	private int maxFilialen;
	private static int bID;
	
	public Bezirk(int id, String name, int einwohner, int maxFilialen, int[] boni)
	{
		this.bID = id;
		this.name = name;
		this.einwohner = einwohner;
		this.maxFilialen = maxFilialen;
		this.boni = boni;
	}
	public double getEinnahmen(int fid){
		
		return 1.65;
	}
	
	public VerbrauchT getVerbrauch(int maxZutaten){
		VerbrauchT test = new VerbrauchT();
		return test;
	}
	
	public void setAbsaetze(int absatz){
		Filiale.setAbsatz(absatz);
	}
	
	public void filialeEroeffnen(int filialnummer){
		// noch zu implementieren
	}
	
	public void setFilialAttribute(int fid, int mitarbeiter, double produktPreis, int groeﬂe, int qualitaet){
		Filiale.setFid(fid);
		Filiale.setMitarbeiter(mitarbeiter);
		Filiale.setProduktPreis(produktPreis);
		Filiale.setGroeﬂe(groeﬂe);
		Filiale.setQualitaet(qualitaet);
	}
	
	public double verkaufen(int fid){
		if (Filiale.getFid() == fid) {
			Filiale.verkaufen();
		}
		return 1.45;
	}
	
	public void setProduktPreis(int fid, double preis){
		if (Filiale.getFid() == fid) {
			Filiale.setProduktPreis(preis);
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
	
	
}

package Client;

public class Filiale {
	private int fID;
	private int typ;
	private FastFood fastFood;
	private int bezirk;
	private int mitarbeiter;
	private double produktPreis;
	private String besitzer;
	private int absatz;		//Wie viele Einheiten die Filiale absetzt
	private double wert;	// Wert der Filiale
	private int groeﬂe; // groeﬂe = 1: Mitarbeiter max 5;
						// groeﬂe = 2: Mitarbeiter max 20;
						// groeﬂe = 3: Mitarbeiter max 60;
	private int qualitaet;
	private double kaufPreis;
	private double verkaufsPreis;
	private double betriebsKostenF;
	private String name;
	private int maxKunden;

	public Filiale(int id, String name, int kaufpreis) {
		this.fID = id;
		this.name = name;
		this.kaufPreis = kaufpreis;
	}

	public String toHTML() {
		if (typ == 0) // Filiale noch neutral
		{
			return "<HTML><BODY>" + "Filiale : " + name + "<br> Kaufpreis: "
					+ kaufPreis + "Ä</HTML></BODY>";
		}
		return "verkauft";

	}

	private int getMAAnz() {
		return mitarbeiter;
	}

	public int mitarbeiterzahlAendern(int zahl) {
		int maxMitarbeiter = 0;
		if (groeﬂe == 1) {
			maxMitarbeiter = 5;
		}

		if (groeﬂe == 2) {
			maxMitarbeiter = 20;
		}

		if (groeﬂe == 3) {
			maxMitarbeiter = 60;
		}

		if (mitarbeiter <= maxMitarbeiter) {
			mitarbeiter = mitarbeiter + zahl;
			return zahl;
			
		}

		return 0;
	}

	


	public VerbrauchT getSollVerbrauch() { //bleibt
		Zutat[] tmpZ = fastFood.getZutaten();

		VerbrauchT tmpV = new VerbrauchT();

		if (tmpZ[0].getId() == 1) { // F¸r alle FastFoods gilt ja, dass die
									// erste Zutat die Teigwaren sind. Also nur
									// eine Abfrage.

			if (tmpZ[0].getQualitaet() == 1) {
				int wert = 1 * maxKunden;
				int[] werte = { wert, 0, 0 };
				tmpV.setZutat(1, werte);
			}

			if (tmpZ[0].getQualitaet() == 2) {
				int wert = 1 * maxKunden;
				int[] werte = { 0, wert, 0 };
				tmpV.setZutat(1, werte);
			}

			if (tmpZ[0].getQualitaet() == 3) {
				int wert = 1 * maxKunden;
				int[] werte = { 0, 0, wert };
				tmpV.setZutat(1, werte);
			}
		}

		if (tmpZ[1].getId() == 2) {

			if (tmpZ[1].getQualitaet() == 1) {
				int wert = 2 * maxKunden;
				int[] werte = { wert, 0, 0 };
				tmpV.setZutat(2, werte);
			}

			if (tmpZ[1].getQualitaet() == 2) {
				int wert = 2 * maxKunden;
				int[] werte = { 0, wert, 0 };
				tmpV.setZutat(2, werte);
			}

			if (tmpZ[1].getQualitaet() == 3) {
				int wert = 2 * maxKunden;
				int[] werte = { 0, 0, wert };
				tmpV.setZutat(2, werte);
			}
		}

		if (tmpZ[1].getId() == 3) {

			if (tmpZ[1].getQualitaet() == 1) {
				int wert = 2 * maxKunden;
				int[] werte = { wert, 0, 0 };
				tmpV.setZutat(3, werte);
			}

			if (tmpZ[1].getQualitaet() == 2) {
				int wert = 2 * maxKunden;
				int[] werte = { 0, wert, 0 };
				tmpV.setZutat(3, werte);
			}

			if (tmpZ[1].getQualitaet() == 3) {
				int wert = 2 * maxKunden;
				int[] werte = { 0, 0, wert };
				tmpV.setZutat(3, werte);
			}
		}

		if (tmpZ[1].getId() == 4) {

			if (tmpZ[1].getQualitaet() == 1) {
				int wert = 2 * maxKunden;
				int[] werte = { wert, 0, 0 };
				tmpV.setZutat(4, werte);
			}

			if (tmpZ[1].getQualitaet() == 2) {
				int wert = 2 * maxKunden;
				int[] werte = { 0, wert, 0 };
				tmpV.setZutat(4, werte);
			}

			if (tmpZ[1].getQualitaet() == 3) {
				int wert = 2 * maxKunden;
				int[] werte = { 0, 0, wert };
				tmpV.setZutat(4, werte);
			}
		}

		return tmpV;
	}

	public double getVerkaufsPreis() {
		return verkaufsPreis;
	}

	public void setVerkaufsPreis(double verkaufsPreis) {
		this.verkaufsPreis = verkaufsPreis;
	}

	
	public double getEinnahmen(){	
		double einnahmen = maxKunden * verkaufsPreis;
		return einnahmen;
	}
	
	public double getEinnahmen(VerbrauchT maxZutaten) {	
		int quali = getQualitaet();
		FastFood food = fastFood;
		VerbrauchT tmpZ = maxZutaten;
		
		Zutat[] z1 = fastFood.getZutaten();
		
		int q1 = z1[0].getQualitaet()-1;
		int q2 = z1[1].getQualitaet()-1;
		
		int id1 = z1[1].getId();
		int id2 = z1[1].getId();
		
		int [] maxZ1 = maxZutaten.getZutat(id1);
		int [] maxZ2 = maxZutaten.getZutat(id2);
		
		int menge1 = 0;
		int menge2 = 0;
		
		for (int i = 0; i < maxZ1.length; i++) {
			if (i == q1) {
				menge1 = maxZ1[i];
			}
		}
		
		for (int i = 0; i < maxZ2.length; i++) {
			if (i == q2) {
				menge2 = maxZ2[i];
			}
		}
		
		int anz1 = menge1;
		int anz2 = menge2/2;
		int anz = 0;
		
		if (anz1 > anz2) {
			anz = anz2;
		}
		
		if (anz1 < anz2) {
			anz = anz1;
		}
		
		if (anz1 == anz2) {
			anz = anz1;
		}
		
		
		
		double einnahmen = verkaufsPreis * anz;
		
		return einnahmen;
	}

	public void eroeffnen(int groeﬂe, int typ, String besitzer, int qualitaet) {
		this.typ = typ;
		
		String fastfoodname = "";
		switch (typ) {
		case 1:
			fastfoodname = "Dˆner";
			break;
		case 2:
			fastfoodname = "Currywurst";
			break;
		case 3:
			fastfoodname = "Pizza";
			break;
		}
		setFastFood(new FastFood(fastfoodname,qualitaet));
		this.groeﬂe = groeﬂe;
		this.besitzer = besitzer;
	}

	public double verkaufen() {
		double FilialeVerkaufsPreis = wert * 0.5;
		typ = 0;
		mitarbeiter = 0;
		produktPreis = 0;
		besitzer = null;
		absatz = 0;
		groeﬂe = 0;
		qualitaet = 0;
		return FilialeVerkaufsPreis;
	}

	public int getfID() {
		return fID;
	}

	public void setfID(int fID) {
		this.fID = fID;
	}

	public int getTyp() {
		return typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
	}

	public int getBezirk() {
		return bezirk;
	}

	public void setBezirk(int bezirk) {
		this.bezirk = bezirk;
	}

	public int getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(int mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public double getProduktPreis() {
		return produktPreis;
	}

	public void setProduktPreis(double produktPreis) {
		this.produktPreis = produktPreis;
	}

	public String getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(String besitzer) {
		this.besitzer = besitzer;
	}

	public int getAbsatz() {
		return absatz;
	}

	public void setAbsatz(int absatz) {
		this.absatz = absatz;
	}

	public double getWert() {
		return wert;
	}

	public void setWert(double wert) {
		this.wert = wert;
	}

	public int getGroeﬂe() {
		return groeﬂe;
	}

	public void setGroeﬂe(int groeﬂe) {
		this.groeﬂe = groeﬂe;
	}

	public int getQualitaet() {
		return qualitaet;
	}

	public void setQualitaet(int qualitaet) {
		this.qualitaet = qualitaet;
	}

	public double getKaufPreis() {
		return kaufPreis;
	}

	public void setKaufPreis(double kaufPreis) {
		this.kaufPreis = kaufPreis;
	}

	public double getBetriebsKostenF() {
		return betriebsKostenF;
	}

	public void setBetriebsKostenF(double betriebsKostenF) {
		this.betriebsKostenF = betriebsKostenF;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FastFood getFastFood() {
		return fastFood;
	}

	public void setFastFood(FastFood fastFood) {
		this.fastFood = fastFood;
	}

	public int getMaxKunden()
	{
		return maxKunden;
	}

	public void setMaxKunden(int maxKunden)
	{
		this.maxKunden = maxKunden;
	}
	
	

}

package Client;

public class Filiale {
	private static int fID;
	private static int typ;
	private int bezirk;
	private static int mitarbeiter;
	private static double produktPreis;
	private static String besitzer;
	private static int filialnummer;
	private static int absatz;
	private static double wert;
	private static int groeße;
	private static int qualitaet;
	private double kaufPreis;
	private double verkaufsPreis;
	private static int freieMitarbeiter = Spieler.getfreieMitarbeiter();
	private static double betriebsKostenF;
	
	public Filiale(int fID, int typ, int bezirk, int mitarbeiter, int produktPreis, String besitzer, int absatz, int groeße, int qualitaet, int verkaufsPreis){
		this.fID = fID;
		this.typ = typ; 
		this.bezirk = bezirk; 
		this.mitarbeiter = mitarbeiter;
		this.produktPreis = produktPreis;
		this.besitzer = besitzer;
		this.absatz = absatz; 
		this.groeße = groeße; 
		this.qualitaet = qualitaet; 
		this.verkaufsPreis = verkaufsPreis;
	}
	
	
	private int getMAAnz(){
		return mitarbeiter;
	}
	
	public int mitarbeiterzahlAendern(int zahl){
		mitarbeiter = mitarbeiter + zahl;
		freieMitarbeiter = freieMitarbeiter - zahl;
		return mitarbeiter;
	}
	
	public VerbrauchT getVerbrauch(){			//Soll Verbrauch
		String fastfoodname = "";
		
		switch (typ) {
		case 1:
			fastfoodname = "Döner";
			break;
		case 2:
			fastfoodname = "Currywurst";
			break;
		case 3:
			fastfoodname = "Pizza";
			break;
		}
		
		FastFood fastfood = new FastFood(fastfoodname, qualitaet);
		
		Zutat[] tmpZ = fastfood.getZutaten();
		
		VerbrauchT tmpV = new VerbrauchT();
		
		
		
		
		if (tmpZ[0].getId() == 1) {				// Für alle FastFoods gilt ja, dass die erste Zutat die Teigwaren sind. Also nur eine Abfrage.
			
			if (tmpZ[0].getQualitaet() == 1) {
				int wert = 1*absatz; 						
				int[] werte = {wert,0,0};
				tmpV.setZutat(1, werte);
			}
			
			if (tmpZ[0].getQualitaet() == 2) {
				int wert = 1*absatz; 						
				int[] werte = {0,wert,0};
				tmpV.setZutat(1, werte);
			}
			
			if (tmpZ[0].getQualitaet() == 3) {
				int wert = 1*absatz; 						
				int[] werte = {0,0,wert};
				tmpV.setZutat(1, werte);
			}
		}
		
		
		if (tmpZ[1].getId() == 2) {
			
			if (tmpZ[1].getQualitaet() == 1) {
				int wert = 2*absatz; 						
				int[] werte = {wert,0,0};
				tmpV.setZutat(2, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 2) {
				int wert = 2*absatz; 						
				int[] werte = {0,wert,0};
				tmpV.setZutat(2, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 3) {
				int wert = 2*absatz; 						
				int[] werte = {0,0,wert};
				tmpV.setZutat(2, werte);
			}
		}
		
		
		if (tmpZ[1].getId() == 3) {
			
			if (tmpZ[1].getQualitaet() == 1) {
				int wert = 2*absatz; 						
				int[] werte = {wert,0,0};
				tmpV.setZutat(3, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 2) {
				int wert = 2*absatz; 						
				int[] werte = {0,wert,0};
				tmpV.setZutat(3, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 3) {
				int wert = 2*absatz; 						
				int[] werte = {0,0,wert};
				tmpV.setZutat(3, werte);
			}
		}
		
		
		if (tmpZ[1].getId() == 4) {
			
			if (tmpZ[1].getQualitaet() == 1) {
				int wert = 2*absatz; 						
				int[] werte = {wert,0,0};
				tmpV.setZutat(4, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 2) {
				int wert = 2*absatz; 						
				int[] werte = {0,wert,0};
				tmpV.setZutat(4, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 3) {
				int wert = 2*absatz; 						
				int[] werte = {0,0,wert};
				tmpV.setZutat(4, werte);
			}
		}
		
		return tmpV;
	}
	
	public VerbrauchT getVerbrauch(int maxKunden){	//Ist Verbrauch		
		String fastfoodname = "";
		
		switch (typ) {
		case 1:
			fastfoodname = "Döner";
			break;
		case 2:
			fastfoodname = "Currywurst";
			break;
		case 3:
			fastfoodname = "Pizza";
			break;
		}
		
		FastFood fastfood = new FastFood(fastfoodname, qualitaet);
		
		Zutat[] tmpZ = fastfood.getZutaten();
		
		VerbrauchT tmpV = new VerbrauchT();
		
		
		
		
		if (tmpZ[0].getId() == 1) {				// Für alle FastFoods gilt ja, dass die erste Zutat die Teigwaren sind. Also nur eine Abfrage.
			
			if (tmpZ[0].getQualitaet() == 1) {
				int wert = 1*maxKunden; 						
				int[] werte = {wert,0,0};
				tmpV.setZutat(1, werte);
			}
			
			if (tmpZ[0].getQualitaet() == 2) {
				int wert = 1*maxKunden; 						
				int[] werte = {0,wert,0};
				tmpV.setZutat(1, werte);
			}
			
			if (tmpZ[0].getQualitaet() == 3) {
				int wert = 1*maxKunden; 						
				int[] werte = {0,0,wert};
				tmpV.setZutat(1, werte);
			}
		}
		
		
		if (tmpZ[1].getId() == 2) {
			
			if (tmpZ[1].getQualitaet() == 1) {
				int wert = 2*maxKunden; 						
				int[] werte = {wert,0,0};
				tmpV.setZutat(2, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 2) {
				int wert = 2*maxKunden; 						
				int[] werte = {0,wert,0};
				tmpV.setZutat(2, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 3) {
				int wert = 2*maxKunden; 						
				int[] werte = {0,0,wert};
				tmpV.setZutat(2, werte);
			}
		}
		
		
		if (tmpZ[1].getId() == 3) {
			
			if (tmpZ[1].getQualitaet() == 1) {
				int wert = 2*maxKunden; 						
				int[] werte = {wert,0,0};
				tmpV.setZutat(3, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 2) {
				int wert = 2*maxKunden; 						
				int[] werte = {0,wert,0};
				tmpV.setZutat(3, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 3) {
				int wert = 2*maxKunden; 						
				int[] werte = {0,0,wert};
				tmpV.setZutat(3, werte);
			}
		}
		
		
		if (tmpZ[1].getId() == 4) {
			
			if (tmpZ[1].getQualitaet() == 1) {
				int wert = 2*maxKunden; 						
				int[] werte = {wert,0,0};
				tmpV.setZutat(4, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 2) {
				int wert = 2*maxKunden; 						
				int[] werte = {0,wert,0};
				tmpV.setZutat(4, werte);
			}
			
			if (tmpZ[1].getQualitaet() == 3) {
				int wert = 2*maxKunden; 						
				int[] werte = {0,0,wert};
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

	public double getEinnahmen(int maxZutaten, int maxKunden){
		// maxZutaten als wurde hier als int eingeplant? 
		// Gehe davon aus das ist der Wert, der sagt wie viele Stück FastFood mit den jetztigen Zutaten hergestellt werden können.
		int wirklicheVerkaeufe = 0;
		
		if (maxZutaten == maxKunden) {
			wirklicheVerkaeufe = maxKunden;
		}
		
		if (maxZutaten > maxKunden) {
			wirklicheVerkaeufe = maxKunden;
		}
		
		if (maxZutaten < maxKunden) {
			wirklicheVerkaeufe = maxZutaten;
		}
		
		
		double einnahmen = wirklicheVerkaeufe * verkaufsPreis;
		return einnahmen;
	}
	
	public void eroeffnen(int groeße, int typ, String besitzer){
		this.typ = typ;
		this.groeße = groeße;
		this.besitzer = besitzer;
	}
	
	public static double verkaufen(){
		double verkaufsPreis = wert * 0.5;
		typ = 0;
		mitarbeiter = 0;
		produktPreis = 0;
		besitzer = null;
		filialnummer = 0;
		absatz = 0;
		groeße = 0;
		qualitaet = 0;
		freieMitarbeiter = 0;		
		return verkaufsPreis;
	}

	public int getAbsatz() {
		return absatz;
	}

	public static void setAbsatz(int absatz2) {
		absatz = absatz2;
	}

	public static int getFid() {
		return fID;
	}

	public static void setFid(int fid) {
		fID = fid;
	}

	public double getProduktPreis() {
		return produktPreis;
	}

	public static void setProduktPreis(double preis) {
		produktPreis = preis;
	}

	public static void setMitarbeiter(int mitarbeiter2) {
		mitarbeiter = mitarbeiter2;
		
	}

	public static void setGroeße(int groeße2) {
		groeße = groeße2;
		
	}

	public static void setQualitaet(int qualitaet2) {
		qualitaet = qualitaet2;
		
	}


	public static double getBetriebsKostenF() {
		return betriebsKostenF;
	}


	public static void setBetriebsKostenF(double betriebsKostenF) {
		Filiale.betriebsKostenF = betriebsKostenF;
	}


	public static String getBesitzer() {
		return besitzer;
	}


	public static void setBesitzer(String besitzer) {
		Filiale.besitzer = besitzer;
	}


	public static int getTyp() {
		return typ;
	}


	public static void setTyp(int typ) {
		Filiale.typ = typ;
	}


	
	
	
	
	
}

package Server;

public class Filiale {
	private int fID;
	private int typ;
	private int bezirk;
	private int mitarbeiter;
	private double produktPreis;
	private String besitzer;
	private int filialnummer;
	private int absatz;
	private double wert;
	private int groeﬂe;
	private int qualitaet;
	private double kaufPreis;
	private double verkaufsPreis;
	
	private int getMAANz(){
		return 1;
	}
	
	public int mitarbeiterzahlAendern(int zahl){
		return zahl;
	}
	
	public VerbrauchT getVerbrauch(){
		VerbrauchT test = new VerbrauchT();
		return test;
	}
	
	public VerbrauchT getVerbrauch(int maxZutaten, int maxKunden){
		VerbrauchT test = new VerbrauchT();
		return test;
	}
	
	public double getEinnahmen(int maxZutaten, int maxKunden){
		return 1.45;		
	}
}

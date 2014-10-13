package Client;

public class Bezirk {
	private String name;
	private int einwohner;
	private int[] boni = new int[3];
	private int freieKundschaft;
	private int maxAnzFilialen;
	private int bID;
	
	public double getEinnahmen(){
		return 1.56;
	}
	
	public VerbrauchT getVerbrauch(int maxZutaten){
		VerbrauchT test = new VerbrauchT();
		return test;
	}
	
	public void setAbsaetze(int absatz){
		
	}
	
	public void filialeEroeffnen(int filialnummer){
		
	}
	
	public void setProduktPreis(int fid){
		
	}
}

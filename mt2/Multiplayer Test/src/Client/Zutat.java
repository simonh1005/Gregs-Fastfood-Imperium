package Client;

public class Zutat {
	
	private String name;
	private int id;
	private int qualitaet;
	
	
	public Zutat(int typ, int qualitaet){
		this.name = getName(typ);
		this.qualitaet = qualitaet;
	}


	public String getName(int typ) {
		if (typ == 1) {
			setName("Teigwaren");
			setId(1);
		}
		
		if (typ == 2) {
			setName("Dönerfüllung");
			setId(2);
		}
		
		if (typ == 3) {
			setName("Wurst");
			setId(3);
		}
		
		if (typ == 4) {
			setName("Pizzabelag");
			setId(4);
		}
		
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public int getQualitaet() {		
		return qualitaet;
	}

	public void setQualitaet(int qualitaet) {
		this.qualitaet = qualitaet;
	}
	
	
}

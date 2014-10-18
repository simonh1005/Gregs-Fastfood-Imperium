package Client;

public class FastFood {
	
	private String name;
	private int qualitaet;
	
	
	public FastFood(String name, int qualitaet){
		setName(name);
		setQualitaet(qualitaet);
	}
	
	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getQualitaet() {
		return qualitaet;
	}

	public void setQualitaet(int qualitaet) {
		this.qualitaet = qualitaet;
	}

	
	
	public Zutat[] getZutaten(){
		Zutat[] zutaten = new Zutat[1];
		
		
		if (name.equals("Döner")) {
			zutaten[0] = new Zutat(1, qualitaet);
			zutaten[1] = new Zutat(2, qualitaet);
		}
		
		if (name.equals("Currywurst")) {
			zutaten[0] = new Zutat(1, qualitaet);
			zutaten[1] = new Zutat(3, qualitaet);		
		}
		
		if (name.equals("Pizza")) {
			zutaten[0] = new Zutat(1, qualitaet);
			zutaten[1] = new Zutat(4, qualitaet);
		}
		
		return zutaten;
	}
}

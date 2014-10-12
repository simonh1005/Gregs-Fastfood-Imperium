package Server;

public class Bezirk {
	private String name;
	private int einwohner;
	private int[] boni = new int[3];
	private int freieKundschaft;
	
	public double calcBoni(){
		return 1.45;
	}
	
	public int calcKundschaft(){
		return 1;
	}
	
	public void setProduktPreis(int fid){
		
	}
}

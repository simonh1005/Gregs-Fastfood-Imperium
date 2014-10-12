public class Filiale
{
	public static final int typ_Burger = 0;
	public static final int typ_Pizza = 1;
	public static final int typ_Wurst = 2;

	int id;
	String name;
	int typ;
	int kaufpreis;
	public Filiale(int id, String name, int typ, int kaufpreis)
	{
		this.id = id;
		this.name = name;
		this.typ = typ;
		this.kaufpreis = kaufpreis;
	}

}

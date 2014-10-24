package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Client.Filiale;

public class FilialeTest
{

	@Test
	public void testFiliale()
	{
		Filiale f = new Filiale(1, "Egon",120000);
		assert(f.getBesitzer().equals("Egon"));
	}

	@Test
	public void testGetVerbrauch()
	{
//		fail("Not yet implemented");
	}

}

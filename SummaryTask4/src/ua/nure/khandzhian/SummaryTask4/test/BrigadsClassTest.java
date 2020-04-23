package ua.nure.khandzhian.SummaryTask4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.khandzhian.SummaryTask4.db.entity.Brigads;

public class BrigadsClassTest {

	static Brigads brigads;
	
	@BeforeClass
	public static void constructorTest(){
		brigads = new Brigads();
	}
	
	@Test
	public void gettersSettersTest(){
	
		brigads.setIdBrigads(1);
		brigads.setIdWorker(10);
		
		Assert.assertEquals(1, brigads.getIdBrigads());
		Assert.assertEquals(10, brigads.getIdWorker());
	}
}

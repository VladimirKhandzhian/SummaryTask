package ua.nure.khandzhian.SummaryTask4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.khandzhian.SummaryTask4.db.entity.Airplane;

public class AirplaneClassTest {

	static Airplane airplane;
	
	@BeforeClass
	public static void constructorTest(){
		airplane = new Airplane();
	}


    @Test
	public void gettersSettersTest() throws Exception{

		airplane.setIdAirplane(1);
		airplane.setModel("KA 256");
		airplane.setNumberOfSeats(500);

		Assert.assertEquals(1, airplane.getIdAirplane());
		Assert.assertEquals("KA 256", airplane.getModel());
		Assert.assertEquals(500, airplane.getNumberOfSeats());
	}
}

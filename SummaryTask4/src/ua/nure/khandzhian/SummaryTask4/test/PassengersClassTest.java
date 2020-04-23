package ua.nure.khandzhian.SummaryTask4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.khandzhian.SummaryTask4.db.entity.Passengers;

public class PassengersClassTest {

	static Passengers passengers;
	
	@BeforeClass
	public static void constructorTest(){
		passengers = new Passengers();
	}
	
	@Test
	public void gettersSettersTest(){
	
		passengers.setPassportId("MR1526");
		passengers.setFirstName("firstName");
		passengers.setSecondName("secondName");
		passengers.setEmail("email");
		passengers.setIdStatus(1);
		passengers.setLogin("login");
		passengers.setPassword("password");
		
		Assert.assertEquals("MR1526", passengers.getPassportId());
		Assert.assertEquals("firstName", passengers.getFirstName());
		Assert.assertEquals("secondName", passengers.getSecondName());
		Assert.assertEquals("email", passengers.getEmail());
		Assert.assertEquals(1, passengers.getIdStatus());
		Assert.assertEquals("login", passengers.getLogin());
		Assert.assertEquals("password", passengers.getPassword());
	}
}

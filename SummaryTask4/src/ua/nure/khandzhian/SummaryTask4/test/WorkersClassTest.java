package ua.nure.khandzhian.SummaryTask4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.khandzhian.SummaryTask4.db.entity.Workers;

public class WorkersClassTest {

	static Workers workers;
	
	@BeforeClass
	public static void constructorTest(){
		workers = new Workers();
	}
	
	@Test
	public void gettersSettersTest(){
	
		workers.setIdWorker(1);
		workers.setFirstName("firstName");
		workers.setSecondName("secondName");
		workers.setEmail("email");
		workers.setIdStatus(1);
		workers.setLogin("login");
		workers.setPassword("password");
		workers.setPhoneNumber("0985631254");
		workers.setProfession("Stuard");
		
		Assert.assertEquals(1, workers.getIdWorker());
		Assert.assertEquals("firstName", workers.getFirstName());
		Assert.assertEquals("secondName", workers.getSecondName());
		Assert.assertEquals("email", workers.getEmail());
		Assert.assertEquals(1, workers.getIdStatus());
		Assert.assertEquals("login", workers.getLogin());
		Assert.assertEquals("password", workers.getPassword());
		Assert.assertEquals("0985631254", workers.getPhoneNumber());
		Assert.assertEquals("Stuard", workers.getProfession());
	}
}

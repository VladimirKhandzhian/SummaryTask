package ua.nure.khandzhian.SummaryTask4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.khandzhian.SummaryTask4.db.entity.Status;

public class StatusClassTest {

	static Status status;
	
	@BeforeClass
	public static void constructorTest(){
		status = new Status();
	}
	
	@Test
	public void gettersSettersTest(){
	
		status.setIdStatus(1);
		status.setStatusName("User");
		
		Assert.assertEquals(1, status.getIdStatus());
		Assert.assertEquals("User", status.getStatusName());
	}
}

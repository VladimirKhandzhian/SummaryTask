package ua.nure.khandzhian.SummaryTask4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.khandzhian.SummaryTask4.db.entity.Requests;

public class RequestsClassTest {

	static Requests requests;
	
	@BeforeClass
	public static void constructorTest(){
		requests = new Requests();
	}
	
	@Test
	public void gettersSettersTest(){
	
		requests.setIdRequest(1);
		requests.setTextOfRequest("Text");
		requests.setIdWorker(2);
		requests.setResponse("True");
		
		Assert.assertEquals(1, requests.getIdRequest());
		Assert.assertEquals("Text", requests.getTextOfRequest());
		Assert.assertEquals(2, requests.getIdWorker());
		Assert.assertEquals("True", requests.getResponse());
	}
}

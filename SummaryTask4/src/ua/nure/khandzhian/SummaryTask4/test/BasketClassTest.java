package ua.nure.khandzhian.SummaryTask4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.khandzhian.SummaryTask4.db.entity.Basket;

public class BasketClassTest {

	static Basket basket;
	
	@BeforeClass
	public static void constructorTest(){
		basket = new Basket();
	}
	
	@Test
	public void gettersSettersTest(){
	
		basket.setId(1);
		basket.setTicketId(100);
		basket.setPassportId("MR1256LK");
		basket.setSessionId("HFKSKDS91596529459SBAABSI");
		
		Assert.assertEquals(1, basket.getId());
		Assert.assertEquals(100, basket.getTicketId());
		Assert.assertEquals("MR1256LK", basket.getPassportId());
		Assert.assertEquals("HFKSKDS91596529459SBAABSI", basket.getSessionId());
	}
}

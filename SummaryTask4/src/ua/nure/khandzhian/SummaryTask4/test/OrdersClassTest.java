package ua.nure.khandzhian.SummaryTask4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;

public class OrdersClassTest {

	static Orders orders;
	
	@BeforeClass
	public static void constructorTest(){
		orders = new Orders();
	}
	
	@Test
	public void gettersSettersTest(){
	
		orders.setId(1);
		orders.setTicketId(20);
		orders.setPassportId("MR1526");
		orders.setFirstName("firstName");
		orders.setSecondName("secondName");
		orders.setEmail("email");
		orders.setPrice(2000);
		
		Assert.assertEquals(1, orders.getId());
		Assert.assertEquals(20, orders.getTicketId());
		Assert.assertEquals("MR1526", orders.getPassportId());
		Assert.assertEquals("firstName", orders.getFirstName());
		Assert.assertEquals("secondName", orders.getSecondName());
		Assert.assertEquals("email", orders.getEmail());
		Assert.assertTrue(2000 == orders.getPrice());
	}
}

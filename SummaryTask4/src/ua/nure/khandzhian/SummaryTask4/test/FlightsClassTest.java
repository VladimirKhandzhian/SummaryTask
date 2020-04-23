package ua.nure.khandzhian.SummaryTask4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.khandzhian.SummaryTask4.db.entity.Flights;

public class FlightsClassTest {

	static Flights flights;
	
	@BeforeClass
	public static void constructorTest(){
		flights = new Flights();
	}
	
	@Test
	public void gettersSettersTest(){
	
		flights.setId(1);
		flights.setFlightNumber("GH 1232");
		flights.setNameOfFlight("Name of flight");
		flights.setDeparture("Town1");
		flights.setDestination("Town2");
		flights.setIdAirplane(3);
		flights.setEmptyOfSeats(150);
		flights.setPrice(1000);
		flights.setFlightStatus("По рассписанию");
		flights.setIdBrigad(8);
		
		Assert.assertEquals(1, flights.getId());
		Assert.assertEquals("GH 1232", flights.getFlightNumber());
		Assert.assertEquals("Name of flight", flights.getNameOfFlight());
		Assert.assertEquals("Town1", flights.getDeparture());
		Assert.assertEquals("Town2", flights.getDestination());
		Assert.assertEquals(3, flights.getIdAirplane());
		Assert.assertEquals(150, flights.getEmptyOfSeats());
		Assert.assertTrue(1000 == flights.getPrice());
		Assert.assertEquals("По рассписанию", flights.getFlightStatus());
		Assert.assertEquals(8, flights.getIdBrigad());
	}
}

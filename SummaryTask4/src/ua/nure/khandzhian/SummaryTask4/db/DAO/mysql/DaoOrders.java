package ua.nure.khandzhian.SummaryTask4.db.DAO.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.db.DBManager;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceOrders;
import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;
import ua.nure.khandzhian.SummaryTask4.exception.DBException;
import ua.nure.khandzhian.SummaryTask4.exception.Messages;

public class DaoOrders implements DaoInterfaceOrders{
	
	private static final Logger LOGGER = Logger.getLogger(DaoOrders.class);

	private static final String INSERT_INTO_ORDERS = "INSERT INTO orders(Id, Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Id_ticket, Id_passport, First_name, Second_name, Email, Price) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String GET_ORDERS_BY_PASSPORT_ID = "SELECT orders.Id, orders.Flight_number, orders.Name_of_flight, orders.Departure, "
			+ "orders.Destination, orders.Departure_date, orders.Destination_date, orders.Id_ticket FROM orders WHERE Id_passport = ?";
	
	private static final String GET_PASSENGER_BY_FLIGHT_ID = "SELECT Id_passport, First_name, Second_name, Email, "
			+ "Id_ticket FROM orders WHERE Id = ?";
	
	private static final String UPDATE_EMPTY_SEATS = "UPDATE flights SET Empty_of_seats = Empty_of_seats - 1 WHERE Id = ?";
	
	private static final String GET_COUNT_OF_TICKET = "SELECT COUNT(Id) FROM orders WHERE Id = ?";
	
	private static final String GET_INFORMATION_FOR_SEND_TICKET = "SELECT flights.Id, flights.Flight_number, flights.Name_of_flight, flights.Departure, "
			+ "flights.Destination, flights.Departure_date, flights.Destination_date, basket.Id_ticket, passengers.Id_passport, passengers.First_name, "
			+ "passengers.Second_name FROM flights INNER JOIN basket ON flights.Id = basket.Id INNER JOIN passengers ON basket.Id_passport = "
			+ "passengers.Id_passport WHERE basket.Id = ? AND basket.Id_passport = ?";
	
	private static final String GET_INFORMATION_FOR_FLIGHTS = "SELECT DISTINCT flights.Id, flights.Flight_number, flights.Name_of_flight, flights.Departure, "
			+ "flights.Destination, flights.Departure_date, flights.Destination_date, basket.Id_ticket, passengers.Id_passport, passengers.First_name, "
			+ "passengers.Second_name FROM flights INNER JOIN basket ON flights.Id = basket.Id INNER JOIN passengers ON basket.Id_passport = "
			+ "passengers.Id_passport WHERE basket.Id = ? AND basket.Id_passport = ?";
	
	@Override
	public void insertOrders(Orders order) throws Exception{
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement1 = connection.prepareStatement(INSERT_INTO_ORDERS);
			int i = 1;
			preparedStatement1.setInt(i++, order.getId());
			preparedStatement1.setString(i++, order.getFlightNumber());
			preparedStatement1.setString(i++, order.getNameOfFlight());
			preparedStatement1.setString(i++, order.getDeparture());
			preparedStatement1.setString(i++, order.getDestination());
			preparedStatement1.setString(i++, order.getDepartureDateTime().toString());
			preparedStatement1.setString(i++, order.getDestinationDateTime().toString());
			preparedStatement1.setInt(i++, order.getTicketId());
			preparedStatement1.setString(i++, order.getPassportId());
			preparedStatement1.setString(i++, order.getFirstName());
			preparedStatement1.setString(i++, order.getSecondName());
			preparedStatement1.setString(i++, order.getEmail());
			preparedStatement1.setDouble(i++, order.getPrice());
			preparedStatement1.executeUpdate();
			preparedStatement2 = connection.prepareStatement(UPDATE_EMPTY_SEATS);
			preparedStatement2.setInt(1, order.getId());
			preparedStatement2.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_INSERT_INTO_ORDERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_INSERT_INTO_ORDERS, e);
		} finally {
			db.close(preparedStatement2);
			db.close(connection, preparedStatement1, resultSet);
			
		}
	}	
	
	@Override
	public List<Orders> getOrdersByPassportId(Orders order) throws Exception{
		
		DBManager db = DBManager.getInstance();
		List<Orders> list = new ArrayList<Orders>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_ORDERS_BY_PASSPORT_ID);
			preparedStatement.setString(1, order.getPassportId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getOrdersByPassportId(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_ORDERS_BY_PASSPORT, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_ORDERS_BY_PASSPORT, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	private Orders getOrdersByPassportId(ResultSet resultSet) throws SQLException {
		Orders order = new Orders();
		order.setId(resultSet.getInt("Id"));
		order.setFlightNumber(resultSet.getString("Flight_number"));
		order.setNameOfFlight(resultSet.getString("Name_of_flight"));
		order.setDeparture(resultSet.getString("Departure"));
		order.setDestination(resultSet.getString("Destination"));
		order.setDepartureDateTime(resultSet.getObject("Departure_date", LocalDateTime.class));
		order.setDestinationDateTime(resultSet.getObject("Destination_date", LocalDateTime.class));
		order.setTicketId(resultSet.getInt("Id_ticket"));
		return order;
	}
	
	@Override
	public List<Orders> getPassengersByFlightId(Orders order) throws Exception{
		
		DBManager db = DBManager.getInstance();
		List<Orders> list = new ArrayList<Orders>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_PASSENGER_BY_FLIGHT_ID);
			preparedStatement.setInt(1, order.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getPassengersByFlightId(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_PASSNEGES_FROM_ORDERS_BY_FLIGHT_ID, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_PASSNEGES_FROM_ORDERS_BY_FLIGHT_ID, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	private Orders getPassengersByFlightId(ResultSet resultSet) throws SQLException {
		Orders order = new Orders();
		order.setTicketId(resultSet.getInt("Id_ticket"));
		order.setPassportId(resultSet.getString("Id_passport"));
		order.setFirstName(resultSet.getString("First_name"));
		order.setSecondName(resultSet.getString("Second_name"));
		order.setEmail(resultSet.getString("Email"));
		return order;
	}
	
	@Override
	public int getCountOfTocketByFlightId(int id) throws Exception{
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int numberOfTicket = 0;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_COUNT_OF_TICKET);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				numberOfTicket = resultSet.getInt(1);
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_COUNT_OF_TICKET, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_COUNT_OF_TICKET, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return numberOfTicket;
	}
		
	@Override
	public List<Orders> getInformationAboutTicket(Orders order) throws Exception{
		
		DBManager db = DBManager.getInstance();
		List<Orders> list = new ArrayList<Orders>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_INFORMATION_FOR_SEND_TICKET);
			preparedStatement.setInt(1, order.getId());
			preparedStatement.setString(2, order.getPassportId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getInformationAboutTicket(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_INFORMATION_ABOUT_TICKET, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_INFORMATION_ABOUT_TICKET, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	private Orders getInformationAboutTicket(ResultSet resultSet) throws SQLException {
		Orders order = new Orders();
		order.setId(resultSet.getInt("Id"));
		order.setFlightNumber(resultSet.getString("Flight_number"));
		order.setNameOfFlight(resultSet.getString("Name_of_flight"));
		order.setDeparture(resultSet.getString("Departure"));
		order.setDestination(resultSet.getString("Destination"));
		order.setDepartureDateTime(resultSet.getObject("Departure_date", LocalDateTime.class));
		order.setDestinationDateTime(resultSet.getObject("Destination_date", LocalDateTime.class));
		order.setTicketId(resultSet.getInt("Id_ticket"));
		order.setPassportId(resultSet.getString("Id_passport"));
		order.setFirstName(resultSet.getString("First_name"));
		order.setSecondName(resultSet.getString("Second_name"));
		return order;
	}
	
	@Override
	public Orders getInformationAboutFlights(Orders order) throws Exception{
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Orders order1 = new Orders();
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_INFORMATION_FOR_FLIGHTS);
			preparedStatement.setInt(1, order.getId());
			preparedStatement.setString(2, order.getPassportId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				order.setId(resultSet.getInt("Id"));
				order1.setFlightNumber(resultSet.getString("Flight_number"));
				order1.setNameOfFlight(resultSet.getString("Name_of_flight"));
				order1.setDeparture(resultSet.getString("Departure"));
				order1.setDestination(resultSet.getString("Destination"));
				order1.setDepartureDateTime(resultSet.getObject("Departure_date", LocalDateTime.class));
				order1.setDestinationDateTime(resultSet.getObject("Destination_date", LocalDateTime.class));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_INFORMATION_ABOUT_FLIGHTS_FROM_ORDERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_INFORMATION_ABOUT_FLIGHTS_FROM_ORDERS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return order1;
	}
	
}

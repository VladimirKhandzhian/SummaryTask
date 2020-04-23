package ua.nure.khandzhian.SummaryTask4.db.DAO.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.db.DBManager;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.entity.Flights;
import ua.nure.khandzhian.SummaryTask4.exception.DBException;
import ua.nure.khandzhian.SummaryTask4.exception.Messages;

public class DaoFlights implements DaoInterfaceFlights{
	
	private static final Logger LOGGER = Logger.getLogger(DaoFlights.class);

	private static final String GET_ALL_FLIGHTS = "SELECT Id, Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Flight_status, Id_brigad, flights.Id_airplane, Model, Empty_of_seats, Price "
			+ "FROM flights INNER JOIN airplane ON flights.Id_airplane = airplane.Id_airplane";
	
	private static final String GET_FLIGHTS_BY_WORKER_ID = "SELECT Id, Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Id_airplane, Flight_status, flights.Id_brigad FROM flights INNER JOIN brigads "
			+ "ON flights.Id_brigad = brigads.Id_brigad INNER JOIN workers ON workers.Id_worker = brigads.Id_worker "
			+ "WHERE workers.Id_worker = ? ORDER BY Departure_date DESC";
	
	private static final String GET_NUMBER_OF_SEATS_BY_AIRPLANE_ID = "SELECT Number_of_seats FROM airplane WHERE Id_airplane = ?";
	
	private static final String UPDATE_STATUS_FLIGHT = "UPDATE flights SET Flight_status = ? WHERE Id = ?";
	
	private static final String DELETE_FLIGHT = "DELETE FROM flights WHERE Id = ?";
	
	private static final String UPDATE_FLIGHT = "UPDATE flights SET Flight_number = ?, Name_of_flight = ?, Departure = ?, "
			+ "Destination = ?, Departure_date = ?, Destination_date = ?, Id_airplane = ?, Id_brigad = ?, Price = ? WHERE Id = ?";
	
	private static final String INSERT_FLIGHT = "INSERT INTO flights(Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Id_airplane, Id_brigad, Empty_of_seats, Price) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String GET_FLIGHT_BY_NUMBER = "SELECT Id, Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Flight_status, Id_brigad, flights.Id_airplane, Model, Empty_of_seats, Price "
			+ "FROM flights INNER JOIN airplane ON flights.Id_airplane = airplane.Id_airplane "
			+ "WHERE Flight_number = ? AND Destination_date > CURDATE() - INTERVAL 4 DAY";
	
	private static final String GET_FLIGHT_BY_DIRECTION = "SELECT Id, Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Flight_status, Id_brigad, flights.Id_airplane, Model, Empty_of_seats, Price "
			+ "FROM flights INNER JOIN airplane ON flights.Id_airplane = airplane.Id_airplane "
			+ "WHERE Departure = ? AND Destination = ? AND Departure_date >= ? AND Departure_date < ? + INTERVAL 1 DAY";
	
	private static final String GET_ALL_FLIGHTS_SORT_BY_ID_DESC = "SELECT Id, Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Flight_status, Id_brigad, flights.Id_airplane, Model, Empty_of_seats, Price "
			+ "FROM flights INNER JOIN airplane ON flights.Id_airplane = airplane.Id_airplane ORDER BY Id DESC";
	
	private static final String GET_ALL_FLIGHTS_SORT_BY_ID_ASC = "SELECT Id, Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Flight_status, Id_brigad, flights.Id_airplane, Model, Empty_of_seats, Price "
			+ "FROM flights INNER JOIN airplane ON flights.Id_airplane = airplane.Id_airplane ORDER BY Id ASC";
	
	private static final String GET_ALL_FLIGHTS_SORT_BY_NAME_DESC = "SELECT Id, Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Flight_status, Id_brigad, flights.Id_airplane, Model, Empty_of_seats, Price "
			+ "FROM flights INNER JOIN airplane ON flights.Id_airplane = airplane.Id_airplane ORDER BY Name_of_flight DESC";
	
	private static final String GET_ALL_FLIGHTS_SORT_BY_NAME_ASC = "SELECT Id, Flight_number, Name_of_flight, Departure, Destination, "
			+ "Departure_date, Destination_date, Flight_status, Id_brigad, flights.Id_airplane, Model, Empty_of_seats, Price "
			+ "FROM flights INNER JOIN airplane ON flights.Id_airplane = airplane.Id_airplane ORDER BY Name_of_flight ASC";
	
	private static final String GET_DAY_BEFORE_DEPARTURE = "SELECT DATEDIFF(((SELECT Departure_date FROM flights WHERE Id = ?)), current_timestamp());";
	
	@Override
	public List<Flights> getFlights() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Flights> list = new ArrayList<Flights>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_FLIGHTS);
			while (resultSet.next()) {
				list.add(getFlights(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	private Flights getFlights(ResultSet resultSet) throws SQLException {
		Flights fligths = new Flights();
		fligths.setId(resultSet.getInt("Id"));
		fligths.setFlightNumber(resultSet.getString("Flight_number"));
		fligths.setNameOfFlight(resultSet.getString("Name_of_flight"));
		fligths.setDeparture(resultSet.getString("Departure"));
		fligths.setDestination(resultSet.getString("Destination"));
		fligths.setDepartureDateTime(resultSet.getObject("Departure_date", LocalDateTime.class));
		fligths.setDestinationDateTime(resultSet.getObject("Destination_date", LocalDateTime.class));
		fligths.setFlightStatus(resultSet.getString("Flight_status"));
		fligths.setIdBrigad(resultSet.getInt("Id_brigad"));
		fligths.setIdAirplane(resultSet.getInt("Id_airplane"));
		fligths.setModel(resultSet.getString("Model"));
		fligths.setEmptyOfSeats(resultSet.getInt("Empty_of_seats"));
		fligths.setPrice(resultSet.getDouble("Price"));
		return fligths;
	}
	
	@Override
	public Flights findFlightById(int id) throws Exception {
		Flights flight = null;
		for (Flights flight1 : getFlights()) {
			if (id == flight1.getId()) {
				flight = flight1;
				break;
			}
		}
		return flight;
	}
	
	@Override
	public List<Flights> getFlightsByWorkerId(int workerId) throws Exception {

		DBManager db = DBManager.getInstance();
		List<Flights> list = new ArrayList<Flights>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_FLIGHTS_BY_WORKER_ID);
			preparedStatement.setInt(1, workerId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getFlightsByWorkerId(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_FLIGHTS_BY_WORKER_ID, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_FLIGHTS_BY_WORKER_ID, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	private Flights getFlightsByWorkerId(ResultSet resultSet) throws SQLException {
		Flights fligths = new Flights();
		fligths.setId(resultSet.getInt("Id"));
		fligths.setFlightNumber(resultSet.getString("Flight_number"));
		fligths.setNameOfFlight(resultSet.getString("Name_of_flight"));
		fligths.setDeparture(resultSet.getString("Departure"));
		fligths.setDestination(resultSet.getString("Destination"));
		fligths.setDepartureDateTime(resultSet.getObject("Departure_date", LocalDateTime.class));
		fligths.setDestinationDateTime(resultSet.getObject("Destination_date", LocalDateTime.class));
		fligths.setFlightStatus(resultSet.getString("Flight_status"));
		fligths.setIdBrigad(resultSet.getInt("Id_brigad"));
		fligths.setIdAirplane(resultSet.getInt("Id_airplane"));
		return fligths;
	}
	
	@Override
	public void updateStatusFlight(int id, String flightStatus) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(UPDATE_STATUS_FLIGHT);
			preparedStatement.setString(1, flightStatus);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_UPDATE_STATUS_OF_FLIGHTS, e);
			throw new DBException(Messages.ERR_CAN_NOT_UPDATE_STATUS_OF_FLIGHTS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void deleteFlight(int id) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_FLIGHT);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_DELETE_FLIGHTS, e);
			throw new DBException(Messages.ERR_CAN_NOT_DELETE_FLIGHTS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void updateFlight(Flights flights) throws Exception{
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(UPDATE_FLIGHT);
			int k = 1;
			preparedStatement.setString(k++, flights.getFlightNumber());
			preparedStatement.setString(k++, flights.getNameOfFlight());
			preparedStatement.setString(k++, flights.getDeparture());
			preparedStatement.setString(k++, flights.getDestination());
			preparedStatement.setString(k++, flights.getDepartureDateTime().toString());
			preparedStatement.setString(k++, flights.getDestinationDateTime().toString());
			preparedStatement.setInt(k++, flights.getIdAirplane());
			preparedStatement.setInt(k++, flights.getIdBrigad());
			preparedStatement.setDouble(k++, flights.getPrice());
			preparedStatement.setInt(k++, flights.getId());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_UPDATE_FLIGHTS, e);
			throw new DBException(Messages.ERR_CAN_NOT_UPDATE_FLIGHTS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void insertFlight(Flights flights) throws Exception{
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet resultSet = null;
		int numberOfSeats = 0;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement1 = connection.prepareStatement(GET_NUMBER_OF_SEATS_BY_AIRPLANE_ID);
			preparedStatement1.setInt(1, flights.getIdAirplane());
			resultSet = preparedStatement1.executeQuery();
			while (resultSet.next()) {
				numberOfSeats = resultSet.getInt("Number_of_seats");
			}
			preparedStatement2 = connection.prepareStatement(INSERT_FLIGHT);
			int k = 1;
			preparedStatement2.setString(k++, flights.getFlightNumber());
			preparedStatement2.setString(k++, flights.getNameOfFlight());
			preparedStatement2.setString(k++, flights.getDeparture());
			preparedStatement2.setString(k++, flights.getDestination());
			preparedStatement2.setString(k++, flights.getDepartureDateTime().toString());
			preparedStatement2.setString(k++, flights.getDestinationDateTime().toString());
			preparedStatement2.setInt(k++, flights.getIdAirplane());
			preparedStatement2.setInt(k++, flights.getIdBrigad());
			preparedStatement2.setInt(k++, numberOfSeats);
			preparedStatement2.setDouble(k++, flights.getPrice());
			preparedStatement2.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_INSERT_INTO_FLIGHTS, e);
			throw new DBException(Messages.ERR_CAN_NOT_INSERT_INTO_FLIGHTS, e);
		} finally {
			db.close(connection, preparedStatement1, resultSet);
			db.close(preparedStatement2);
		}
	}
	
	@Override
	public List<Flights> getFlightsByNumber(String flightNumber) throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Flights> list = new ArrayList<Flights>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_FLIGHT_BY_NUMBER);
			preparedStatement.setString(1, flightNumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getFlights(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_FLIGHTS_BY_NUMBER, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_FLIGHTS_BY_NUMBER, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	@Override
	public List<Flights> getFlightsByDirection(Flights flight) throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Flights> list = new ArrayList<Flights>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_FLIGHT_BY_DIRECTION);
			preparedStatement.setString(1, flight.getDeparture());
			preparedStatement.setString(2, flight.getDestination());
			preparedStatement.setString(3, flight.getDepartureDateTime().toString());
			preparedStatement.setString(4, flight.getDepartureDateTime().toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getFlights(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_FLIGHTS_BY_DIRECTION, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_FLIGHTS_BY_DIRECTION, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	@Override
	public List<Flights> getFlightsByIdDesc() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Flights> list = new ArrayList<Flights>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_FLIGHTS_SORT_BY_ID_DESC);
			while (resultSet.next()) {
				list.add(getFlights(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	@Override
	public List<Flights> getFlightsByIdAsc() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Flights> list = new ArrayList<Flights>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_FLIGHTS_SORT_BY_ID_ASC);
			while (resultSet.next()) {
				list.add(getFlights(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	@Override
	public List<Flights> getFlightsByNameDesc() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Flights> list = new ArrayList<Flights>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_FLIGHTS_SORT_BY_NAME_DESC);
			while (resultSet.next()) {
				list.add(getFlights(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	@Override
	public List<Flights> getFlightsByNameAsc() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Flights> list = new ArrayList<Flights>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_FLIGHTS_SORT_BY_NAME_ASC);
			while (resultSet.next()) {
				list.add(getFlights(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_FLIGHTS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	@Override
	public int getDayBeforeDeparture(int flightId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int dayBeforeDeparture = 0;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_DAY_BEFORE_DEPARTURE);
			preparedStatement.setInt(1, flightId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				dayBeforeDeparture = resultSet.getInt(1);
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_DAY_BEFORE_DEPARTURE, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_DAY_BEFORE_DEPARTURE, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return dayBeforeDeparture;
	}
}

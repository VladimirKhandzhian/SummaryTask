package ua.nure.khandzhian.SummaryTask4.db.DAO.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.db.DBManager;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfacePassengers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Passengers;
import ua.nure.khandzhian.SummaryTask4.exception.DBException;
import ua.nure.khandzhian.SummaryTask4.exception.Messages;

public class DaoPassengers implements DaoInterfacePassengers{
	
	private static final Logger LOGGER = Logger.getLogger(DaoPassengers.class);
	
	private static final String GET_ALL_PASSENGERS = "SELECT Id_passport, First_name, Second_name, Email, "
			+ "Login, Password, Id_status FROM passengers";

	private static final String INSERT_PASSENGER = "INSERT INTO passengers(Id_passport, First_name, "
			+ "Second_name, Email, Login, Password) VALUES(?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_PASSENGER = "UPDATE passengers SET Id_passport = ?, First_name = ?, "
			+ "Second_name = ?, Email = ? WHERE Id_passport = ?";
	
	private static final String DELETE_PASSENGER = "DELETE FROM passengers WHERE Id_passport = ?";
	
	@Override
	public List<Passengers> getPassengers() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Passengers> list = new ArrayList<Passengers>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_PASSENGERS);
			while (resultSet.next()) {
				list.add(getPassenger(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_PASSENGERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_PASSENGERS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	private Passengers getPassenger(ResultSet resultSet) throws SQLException {
		Passengers passenger = new Passengers();
		passenger.setPassportId(resultSet.getString("Id_passport"));
		passenger.setFirstName(resultSet.getString("First_name"));
		passenger.setSecondName(resultSet.getString("Second_name"));
		passenger.setEmail(resultSet.getString("Email"));
		passenger.setLogin(resultSet.getString("Login"));
		passenger.setPassword(resultSet.getString("Password"));
		passenger.setIdStatus(resultSet.getInt("Id_status"));
		return passenger;
	}
	
	@Override
	public Passengers findPassengerByLogin(String login) throws Exception {
		Passengers passenger = null;
		for (Passengers passenger1 : getPassengers()) {
			if (login.equals(passenger1.getLogin())) {
				passenger = passenger1;
				break;
			}
		}
		return passenger;
	}
	
	@Override
	public Passengers findPassengerById(String passportId) throws Exception {
		Passengers passenger = null;
		for (Passengers passenger1 : getPassengers()) {
			if (passportId.equals(passenger1.getPassportId())) {
				passenger = passenger1;
				break;
			}
		}
		return passenger;
	}
	
	@Override
	public void insertPassenger(Passengers passenger) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(INSERT_PASSENGER);
			int i = 1;
			preparedStatement.setString(i++, passenger.getPassportId());
			preparedStatement.setString(i++, passenger.getFirstName());
			preparedStatement.setString(i++, passenger.getSecondName());
			preparedStatement.setString(i++, passenger.getEmail());
			preparedStatement.setString(i++, passenger.getLogin());
			preparedStatement.setString(i++, passenger.getPassword());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_INSERT_INTO_PASSENGERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_INSERT_INTO_PASSENGERS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void updatePassenger(Passengers passenger, String oldPassportId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(UPDATE_PASSENGER);
			int i = 1;
			preparedStatement.setString(i++, passenger.getPassportId());
			preparedStatement.setString(i++, passenger.getFirstName());
			preparedStatement.setString(i++, passenger.getSecondName());
			preparedStatement.setString(i++, passenger.getEmail());
			preparedStatement.setString(i++, oldPassportId);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_UPDATE_PASSENGERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_UPDATE_PASSENGERS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void deletePassenger(String passportId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_PASSENGER);
			preparedStatement.setString(1, passportId);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_DELETE_PASSENGERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_DELETE_PASSENGERS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
}

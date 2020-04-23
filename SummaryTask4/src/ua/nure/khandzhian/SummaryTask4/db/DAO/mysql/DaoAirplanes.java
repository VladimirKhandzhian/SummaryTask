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
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceAirplanes;
import ua.nure.khandzhian.SummaryTask4.db.entity.Airplane;
import ua.nure.khandzhian.SummaryTask4.exception.DBException;
import ua.nure.khandzhian.SummaryTask4.exception.Messages;

public class DaoAirplanes implements DaoInterfaceAirplanes{
	
	private static final Logger LOGGER = Logger.getLogger(DaoAirplanes.class);

	private static final String GET_AIRPLANES = "SELECT Id_airplane, Model, Number_of_seats FROM airplane";
	
	private static final String GET_EMPTY_AIRPLANES = "SELECT Id_airplane, Model, Number_of_seats " + 
			"FROM airplane WHERE Id_airplane != ?";
	
	private static final String GET_AIRPLANES_BY_ID = "SELECT Model, Number_of_seats FROM airplane WHERE Id_airplane = ?";
	
	private static final String INSERT_AIRPLANE = "INSERT INTO airplane(Model, Number_of_seats) VALUES(?, ?)";
	
	private static final String UPDATE_AIRPLANE = "UPDATE airplane SET Model = ?, Number_of_seats = ? WHERE Id_airplane = ?";
	
	private static final String DELETE_AIRPLANE = "DELETE FROM airplane WHERE Id_airplane = ?";

	
	@Override
	public List<Airplane> getAirplane() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Airplane> list = new ArrayList<Airplane>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_AIRPLANES);
			while (resultSet.next()) {
				list.add(getAirplane(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_AIRPLANES, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_AIRPLANES, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	private Airplane getAirplane(ResultSet resultSet) throws SQLException {
		Airplane airplane = new Airplane();
		airplane.setIdAirplane(resultSet.getInt("Id_airplane"));
		airplane.setModel(resultSet.getString("Model"));
		airplane.setNumberOfSeats(resultSet.getInt("Number_of_seats"));
		return airplane;
	}
	
	@Override
	public Airplane findAirplaneById(int id) throws Exception {
		Airplane airplane = null;
		for (Airplane airplane1 : getAirplane()) {
			if (id == airplane1.getIdAirplane()) {
				airplane = airplane1;
				break;
			}
		}
		return airplane;
	}
	
	@Override
	public List<Airplane> getEmptyAirplane(int airplaneId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Airplane> list = new ArrayList<Airplane>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_EMPTY_AIRPLANES);
			preparedStatement.setInt(1, airplaneId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getAirplane(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_EMPTY_AIRPLANES, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_EMPTY_AIRPLANES, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	@Override
	public String getAirplaneModelById(int airplaneId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		String model = "";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_AIRPLANES_BY_ID);
			preparedStatement.setInt(1, airplaneId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				model = resultSet.getString("Model");
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_AIRPLANES_MODEL, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_AIRPLANES_MODEL, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return model;
	}
	
	@Override
	public int getNumberOfSeatsById(int airplaneId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		int numberOfSeats = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_AIRPLANES_BY_ID);
			preparedStatement.setInt(1, airplaneId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				numberOfSeats = resultSet.getInt("Number_of_seats");
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_AIRPLANES_NUMBER_OF_SEATS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_AIRPLANES_NUMBER_OF_SEATS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return numberOfSeats;
	}

	@Override
	public void insertAirplane(Airplane airplane) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(INSERT_AIRPLANE);
			int i = 1;
			preparedStatement.setString(i++, airplane.getModel());
			preparedStatement.setInt(i++, airplane.getNumberOfSeats());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_INSERT_AIRPLANES, e);
			throw new DBException(Messages.ERR_CAN_NOT_INSERT_AIRPLANES, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void updateAirplane(Airplane airplane) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(UPDATE_AIRPLANE);
			int i = 1;
			preparedStatement.setString(i++, airplane.getModel());
			preparedStatement.setInt(i++, airplane.getNumberOfSeats());
			preparedStatement.setInt(i++, airplane.getIdAirplane());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_UPDATE_AIRPLANES, e);
			throw new DBException(Messages.ERR_CAN_NOT_UPDATE_AIRPLANES, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void deleteAirplane(int airplaneId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_AIRPLANE);
			preparedStatement.setInt(1, airplaneId);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_DELETE_AIRPLANES, e);
			throw new DBException(Messages.ERR_CAN_NOT_DELETE_AIRPLANES, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
}

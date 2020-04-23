package ua.nure.khandzhian.SummaryTask4.db.DAO.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.db.DBManager;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceBasket;
import ua.nure.khandzhian.SummaryTask4.db.entity.Basket;
import ua.nure.khandzhian.SummaryTask4.exception.DBException;
import ua.nure.khandzhian.SummaryTask4.exception.Messages;

public class DaoBasket implements DaoInterfaceBasket{
	
	private static final Logger LOGGER = Logger.getLogger(DaoBasket.class);

	private static final String GET_ALL_TICKETS = "SELECT Id, Id_ticket, Id_passport FROM basket WHERE Id = ? "
			+ "UNION SELECT Id, Id_ticket, Id_passport FROM orders WHERE Id = ?";
	
	private static final String GET_ORDERS_BY_CLIENT_ID = "SELECT Id, Id_ticket, Id_passport FROM basket WHERE Id = ? AND Id_passport = ?";
	
	private static final String DELETE_FROM_BASKET = "DELETE FROM basket WHERE Id = ? AND Id_ticket = ? AND Id_passport = ?";
	
	private static final String INSERT_INTO_BASKET = "INSERT INTO basket(Id, Id_ticket, Id_passport, SessionId) VALUES(?, ?, ?, ?)";
	
	private static final String GET_SUM_TO_PAID = "CALL getSumToPaid(?, ?, ?)";
	
	private static final String DELETE_FROM_BASKET_BY_SESSION_ID = "DELETE FROM basket WHERE SessionId = ?";
	
	@Override
	public List<Basket> getTicketByFlightId(int flightId) throws Exception {

		DBManager db = DBManager.getInstance();
		List<Basket> list = new ArrayList<Basket>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_ALL_TICKETS);
			preparedStatement.setInt(1, flightId);
			preparedStatement.setInt(2, flightId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getTicketByFlightId(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_ALL_TICKET_BY_ID, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_ALL_TICKET_BY_ID, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	private Basket getTicketByFlightId(ResultSet resultSet) throws SQLException {
		Basket basket = new Basket();
		basket.setId(resultSet.getInt("Id"));
		basket.setTicketId(resultSet.getInt("Id_ticket"));
		basket.setPassportId(resultSet.getString("Id_passport"));
		return basket;
	}
	
	@Override
	public List<Basket> getBasketByClientId(Basket basket) throws Exception {

		DBManager db = DBManager.getInstance();
		List<Basket> list = new ArrayList<Basket>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_ORDERS_BY_CLIENT_ID);
			preparedStatement.setInt(1, basket.getId());
			preparedStatement.setString(2, basket.getPassportId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getTicketByFlightId(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_BASKET_BY_CLIENT_ID, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_BASKET_BY_CLIENT_ID, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	@Override
	public void deleteFromBasket(Basket basket) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_FROM_BASKET);
			int i = 1;
			preparedStatement.setInt(i++, basket.getId());
			preparedStatement.setInt(i++, basket.getTicketId());
			preparedStatement.setString(i++, basket.getPassportId());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_DELETE_FROM_BASKET, e);
			throw new DBException(Messages.ERR_CAN_NOT_DELETE_FROM_BASKET, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void insertIntoBasket(Basket basket) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(INSERT_INTO_BASKET);
			int i = 1;
			preparedStatement.setInt(i++, basket.getId());
			preparedStatement.setInt(i++, basket.getTicketId());
			preparedStatement.setString(i++, basket.getPassportId());
			preparedStatement.setString(i++, basket.getSessionId());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_INSERT_INTO_BASKET, e);
			throw new DBException(Messages.ERR_CAN_NOT_INSERT_INTO_BASKET, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public double getSumToPaid(Basket basket) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		double sumToPaid = 0;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			callableStatement = connection.prepareCall(GET_SUM_TO_PAID);
			int i = 1;
			callableStatement.setString(i++, basket.getPassportId());
			callableStatement.setInt(i++, basket.getId());
			callableStatement.setDouble(i++, Types.DOUBLE);
			callableStatement.execute();
			sumToPaid = callableStatement.getDouble(3);
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_SUM_TO_PAID, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_SUM_TO_PAID, e);
		} finally {
			db.close(connection, callableStatement, resultSet);
		}
		return sumToPaid;
	}
	
	@Override
	public void deleteFromBasketBySessionId(String sessionId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_FROM_BASKET_BY_SESSION_ID);
			preparedStatement.setString(1, sessionId);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_DELETE_FROM_BASKET_BY_SESSION, e);
			throw new DBException(Messages.ERR_CAN_NOT_DELETE_FROM_BASKET_BY_SESSION, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
}

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
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceRequests;
import ua.nure.khandzhian.SummaryTask4.db.entity.Requests;
import ua.nure.khandzhian.SummaryTask4.exception.DBException;
import ua.nure.khandzhian.SummaryTask4.exception.Messages;

public class DaoRequests implements DaoInterfaceRequests{
	
	private static final Logger LOGGER = Logger.getLogger(DaoRequests.class);

	private static final String GET_ALL_REQUEST = "SELECT workers.Id_worker, First_name, Second_name, "
			+ "Phone_number, Email, Login, Password, Id_status, Profession, TextOfRequest, Id_request, Response FROM "
			+ "workers INNER JOIN requests ON workers.Id_worker = requests.Id_worker ORDER BY Response";
	
	private static final String INSERT_REQUEST = "INSERT INTO requests(TextOfRequest, Id_worker) VALUES(?, ?)";
	
	private static final String UPDATE_REQUEST = "UPDATE requests SET Response = ? WHERE Id_request = ?";
	
	private static final String GET_REQUEST_BY_WORKER_ID = "SELECT workers.Id_worker, TextOfRequest, Id_request, Response FROM "
			+ "workers INNER JOIN requests ON workers.Id_worker = requests.Id_worker WHERE workers.Id_worker = ? ORDER BY Id_request";
	
	@Override
	public List<Requests> getRequests() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Requests> list = new ArrayList<Requests>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_REQUEST);
			while (resultSet.next()) {
				list.add(getRequest(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_REQUEST, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_REQUEST, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	private Requests getRequest(ResultSet resultSet) throws SQLException {
		Requests request = new Requests();
		request.setIdRequest(resultSet.getInt("Id_request"));
		request.setTextOfRequest(resultSet.getString("TextOfRequest"));
		request.setIdWorker(resultSet.getInt("Id_worker"));
		request.setFirstName(resultSet.getString("First_name"));
		request.setSecondName(resultSet.getString("Second_name"));
		request.setPhoneNumber(resultSet.getString("Phone_number"));
		request.setEmail(resultSet.getString("Email"));
		request.setLogin(resultSet.getString("Login"));
		request.setPassword(resultSet.getString("Password"));
		request.setIdStatus(resultSet.getInt("Id_status"));
		request.setProfession(resultSet.getString("Profession"));
		request.setResponse(resultSet.getString("Response"));
		return request;
	}
	
	@Override
	public void insertRequest(Requests requests) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(INSERT_REQUEST);
			int i = 1;
			preparedStatement.setString(i++, requests.getTextOfRequest());
			preparedStatement.setInt(i++, requests.getIdWorker());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_INSERT_INTO_REQUEST, e);
			throw new DBException(Messages.ERR_CAN_NOT_INSERT_INTO_REQUEST, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void updateRequest(Requests request) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(UPDATE_REQUEST);
			int i = 1;
			preparedStatement.setString(i++, request.getResponse());
			preparedStatement.setInt(i++, request.getIdRequest());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_UPDATE_REQUEST, e);
			throw new DBException(Messages.ERR_CAN_NOT_UPDATE_REQUEST, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public List<Requests> getRequestsByWorkerId(int workerId) throws Exception {

		DBManager db = DBManager.getInstance();
		List<Requests> list = new ArrayList<Requests>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_REQUEST_BY_WORKER_ID);
			preparedStatement.setInt(1, workerId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getRequestsByWorkerId(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_REQUEST_BY_WORKER_ID, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_REQUEST_BY_WORKER_ID, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	private Requests getRequestsByWorkerId(ResultSet resultSet) throws SQLException {
		Requests request = new Requests();
		request.setIdRequest(resultSet.getInt("Id_request"));
		request.setTextOfRequest(resultSet.getString("TextOfRequest"));
		request.setIdWorker(resultSet.getInt("Id_worker"));
		request.setResponse(resultSet.getString("Response"));
		return request;
	}

}

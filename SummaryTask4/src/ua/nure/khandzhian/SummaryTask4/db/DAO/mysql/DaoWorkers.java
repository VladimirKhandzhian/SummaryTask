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
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceWorkers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Workers;
import ua.nure.khandzhian.SummaryTask4.exception.DBException;
import ua.nure.khandzhian.SummaryTask4.exception.Messages;

public class DaoWorkers implements DaoInterfaceWorkers{
	
	private static final Logger LOGGER = Logger.getLogger(DaoWorkers.class);
	
	private static final String GET_ALL_WORKERS = "SELECT Id_worker, First_name, Second_name, "
			+ "Phone_number, Email, Login, Password, Id_status, Profession FROM workers";
	
	private static final String INSERT_WORKER = "INSERT INTO workers(First_name, Second_name, "
			+ "Phone_number, Email, Login, Password, Id_status, Profession) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_WORKER = "UPDATE workers SET First_name = ?, Second_name = ?, "
			+ "Phone_number = ?, Email = ?, Profession = ? WHERE Id_worker = ?";
	
	private static final String GET_WORKER_BY_PROFESSION = "SELECT workers.Id_worker, First_name, Second_name, Profession "
			+ "FROM brigads RIGHT JOIN workers ON brigads.Id_worker = workers.Id_worker "
			+ "WHERE brigads.Id_worker IS NULL AND Id_status = 1 ORDER BY Profession";
	
	private static final String GET_FREE_COMMANDER= "SELECT workers.Id_worker, First_name, Second_name, Profession "
			+ "FROM brigads RIGHT JOIN workers ON brigads.Id_worker = workers.Id_worker "
			+ "WHERE brigads.Id_worker IS NULL AND Id_status = 1 AND Profession = 'Командир воздушного судна'";
	
	private static final String DELETE_WORKER = "DELETE FROM workers WHERE Id_worker = ?";
	
	@Override
	public List<Workers> getWorkers() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Workers> list = new ArrayList<Workers>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_WORKERS);
			while (resultSet.next()) {
				list.add(getWorker(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_WORKERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_WORKERS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	private Workers getWorker(ResultSet resultSet) throws SQLException {
		Workers worker = new Workers();
		worker.setIdWorker(resultSet.getInt("Id_worker"));
		worker.setFirstName(resultSet.getString("First_name"));
		worker.setSecondName(resultSet.getString("Second_name"));
		worker.setPhoneNumber(resultSet.getString("Phone_number"));
		worker.setEmail(resultSet.getString("Email"));
		worker.setLogin(resultSet.getString("Login"));
		worker.setPassword(resultSet.getString("Password"));
		worker.setIdStatus(resultSet.getInt("Id_status"));
		worker.setProfession(resultSet.getString("Profession"));
		return worker;
	}
	
	
	@Override
	public Workers findWorkerByLogin(String login) throws Exception {
		Workers worker = null;
		for (Workers worker1 : getWorkers()) {
			if (login.equals(worker1.getLogin())) {
				worker = worker1;
				break;
			}
		}
		return worker;
	}
	
	@Override
	public Workers findWorkerById(int id) throws Exception {
		Workers worker = null;
		for (Workers worker1 : getWorkers()) {
			if (id == worker1.getIdWorker()) {
				worker = worker1;
				break;
			}
		}
		return worker;
	}
	
	@Override
	public void insertWorker(Workers worker) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(INSERT_WORKER);
			int i = 1;
			preparedStatement.setString(i++, worker.getFirstName());
			preparedStatement.setString(i++, worker.getSecondName());
			preparedStatement.setString(i++, worker.getPhoneNumber());
			preparedStatement.setString(i++, worker.getEmail());
			preparedStatement.setString(i++, worker.getLogin());
			preparedStatement.setString(i++, worker.getPassword());
			preparedStatement.setInt(i++, worker.getIdStatus());
			preparedStatement.setString(i++, worker.getProfession());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_INSERT_INTO_WORKERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_INSERT_INTO_WORKERS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void updateWorker(Workers worker) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(UPDATE_WORKER);
			int i = 1;
			preparedStatement.setString(i++, worker.getFirstName());
			preparedStatement.setString(i++, worker.getSecondName());
			preparedStatement.setString(i++, worker.getPhoneNumber());
			preparedStatement.setString(i++, worker.getEmail());
			preparedStatement.setString(i++, worker.getProfession());
			preparedStatement.setInt(i++, worker.getIdWorker());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_UPDATE_WORKERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_UPDATE_WORKERS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public List<Workers> getEmptyWorker() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Workers> list = new ArrayList<Workers>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_WORKER_BY_PROFESSION);
			while (resultSet.next()) {
				list.add(getEmptyWorker(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_EMPTY_WORKERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_EMPTY_WORKERS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	@Override
	public List<Workers> getEmptyCommander() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Workers> list = new ArrayList<Workers>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_FREE_COMMANDER);
			while (resultSet.next()) {
				list.add(getEmptyWorker(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_EMPTY_COMMANDERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_EMPTY_COMMANDERS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	private Workers getEmptyWorker(ResultSet resultSet) throws SQLException {
		Workers worker = new Workers();
		worker.setIdWorker(resultSet.getInt("Id_worker"));
		worker.setFirstName(resultSet.getString("First_name"));
		worker.setSecondName(resultSet.getString("Second_name"));
		worker.setProfession(resultSet.getString("Profession"));
		return worker;
	}
	
	@Override
	public void deleteWorker(int workerId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_WORKER);
			preparedStatement.setInt(1, workerId);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_DELETE_WORKERS, e);
			throw new DBException(Messages.ERR_CAN_NOT_DELETE_WORKERS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
}

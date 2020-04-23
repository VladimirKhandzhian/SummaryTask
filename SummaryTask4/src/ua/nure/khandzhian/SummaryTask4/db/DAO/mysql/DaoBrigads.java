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
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceBrigads;
import ua.nure.khandzhian.SummaryTask4.db.entity.Brigads;
import ua.nure.khandzhian.SummaryTask4.exception.DBException;
import ua.nure.khandzhian.SummaryTask4.exception.Messages;

public class DaoBrigads implements DaoInterfaceBrigads{
	
	private static final Logger LOGGER = Logger.getLogger(DaoBrigads.class);

	private static final String GET_ALL_BRIGADS = "SELECT workers.Id_worker, First_name, Second_name, Profession, brigads.Id_brigad "
			+ "FROM workers INNER JOIN brigads ON workers.Id_worker = brigads.Id_worker ORDER BY Profession";
	
	private static final String INSERT_BRIGADS = "INSERT INTO brigads(Id_brigad, Id_worker) VALUES(?, ?)";
	
	private static final String GET_BRIGADS_ID = "SELECT DISTINCT Id_brigad FROM brigads";
	
	private static final String DELETE_WORKER = "DELETE FROM brigads WHERE Id_brigad = ? AND Id_worker = ?";
	
	private static final String GET_EMPTY_BRIGADS = "SELECT DISTINCT Id_brigad FROM brigads WHERE Id_brigad != ?";
	
	private static final String GET_MAX_BRIGAD_ID = "SELECT MAX(Id_brigad) + 1 FROM brigads";
	
	@Override
	public List<Brigads> getBrigads() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Brigads> list = new ArrayList<Brigads>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_BRIGADS);
			while (resultSet.next()) {
				list.add(getBrigad(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_BRIGADS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_BRIGADS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	private Brigads getBrigad(ResultSet resultSet) throws SQLException {
		Brigads brigads = new Brigads();
		brigads.setIdWorker(resultSet.getInt("Id_worker"));
		brigads.setFirstName(resultSet.getString("First_name"));
		brigads.setSecondName(resultSet.getString("Second_name"));
		brigads.setProfession(resultSet.getString("Profession"));
		brigads.setIdBrigads(resultSet.getInt("Id_brigad"));
		return brigads;
	}
	
	@Override
	public void insertBrigads(int brigadId, int workerId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(INSERT_BRIGADS);
			int i = 1;
			preparedStatement.setInt(i++, brigadId);
			preparedStatement.setInt(i++, workerId);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_INSERT_INTO_BRIGADS, e);
			throw new DBException(Messages.ERR_CAN_NOT_INSERT_INTO_BRIGADS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public void deleteWorkerFromBrigads(int brigadId, int workerId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_WORKER);
			int i = 1;
			preparedStatement.setInt(i++, brigadId);
			preparedStatement.setInt(i++, workerId);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_DELETE_WORKER_FROM_BRIGADS, e);
			throw new DBException(Messages.ERR_CAN_NOT_DELETE_WORKER_FROM_BRIGADS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public List<Brigads> getDistinctBrigads() throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Brigads> list = new ArrayList<Brigads>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_BRIGADS_ID);
			while (resultSet.next()) {
				list.add(getDistinctBrigad(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_DISTINCT_BRIGADS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_DISTINCT_BRIGADS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return list;
	}
	
	private Brigads getDistinctBrigad(ResultSet resultSet) throws SQLException {
		Brigads brigads = new Brigads();
		brigads.setIdBrigads(resultSet.getInt("Id_brigad"));
		return brigads;
	}
	
	@Override
	public List<Brigads> getEmptyBrigads(int brigadId) throws Exception {
		
		DBManager db = DBManager.getInstance();
		List<Brigads> list = new ArrayList<Brigads>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(GET_EMPTY_BRIGADS);
			preparedStatement.setInt(1, brigadId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getDistinctBrigad(resultSet));
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_EMPTY_BRIGADS, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_EMPTY_BRIGADS, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	@Override
	public int getMaxBrigadId() throws Exception {
		
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int maxBrigadId = 0;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_MAX_BRIGAD_ID);
			while (resultSet.next()) {
				maxBrigadId = resultSet.getInt(1);
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOGGER.error(Messages.ERR_CAN_NOT_GET_MAX_BRIGAD_ID, e);
			throw new DBException(Messages.ERR_CAN_NOT_GET_MAX_BRIGAD_ID, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return maxBrigadId;
	}
}

package ua.nure.khandzhian.SummaryTask4.db.DAO;

import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.entity.Workers;

public interface DaoInterfaceWorkers {

	public Workers findWorkerByLogin(String login) throws Exception;
	
	public Workers findWorkerById(int id) throws Exception;
	
	public List<Workers> getWorkers() throws Exception;
	
	public List<Workers> getEmptyWorker() throws Exception;

	public List<Workers> getEmptyCommander() throws Exception;

	public void deleteWorker(int workerId) throws Exception;

	public void updateWorker(Workers worker) throws Exception;

	public void insertWorker(Workers worker) throws Exception;
}

package ua.nure.khandzhian.SummaryTask4.db.DAO;

import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.entity.Requests;

public interface DaoInterfaceRequests {
	
	public List<Requests> getRequests() throws Exception;
	
	public void insertRequest(Requests requests) throws Exception;
	
	public void updateRequest(Requests request) throws Exception;
	
	public List<Requests> getRequestsByWorkerId(int workerId) throws Exception;

}

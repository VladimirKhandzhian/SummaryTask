package ua.nure.khandzhian.SummaryTask4.db.DAO;

import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.entity.Brigads;

public interface DaoInterfaceBrigads {

	public List<Brigads> getBrigads() throws Exception;
	
	public List<Brigads> getDistinctBrigads() throws Exception;
	
	public void insertBrigads(int brigadId, int workerId) throws Exception;
	
	public void deleteWorkerFromBrigads(int brigadId, int workerId) throws Exception;

	public List<Brigads> getEmptyBrigads(int brigadId) throws Exception;

	public int getMaxBrigadId() throws Exception;
}

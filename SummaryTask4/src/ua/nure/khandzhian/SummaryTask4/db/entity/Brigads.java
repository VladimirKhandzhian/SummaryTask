package ua.nure.khandzhian.SummaryTask4.db.entity;

import java.io.Serializable;
import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceBrigads;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoBrigads;

public class Brigads extends Workers implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idBrigads;
	private int idWorker;
	
	public int getIdBrigads() {
		return idBrigads;
	}
	
	public void setIdBrigads(int idBrigads) {
		this.idBrigads = idBrigads;
	}

	public int getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(int idWorker) {
		this.idWorker = idWorker;
	}

	@Override
	public String toString() {
		return "Brigads [idBrigads=" + idBrigads + ", idWorker=" + idWorker + "]";
	}
	
	public List<Brigads> getBrigads() throws Exception{
		DaoInterfaceBrigads daoInterfaceBrigads = new DaoBrigads();
		return daoInterfaceBrigads.getBrigads();
	}
	
	public List<Brigads> getDistinctBrigads() throws Exception{
		DaoInterfaceBrigads daoInterfaceBrigads = new DaoBrigads();
		return daoInterfaceBrigads.getDistinctBrigads();
	}
	
	public int getMaxBrigads() throws Exception{
		DaoInterfaceBrigads daoInterfaceBrigads = new DaoBrigads();
		return daoInterfaceBrigads.getMaxBrigadId();
	}
	
	
}

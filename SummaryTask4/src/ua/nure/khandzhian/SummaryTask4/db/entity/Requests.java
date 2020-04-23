package ua.nure.khandzhian.SummaryTask4.db.entity;

import java.io.Serializable;
import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceRequests;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoRequests;

public class Requests extends Workers implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idRequest;
	private String textOfRequest;
	private int idWorker;
	private String response;
	
	public int getIdRequest() {
		return idRequest;
	}
	
	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}

	public String getTextOfRequest() {
		return textOfRequest;
	}

	public void setTextOfRequest(String textOfRequest) {
		this.textOfRequest = textOfRequest;
	}

	public int getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(int idWorker) {
		this.idWorker = idWorker;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Requests [idRequest=" + idRequest + ", textOfRequest=" + textOfRequest + ", idWorker=" + idWorker
				+ ", response=" + response + "]";
	}

	public List<Requests> getRequests() throws Exception{
		DaoInterfaceRequests daoInterfaceRequests = new DaoRequests();
		return daoInterfaceRequests.getRequests();
	}
}

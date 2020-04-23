package ua.nure.khandzhian.SummaryTask4.db.entity;

import java.io.Serializable;

public class Status implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idStatus;
	private String statusName;
	
	public int getIdStatus() {
		return idStatus;
	}
	
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	
	public String getStatusName() {
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "Status [idStatus=" + idStatus + ", statusName=" + statusName + "]";
	}
	
}

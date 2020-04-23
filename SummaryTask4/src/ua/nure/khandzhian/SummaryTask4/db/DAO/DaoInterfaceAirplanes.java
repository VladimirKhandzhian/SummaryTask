package ua.nure.khandzhian.SummaryTask4.db.DAO;

import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.entity.Airplane;

public interface DaoInterfaceAirplanes {
	
	public List<Airplane> getAirplane() throws Exception;

	public Airplane findAirplaneById(int id) throws Exception;

	public List<Airplane> getEmptyAirplane(int airplaneId) throws Exception;

	public String getAirplaneModelById(int airplaneId) throws Exception;
	
	public int getNumberOfSeatsById(int airplaneId) throws Exception;

	public void insertAirplane(Airplane airplane) throws Exception;

	public void updateAirplane(Airplane airplane) throws Exception;

	public void deleteAirplane(int airplaneId) throws Exception;



}
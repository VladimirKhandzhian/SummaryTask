package ua.nure.khandzhian.SummaryTask4.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoOrders;

public class MyTag extends SimpleTagSupport{
	
	int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		
		DaoInterfaceOrders daoInterfaceOrders = new DaoOrders();
		
		JspWriter out = getJspContext().getOut();
		
		int count = 0;
		
		try {
			count = daoInterfaceOrders.getCountOfTocketByFlightId(getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.print("Number of tickets purchased: " + count);
	}
}

package ua.nure.khandzhian.SummaryTask4.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceBasket;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoBasket;

@WebListener
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent sessionEvent) {
   
    	System.out.println("Session Created:: ID = " + sessionEvent.getSession().getId());
    	
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {

    	DaoInterfaceBasket daoInterfaceBasket = new DaoBasket();
    	try {
    		daoInterfaceBasket.deleteFromBasketBySessionId(sessionEvent.getSession().getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Session Destroyed:: ID = " + sessionEvent.getSession().getId());
		}
    }
	
}
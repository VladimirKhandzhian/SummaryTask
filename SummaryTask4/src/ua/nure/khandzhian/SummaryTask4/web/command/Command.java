package ua.nure.khandzhian.SummaryTask4.web.command;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command implements Serializable {
	private static final long serialVersionUID = 1L;
	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}

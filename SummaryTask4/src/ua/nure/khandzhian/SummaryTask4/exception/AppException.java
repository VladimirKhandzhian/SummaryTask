package ua.nure.khandzhian.SummaryTask4.exception;

public class AppException extends Exception {

	private static final long serialVersionUID = 1L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}
}

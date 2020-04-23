package ua.nure.khandzhian.SummaryTask4.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsValidator {

	 public static final String REGEX_NAME_EN = "[a-zA-Z]{1,20}";
	 public static final String REGEX_NAME_RU = "[à-ÿÀ-ß¸¨úÚ]{1,20}";
	 public static final String REGEX_LOGIN = "[a-zA-Z0-9]{1,20}";
	 public static final String REGEX_MONEY = "[0-9]{1,20}";
	 private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
	            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 	 private static final String REGEX_CARD_NUMBER = "([2-6]([0-9]{3}) ?)(([0-9]{4} ?){3})";
 	 private static final String REGEX_DATE = "(0[1-9]|1[0-2])/([0-9]{2})";
 	 private static final String REGEX_CVV = "[0-9]{3}";
	 
	 public boolean validateFieldName(String name) {
		boolean validate = false;
	    Pattern p = Pattern.compile(REGEX_NAME_EN);
		Matcher m = p.matcher(name);	
		if(m.matches()) {
			validate = true;
		}
		Pattern p1 = Pattern.compile(REGEX_NAME_RU);
		Matcher m1 = p1.matcher(name);	
		if(m1.matches()) {
			validate = true;
		}
			return validate;
		}
		
	 public boolean validateFieldLoginPassword(String name) {
	    Pattern p = Pattern.compile(REGEX_LOGIN);
		Matcher m = p.matcher(name);	
		return m.matches();
	 }
		
	 public boolean validateFieldEmail(String email) {
		 Pattern p = Pattern.compile(REGEX_EMAIL);
		 Matcher m = p.matcher(email);
		 return m.matches();
	 }
	 
	 public boolean cardNumber(String cardNumber) {
		 Pattern p = Pattern.compile(REGEX_CARD_NUMBER);
		 Matcher m = p.matcher(cardNumber);
		 return m.matches();
	 }
	 
	 public boolean cardDate(String cardDate) {
		 Pattern p = Pattern.compile(REGEX_DATE);
		 Matcher m = p.matcher(cardDate);
		 return m.matches();
	 }
	 
	 public boolean cardCVV(String CVV) {
		 Pattern p = Pattern.compile(REGEX_CVV);
		 Matcher m = p.matcher(CVV);
		 return m.matches();
	 }
}


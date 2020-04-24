package ua.nure.khandzhian.SummaryTask4.createTicket;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoOrders;
import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;

public class TicketCreator {
	
	public void createTicket(Orders order, String ticketForSend) throws Exception {
		
		DaoInterfaceOrders daoInterfaceOrders = new DaoOrders();
		 
		Document document = new Document();
		
		PdfWriter.getInstance(document, 
				new FileOutputStream("C:\\Users\\Vova4\\Desktop\\Tickets\\Flight ¹" + 
						order.getId() + "Ticket ¹" + ticketForSend + ".pdf"));

		document.open();
		
		Orders order1 = daoInterfaceOrders.getInformationAboutFlights(order);
		
		final BaseFont baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(baseFont);
		
		document.add(new Paragraph("Flights number: " + order1.getFlightNumber(), font));
		document.add(new Paragraph("Name of flights: " + order1.getNameOfFlight(), font));
		document.add(new Paragraph("Departure: " + order1.getDeparture(), font));
		document.add(new Paragraph("Destination: " + order1.getDestination(), font));
		document.add(new Paragraph("Departure date/time: " + order1.getDepartureDateTime(), font));
		document.add(new Paragraph("Destination date/time: " + order1.getDestinationDateTime(), font));
		
		PdfPTable t = new PdfPTable(4);
		t.setSpacingBefore(15);
	    t.setSpacingAfter(15);
	    
	    t.addCell("Number of ticket");
	    t.addCell("Number of passport");
    	t.addCell("First name");
    	t.addCell("Second name");
    	
	   
	    for(Orders order2 : daoInterfaceOrders.getInformationAboutTicket(order)) {
	    	t.addCell("" + order2.getTicketId());
	    	t.addCell(new PdfPCell(new Phrase(order2.getPassportId())));
	    	t.addCell(new PdfPCell(new Phrase(order2.getFirstName(), font)));
	    	t.addCell(new PdfPCell(new Phrase(order2.getSecondName(), font)));
	    }

		document.add(t);
		document.close();
	
	}
}

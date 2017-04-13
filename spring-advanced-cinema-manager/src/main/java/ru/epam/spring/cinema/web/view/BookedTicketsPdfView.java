package ru.epam.spring.cinema.web.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PatternColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class BookedTicketsPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<TicketViewItem> tickets = (List<TicketViewItem>) model.get("tickets");

        // define font for title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA);
        titleFont.setStyle(Font.BOLD);

        // title
		document.add(new Paragraph("Cinema Manager", titleFont));

		// subtitle
		document.add(new Paragraph("Booked tickets:"));

		// define table
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
        table.setWidths(new float[] {1.5f, 1, 1.5f, 1});
        table.setSpacingBefore(10);

        // define font for table header row
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA);
        headerFont.setColor(PatternColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(PatternColor.BLUE);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("User", headerFont));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Event", headerFont));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", headerFont));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Seat", headerFont));
        table.addCell(cell);

        // write table content
		for (TicketViewItem ticket : tickets) {
			table.addCell(ticket.getUserName());
			table.addCell(ticket.getEventName());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String formattedDate = dateFormat.format(ticket.getDate());
			table.addCell(formattedDate);
			table.addCell(String.valueOf(ticket.getSeat()));
		}

		document.add(table);
	}

}

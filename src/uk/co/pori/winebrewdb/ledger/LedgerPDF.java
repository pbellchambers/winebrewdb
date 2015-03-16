package uk.co.pori.winebrewdb.ledger;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

/**
 * This handles generating the pdf file for the ledger data.
 * 
 * @author paul.bellchambers
 *
 */
public class LedgerPDF {
 
	private static Font text3 = new Font(Font.FontFamily.HELVETICA, 12,Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 9,Font.BOLD);
	private static Font smallNormal = new Font(Font.FontFamily.HELVETICA, 9,Font.NORMAL);
	private static Font headerFoot = new Font(Font.FontFamily.HELVETICA, 7,Font.ITALIC,BaseColor.GRAY);
	
	/**
	 * Attempts to automatically open the file at the specified location.
	 * 
	 * @param location File location.
	 */
	private static void openPDF(String location) {
 
	  try {
 
		File pdfFile = new File(location);
		if (pdfFile.exists()) {
 
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				JOptionPane.showMessageDialog(null,
						"Automatic file opening is not supported on this system.\n\nYou will need to open the following file yourself:\n" + location,
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}
 
		} else {
			JOptionPane.showMessageDialog(null,
					"The file at the following location could not be opened:\n" + location + "\n\nEither the file is missing/corrupt, or could not be accessed.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
 
	  } catch (Exception ex) {
		ex.printStackTrace();
	  }
 
	}
	
	/**
	 * Creates the physical pdf file at the specified location.
	 * 
	 * @param location File location.
	 */
	public static void createPDF(String location) {

		try {
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(location));
				writer.setBoxSize("box", new Rectangle(36, 36, 559, 815));

	            HeaderFooter event = new HeaderFooter();
	            writer.setPageEvent(event);
				
				document.open();
				addMetaData(document);
				addEquipmentCosts(document);
				addBrewCosts(document);
				document.close();
				openPDF(location);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"There was a problem saving the file at the following location:\n" + location + "\n\nPlease check you have permission to write to this location.",
						"Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}



	/**
	 * Adds metadata to the document (iText allows to add metadata to the PDF which can be viewed in your Adobe Reader under File -> Properties). 
	 * 
	 * @param document The specified document (will always be "document" for this). 
	 */
	private static void addMetaData(Document document) {
		document.addTitle("WineBrewDB Ledger Data");
		document.addSubject("Ledger Data");
		document.addKeywords("WineBrewDB, Homebrew, LedgerData, Ledger");
		document.addAuthor("WineBrewDB");
		document.addCreator("WineBrewDB");
	}
	
	/**
	 * Gets and adds the ledger equipment costs data to the specified document.
	 * 
	 * @param document The specified document (will always be "document" for this).
	 * @throws DocumentException
	 */
	private static void addEquipmentCosts(Document document) throws DocumentException {
		Paragraph genericParagraph = new Paragraph();
		Chunk tab2 = new Chunk(new VerticalPositionMark(), 170, true);
		Chunk tab3 = new Chunk(new VerticalPositionMark(), 360, true);
		
		//Empty Line
		addEmptyLine(genericParagraph, 1);
		//Header
		genericParagraph.add(new Paragraph("Equipment & Non-brew-specific Costs", text3));
		addEmptyLine(genericParagraph, 1);
		// Add a table
		createEquipmentCostsTable(genericParagraph);
		addEmptyLine(genericParagraph, 1);
	
		genericParagraph.add(new Phrase("Total Cost:  ",smallBold));
		genericParagraph.add(new Phrase(LedgerEquipmentPanel.textLedgerEquipmentCostTotalCost.getText(),smallNormal));
		genericParagraph.add(new Chunk(tab2));
		genericParagraph.add(new Phrase("Cost / All Brews:  ",smallBold));
		genericParagraph.add(new Phrase(LedgerEquipmentPanel.textLedgerEquipmentCostCostPerAllBrews.getText(),smallNormal));
		genericParagraph.add(new Chunk(tab3));
		genericParagraph.add(new Phrase("Cost / All Bottles:  ",smallBold));
		genericParagraph.add(new Phrase(LedgerEquipmentPanel.textLedgerEquipmentCostCostPerBottle.getText(),smallNormal));
		
		// Now add all this to the document
		document.add(genericParagraph);
		
		// Start a new page
		document.newPage();
	
	}
	
	/**
	 * Gets and adds the ledger brew costs data to the specified document.
	 * 
	 * @param document The specified document (will always be "document" for this).
	 * @throws DocumentException
	 */
	private static void addBrewCosts(Document document) throws DocumentException {
		Paragraph genericParagraph = new Paragraph();
		
		//Empty Line
		addEmptyLine(genericParagraph, 1);
		//Header
		genericParagraph.add(new Paragraph("Brew Costs", text3));
		addEmptyLine(genericParagraph, 1);
		// Add a table
		createBrewCostsTable(genericParagraph);
	
		// Now add all this to the document
		document.add(genericParagraph);
		
		// Start a new page
		document.newPage();
	
	}
	
	/**
	 * Loops through and creates the table containing all the equipment costs.
	 * 
	 * @param preface The paragraph to which the table should be added (will always be "genericParagraph" for this).
	 * @throws BadElementException
	 */
	private static void createEquipmentCostsTable(Paragraph preface)
			throws BadElementException {
		PdfPTable table = new PdfPTable(4);
        try {
			table.setTotalWidth(new float[]{ 67, 200, 70, 200 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        table.setLockedWidth(true);
		
		table.getDefaultCell().setPadding(10);
		
		// table.setBorderWidth(1);
	
		PdfPCell c1 = new PdfPCell(new Phrase("Date",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Line Item",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Cost",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Supplier",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);
		
		int totalRows = LedgerEquipmentPanel.LedgerEquipmentCostTable.getRowCount();
		int row = 0;
		
		while(row < totalRows) {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			table.addCell(new Phrase(LedgerEquipmentPanel.LedgerEquipmentCostTable.getValueAt(row,1).toString(),smallNormal));
			table.addCell(new Phrase(LedgerEquipmentPanel.LedgerEquipmentCostTable.getValueAt(row,2).toString(),smallNormal));
			table.addCell(new Phrase(nf.format(LedgerEquipmentPanel.LedgerEquipmentCostTable.getValueAt(row,3)),smallNormal));
			table.addCell(new Phrase(LedgerEquipmentPanel.LedgerEquipmentCostTable.getValueAt(row,4).toString(),smallNormal));
			
			row = row + 1;
		}
	
		preface.add(table);
	
	}
	
	/**
	 * Loops through and creates the table containing all the brew costs.
	 * 
	 * @param preface The paragraph to which the table should be added (will always be "genericParagraph" for this).
	 * @throws BadElementException
	 */
	private static void createBrewCostsTable(Paragraph preface)
			throws BadElementException {
		PdfPTable table = new PdfPTable(5);
        try {
			table.setTotalWidth(new float[]{ 240, 67, 70, 90, 70 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        table.setLockedWidth(true);
		
		table.getDefaultCell().setPadding(10);
		
		// table.setBorderWidth(1);
	
		PdfPCell c1 = new PdfPCell(new Phrase("Brew Name",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Date Started",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Total Cost",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Number of Bottles",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Cost Per Bottle",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		int totalRows = LedgerBrewCostSearchPanel.LedgerBrewCostTable.getRowCount();
		int row = 0;
		
		while(row < totalRows) {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			table.addCell(new Phrase(LedgerBrewCostSearchPanel.LedgerBrewCostTable.getValueAt(row,1).toString(),smallNormal));
			table.addCell(new Phrase(LedgerBrewCostSearchPanel.LedgerBrewCostTable.getValueAt(row,2).toString(),smallNormal));
			table.addCell(new Phrase(nf.format(LedgerBrewCostSearchPanel.LedgerBrewCostTable.getValueAt(row,3)),smallNormal));
			table.addCell(new Phrase(LedgerBrewCostSearchPanel.LedgerBrewCostTable.getValueAt(row,4).toString(),smallNormal));
			table.addCell(new Phrase(nf.format(LedgerBrewCostSearchPanel.LedgerBrewCostTable.getValueAt(row,5)),smallNormal));
			
			row = row + 1;
		}
	
		preface.add(table);
	
	}
	
	/**
	 * Adds a specified number of empty lines to a specified paragraph.
	 * 
	 * @param paragraph The specified paragraph (will always be "genericParagraph" for this).
	 * @param number The number of empty lines.
	 */
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	/**
	 * Adds the header and footer to the document.
	 * 
	 * @author paul.bellchambers
	 *
	 */
	static class HeaderFooter extends PdfPageEventHelper {

		/**
		 * Adds the header and footer to the document.
		 */
        public void onEndPage (PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("box");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Phrase("WineBrewDB",headerFoot),rect.getLeft(), rect.getTop(), 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_RIGHT, new Phrase("Ledger Data - " + dateFormat.format(date),headerFoot),rect.getRight(), rect.getTop(), 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase(String.format("Page %d", writer.getPageNumber()),headerFoot),(rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
        }
    }

}
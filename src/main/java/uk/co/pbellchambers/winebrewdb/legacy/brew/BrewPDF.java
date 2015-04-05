package uk.co.pbellchambers.winebrewdb.legacy.brew;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import uk.co.pbellchambers.winebrewdb.legacy.util.Dates;

import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;

/**
 * This handles generating the pdf file for the selected brew.
 * 
 * @author paul.bellchambers
 *
 */
public class BrewPDF {
	
	private static Font heading1Font = new Font(Font.FontFamily.HELVETICA, 18,Font.BOLD);
	private static Font text2 = new Font(Font.FontFamily.HELVETICA, 12,Font.ITALIC);
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
		//TODO: Make pictures print too
		
		try {
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(location));
				writer.setBoxSize("box", new Rectangle(36, 36, 559, 815));

	            HeaderFooter event = new HeaderFooter();
	            writer.setPageEvent(event);
				
				document.open();
				addMetaData(document);
				addBrewData(document);
				addBrewNotes(document);
				addBrewCosts(document);
				//addBrewPictures(document);
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
		document.addTitle(BrewDataPanel.textBrewNameB.getText());
		document.addSubject("Brew Data for brew: " + BrewDataPanel.textBrewNameB.getText());
		document.addKeywords("WineBrewDB, Homebrew, BrewData");
		document.addAuthor("WineBrewDB");
		document.addCreator("WineBrewDB");
	}
	
	/**
	 * Gets and adds the brew data to the specified document.
	 * 
	 * @param document The specified document (will always be "document" for this).
	 * @throws DocumentException
	 */
	private static void addBrewData(Document document)
			throws DocumentException {
		Paragraph genericParagraph = new Paragraph();
		LineSeparator ls = new LineSeparator();
		Chunk tab1 = new Chunk(new VerticalPositionMark(), 75, true);
		Chunk tab2 = new Chunk(new VerticalPositionMark(), 170, true);
		Chunk tab3 = new Chunk(new VerticalPositionMark(), 275, true);
		Chunk tab4 = new Chunk(new VerticalPositionMark(), 350, true);
		Chunk tab5 = new Chunk(new VerticalPositionMark(), 455, true);
		
		//Empty Line
		addEmptyLine(genericParagraph, 1);
		
		//Header
		genericParagraph.add(new Paragraph(BrewDataPanel.textBrewNameB.getText(), heading1Font));
		genericParagraph.add(new Phrase("Date Planned: " + Dates
            .dateToString(BrewDataPanel.chooserBrewDatePlannedB.getDate()), text2));
		genericParagraph.add(new Chunk(tab2));
		genericParagraph.add(new Phrase("Date Started: " + Dates.dateToString(BrewDataPanel.chooserBrewDateStartedB.getDate()), text2));
		genericParagraph.add(new Chunk(tab4));
		genericParagraph.add(new Phrase("Date Bottled: " + Dates.dateToString(BrewDataPanel.chooserBrewDateBottledB.getDate()), text2));
		addEmptyLine(genericParagraph, 1);
		genericParagraph.add(new Chunk(ls));
		addEmptyLine(genericParagraph, 2);
		
		genericParagraph.add(new Phrase("Recipe From:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewRecipeB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 1);
		genericParagraph.add(new Phrase("Yeast Used:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewYeastB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 2);
		
		genericParagraph.add(new Phrase("Colour:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		genericParagraph.add(new Phrase(BrewDataPanel.comboBrewColourB.getSelectedItem().toString(),smallNormal));
		genericParagraph.add(new Chunk(tab2));
		genericParagraph.add(new Phrase("Volume Made (gallons):",smallBold));
		genericParagraph.add(new Chunk(tab3));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewVolumeMadeB.getText(),smallNormal));
		genericParagraph.add(new Chunk(tab4));
		genericParagraph.add(new Phrase("Number of Bottles:",smallBold));
		genericParagraph.add(new Chunk(tab5));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewNumberBottlesB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Phrase("Current Status:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		String currentStatus = "";
		if(BrewDataPanel.chckbxBrewInPlanningB.isSelected()){
			currentStatus = currentStatus + "*In Planning*  ";
		}
		if(BrewDataPanel.chckbxBrewInFermentingB.isSelected()){
			currentStatus = currentStatus + "*In Fermenting*  ";
		}
		if(BrewDataPanel.chckbxBrewInFiningB.isSelected()){
			currentStatus = currentStatus + "*In Fining*  ";
		}
		if(BrewDataPanel.chckbxBrewInMaturingB.isSelected()){
			currentStatus = currentStatus + "*In Maturing*  ";
		}
		if(BrewDataPanel.chckbxBrewInBottlesB.isSelected()){
			currentStatus = currentStatus + "*In Bottles*  ";
		}
		if(BrewDataPanel.chckbxBrewDrunkB.isSelected()){
			currentStatus = currentStatus + "*Drunk*  ";
		}
		genericParagraph.add(new Phrase(currentStatus,smallNormal));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Chunk(ls));
		addEmptyLine(genericParagraph, 2);
		
		genericParagraph.add(new Phrase("Start SG:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewStartSGB.getText().toString(),smallNormal));
		genericParagraph.add(new Chunk(tab2));
		genericParagraph.add(new Phrase("Start Adjusted SG:",smallBold));
		genericParagraph.add(new Chunk(tab3));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewStartAdjustedSGB.getText(),smallNormal));
		genericParagraph.add(new Chunk(tab4));
		genericParagraph.add(new Phrase("End SG:",smallBold));
		genericParagraph.add(new Chunk(tab5));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewEndSGB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Phrase("Aimed ABV%:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewAimedABVB.getText().toString(),smallNormal));
		genericParagraph.add(new Chunk(tab2));
		genericParagraph.add(new Phrase("Final ABV%:",smallBold));
		genericParagraph.add(new Chunk(tab3));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewFinalABVB.getText(),smallNormal));
		genericParagraph.add(new Chunk(tab4));
		genericParagraph.add(new Phrase("Final Adjusted ABV%:",smallBold));
		genericParagraph.add(new Chunk(tab5));
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewFinalAdjustedABVB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Chunk(ls));
		addEmptyLine(genericParagraph, 2);
		
		genericParagraph.add(new Phrase("Tasting Notes:",smallBold));
		addEmptyLine(genericParagraph, 1);
		genericParagraph.add(new Phrase(BrewDataPanel.textBrewTastingNotesB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Phrase("Thumbs:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		genericParagraph.add(new Phrase(BrewDataPanel.comboBrewThumbsB.getSelectedItem().toString(),smallNormal));
		addEmptyLine(genericParagraph, 2);		
		genericParagraph.add(new Phrase("General Notes:",smallBold));
		addEmptyLine(genericParagraph, 1);
		genericParagraph.add(new Paragraph(BrewDataPanel.textBrewGeneralNotesB.getText(),smallNormal));
	
		// Now add all this to the document
		document.add(genericParagraph);
		
		// Start a new page
		document.newPage();
	}
	
	/**
	 * Gets and adds the brew notes to the specified document.
	 * 
	 * @param document The specified document (will always be "document" for this).
	 * @throws DocumentException
	 */
	private static void addBrewNotes(Document document) throws DocumentException {
		Paragraph genericParagraph = new Paragraph();
		//Empty Line
		addEmptyLine(genericParagraph, 1);
		//Header
		genericParagraph.add(new Paragraph("Brew Notes", text3));
		addEmptyLine(genericParagraph, 1);
		// Add a table
		createNotesTable(genericParagraph);
	
		// Now add all this to the document
		document.add(genericParagraph);
		
		// Start a new page
		document.newPage();
	
	}
	
	/**
	 * Gets and adds the brew costs to the specified document.
	 * 
	 * @param document The specified document (will always be "document" for this).
	 * @throws DocumentException
	 */
	private static void addBrewCosts(Document document) throws DocumentException {
		Paragraph genericParagraph = new Paragraph();
		Chunk tab2 = new Chunk(new VerticalPositionMark(), 170, true);
		
		//Empty Line
		addEmptyLine(genericParagraph, 1);
		//Header
		genericParagraph.add(new Paragraph("Brew Costs", text3));
		addEmptyLine(genericParagraph, 1);
		// Add a table
		createCostsTable(genericParagraph);
		addEmptyLine(genericParagraph, 1);
		
		genericParagraph.add(new Phrase("Total Cost:  ",smallBold));
		genericParagraph.add(new Phrase(BrewCostPanel.textBrewCostTotalCost.getText(),smallNormal));
		genericParagraph.add(new Chunk(tab2));
		genericParagraph.add(new Phrase("Cost Per Bottle:  ",smallBold));
		genericParagraph.add(new Phrase(BrewCostPanel.textBrewCostCostPerBottle.getText(),smallNormal));
	
		// Now add all this to the document
		document.add(genericParagraph);
		
		// Start a new page
		document.newPage();
	
	}
	
	
//	private static void addBrewPictures(Document document) throws DocumentException {
//		Paragraph genericParagraph = new Paragraph();
//		//Empty Line
//		addEmptyLine(genericParagraph, 1);
//		//Header
//		genericParagraph.add(new Paragraph("Brew Pictures", text3));
//		addEmptyLine(genericParagraph, 1);
//		// Add a table
//		createPicturesTable(genericParagraph);
//	
//		// Now add all this to the document
//		document.add(genericParagraph);
//		
//		// Start a new page
//		document.newPage();
//	
//	}
//	
	
	/**
	 * Loops through and creates the table containing all the brew notes.
	 * 
	 * @param preface The paragraph to which the table should be added (will always be "genericParagraph" for this).
	 * @throws BadElementException
	 */
	private static void createNotesTable(Paragraph preface)
			throws BadElementException {
		PdfPTable table = new PdfPTable(4);
        try {
			table.setTotalWidth(new float[]{ 67, 80, 180, 210 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        table.setLockedWidth(true);
		
		table.getDefaultCell().setPadding(10);
		
		// table.setBorderWidth(1);
	
		PdfPCell c1 = new PdfPCell(new Phrase("Date",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Days Since Start",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Incident",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Notes",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);
		
		int totalRows = BrewNotesPanel.BrewNotesTable.getRowCount();
		int row = 0;
		
		while(row < totalRows) {
			table.addCell(new Phrase(BrewNotesPanel.BrewNotesTable.getValueAt(row,1).toString(),smallNormal));
			table.addCell(new Phrase(BrewNotesPanel.BrewNotesTable.getValueAt(row,2).toString(),smallNormal));
			table.addCell(new Phrase(BrewNotesPanel.BrewNotesTable.getValueAt(row,3).toString(),smallNormal));
			table.addCell(new Phrase(BrewNotesPanel.BrewNotesTable.getValueAt(row,4).toString(),smallNormal));
			
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
	private static void createCostsTable(Paragraph preface)
			throws BadElementException {
		PdfPTable table = new PdfPTable(3);
        try {
			table.setTotalWidth(new float[]{ 233, 70, 234 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        table.setLockedWidth(true);
		
		table.getDefaultCell().setPadding(10);
		
		// table.setBorderWidth(1);
	
		PdfPCell c1 = new PdfPCell(new Phrase("Line Item",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Cost",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
	
		c1 = new PdfPCell(new Phrase("Supplier",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		int totalRows = BrewCostPanel.BrewCostTable.getRowCount();
		int row = 0;
		
		while(row < totalRows) {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			table.addCell(new Phrase(BrewCostPanel.BrewCostTable.getValueAt(row,1).toString(),smallNormal));
			table.addCell(new Phrase(nf.format(BrewCostPanel.BrewCostTable.getValueAt(row,2)),smallNormal));
			table.addCell(new Phrase(BrewCostPanel.BrewCostTable.getValueAt(row,3).toString(),smallNormal));
			
			row = row + 1;
		}
	
		preface.add(table);
	
	}
	
	
//	private static void createPicturesTable(Paragraph preface)
//			throws BadElementException {
//		PdfPTable table = new PdfPTable(4);
//        try {
//			table.setTotalWidth(new float[]{ 67, 80, 180, 210 });
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//        table.setLockedWidth(true);
//		
//		table.getDefaultCell().setPadding(10);
//		
//		// table.setBorderWidth(1);
//	
//		PdfPCell c1 = new PdfPCell(new Phrase("Date",smallBold));
//		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(c1);
//	
//		c1 = new PdfPCell(new Phrase("Days Since Start",smallBold));
//		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(c1);
//	
//		c1 = new PdfPCell(new Phrase("Incident",smallBold));
//		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(c1);
//		
//		c1 = new PdfPCell(new Phrase("Notes",smallBold));
//		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(c1);
//		table.setHeaderRows(1);
//		
//		int totalRows = BrewNotesPanel.BrewNotesTable.getRowCount();
//		int row = 0;
//		
//		while(row < totalRows) {
//			table.addCell(new Phrase(BrewNotesPanel.BrewNotesTable.getValueAt(row,1).toString(),smallNormal));
//			table.addCell(new Phrase(BrewNotesPanel.BrewNotesTable.getValueAt(row,2).toString(),smallNormal));
//			table.addCell(new Phrase(BrewNotesPanel.BrewNotesTable.getValueAt(row,3).toString(),smallNormal));
//			table.addCell(new Phrase(BrewNotesPanel.BrewNotesTable.getValueAt(row,4).toString(),smallNormal));
//			
//			row = row + 1;
//		}
//	
//		preface.add(table);
//	
//	}
	
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
            
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Phrase("WineBrewDB",headerFoot),rect.getLeft(), rect.getTop(), 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_RIGHT, new Phrase("Brew Data: " + BrewDataPanel.textBrewNameB.getText(),headerFoot),rect.getRight(), rect.getTop(), 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase(String.format("Page %d", writer.getPageNumber()),headerFoot),(rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
        }
    }


}
	

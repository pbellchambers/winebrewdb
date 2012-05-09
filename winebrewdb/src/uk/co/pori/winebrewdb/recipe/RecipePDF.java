package uk.co.pori.winebrewdb.recipe;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

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
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

/**
 * This handles generating the pdf file for the selected recipe.
 * 
 * @author paul.bellchambers
 *
 */
public class RecipePDF {
 
	private static Font heading1Font = new Font(Font.FontFamily.HELVETICA, 18,Font.BOLD);
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
				addBrewData(document);
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
		document.addTitle(RecipeDataPanel.textRecipeNameB.getText());
		document.addSubject("Recipe Data for recipe: " + RecipeDataPanel.textRecipeNameB.getText());
		document.addKeywords("WineBrewDB, Homebrew, RecipeData, Recipe");
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
		Chunk tab1 = new Chunk(new VerticalPositionMark(), 85, true);
		Chunk tab4 = new Chunk(new VerticalPositionMark(), 380, true);
		Chunk tab5 = new Chunk(new VerticalPositionMark(), 465, true);
		
		//Empty Line
		addEmptyLine(genericParagraph, 1);
		
		//Header
		genericParagraph.add(new Paragraph(RecipeDataPanel.textRecipeNameB.getText(), heading1Font));
		addEmptyLine(genericParagraph, 1);
		genericParagraph.add(new Chunk(ls));
		addEmptyLine(genericParagraph, 2);
		
		genericParagraph.add(new Phrase("Inspiration:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		genericParagraph.add(new Phrase(RecipeDataPanel.textInspirationB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 1);
		genericParagraph.add(new Phrase("Suggested Yeast:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		genericParagraph.add(new Phrase(RecipeDataPanel.textSuggestedYeastB.getText(),smallNormal));
		genericParagraph.add(new Chunk(tab4));
		genericParagraph.add(new Phrase("Volume (gallons):",smallBold));
		genericParagraph.add(new Chunk(tab5));
		genericParagraph.add(new Phrase(RecipeDataPanel.textVolumeB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Chunk(ls));
		addEmptyLine(genericParagraph, 2);		

		genericParagraph.add(new Phrase("Ingredients:",smallBold));
		addEmptyLine(genericParagraph, 1);
		genericParagraph.add(new Phrase(RecipeDataPanel.textIngredientsB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Chunk(ls));
		addEmptyLine(genericParagraph, 2);
		
		genericParagraph.add(new Phrase("Method:",smallBold));
		addEmptyLine(genericParagraph, 1);
		genericParagraph.add(new Phrase(RecipeDataPanel.textMethodB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Chunk(ls));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Phrase("Notes:",smallBold));
		addEmptyLine(genericParagraph, 1);
		genericParagraph.add(new Phrase(RecipeDataPanel.textNotesB.getText(),smallNormal));
		addEmptyLine(genericParagraph, 2);
		genericParagraph.add(new Phrase("References:",smallBold));
		genericParagraph.add(new Chunk(tab1));
		genericParagraph.add(new Phrase(RecipeDataPanel.textReferencesB.getText(),smallNormal));
		
	
		// Now add all this to the document
		document.add(genericParagraph);
		
		// Start a new page
		document.newPage();
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
            
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Phrase("WineBrewDB",headerFoot),rect.getLeft(), rect.getTop(), 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_RIGHT, new Phrase("Recipe Data: " + RecipeDataPanel.textRecipeNameB.getText(),headerFoot),rect.getRight(), rect.getTop(), 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase(String.format("Page %d", writer.getPageNumber()),headerFoot),(rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
        }
    }	
	
}
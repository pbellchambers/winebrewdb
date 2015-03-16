package uk.co.pbellchambers.winebrewdb.sqlite;

import uk.co.pbellchambers.winebrewdb.Dates;
import uk.co.pbellchambers.winebrewdb.MainWindow;
import uk.co.pbellchambers.winebrewdb.brew.*;
import uk.co.pbellchambers.winebrewdb.information.InformationEditTab;
import uk.co.pbellchambers.winebrewdb.ledger.LedgerBrewCostSearchPanel;
import uk.co.pbellchambers.winebrewdb.ledger.LedgerEquipmentPanel;
import uk.co.pbellchambers.winebrewdb.recipe.RecipeAddPanel;
import uk.co.pbellchambers.winebrewdb.recipe.RecipeDataPanel;
import uk.co.pbellchambers.winebrewdb.recipe.RecipeSearchPanel;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Vector;

/**
 * The DBEngine is used for all queries to the sqlite database.
 * 
 * @author Paul.Bellchambers
 *
 */
public class DBEngine {
	
	
	private static ImageIcon imageIconBrewPicture;


	/**
	 * Creates the JDBC connection to the sqlite database specified in the ini file.
	 * 
	 * @return Returns the connection.
	 * @throws Exception
	 */
	public static Connection dbConnection() throws Exception {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + MainWindow.DatabaseLocationFromIni);	
		
	}
	
	
	/**
	 * Gets everything from the Brews table according to the filters entered by the user.
	 * 
	 * @return Returns the brews table data.
	 * @throws Exception
	 */
	public static Vector<Vector<Object>> getBrews() throws Exception {
	    Connection conn = dbConnection();
	    String Colour = null;
	    String ThumbsUp = null;
	    String InPlanning = null;
	    String InFermenting = null;
	    String InFining = null;
	    String InMaturing = null;
	    String InBottles = null;
	    String Drunk = null;
	      
	    if(BrewSearchPanel.comboColour.getSelectedItem().equals("Any")){
	    	Colour ="White','Red','Ros�','',' ','Other";
	    } else {
	    	Colour = (String) BrewSearchPanel.comboColour.getSelectedItem();
	    }
	    
	    if(BrewSearchPanel.comboThumbs.getSelectedItem().equals("Any")){
	    	ThumbsUp ="Up','Middle','',' ','Down";
	    } else {
	    	ThumbsUp = (String) BrewSearchPanel.comboThumbs.getSelectedItem();
	    }
	    
	    if(BrewSearchPanel.chckbxInPlanning.isSelected()){
	    	InPlanning = "1";
	    } else {
	    	InPlanning = "3";
	    }
	    
	    if(BrewSearchPanel.chckbxInFermenting.isSelected()){
	    	InFermenting = "1";
	    } else {
	    	InFermenting = "3";
	    }
	    
	    if(BrewSearchPanel.chckbxInFining.isSelected()){
	    	InFining = "1";
	    } else {
	    	InFining = "3";
	    }
	    
	    if(BrewSearchPanel.chckbxInMaturing.isSelected()){
	    	InMaturing = "1";
	    } else {
	    	InMaturing = "3";
	    }
	    
	    if(BrewSearchPanel.chckbxInBottles.isSelected()){
	    	InBottles = "1";
	    } else {
	    	InBottles = "3";
	    }
	    
	    if(BrewSearchPanel.chckbxDrunk.isSelected()){
	    	Drunk = "1";
	    } else {
	    	Drunk = "3";
	    }
	     
	    Vector<Vector<Object>> Brews = new Vector<Vector<Object>>();
	    PreparedStatement pre = conn.prepareStatement(
	    	"select * from Brews where BrewName like '%" + 
	    	BrewSearchPanel.textBrewName.getText() + 
	    	"%' and RecipeFrom like '%" + 
	    	BrewSearchPanel.textRecipeFrom.getText() + 
	    	"%' and Yeast like '%" + 
	    	BrewSearchPanel.textYeast.getText() + 
	    	"%' and Notes like '%" + 
	    	BrewSearchPanel.textNotes.getText() + 
	    	"%' and TastingNotes like '%" + 
	    	BrewSearchPanel.textTastingNotes.getText() + 
	    	"%' and Colour in ('" +
	    	Colour +
	    	"') and ThumbsUp in('" +
	    	ThumbsUp +
			"')	and BrewRef in (select BrewRef from Brews where InPlanning='" +
			InPlanning +
	    	"' or InFermenting='" +
	    	InFermenting +
			"' or InFining='" +
			InFining +
	    	"' or InMaturing='" +
			InMaturing +
			"' or InBottles='" +
	    	InBottles +
			"' or Drunk='" +
			Drunk +
	    	"') order by DateStarted desc"
			);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<Object> brew = new Vector<Object>();
		    brew.add(rs.getString(1)); //BrewRef
		    brew.add(rs.getString(2)); //BrewName
		    brew.add(rs.getString(3)); //DatePlanned
		    brew.add(rs.getString(4)); //DateStarted
		    brew.add(rs.getString(5)); //DateBottled
		    brew.add(rs.getString(6)); //RecipeFrom
		    brew.add(rs.getString(7)); //Yeast
		    brew.add(rs.getString(8)); //StartSG
		    brew.add(rs.getString(9)); //StartAdjustedSG
		    brew.add(rs.getString(10)); //EndSG
		    brew.add(rs.getString(11)); //AimedABV
		    brew.add(rs.getString(12)); //FinalABV
		    brew.add(rs.getString(13)); //FinalAdjustedABV
		    brew.add(rs.getString(14)); //Notes
		    brew.add(rs.getString(15)); //TastingNotes
		    brew.add(rs.getString(16)); //ThumbsUp
		    brew.add(rs.getString(17)); //InPlanning
		    brew.add(rs.getString(18)); //InFermenting
		    brew.add(rs.getString(19)); //InFining
		    brew.add(rs.getString(20)); //InMaturing
		    brew.add(rs.getString(21)); //InBottles
		    brew.add(rs.getString(22)); //Drunk
		    brew.add(rs.getString(23)); //VolumeMade
		    brew.add(rs.getString(24)); //NumberBottles
		    brew.add(rs.getString(25)); //Colour
		    brew.add(rs.getFloat(26)); //TotalCost
		    brew.add(rs.getFloat(27)); //CostPerBottle
		    Brews.add(brew);
	    }

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();

	    return Brews;	    
	    
	}
	
	
	/**
	 * Updates the brew data in the database with what has been entered on the edit brew tab.
	 * 
	 * @throws Exception
	 */
	public static void updateBrew() throws Exception {
	    Connection conn = dbConnection();
	    String InPlanningB = null;
	    String InFermentingB = null;
	    String InFiningB = null;
	    String InMaturingB = null;
	    String InBottlesB = null;
	    String DrunkB = null;
	    String BrewDatePlannedUpdateB = null;
	    String BrewDateStartedUpdateB = null;
	    String BrewDateBottledUpdateB = null;
	    
	    if(BrewDataPanel.chckbxBrewInPlanningB.isSelected()){
	    	InPlanningB = "1";
	    } else {
	    	InPlanningB = "0";
	    }
	    
	    if(BrewDataPanel.chckbxBrewInFermentingB.isSelected()){
	    	InFermentingB = "1";
	    } else {
	    	InFermentingB = "0";
	    }
	    
	    if(BrewDataPanel.chckbxBrewInFiningB.isSelected()){
	    	InFiningB = "1";
	    } else {
	    	InFiningB = "0";
	    }
	    
	    if(BrewDataPanel.chckbxBrewInMaturingB.isSelected()){
	    	InMaturingB = "1";
	    } else {
	    	InMaturingB = "0";
	    }
	    
	    if(BrewDataPanel.chckbxBrewInBottlesB.isSelected()){
	    	InBottlesB = "1";
	    } else {
	    	InBottlesB = "0";
	    }
	    
	    if(BrewDataPanel.chckbxBrewDrunkB.isSelected()){
	    	DrunkB = "1";
	    } else {
	    	DrunkB = "0";
	    }
	    
	    //Cover empty dates
	    if(BrewDataPanel.chooserBrewDatePlannedB.getDate() == null){
	    	BrewDatePlannedUpdateB = "";
	    } else{
	    	BrewDatePlannedUpdateB = Dates.dateToString(BrewDataPanel.chooserBrewDatePlannedB.getDate());
	    }
	    if(BrewDataPanel.chooserBrewDateStartedB.getDate() == null){
	    	BrewDateStartedUpdateB = "";
	    } else{
	    	BrewDateStartedUpdateB = Dates.dateToString(BrewDataPanel.chooserBrewDateStartedB.getDate());
	    }
	    if(BrewDataPanel.chooserBrewDateBottledB.getDate() == null){
	    	BrewDateBottledUpdateB = "";
	    } else{
	    	BrewDateBottledUpdateB = Dates.dateToString(BrewDataPanel.chooserBrewDateBottledB.getDate());
	    }
	     
	    PreparedStatement pre = conn.prepareStatement(
	    	"update Brews set BrewName='" +
	    	BrewDataPanel.textBrewNameB.getText().replaceAll("'", "''") +
	    	"',Colour='" +
	    	BrewDataPanel.comboBrewColourB.getSelectedItem() +
	    	"',RecipeFrom='" +
	   		BrewDataPanel.textBrewRecipeB.getText().replaceAll("'", "''") +
	    	"',ThumbsUp='" +
	   		BrewDataPanel.comboBrewThumbsB.getSelectedItem() +
	    	"',DatePlanned='" +
	    	BrewDatePlannedUpdateB +
	    	"',DateStarted='" +
	    	BrewDateStartedUpdateB +
	    	"',DateBottled='" +
	    	BrewDateBottledUpdateB +
	    	"',StartSG='" +
	   		BrewDataPanel.textBrewStartSGB.getText().replaceAll("'", "''") +
	    	"',StartAdjustedSG='" +
	   		BrewDataPanel.textBrewStartAdjustedSGB.getText().replaceAll("'", "''") +
	    	"',EndSG='" +
	   		BrewDataPanel.textBrewEndSGB.getText().replaceAll("'", "''") +
	    	"',AimedABV='" +
	   		BrewDataPanel.textBrewAimedABVB.getText().replaceAll("'", "''") +
	    	"',FinalABV='" +
	   		BrewDataPanel.textBrewFinalABVB.getText().replaceAll("'", "''") +
	    	"',FinalAdjustedABV='" +
	   		BrewDataPanel.textBrewFinalAdjustedABVB.getText().replaceAll("'", "''") +
	    	"',Yeast='" +
	   		BrewDataPanel.textBrewYeastB.getText().replaceAll("'", "''") +
	    	"',VolumeMade='" +
	   		BrewDataPanel.textBrewVolumeMadeB.getText().replaceAll("'", "''") +
	    	"',InPlanning='" +
	   		InPlanningB +
	    	"',InFermenting='" +
	   		InFermentingB +
	    	"',InFining='" +
	   		InFiningB +
	    	"',NumberBottles='" +
	   		BrewDataPanel.textBrewNumberBottlesB.getText().replaceAll("'", "''") +
	    	"',InMaturing='" +
	   		InMaturingB +
	    	"',InBottles='" +
	   		InBottlesB +
	    	"',Drunk='" +
	   		DrunkB +
	    	"',TastingNotes='" +
	   		BrewDataPanel.textBrewTastingNotesB.getText().replaceAll("'", "''") +
	    	"',Notes='" +
	   		BrewDataPanel.textBrewGeneralNotesB.getText().replaceAll("'", "''") +
	    	"' where BrewRef='" +
	    	BrewDataPanel.textBrewRefB.getText() +
	    	"'"
	    );
	    
	    pre.executeUpdate();  

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();    
	    
	}
	
	
	/**
	 * Adds a new brew to the database with what has been entered on the add brew tab.
	 * 
	 * @throws Exception
	 */
	public static void addBrew() throws Exception {
	    Connection conn = dbConnection();
	    String InPlanningAdd = null;
	    String InFermentingAdd = null;
	    String InFiningAdd = null;
	    String InMaturingAdd = null;
	    String InBottlesAdd = null;
	    String DrunkAdd = null;
	    String BrewDatePlannedAddB = null;
	    String BrewDateStartedAddB = null;
	    String BrewDateBottledAddB = null;
	    
	    if(BrewAddPanel.chckbxBrewInPlanningAdd.isSelected()){
	    	InPlanningAdd = "1";
	    } else {
	    	InPlanningAdd = "0";
	    }
	    
	    if(BrewAddPanel.chckbxBrewInFermentingAdd.isSelected()){
	    	InFermentingAdd = "1";
	    } else {
	    	InFermentingAdd = "0";
	    }
	    
	    if(BrewAddPanel.chckbxBrewInFiningAdd.isSelected()){
	    	InFiningAdd = "1";
	    } else {
	    	InFiningAdd = "0";
	    }
	    
	    if(BrewAddPanel.chckbxBrewInMaturingAdd.isSelected()){
	    	InMaturingAdd = "1";
	    } else {
	    	InMaturingAdd = "0";
	    }
	    
	    if(BrewAddPanel.chckbxBrewInBottlesAdd.isSelected()){
	    	InBottlesAdd = "1";
	    } else {
	    	InBottlesAdd = "0";
	    }
	    
	    if(BrewAddPanel.chckbxBrewDrunkAdd.isSelected()){
	    	DrunkAdd = "1";
	    } else {
	    	DrunkAdd = "0";
	    }
	    
	    //Cover empty dates
	    if(BrewAddPanel.chooserBrewDatePlannedAdd.getDate() == null){
	    	BrewDatePlannedAddB = "";
	    } else{
	    	BrewDatePlannedAddB = Dates.dateToString(BrewAddPanel.chooserBrewDatePlannedAdd.getDate());
	    }
	    if(BrewAddPanel.chooserBrewDateStartedAdd.getDate() == null){
	    	BrewDateStartedAddB = "";
	    } else{
	    	BrewDateStartedAddB = Dates.dateToString(BrewAddPanel.chooserBrewDateStartedAdd.getDate());
	    }
	    if(BrewAddPanel.chooserBrewDateBottledAdd.getDate() == null){
	    	BrewDateBottledAddB = "";
	    } else{
	    	BrewDateBottledAddB = Dates.dateToString(BrewAddPanel.chooserBrewDateBottledAdd.getDate());
	    }
	    
	     
	    PreparedStatement pre = conn.prepareStatement(
    		"insert into Brews(BrewName,Colour,RecipeFrom,ThumbsUp,DatePlanned,DateStarted,DateBottled,StartSG,StartAdjustedSG,EndSG,AimedABV,FinalABV,FinalAdjustedABV,Yeast,VolumeMade,InPlanning,InFermenting,InFining,NumberBottles,InMaturing,InBottles,Drunk,TastingNotes,Notes,TotalCost,CostPerBottle) values('" +
    		BrewAddPanel.textBrewNameAdd.getText().replaceAll("'", "''") +
    		"','" +
    		BrewAddPanel.comboBrewColourAdd.getSelectedItem() +
    		"','" +
    		BrewAddPanel.textBrewRecipeAdd.getText().replaceAll("'", "''") +
    		"','" +
    		BrewAddPanel.comboBrewThumbsAdd.getSelectedItem() +
    		"','" +
    		BrewDatePlannedAddB +
    		"','" +
    		BrewDateStartedAddB +
    		"','" +
    		BrewDateBottledAddB +
    		"','" +
    		BrewAddPanel.textBrewStartSGAdd.getText().replaceAll("'", "''") +
    		"','" +
    		BrewAddPanel.textBrewStartAdjustedSGAdd.getText().replaceAll("'", "''") +
    		"','" +
    		BrewAddPanel.textBrewEndSGAdd.getText().replaceAll("'", "''") +
    		"','" +
    		BrewAddPanel.textBrewAimedABVAdd.getText().replaceAll("'", "''") +
    		"','" +
    		BrewAddPanel.textBrewFinalABVAdd.getText().replaceAll("'", "''") +
    		"','" +
    		BrewAddPanel.textBrewFinalAdjustedABVAdd.getText().replaceAll("'", "''") +
    		"','" +
    		BrewAddPanel.textBrewYeastAdd.getText().replaceAll("'", "''") +
    		"','" +
    		BrewAddPanel.textBrewVolumeMadeAdd.getText().replaceAll("'", "''") +
    		"','" +
    		InPlanningAdd +
    		"','" +
    		InFermentingAdd +
    		"','" +
    		InFiningAdd +
    		"','" +
    		BrewAddPanel.textBrewNumberBottlesAdd.getText().replaceAll("'", "''") +
    		"','" +
    		InMaturingAdd +
    		"','" +
    		InBottlesAdd +
    		"','" +
    		DrunkAdd +
	   		"','" +
	   		BrewAddPanel.textBrewTastingNotesAdd.getText().replaceAll("'", "''") +
	   		"','" +
	   		BrewAddPanel.textBrewGeneralNotesAdd.getText().replaceAll("'", "''") +
	   		"','" +
	   		BrewAddPanel.textBrewTotalCostAdd.getText().replaceAll("'", "''") +
	   		"','" +
	   		BrewAddPanel.textBrewCostPerBottleAdd.getText().replaceAll("'", "''") +
	    	"')"
	    );
	    
	    pre.executeUpdate();  

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();    
	    
	}
	
	
	/**
	 * Deletes the selected brew and all related data from the database.
	 * 
	 * @throws Exception
	 */
	public static void deleteBrew() throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("delete from Brews where BrewRef='" + BrewDataPanel.textBrewRefB.getText() + "'");
		PreparedStatement pre2 = conn.prepareStatement("delete from BrewNotes where BrewRef='" + BrewDataPanel.textBrewRefB.getText() + "'");
		PreparedStatement pre3 = conn.prepareStatement("delete from BrewPictures where BrewRef='" + BrewDataPanel.textBrewRefB.getText() + "'");
		PreparedStatement pre4 = conn.prepareStatement("delete from BrewCosts where BrewRef='" + BrewDataPanel.textBrewRefB.getText() + "'");

		pre.executeUpdate();
		pre2.executeUpdate();
		pre3.executeUpdate();
		pre4.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
	    
	}
	

	/**
	 * Gets all brew notes data from the BrewNotes table for the selected brew.
	 * 
	 * @return Returns the brew notes data.
	 * @throws Exception
	 */
	public static Vector<Vector<Object>> getBrewNotes() throws Exception {
	    Connection conn = dbConnection();
	     
	    Vector<Vector<Object>> BrewNotes = new Vector<Vector<Object>>();
	    PreparedStatement pre = conn.prepareStatement(
	    	"select BrewNoteRef,Date,DaysSinceStart,Incident,Notes from BrewNotes where BrewRef='" + 
	    	BrewDataPanel.textBrewRefB.getText() +
	    	"' order by BrewNoteRef asc"
			);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<Object> brewnote = new Vector<Object>();
	    	brewnote.add(rs.getString(1)); //BrewNoteRef
	    	brewnote.add(rs.getString(2)); //Date
	    	brewnote.add(rs.getString(3)); //DaysSinceStart
	    	brewnote.add(rs.getString(4)); //Incident
	    	brewnote.add(rs.getString(5)); //Notes
	    	BrewNotes.add(brewnote);
	    }

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    return BrewNotes;	    
	    
	}
	
	
	/**
	 * Adds a brew note to the BrewNotes table for the selected brew.
	 * 
	 * @throws Exception
	 */
	public static void addBrewNote() throws Exception {
		Connection conn = dbConnection();
	    String BrewNoteDateAddB = null;
		
		//Cover empty dates
	    if(BrewNotesPanel.chooserBrewNoteDate.getDate() == null){
	    	BrewNoteDateAddB = "";
	    } else{
	    	BrewNoteDateAddB = Dates.dateToString(BrewNotesPanel.chooserBrewNoteDate.getDate());
	    }
		
		PreparedStatement pre = conn.prepareStatement(
			"insert into BrewNotes(BrewRef,BrewNoteRef,Date,DaysSinceStart,Incident,Notes) values('" + 
			BrewDataPanel.textBrewRefB.getText() + 
			"','" +
			BrewNotesPanel.textBrewNoteRef.getText() +
			"','" +
			BrewNoteDateAddB +
			"','" +
			BrewNotesPanel.textBrewNoteDaysSinceStart.getText() +
			"','" +
			BrewNotesPanel.textBrewNoteIncident.getText().replaceAll("'", "''") +
			"','" +
			BrewNotesPanel.textBrewNoteNote.getText().replaceAll("'", "''") +
			"')"
		);

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
	    
	}
	
	
	/**
	 * Calculates the next brew note ref that should be set by getting the current highest value for the selected brew and adding 1 to it.
	 * 
	 * @return Returns the next brew note ref.
	 * @throws Exception
	 */
	public static String getNextBrewNoteRef() throws Exception {
		Connection conn = dbConnection();
				
		PreparedStatement pre = conn.prepareStatement(
			"SELECT BrewNoteRef from BrewNotes where BrewRef='" +
			BrewDataPanel.textBrewRefB.getText() +
			"' order by BrewNoteRef desc limit 1"
		);

		int MaxBrewNoteRef = 0;
		int NextBrewNoteRef = 0;
		
		ResultSet rs = pre.executeQuery();
		
		while(rs.next()){
			//Add one to the current highest
			MaxBrewNoteRef = rs.getInt(1);			
		}
		
		NextBrewNoteRef = MaxBrewNoteRef + 1;
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    return Integer.toString(NextBrewNoteRef);
			    
	}
	
	
	/**
	 * Updates the selected brew note in the database with what has been entered on the brew notes tab.
	 * 
	 * @throws Exception
	 */
	public static void updateBrewNote() throws Exception {
		Connection conn = dbConnection();
		String BrewNoteDateUpdateB = null;
		
		//Cover empty dates
	    if(BrewNotesPanel.chooserBrewNoteDate.getDate() == null){
	    	BrewNoteDateUpdateB = "";
	    } else{
	    	BrewNoteDateUpdateB = Dates.dateToString(BrewNotesPanel.chooserBrewNoteDate.getDate());
	    }
		
		PreparedStatement pre = conn.prepareStatement(
			"update BrewNotes set Date='" + 
			BrewNoteDateUpdateB + 
			"',DaysSinceStart='" +
			BrewNotesPanel.textBrewNoteDaysSinceStart.getText() +
			"',Incident='" +
			BrewNotesPanel.textBrewNoteIncident.getText().replaceAll("'", "''") +
			"',Notes='" +
			BrewNotesPanel.textBrewNoteNote.getText().replaceAll("'", "''") +
			"' where BrewRef='" +
			BrewDataPanel.textBrewRefB.getText() +
			"' and BrewNoteRef='" +
			BrewNotesPanel.textBrewNoteRef.getText() +
			"'"
		);

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
		    
	}
	
	
	/**
	 * Deletes selected brew note from the database.
	 * 
	 * @throws Exception
	 */
	public static void deleteBrewNote() throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("delete from BrewNotes where BrewRef='" + BrewDataPanel.textBrewRefB.getText() + "' and BrewNoteRef='" + BrewNotesPanel.textBrewNoteRef.getText() + "'");

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	   if(conn!=null)
	  conn.close();  
		    
	}
		
		
	/**
	 * Gets the list of brew pictures from the BrewPictures table for the currently selected brew.
	 * 
	 * @return Returns the list of brew pictures.
	 * @throws Exception
	 */
	public static Vector<Vector<Object>> getBrewPictureTable() throws Exception {
	    Connection conn = dbConnection();
	     
	    Vector<Vector<Object>> BrewPictureTable = new Vector<Vector<Object>>();
	    PreparedStatement pre = conn.prepareStatement(
	    	"select BrewPicRef,Description from BrewPictures where BrewRef='" + 
	    	BrewDataPanel.textBrewRefB.getText() +
	    	"' order by BrewPicRef asc"
			);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<Object> brewpicture = new Vector<Object>();
	    	brewpicture.add(rs.getString(1)); //BrewPicRef
	    	brewpicture.add(rs.getString(2)); //Description
	    	BrewPictureTable.add(brewpicture);
	    }

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    return BrewPictureTable;	    
	    
	}
	
	
	/**
	 * Calculates the next brew picture ref that should be set by getting the current highest value for the selected brew and adding 1 to it.
	 * 
	 * @return Returns the next brew picture ref.
	 * @throws Exception
	 */
	public static String getNextBrewPictureRef() throws Exception {
		Connection conn = dbConnection();
					
		PreparedStatement pre = conn.prepareStatement(
			"SELECT BrewPicRef from BrewPictures where BrewRef='" +
			BrewDataPanel.textBrewRefB.getText() +
			"' order by BrewPicRef desc limit 1"
		);
		
		int MaxBrewPictureRef = 0;
		int NextBrewPictureRef = 0;

		MaxBrewPictureRef = 0;
		ResultSet rs = pre.executeQuery();
		
		while(rs.next()){
			//Add one to the current highest
			MaxBrewPictureRef = rs.getInt(1);			
		}
		
		NextBrewPictureRef = MaxBrewPictureRef + 1;
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    return Integer.toString(NextBrewPictureRef);
				    
	}
	
	
	/**
	 * Updates the selected brew picture in the database with the information entered on the brew pictures tab.
	 * 
	 * @throws Exception
	 */
	public static void updateBrewPicture() throws Exception {
		Connection conn = dbConnection();
	
		PreparedStatement pre = conn.prepareStatement(
			"update BrewPictures set Description='" + 
			BrewPicturesPanel.textBrewPictureDescription.getText().replaceAll("'", "''") +
			"' where BrewRef='" +
			BrewDataPanel.textBrewRefB.getText() +
			"' and BrewPicRef='" +
			BrewPicturesPanel.textBrewPictureRef.getText() +
			"'"
		);

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
		    
	}
	
	
	/**
	 * Deletes the selected brew picture from the database.
	 * 
	 * @throws Exception
	 */
	public static void deleteBrewPicture() throws Exception {
		Connection conn = dbConnection();
			
		PreparedStatement pre = conn.prepareStatement(
				"delete from BrewPictures where BrewRef='" + 
				BrewDataPanel.textBrewRefB.getText() + 
				"' and BrewPicRef='" +
				BrewPicturesPanel.textBrewPictureRef.getText() +
				"'");

		pre.executeUpdate();
			
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
		    
	}
	
	
	/**
	 * Gets the image for the selected brew picture from the database and sets it to imageIconBrewPicture
	 * 
	 * @throws Exception
	 */
	public static void getBrewPicture() throws Exception {
		Connection conn = dbConnection();
			
		PreparedStatement pre = conn.prepareStatement(
				"select Picture from BrewPictures where BrewRef='" +
				BrewDataPanel.textBrewRefB.getText() +
				"' and BrewPicRef='" +
				BrewPicturesPanel.textBrewPictureRef.getText() + 
				"'"
			);

		ResultSet rs = pre.executeQuery();
	       
		while(rs.next()){
	        imageIconBrewPicture = new ImageIcon(rs.getBytes(1));
		}
			
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	}
	
	
	/**
	 * Adds the new brew picture on the brew pictures tab to the database.
	 * 
	 * @throws Exception
	 */
	public static void insertBrewPicture() throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement(
				"insert into BrewPictures(Picture,BrewRef,BrewPicRef,Description) values(?,'" +
				BrewDataPanel.textBrewRefB.getText() +
				"','" +
				BrewPicturesPanel.textBrewPictureRef.getText() + 
				"','" +
				BrewPicturesPanel.textBrewPictureDescription.getText().replaceAll("'", "''") +
				"')"
			);
		 
		pre.setBytes(1, getBytesFromFile(new File(BrewPicturesPanel.textBrewPictureFilename.getText().replace("\\", "\\\\"))));
		
		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();		
		
	}
	
	
	/**
	 * Gets the bytes from a file specified by the user (used to load into the brew pictures table into the database).
	 * 
	 * @param file User specified file.
	 * @return Returns the bytes of that file.
	 * @throws IOException
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		
		// Get the size of the file
	    long length = file.length();
	    
	    // You cannot create an array using a long type.
	    // It needs to be an int type.
	    // Before converting to an int type, check
	    // to ensure that file is not larger than Integer.MAX_VALUE.
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    
	    // Create the byte array to hold the data
	    byte[] bytes = new byte[(int)length];
	    
	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }
	    
	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) {
	    	is.close();
	        throw new IOException("Could not completely read file "+file.getName());
	    }
	    
	    // Close the input stream and return bytes
	    is.close();
	    return bytes;
	}
	
		
	/**
	 * Gets the bytes from a brew picture stored in the database and writes it to a user specified file.
	 * 
	 * @param filename The user specified file.
	 * @throws Exception
	 */
	public static void writeToFile(File filename) throws Exception {
		byte[] imageBytes = null;
		BufferedOutputStream bufferedOutput = null;
		
		//Get Bytes for picture
		Connection conn = dbConnection();
			
		PreparedStatement pre;
		pre = conn.prepareStatement(
			"select Picture from BrewPictures where BrewRef='" +
			BrewDataPanel.textBrewRefB.getText() +
			"' and BrewPicRef='" +
			BrewPicturesPanel.textBrewPictureRef.getText() + 
			"'"
		);

		ResultSet rs = pre.executeQuery();
			       
		while(rs.next()){
			imageBytes = rs.getBytes(1);
		}
					
		/*Close the connection after use (MUST)*/
		if(conn!=null)
		conn.close();
		     
		//Construct the BufferedOutputStream object
	    bufferedOutput = new BufferedOutputStream(new FileOutputStream(filename));
	        
	    //Start writing to the output stream
	    bufferedOutput.write(imageBytes);       
	        
	    //Close the BufferedOutputStream
	    if (bufferedOutput != null) {
	    	bufferedOutput.flush();
	    	bufferedOutput.close();
	    }

	}
	

	/**
	 * Scales the imageIconBrewPicture image to fit the width/height of the panel.
	 * 
	 * @param panelwidth New width of the panel.
	 * @param panelheight New height of the panel.
	 * @param location Will be "file" if the image is a file on disk and not yet stored in the database, anything else otherwise.
	 * @return Returns the scaled image as imageIconScaledBrewPicture
	 */
	public static ImageIcon scaledImageIcon(int panelwidth, int panelheight, String location) {
		if(location.equals("file")){
			try {
				imageIconBrewPicture = new ImageIcon(getBytesFromFile(new File(BrewPicturesPanel.textBrewPictureFilename.getText().replace("\\", "\\\\"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				getBrewPicture();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		//Get sizes
		Image imageBrewPicture = imageIconBrewPicture.getImage();
		BigDecimal DecimalIconWidth = new BigDecimal(imageIconBrewPicture.getIconWidth());
		BigDecimal DecimalPanelWidth = new BigDecimal(panelwidth);
		BigDecimal DecimalIconHeight = new BigDecimal(imageIconBrewPicture.getIconHeight());
		BigDecimal DecimalPanelHeight = new BigDecimal(panelheight);
		

		//Calculate new sizes
		BigDecimal factor;
		BigDecimal factor2;
		int NewWidth;
		int NewHeight;
		int NewNewWidth;
		int NewNewHeight;
		
		if (imageIconBrewPicture.getIconWidth() <= panelwidth && imageIconBrewPicture.getIconHeight() <= panelheight){
			NewNewWidth = imageIconBrewPicture.getIconWidth();
			NewNewHeight = imageIconBrewPicture.getIconHeight();
		} else{
			if (imageIconBrewPicture.getIconWidth() >= imageIconBrewPicture.getIconHeight()) {
				factor = DecimalIconWidth.divide(DecimalPanelWidth, 0, BigDecimal.ROUND_HALF_UP);
				NewWidth = Integer.valueOf(DecimalIconWidth.divide(factor, 0, BigDecimal.ROUND_HALF_UP).intValue());
				NewHeight = Integer.valueOf(DecimalIconHeight.divide(factor, 0, BigDecimal.ROUND_HALF_UP).intValue());
				if (NewHeight > Integer.valueOf(DecimalPanelHeight.intValue())){
					factor2 = new BigDecimal(NewHeight).divide(DecimalPanelHeight, 0, BigDecimal.ROUND_HALF_UP);
					NewNewWidth = Integer.valueOf(new BigDecimal(NewWidth).divide(factor2).intValue());
					NewNewHeight = Integer.valueOf(new BigDecimal(NewHeight).divide(factor2).intValue());
				} else {
					NewNewWidth = NewWidth;
					NewNewHeight = NewHeight;
				}
			}else {
				factor = DecimalIconHeight.divide(DecimalPanelHeight, 0, BigDecimal.ROUND_HALF_UP);
				NewWidth = Integer.valueOf(DecimalIconWidth.divide(factor, 0, BigDecimal.ROUND_HALF_UP).intValue());
				NewHeight = Integer.valueOf(DecimalIconHeight.divide(factor, 0, BigDecimal.ROUND_HALF_UP).intValue());
				if (NewWidth > Integer.valueOf(DecimalPanelWidth.intValue())){
					factor2 = new BigDecimal(NewWidth).divide(DecimalPanelWidth, 0, BigDecimal.ROUND_HALF_UP);
					NewNewWidth = Integer.valueOf(new BigDecimal(NewWidth).divide(factor2).intValue());
					NewNewHeight = Integer.valueOf(new BigDecimal(NewHeight).divide(factor2).intValue());
				} else {
					NewNewWidth = NewWidth;
					NewNewHeight = NewHeight;
				}
			}
		}
		
		//Resize and return
		Image imageScaledBrewPicture = imageBrewPicture.getScaledInstance(NewNewWidth, NewNewHeight, Image.SCALE_SMOOTH); 
        ImageIcon imageIconScaledBrewPicture = new ImageIcon(imageScaledBrewPicture);
		return imageIconScaledBrewPicture;
		
	}
	
	
	/**
	 * Gets everything from the recipes table in the database.
	 * 
	 * @return Returns all the data.
	 * @throws Exception
	 */
	public static Vector<Vector<Object>> getRecipes() throws Exception {
	    Connection conn = dbConnection();
	          
	    Vector<Vector<Object>> Recipes = new Vector<Vector<Object>>();
	    PreparedStatement pre = conn.prepareStatement(
	    	"select * from Recipes where RecipeName like '%" + 
	    	RecipeSearchPanel.textRecipeName.getText() + 
	    	"%' and Inspiration like '%" + 
	    	RecipeSearchPanel.textInspiration.getText() + 
	    	"%' and Ingredients like '%" + 
	    	RecipeSearchPanel.textIngredients.getText() + 
	    	"%' and SuggestedYeast like '%" + 
	    	RecipeSearchPanel.textSuggestedYeast.getText() + 
	    	"%' and Method like '%" + 
	    	RecipeSearchPanel.textMethod.getText() + 
	    	"%' and Notes like '%" + 
	    	RecipeSearchPanel.textNotes.getText() + 
	    	"%'"
			);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<Object> Recipe = new Vector<Object>();
		    Recipe.add(rs.getString(1)); //RecipeRef
		    Recipe.add(rs.getString(2)); //RecipeName
		    Recipe.add(rs.getString(3)); //Inspiration
		    Recipe.add(rs.getString(4)); //Ingredients
		    Recipe.add(rs.getString(5)); //SuggestedYeast
		    Recipe.add(rs.getString(6)); //Method
		    Recipe.add(rs.getString(7)); //Notes
		    Recipe.add(rs.getString(8)); //References
		    Recipe.add(rs.getString(9)); //Volume
		    Recipes.add(Recipe);
	    }

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();

	    return Recipes;	    
	    
	}


	/**
	 * Updates the selected recipe in the database with the data on the recipe data tab.
	 * 
	 * @throws Exception
	 */
	public static void updateRecipe() throws Exception {
	    Connection conn = dbConnection();
	        
	    PreparedStatement pre = conn.prepareStatement(
	    	"update Recipes set RecipeName='" +
	    	RecipeDataPanel.textRecipeNameB.getText().replaceAll("'", "''") +
	    	"',Inspiration='" +
	    	RecipeDataPanel.textInspirationB.getText().replaceAll("'", "''") +
	    	"',Ingredients='" +
	   		RecipeDataPanel.textIngredientsB.getText().replaceAll("'", "''") +
	    	"',SuggestedYeast='" +
	   		RecipeDataPanel.textSuggestedYeastB.getText().replaceAll("'", "''") +
	    	"',Method='" +
	    	RecipeDataPanel.textMethodB.getText().replaceAll("'", "''") +
	    	"',Notes='" +
	    	RecipeDataPanel.textNotesB.getText().replaceAll("'", "''") +
	    	"',Reffs='" +
	    	RecipeDataPanel.textReferencesB.getText().replaceAll("'", "''") +
	    	"',Volume='" +
	   		RecipeDataPanel.textVolumeB.getText().replaceAll("'", "''") +
	    	"' where RecipeRef='" +
			RecipeDataPanel.textRecipeRefB.getText() +
			"'"
	    );
	    
	    pre.executeUpdate();  

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();    
	    
	}


	/**
	 * Adds a new recipe to the recipes table with the data on the add recipe tab.
	 * 
	 * @throws Exception
	 */
	public static void addRecipe() throws Exception {
	    Connection conn = dbConnection(); 
	     
	    PreparedStatement pre = conn.prepareStatement(
	    	"insert into Recipes(RecipeName,Inspiration,Ingredients,SuggestedYeast,Method,Notes,Reffs,Volume) values('" +
	    	RecipeAddPanel.textRecipeNameAdd.getText().replaceAll("'", "''") +
	    	"','" +
	    	RecipeAddPanel.textInspirationAdd.getText().replaceAll("'", "''") +
	    	"','" +
	    	RecipeAddPanel.textIngredientsAdd.getText().replaceAll("'", "''") +
	    	"','" +
	    	RecipeAddPanel.textSuggestedYeastAdd.getText().replaceAll("'", "''") +
	    	"','" +
	    	RecipeAddPanel.textMethodAdd.getText().replaceAll("'", "''") +
	    	"','" +
	    	RecipeAddPanel.textNotesAdd.getText().replaceAll("'", "''") +
	    	"','" +
	    	RecipeAddPanel.textReferencesAdd.getText().replaceAll("'", "''") +
	    	"','" +
	    	RecipeAddPanel.textVolumeAdd.getText().replaceAll("'", "''") +
	    	"')"
	    );
	    
	    pre.executeUpdate();  

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();    
	    
	}


	/**
	 * Deletes the selected recipe from the recipes table in the database.
	 * 
	 * @throws Exception
	 */
	public static void deleteRecipe() throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("delete from Recipes where RecipeRef='" + RecipeDataPanel.textRecipeRefB.getText() + "'");

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
	    
	}
	
	
	
	/**
	 * Gets the costs for the selected brew from the BrewCosts table in the database.
	 * 
	 * @param brewref The brewref for the selected brew.
	 * @return Returns the brew costs data.
	 * @throws Exception
	 */
	public static Vector<Vector<Object>> getBrewCosts(String brewref) throws Exception {
	    Connection conn = dbConnection();
	     
	    Vector<Vector<Object>> BrewCosts = new Vector<Vector<Object>>();
	    PreparedStatement pre = conn.prepareStatement(
	    	"select BrewCostRef,LineItem,Cost,Supplier from BrewCosts where BrewRef='" + 
	    	brewref +
	    	"' order by BrewCostRef asc"
			);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<Object> brewcost = new Vector<Object>();
	    	brewcost.add(rs.getString(1)); //BrewCostRef
	    	brewcost.add(rs.getString(2)); //LineItem
	    	brewcost.add(rs.getFloat(3)); //Cost
	    	brewcost.add(rs.getString(4)); //Supplier
	    	BrewCosts.add(brewcost);
	    }

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    return BrewCosts;	    
	    
	}


	/**
	 * Adds a new cost for the selected brew to the BrewCosts table in the database.
	 * 
	 * @param brewref The brewref for the selected brew.
	 * @param brewcostlineitem The user entered lineitem string for the selected brew.
	 * @param brewcostcost The user entered cost for the selected brew.
	 * @param brewcostsupplier The user entered supplier for the selected brew.
	 * @throws Exception
	 */
	public static void addBrewCost(String brewref, String brewcostlineitem, String brewcostcost, String brewcostsupplier) throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement(
			"insert into BrewCosts(BrewRef,BrewCostRef,LineItem,Cost,Supplier) values('" + 
			brewref + 
			"','" +
			getNextBrewCostRef(brewref) +
			"','" +
			brewcostlineitem +
			"','" +
			brewcostcost +
			"','" +
			brewcostsupplier +
			"')"
		);

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
	    
	}


	/**
	 * Gets the next available brew cost ref for the selected brew, by selecting the current highest value for that brew and adding 1.
	 * 
	 * @param brewref The brewref for the selected brew.
	 * @return Returns the next brewcostref.
	 * @throws Exception
	 */
	public static String getNextBrewCostRef(String brewref) throws Exception {
		Connection conn = dbConnection();
				
		PreparedStatement pre = conn.prepareStatement(
			"SELECT BrewCostRef from BrewCosts where BrewRef='" +
			brewref +
			"' order by BrewCostRef desc limit 1"
		);

		int MaxBrewCostRef = 0;
		int NextBrewCostRef = 0;
		
		ResultSet rs = pre.executeQuery();
		
		while(rs.next()){
			//Add one to the current highest
			MaxBrewCostRef = rs.getInt(1);			
		}
		
		NextBrewCostRef = MaxBrewCostRef + 1;
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    return Integer.toString(NextBrewCostRef);
			    
	}


	/**
	 * Updates the selected brew cost with the data entered by the user.
	 *  
	 * @param brewref The brewref for the selected brew.
	 * @param brewcostlineitem The user entered line item string for the brew cost.
	 * @param brewcostcost The user entered cost for the brew cost.
	 * @param brewcostsupplier The user entered supplier for the brew cost.
	 * @param brewcostref The brewcostref of the selected brew cost.
	 * @throws Exception
	 */
	public static void updateBrewCost(String brewref, String brewcostlineitem, String brewcostcost, String brewcostsupplier, String brewcostref) throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement(
			"update BrewCosts set LineItem='" + 
			brewcostlineitem + 
			"',Cost='" +
			brewcostcost +
			"',Supplier='" +
			brewcostsupplier +
			"' where BrewRef='" +
			brewref +
			"' and BrewCostRef='" +
			brewcostref +
			"'"
		);

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
		    
	}


	/**
	 * Deletes the selected brew cost from the database.
	 * 
	 * @param brewref The brewref of the selected brew cost.
	 * @param brewcostref The brewcostref of the selected brew cost.
	 * @throws Exception
	 */
	public static void deleteBrewCost(String brewref, String brewcostref) throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("delete from BrewCosts where BrewRef='" + brewref + "' and BrewCostRef='" + brewcostref + "'");

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	   if(conn!=null)
	  conn.close();  
		    
	}
	
	
	/**
	 * Sets the total brew cost in the database for the selected brew. It does this by totalling the sum of all costs for the selected brew in sql, and then setting that value against the brew in the Brews table.
	 * 
	 * @param brewref The brewref of the selected brew.
	 * @param numberbottles The number of bottles on the brew data tab for the selected brew.
	 * @throws Exception
	 */
	public static void setTotalBrewCost(String brewref, String numberbottles) throws Exception {
		Connection conn = dbConnection();
				
		PreparedStatement pre = conn.prepareStatement(
			"SELECT Sum(Cost) from BrewCosts where BrewRef='" +
			brewref +
			"'"
		);

		
		ResultSet rs = pre.executeQuery();
		Float TotalBrewCost = null;
		
		while(rs.next()){
			//Add one to the current highest
			TotalBrewCost = rs.getFloat(1);			
		}
		
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();

	    Connection conn2 = dbConnection();
		PreparedStatement pre2 = conn2.prepareStatement(
			"update Brews set TotalCost='" + 
			TotalBrewCost +
			"' where BrewRef='" +
			brewref +
			"'"
		);

		pre2.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn2!=null)
	    conn2.close();
	    
	    if(numberbottles.equals("") || numberbottles.equals("0") || numberbottles == null){
		    Connection conn3 = dbConnection();
			PreparedStatement pre3 = conn3.prepareStatement(
				"update Brews set CostPerBottle='0' where BrewRef='" +
				brewref +
				"'"
			);

			pre3.executeUpdate();
			
			/*Close the connection after use (MUST)*/
		    if(conn3!=null)
		    conn3.close();
	    }else{
	    	BigDecimal BigDecimalTotalBrewCost = new BigDecimal(TotalBrewCost);
	    	BigDecimal BigDecimalNumberBottles = new BigDecimal(numberbottles);
	    	BigDecimal CostPerBottle = BigDecimalTotalBrewCost.divide(BigDecimalNumberBottles, 2, BigDecimal.ROUND_HALF_UP);
	    	
		    Connection conn3 = dbConnection();
			PreparedStatement pre3 = conn3.prepareStatement(
				"update Brews set CostPerBottle='" + 
				CostPerBottle.toString() + 
				"' where BrewRef='" +
				brewref +
				"'"
			);

			pre3.executeUpdate();
			
			/*Close the connection after use (MUST)*/
		    if(conn3!=null)
		    conn3.close();
	    }
			    
	}
	
	/**
	 * Gets the total brew cost for the selected brew. It does this by totalling the sum of all costs for the selected brew in sql.
	 * 
	 * @param brewref The brewref of the selected brew.
	 * @return Returns the total brew cost of the selected brew.
	 * @throws Exception
	 */
	public static String getTotalBrewCost(String brewref) throws Exception {
		Connection conn = dbConnection();
				
		PreparedStatement pre = conn.prepareStatement(
			"SELECT Sum(Cost) from BrewCosts where BrewRef='" +
			brewref +
			"'"
		);

		
		ResultSet rs = pre.executeQuery();
		Float TotalBrewCost = null;
		
		while(rs.next()){
			//Add one to the current highest
			TotalBrewCost = rs.getFloat(1);			
		}
		
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    NumberFormat nf = NumberFormat.getCurrencyInstance();
	    	    
		return nf.format(TotalBrewCost).toString();  
			    
	}
	
	/**
	 * Gets the brew cost per bottle for the selected brew.
	 * 
	 * @param brewref The brewref of the selected brew.
	 * @return Returns the brew cost per bottle for the selected brew.
	 * @throws Exception
	 */
	public static String getBrewCostPerBottle(String brewref) throws Exception {
		Connection conn = dbConnection();
				
		PreparedStatement pre = conn.prepareStatement(
			"SELECT CostPerBottle from Brews where BrewRef='" +
			brewref +
			"'"
		);

		
		ResultSet rs = pre.executeQuery();
		Float CostPerBottle = null;
		
		while(rs.next()){
			CostPerBottle = rs.getFloat(1);			
		}
		
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    NumberFormat nf = NumberFormat.getCurrencyInstance();
	    	    
		return nf.format(CostPerBottle).toString();  
			    
	}

	
	/**
	 * Gets all costs from the EquipmentCosts table in the database based on the user entered filters.
	 * 
	 * @return Returns the costs.
	 * @throws Exception
	 */
	public static Vector<Vector<Object>> getEquipmentCosts() throws Exception {
	    Connection conn = dbConnection();
	    String LedgerEquipmentDatesFilterA = null;
	    String LedgerEquipmentDatesFilterB = null;
	    String LedgerEquipmentCostsFilterA = null;
	    String LedgerEquipmentCostsFilterB = null;
		
	    //Cover empty dates & costs
	    if(LedgerEquipmentPanel.chooserLedgerEquipmentDatesFilterA.getDate() == null){
	    	LedgerEquipmentDatesFilterA = "1500/01/01";
	    } else{
	    	LedgerEquipmentDatesFilterA = Dates.dateToString(LedgerEquipmentPanel.chooserLedgerEquipmentDatesFilterA.getDate());
	    }
	    if(LedgerEquipmentPanel.chooserLedgerEquipmentDatesFilterB.getDate() == null){
	    	LedgerEquipmentDatesFilterB = "2500/01/01";
	    } else{
	    	LedgerEquipmentDatesFilterB = Dates.dateToString(LedgerEquipmentPanel.chooserLedgerEquipmentDatesFilterB.getDate());
	    }
	    if(LedgerEquipmentPanel.textLedgerEquipmentCostsFilterA.getText() == null || LedgerEquipmentPanel.textLedgerEquipmentCostsFilterA.getText().equals("")){
	    	LedgerEquipmentCostsFilterA = "-9999999.0";
	    } else{
	    	LedgerEquipmentCostsFilterA = LedgerEquipmentPanel.textLedgerEquipmentCostsFilterA.getText().replaceAll("[^0-9\\.]", "");
	    }
	    if(LedgerEquipmentPanel.textLedgerEquipmentCostsFilterB.getText() == null || LedgerEquipmentPanel.textLedgerEquipmentCostsFilterB.getText().equals("")){
	    	LedgerEquipmentCostsFilterB = "9999999.0";
	    } else{
	    	LedgerEquipmentCostsFilterB = LedgerEquipmentPanel.textLedgerEquipmentCostsFilterB.getText().replaceAll("[^0-9\\.]", "");
	    }
	     
	    Vector<Vector<Object>> EquipmentCosts = new Vector<Vector<Object>>();
	    PreparedStatement pre = conn.prepareStatement(
	    	"select EquipmentCostRef,Date,LineItem,Cost,Supplier from EquipmentCosts where Date >= '" +
	    	LedgerEquipmentDatesFilterA +		
	    	"' and Date <= '" +
	    	LedgerEquipmentDatesFilterB +
	    	"' and LineItem like '%" +
	    	LedgerEquipmentPanel.textLedgerEquipmentLineItemFilter.getText() +
	    	"%' and Cost >= '" +
	    	LedgerEquipmentCostsFilterA +
	    	"' and Cost <= '" +
	    	LedgerEquipmentCostsFilterB +
	    	"' and Supplier like '%" +
	    	LedgerEquipmentPanel.textLedgerEquipmentSupplierFilter.getText() +
	    	"%' order by Date desc"
			);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<Object> equipmentcost = new Vector<Object>();
	    	equipmentcost.add(rs.getString(1)); //EquipmentCostRef
	    	equipmentcost.add(rs.getString(2)); //Date
	    	equipmentcost.add(rs.getString(3)); //LineItem
	    	equipmentcost.add(rs.getFloat(4)); //Cost
	    	equipmentcost.add(rs.getString(5)); //Supplier
	    	EquipmentCosts.add(equipmentcost);
	    }

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    return EquipmentCosts;	    
	    
	}


	/**
	 * Adds the entered equipment cost item to the EquipmentCosts table.
	 * 
	 * @throws Exception
	 */
	public static void addEquipmentCost() throws Exception {
		Connection conn = dbConnection();
	    String LedgerEquipmentCostDate = null;
		
	    //Cover empty dates
	    if(LedgerEquipmentPanel.chooserLedgerEquipmentCostDate.getDate() == null){
	    	LedgerEquipmentCostDate = "";
	    } else{
	    	LedgerEquipmentCostDate = Dates.dateToString(LedgerEquipmentPanel.chooserLedgerEquipmentCostDate.getDate());
	    }
		
		PreparedStatement pre = conn.prepareStatement(
			"insert into EquipmentCosts(Date,LineItem,Cost,Supplier) values('" + 
			LedgerEquipmentCostDate +
			"','" +
			LedgerEquipmentPanel.textLedgerEquipmentCostLineItem.getText().replaceAll("'", "''") +
			"','" +
			LedgerEquipmentPanel.textLedgerEquipmentCostCost.getText().replaceAll("[^0-9\\.]", "") +
			"','" +
			LedgerEquipmentPanel.textLedgerEquipmentCostSupplier.getText().replaceAll("'", "''") +
			"')"
		);

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
	    
	}


	/**
	 * Updates the selected equipment cost in the database with the user entered data.
	 * 
	 * @throws Exception
	 */
	public static void updateEquipmentCost() throws Exception {
		Connection conn = dbConnection();
	    String LedgerEquipmentCostDate = null;
		
	    //Cover empty dates
	    if(LedgerEquipmentPanel.chooserLedgerEquipmentCostDate.getDate() == null){
	    	LedgerEquipmentCostDate = "";
	    } else{
	    	LedgerEquipmentCostDate = Dates.dateToString(LedgerEquipmentPanel.chooserLedgerEquipmentCostDate.getDate());
	    }
		
		PreparedStatement pre = conn.prepareStatement(
			"update EquipmentCosts set Date='" + 
			LedgerEquipmentCostDate + 
			"',LineItem='" +		
			LedgerEquipmentPanel.textLedgerEquipmentCostLineItem.getText().replaceAll("'", "''") + 
			"',Cost='" +
			LedgerEquipmentPanel.textLedgerEquipmentCostCost.getText().replaceAll("[^0-9\\.]", "") +
			"',Supplier='" +
			LedgerEquipmentPanel.textLedgerEquipmentCostSupplier.getText().replaceAll("'", "''") +
			"' where EquipmentCostRef='" +
			LedgerEquipmentPanel.textLedgerEquipmentCostRef.getText() +
			"'"
		);

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
		    
	}


	/**
	 * Deletes the currently selected equipment cost from the database.
	 * 
	 * @throws Exception
	 */
	public static void deleteEquipmentCost() throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("delete from EquipmentCosts where EquipmentCostRef='" + LedgerEquipmentPanel.textLedgerEquipmentCostRef.getText() + "'");

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	   if(conn!=null)
	  conn.close();  
		    
	}
	
	/**
	 * Gets the total number of brews from the Brews table in the database where the brew isn't in planning.
	 * 
	 * @return Returns the total number of brews.
	 * @throws Exception
	 */
	public static BigDecimal getTotalNumberBrews() throws Exception {
		Connection conn = dbConnection();
				
		PreparedStatement pre = conn.prepareStatement(
			"SELECT Count(*) from Brews where InPlanning = '0'"
		);

		
		ResultSet rs = pre.executeQuery();
		int NumberBrews = 0;
		BigDecimal DecimalNumberBrews = new BigDecimal("0");
		
		while(rs.next()){
			//Add one to the current highest
			NumberBrews = rs.getInt(1);			
		}
		
		
		if(NumberBrews == 0){
			DecimalNumberBrews = new BigDecimal("0.00000001");
		}else{
			DecimalNumberBrews = new BigDecimal(NumberBrews);
		}		
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    	    
		return DecimalNumberBrews;  
			    
	}
	
	
	/**
	 * Gets the total number of bottles for all brews in the database where the brew is not in planning.
	 * 
	 * @return Returns the total number of bottles.
	 * @throws Exception
	 */
	public static BigDecimal getTotalNumberBottles() throws Exception {
		Connection conn = dbConnection();
				
		PreparedStatement pre = conn.prepareStatement(
			"SELECT Sum(NumberBottles) from Brews where InPlanning = '0'"
		);

		
		ResultSet rs = pre.executeQuery();
		int NumberBottles = 0;
		BigDecimal DecimalNumberBottles = new BigDecimal("0");
		
		while(rs.next()){
			//Add one to the current highest
			NumberBottles = rs.getInt(1);			
		}
		
		
		if(NumberBottles == 0){
			DecimalNumberBottles = new BigDecimal("0.00000001");
		}else{
			DecimalNumberBottles = new BigDecimal(NumberBottles);
		}		
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    	    
		return DecimalNumberBottles;  
			    
	}
	

	/**
	 * Gets the brews from the database to be displayed on the ledger brews tab based on the user entered filters.
	 * 
	 * @return Returns the selected brews.
	 * @throws Exception
	 */
	public static Vector<Vector<Object>> getLedgerBrews() throws Exception {
	    Connection conn = dbConnection();
	    String BrewCostDatesFilterA = null;
	    String BrewCostDatesFilterB = null;
	    String BrewCostTotalCostFilterA = null;
	    String BrewCostTotalCostFilterB = null;
	    String BrewCostCostPerBottleFilterA = null;
	    String BrewCostCostPerBottleFilterB = null;
	    String BrewCostNumberBottlesFilterA = null;
	    String BrewCostNumberBottlesFilterB = null;
		
	    //Cover empty dates & costs
	    if(LedgerBrewCostSearchPanel.chooserLedgerBrewDatesFilterA.getDate() == null){
	    	BrewCostDatesFilterA = "1500/01/01";
	    } else{
	    	BrewCostDatesFilterA = Dates.dateToString(LedgerBrewCostSearchPanel.chooserLedgerBrewDatesFilterA.getDate());
	    }
	    if(LedgerBrewCostSearchPanel.chooserLedgerBrewDatesFilterB.getDate() == null){
	    	BrewCostDatesFilterB = "2500/01/01";
	    } else{
	    	BrewCostDatesFilterB = Dates.dateToString(LedgerBrewCostSearchPanel.chooserLedgerBrewDatesFilterB.getDate());
	    }
	    if(LedgerBrewCostSearchPanel.textLedgerBrewTotalCostA.getText() == null || LedgerBrewCostSearchPanel.textLedgerBrewTotalCostA.getText().equals("")){
	    	BrewCostTotalCostFilterA = "-9999999.0";
	    } else{
	    	BrewCostTotalCostFilterA = LedgerBrewCostSearchPanel.textLedgerBrewTotalCostA.getText().replaceAll("[^0-9\\.]", "");
	    }
	    if(LedgerBrewCostSearchPanel.textLedgerBrewTotalCostB.getText() == null || LedgerBrewCostSearchPanel.textLedgerBrewTotalCostB.getText().equals("")){
	    	BrewCostTotalCostFilterB = "9999999.0";
	    } else{
	    	BrewCostTotalCostFilterB = LedgerBrewCostSearchPanel.textLedgerBrewTotalCostB.getText().replaceAll("[^0-9\\.]", "");
	    }
	    if(LedgerBrewCostSearchPanel.textLedgerBrewCostPerBottleA.getText() == null || LedgerBrewCostSearchPanel.textLedgerBrewCostPerBottleA.getText().equals("")){
	    	BrewCostCostPerBottleFilterA = "-9999999.0";
	    } else{
	    	BrewCostCostPerBottleFilterA = LedgerBrewCostSearchPanel.textLedgerBrewCostPerBottleA.getText().replaceAll("[^0-9\\.]", "");
	    }
	    if(LedgerBrewCostSearchPanel.textLedgerBrewCostPerBottleB.getText() == null || LedgerBrewCostSearchPanel.textLedgerBrewCostPerBottleB.getText().equals("")){
	    	BrewCostCostPerBottleFilterB = "9999999.0";
	    } else{
	    	BrewCostCostPerBottleFilterB = LedgerBrewCostSearchPanel.textLedgerBrewCostPerBottleB.getText().replaceAll("[^0-9\\.]", "");
	    }
	    if(LedgerBrewCostSearchPanel.textLedgerBrewNumberBottlesA.getText() == null || LedgerBrewCostSearchPanel.textLedgerBrewNumberBottlesA.getText().equals("")){
	    	BrewCostNumberBottlesFilterA = "0";
	    } else{
	    	BrewCostNumberBottlesFilterA = LedgerBrewCostSearchPanel.textLedgerBrewNumberBottlesA.getText().replaceAll("[^0-9\\.]", "");
	    }
	    if(LedgerBrewCostSearchPanel.textLedgerBrewNumberBottlesB.getText() == null || LedgerBrewCostSearchPanel.textLedgerBrewNumberBottlesB.getText().equals("")){
	    	BrewCostNumberBottlesFilterB = "9999999";
	    } else{
	    	BrewCostNumberBottlesFilterB = LedgerBrewCostSearchPanel.textLedgerBrewNumberBottlesB.getText().replaceAll("[^0-9\\.]", "");
	    }
	    
	    String datefilter;
	    if(LedgerBrewCostSearchPanel.chooserLedgerBrewDatesFilterA.getDate() == null && LedgerBrewCostSearchPanel.chooserLedgerBrewDatesFilterB.getDate() == null){
    			datefilter = "%";	    		
	    	} else {
	    		datefilter = "%' and DateStarted >= '" + BrewCostDatesFilterA + "' and DateStarted <= '" + BrewCostDatesFilterB;
	    	}
	     
	    Vector<Vector<Object>> Brews = new Vector<Vector<Object>>();
	    PreparedStatement pre = conn.prepareStatement(
	    	"select BrewRef,BrewName,DateStarted,TotalCost,NumberBottles,CostPerBottle from Brews where BrewName like '%" +
	    	LedgerBrewCostSearchPanel.textLedgerBrewName.getText() +
	    	datefilter +	    	
	    	"' and TotalCost >= '" +
	    	BrewCostTotalCostFilterA +
	    	"' and TotalCost <= '" +
	    	BrewCostTotalCostFilterB +
	    	"' and CostPerBottle >= '" +
	    	BrewCostCostPerBottleFilterA +
	    	"' and CostPerBottle <= '" +
	    	BrewCostCostPerBottleFilterB +
	    	"' and NumberBottles >= '" +
	    	BrewCostNumberBottlesFilterA +
	    	"' and NumberBottles <= '" +
	    	BrewCostNumberBottlesFilterB +
	    	"' and InPlanning = '0' order by DateStarted desc"
			);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<Object> brew = new Vector<Object>();
		    brew.add(rs.getString(1)); //BrewRef
		    brew.add(rs.getString(2)); //BrewName
		    brew.add(rs.getString(3)); //DateStarted
		    brew.add(rs.getFloat(4)); //TotalCost
		    brew.add(rs.getString(5)); //NumberBottles
		    brew.add(rs.getFloat(6)); //CostPerBottle
		    Brews.add(brew);
	    }

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();

	    return Brews;	    
	    
	}
	
		
	/**
	 * Gets all of the information tab data from the database.
	 * 
	 * @return Returns the information tab data.
	 * @throws Exception
	 */
	public static Vector<Vector<Object>> getInformationTabData() throws Exception {
	    Connection conn = dbConnection();
	     
	    Vector<Vector<Object>> InformationTabs = new Vector<Vector<Object>>();
	    PreparedStatement pre = conn.prepareStatement(
	    		"select ID,TabName,TabContent,HTML from InformationTabs"
	    		);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<Object> tab = new Vector<Object>();
	    	tab.add(rs.getString(1)); //ID
	    	tab.add(rs.getString(2)); //TabName
	    	tab.add(rs.getString(3)); //TabContent
	    	tab.add(rs.getString(4)); //HTML
	    	InformationTabs.add(tab);
	    }

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();

	    return InformationTabs;	    
	    
	}
	
	
	/**
	 * Deletes the currently selected information tab data from the database.
	 * 
	 * @throws Exception
	 */
	public static void deleteInformationTabData() throws Exception {
	    Connection conn = dbConnection();
	     
	    PreparedStatement pre = conn.prepareStatement(
	    		"delete from InformationTabs where ID='" +
	    		InformationEditTab.textInformationOrigTabID +
	    		"'"
	    		);

	    pre.executeUpdate();

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	}
	
	
	/**
	 * Updates the currently selected information tab data in the database.
	 * 
	 * @throws Exception
	 */
	public static void updateInformationTabData() throws Exception {
	    Connection conn = dbConnection();
	    
	    int HTML;	    
	    if(InformationEditTab.checkboxInformationTabHTML.isSelected()){
	    	HTML = 1;
	    } else {
	    	HTML = 0;
	    }
	     
	    PreparedStatement pre = conn.prepareStatement(
	    		"update InformationTabs set ID='" +
	    		InformationEditTab.textInformationTabID.getText().replaceAll("[^0-9]", "") +
	    		"', TabName='" +
	    		InformationEditTab.textInformationTabName.getText().replaceAll("'", "''") +
	    		"', TabContent='" +
	    		InformationEditTab.textInformationTabContent.getText().replaceAll("'", "''") + 
	    		"', HTML='" +
	    		HTML +
	    		"' where ID='" +
	    		InformationEditTab.textInformationOrigTabID +
	    		"'"
	    		);

	    pre.executeUpdate();

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	}
	
	
	/**
	 * Adds new information tab data in the database.
	 * 
	 * @throws Exception
	 */
	public static void addInformationTabData() throws Exception {
	    Connection conn = dbConnection();
	     
	    int HTML;	    
	    if(InformationEditTab.checkboxInformationTabHTML.isSelected()){
	    	HTML = 1;
	    } else {
	    	HTML = 0;
	    }
	     
	    PreparedStatement pre = conn.prepareStatement(
	    		"insert into InformationTabs(ID,TabName,TabContent,HTML) values('" +
	    		InformationEditTab.textInformationTabID.getText().replaceAll("[^0-9]", "") +
	    		"','" +
	    		InformationEditTab.textInformationTabName.getText().replaceAll("'", "''") +
	    		"','" +
	    		InformationEditTab.textInformationTabContent.getText().replaceAll("'", "''") + 
	    		"','" +
	    		HTML +
	    		"')"
	    		);

	    pre.executeUpdate();

	    /*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	}
	
	
}
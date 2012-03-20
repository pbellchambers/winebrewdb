package com.pori.WineBrewDB.SQLite;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.pori.WineBrewDB.Dates;
import com.pori.WineBrewDB.MainWindow;
import com.pori.WineBrewDB.Brew.BrewAddPanel;
import com.pori.WineBrewDB.Brew.BrewDataPanel;
import com.pori.WineBrewDB.Brew.BrewNotesPanel;
import com.pori.WineBrewDB.Brew.BrewPicturesPanel;
import com.pori.WineBrewDB.Brew.BrewSearchPanel;
import com.pori.WineBrewDB.Ledger.LedgerBrewCostSearchPanel;
import com.pori.WineBrewDB.Ledger.LedgerEquipmentPanel;
import com.pori.WineBrewDB.Recipe.RecipeAddPanel;
import com.pori.WineBrewDB.Recipe.RecipeDataPanel;
import com.pori.WineBrewDB.Recipe.RecipeSearchPanel;

public class DBEngine {
	
	
	private static ImageIcon imageIconBrewPicture;


	//Create the connection
	public static Connection dbConnection() throws Exception {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + MainWindow.DatabaseLocationFromIni);	
		
	}
	
	
	//Get everything from Brews table
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
	    	Colour ="White','Red','Rosé','',' ','Other";
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
	
	
	//Update Brew
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
	
	
	//Add Brew
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
	
	
	//Delete Brew
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
	

	//Get Notes from Brew Notes table
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
	
	
	//Add Brew Note
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
	
	
	//Get next brew note ref
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
	
	
	//Update Brew Note
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
	
	
	//Delete Brew Note
	public static void deleteBrewNote() throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("delete from BrewNotes where BrewRef='" + BrewDataPanel.textBrewRefB.getText() + "' and BrewNoteRef='" + BrewNotesPanel.textBrewNoteRef.getText() + "'");

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	   if(conn!=null)
	  conn.close();  
		    
	}
		
		
	//Get Brew Picture Table Data
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
	
	
	//Get next brew picture ref
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
	
	
	//Update Brew Picture
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
	
	
	//Delete Brew Picture
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
	
	
	//Get Brew Picture from DB
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
	
	
	//Insert Brew Picture
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
	
	
	//Get Bytes From File
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
	        throw new IOException("Could not completely read file "+file.getName());
	    }
	    
	    // Close the input stream and return bytes
	    is.close();
	    return bytes;
	}
	
		
	//Write to file
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
	

	//Scaled Image Icon
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
	
	
	//Get everything from Recipes table
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


	//Update Recipe
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


	//Add Recipe
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


	//Delete Recipe
	public static void deleteRecipe() throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("delete from Recipes where RecipeRef='" + RecipeDataPanel.textRecipeRefB.getText() + "'");

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
	    
	}
	
	
	
	//Get Costs from Brew Costs table
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


	//Add Brew Cost
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


	//Get next brew cost ref
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


	//Update Brew Cost
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


	//Delete Brew Cost
	public static void deleteBrewCost(String brewref, String brewcostref) throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("delete from BrewCosts where BrewRef='" + brewref + "' and BrewCostRef='" + brewcostref + "'");

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	   if(conn!=null)
	  conn.close();  
		    
	}
	
	
	//Set total brew cost
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
			//Add one to the current highest
			CostPerBottle = rs.getFloat(1);			
		}
		
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    
	    NumberFormat nf = NumberFormat.getCurrencyInstance();
	    	    
		return nf.format(CostPerBottle).toString();  
			    
	}

	
	//Get Costs from Equipment Costs table
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


	//Add Equipment Cost
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


	//Update Equipment Cost
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


	//Delete Equipment Cost
	public static void deleteEquipmentCost() throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("delete from EquipmentCosts where EquipmentCostRef='" + LedgerEquipmentPanel.textLedgerEquipmentCostRef.getText() + "'");

		pre.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	   if(conn!=null)
	  conn.close();  
		    
	}
	
	//Get Total Brews
	public static BigDecimal getTotalNumberBrews() throws Exception {
		Connection conn = dbConnection();
				
		PreparedStatement pre = conn.prepareStatement(
			"SELECT Count(*) from Brews where InPlanning = '0'"
		);

		
		ResultSet rs = pre.executeQuery();
		int NumberBrews = 0;
		
		while(rs.next()){
			//Add one to the current highest
			NumberBrews = rs.getInt(1);			
		}
		
		BigDecimal DecimalNumberBrews = new BigDecimal(NumberBrews);
		
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    	    
		return DecimalNumberBrews;  
			    
	}
	
	
	//Get Total Bottles
	public static BigDecimal getTotalNumberBottles() throws Exception {
		Connection conn = dbConnection();
				
		PreparedStatement pre = conn.prepareStatement(
			"SELECT Sum(NumberBottles) from Brews where InPlanning = '0'"
		);

		
		ResultSet rs = pre.executeQuery();
		int NumberBottles = 0;
		
		while(rs.next()){
			//Add one to the current highest
			NumberBottles = rs.getInt(1);			
		}
		
		BigDecimal DecimalNumberBottles = new BigDecimal(NumberBottles);
		
	    
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();
	    	    
		return DecimalNumberBottles;  
			    
	}
	

	//Get ledger brews
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
	
	
	
}
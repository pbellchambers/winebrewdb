package com.pori.WineBrewDB.SQLite;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.pori.WineBrewDB.BrewAddPanel;
import com.pori.WineBrewDB.BrewDataPanel;
import com.pori.WineBrewDB.BrewNotesPanel;
import com.pori.WineBrewDB.BrewSearchPanel;
import com.pori.WineBrewDB.Dates;

public class DBEngine {
	
	public static String Colour;
	public static String ThumbsUp;
	public static String InPlanning;
	public static String InFermenting;
	public static String InFining;
	public static String InMaturing;
	public static String InBottles;
	public static String Drunk;
	private static String InPlanningB;
	private static String InFermentingB;
	private static String InFiningB;
	private static String InMaturingB;
	private static String InBottlesB;
	private static String DrunkB;
	private static String InPlanningAdd;
	private static String InFermentingAdd;
	private static String InFiningAdd;
	private static String InMaturingAdd;
	private static String InBottlesAdd;
	private static String DrunkAdd;
	private static int NextBrewNoteRef;
	private static int MaxBrewNoteRef;
	private static String BrewDatePlannedAddB;
	private static String BrewDateStartedAddB;
	private static String BrewDateBottledAddB;
	private static String BrewDatePlannedUpdateB;
	private static String BrewDateStartedUpdateB;
	private static String BrewDateBottledUpdateB;
	private static String BrewNoteDateAddB;
	private static String BrewNoteDateUpdateB;
	private static ImageIcon imageIconBrewPicture;
	private static Image imageBrewPicture;
	private static Image imageScaledBrewPicture;
	public static ImageIcon imageIconScaledBrewPicture;
	
	
	//Create the connection
	public static Connection dbConnection() throws Exception {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:WineBrewDBData.sqlite");
	}
	
	
	//Get everything from Brews table
	public static Vector<Vector<String>> getBrews() throws Exception {
	    Connection conn = dbConnection();
	      
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
	     
	    Vector<Vector<String>> Brews = new Vector<Vector<String>>();
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
	    	"')"
			);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<String> brew = new Vector<String>();
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
    		"insert into Brews(BrewName,Colour,RecipeFrom,ThumbsUp,DatePlanned,DateStarted,DateBottled,StartSG,StartAdjustedSG,EndSG,AimedABV,FinalABV,FinalAdjustedABV,Yeast,VolumeMade,InPlanning,InFermenting,InFining,NumberBottles,InMaturing,InBottles,Drunk,TastingNotes,Notes) values('" +
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

		pre.executeUpdate();
		pre2.executeUpdate();
		pre3.executeUpdate();
		
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
	    
	}
	

	//Get Notes from Brew Notes table
	public static Vector<Vector<String>> getBrewNotes() throws Exception {
	    Connection conn = dbConnection();
	     
	    Vector<Vector<String>> BrewNotes = new Vector<Vector<String>>();
	    PreparedStatement pre = conn.prepareStatement(
	    	"select BrewNoteRef,Date,DaysSinceStart,Incident,Notes from BrewNotes where BrewRef='" + 
	    	BrewDataPanel.textBrewRefB.getText() +
	    	"' order by BrewNoteRef asc"
			);

	    ResultSet rs = pre.executeQuery();

	    while(rs.next()){
	    Vector<String> brewnote = new Vector<String>();
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

		MaxBrewNoteRef = 0;
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
		
		
	//Delete Brew Picture
	public static void deleteBrewPicture() throws Exception {
		Connection conn = dbConnection();
			
		PreparedStatement pre = conn.prepareStatement("delete from BrewPictures where BrewRef='" + BrewDataPanel.textBrewRefB.getText() + "' and BrewPictureRef='" + "'");

		pre.executeUpdate();
			
		/*Close the connection after use (MUST)*/
	    if(conn!=null)
	    conn.close();  
		    
	}
	
		
	//Get Brew Picture
	public static void getBrewPicture() throws Exception {
		Connection conn = dbConnection();
		
		PreparedStatement pre = conn.prepareStatement("select Picture from BrewPictures where RowID='12'");

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
		
		PreparedStatement pre = conn.prepareStatement("insert into BrewPictures(Picture,BrewRef,BrewPicRef) values(?,'1','30')");
		 
		pre.setBytes(1, getBytesFromFile(new File("C:\\workspace\\WineBrewDB\\src\\com\\pori\\WineBrewDB\\Images\\winebrewdb256.png")));
		
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
	
	
	//Scaled Image Icon
	public static ImageIcon scaledImageIcon(int width, int height) {
		imageBrewPicture = imageIconBrewPicture.getImage();
		System.out.println(imageIconBrewPicture.getIconWidth());
		System.out.println(imageIconBrewPicture.getIconHeight());
        imageScaledBrewPicture = imageBrewPicture.getScaledInstance(width, height, Image.SCALE_SMOOTH); 
        imageIconScaledBrewPicture = new ImageIcon(imageScaledBrewPicture);
		return imageIconScaledBrewPicture;
		
	}
	
	
}
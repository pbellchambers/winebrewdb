package com.pori.WineBrewDB.SQLite;

import java.sql.*;
import java.util.Vector;

import com.pori.WineBrewDB.BrewPanel;

public class DBEngine {
	
	public static String Colour;
	public static String ThumbsUp;
	public static String InPlanning;
	public static String InFermenting;
	public static String InFining;
	public static String InMaturing;
	public static String InBottles;
	public static String Drunk;
	
	//Create the connection
	public static Connection dbConnection() throws Exception {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:WineBrewDBData.sqlite");
	}
	
	//Get everything from Brews table
	public static Vector<Vector<String>> getBrews() throws Exception {
	    Connection conn = dbConnection();
	      
	    if(BrewPanel.comboColour.getSelectedItem().equals("Any")){
	    	Colour ="White','Red','Ros�','','Other";
	    } else {
	    	Colour = (String) BrewPanel.comboColour.getSelectedItem();
	    }
	    
	    if(BrewPanel.comboThumbs.getSelectedItem().equals("Any")){
	    	ThumbsUp ="Up','Middle','','Down";
	    } else {
	    	ThumbsUp = (String) BrewPanel.comboThumbs.getSelectedItem();
	    }
	    
	    if(BrewPanel.chckbxInPlanning.isSelected()){
	    	InPlanning = "1";
	    } else {
	    	InPlanning = "3";
	    }
	    
	    if(BrewPanel.chckbxInFermenting.isSelected()){
	    	InFermenting = "1";
	    } else {
	    	InFermenting = "3";
	    }
	    
	    if(BrewPanel.chckbxInFining.isSelected()){
	    	InFining = "1";
	    } else {
	    	InFining = "3";
	    }
	    
	    if(BrewPanel.chckbxInMaturing.isSelected()){
	    	InMaturing = "1";
	    } else {
	    	InMaturing = "3";
	    }
	    
	    if(BrewPanel.chckbxInBottles.isSelected()){
	    	InBottles = "1";
	    } else {
	    	InBottles = "3";
	    }
	    
	    if(BrewPanel.chckbxDrunk.isSelected()){
	    	Drunk = "1";
	    } else {
	    	Drunk = "3";
	    }
	     
	    Vector<Vector<String>> Brews = new Vector<Vector<String>>();
	    PreparedStatement pre = conn.prepareStatement(
	    		"select * from Brews where BrewName like '%" + 
	    		BrewPanel.textBrewName.getText() + 
	    		"%' and RecipeFrom like '%" + 
	    		BrewPanel.textRecipeFrom.getText() + 
	    		"%' and Yeast like '%" + 
	    		BrewPanel.textYeast.getText() + 
	    		"%' and Notes like '%" + 
	    		BrewPanel.textNotes.getText() + 
	    		"%' and TastingNotes like '%" + 
	    		BrewPanel.textTastingNotes.getText() + 
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
	    		"')");

	    ResultSet rs = pre.executeQuery();

	    while(rs.next())
	    {
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
	
    //Statement stat = conn.createStatement();
    //stat.executeUpdate("drop table if exists people;");
    //stat.executeUpdate("create table people (name, occupation);");
     
    //PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?);");

    //prep.setString(1, "Gandhi2");
    //prep.setString(2, "politics2");
    //prep.addBatch();
    //prep.setString(1, "Turing2");
    //prep.setString(2, "computers2");
    //prep.addBatch();
    //prep.setString(1, "Wittgenstein2");
    //prep.setString(2, "smartypants2");
    //prep.addBatch();

    //conn.setAutoCommit(false);
    //prep.executeBatch();
    //conn.setAutoCommit(true);

    //ResultSet rs = stat.executeQuery("select * from people;");
    //while (rs.next()) {
     	//System.out.println("name = " + rs.getString("name"));
     	//System.out.println("job = " + rs.getString("occupation"));
    //}
    //rs.close();
    //conn.close();	
	
	
}
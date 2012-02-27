package com.pori.WineBrewDB.SQLite;

import java.sql.*;
import java.util.Vector;

public class DBEngine {
	
	//Create the connection
	public static Connection dbConnection() throws Exception {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:WineBrewDBData.sqlite");
	}
	
	//Get everything from Brews table
	public static Vector<Vector<String>> getBrews() throws Exception {
	    Connection conn = dbConnection();
	    
	    Vector<Vector<String>> Brews = new Vector<Vector<String>>();
	    PreparedStatement pre = conn.prepareStatement("select * from Brews");

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
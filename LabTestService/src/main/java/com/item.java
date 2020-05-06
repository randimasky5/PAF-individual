package com;

import java.sql.*;


public class item {

	private Connection connect(){
		
	Connection con = null;
			try
				{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare","root","");
				}
			catch (Exception e){
				e.printStackTrace();
			}
	return con;
	
	}
	
	//insert-----------------------------------------------------------------------------------------------------------------------------
	
	public String insertItem(String name, String price, String desc , String labNo , String hosName)
    {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{    
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = "insert into testsnrooms"
						+"(`test_name`, `test_cost`, `test_desc`, `room_no`, `hospital_name`)"
						 + " values ( ?, ?, ?, ?, ? )";
						PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				//preparedStmt.setInt(1, 0);
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, price);
				preparedStmt.setString(3, desc); 
				preparedStmt.setString(4, labNo);
				preparedStmt.setString(5, hosName);
	
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newItems = readItems();
				output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			}
				catch (Exception e)
			{
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
					System.err.println(e.getMessage());
			}
			return output;
    		}
	
	
	//view------------------------------------------------------------------------------------------------------------------------
	public String readItems(){
		
		String output = "";
			try{
			Connection con = connect();
			
			if (con == null){
				return "Error while connecting to the database for reading."; 
			}
			// Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr><th>Test Name</th>"
					+ "<th>Test Price</th>"
					+ "<th>Test Description</th>"
					+ "<th>Lab No </th>"
					+ "<th>Hospital  Name</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";
			
			String query = "select * from testsnrooms";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()){
				
				String testID = Integer.toString(rs.getInt("test_id"));
				String testName = rs.getString("test_name");
				String LabNo = rs.getString("room_no");
				String testCost = Double.toString(rs.getDouble("test_cost"));
				String testDesc = rs.getString("test_desc");
				String HosName = rs.getString("hospital_name");

				
				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate' type='hidden' value='" + testID+ "'>" 
				+ testName + "</td>";
				
				output += "<td>" + testCost + "</td>";
				output += "<td>" + testDesc + "</td>";
				output += "<td>" + LabNo + "</td>";
				output += "<td>" + HosName + "</td>";
				 // buttons
				
				output += "<td><input name='btnUpdate'type='button' "
						+ "value='Update'class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove'type='button' "
						+ "value='Remove'class='btnRemove btn btn-danger'data-itemid='"+ testID + "'>" + "</td></tr>";
			}
			
			con.close();
			// Complete the html table
			output += "</table>";
			}
			catch (Exception e){
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			
	return output;
	
	}
	
	
	//update----------------------------------------------------------------------------------------------------------------
	public String updateItem(String ID, String name, String cost, String desc, String labno, String hosName) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE testsnrooms SET test_name=?,test_cost=?,test_desc=?,room_no=?,hospital_name=? WHERE test_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			
			preparedStmt.setString(2, cost);
			preparedStmt.setString(3, desc);
			preparedStmt.setString(4, labno);
			preparedStmt.setString(5, hosName);
			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";;
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//delete-------------------------------------------------------------------------------------------------------------------
	public String deleteItem(String testID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {

				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from testsnrooms where test_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(testID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
}

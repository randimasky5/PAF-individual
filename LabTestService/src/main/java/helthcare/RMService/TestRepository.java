package helthcare.RMService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import utility.connectionBuilder;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


public class TestRepository {
	 //Connection con = null;
	 connectionBuilder cb = new connectionBuilder();
		Connection con = cb.connect();
	List<Test> tests;
	
	public TestRepository() {

		
	/*	try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}	*/
		
		
	}
	
	//public List<Test> getTests(){
	public String getTests(){
		String output ="";
		output = "<table border=\"1\">"
				+ "<tr><th>Item Code</th><th>Item Name</th><th>Item Price</th>"
				+ "<th>Item Description</th><th>Update</th><th>Remove</th></tr>";
		List<Test> tests = new ArrayList<>();
		String sql = "Select * from testsnrooms";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
			/*	Test t = new Test();
				t.setTest_id(rs.getInt(1));
				t.setTest_name(rs.getString(2));
				t.setTest_cost(rs.getString(3));
				t.setTest_desc(rs.getString(4));
				t.setRoom_no(rs.getString(5));
				t.setHospital_name(rs.getString(6));
				
				tests.add(t);*/
				
				String id = rs.getString(1);
				String name = rs.getString(2);
				String cost= rs.getString(3);
				String des= rs.getString(4);
				String room= rs.getString(5);
				String hos= rs.getString(6);
				
			

				output +=  "<tr><td><input id='hidItemIDUpdate' "
						+ "name='hidItemIDUpdate' type='hidden' "
						+ "value='" + id + "'>" 
						+ name + "</td>";
				output += "<td>" + cost + "</td>";
				output += "<td>" + des + "</td>";
				output += "<td>" + room + "</td>";
				output += "<td>" + hos + "</td>";
				
				// Add into the html table
				
				// buttons
				output += "<td><input name='btnUpdate' type='button'value='Update1'"
						+ "class='btnUpdate btn btn-secondary'></td><td>"
						+ "	<form action = 'TestServlet' method='post' ><Button name='btnRem' type='submit'  >Remove</button>"
								+ "<input type ='hidden' name = 'btnremove' value='" + id + "' ></form>" + "</td></tr>";
				
				
			
		}
			output += "</table>";}
		catch(Exception e) {
			System.out.println(e);
		}
		return output;
	}
	
	public String getTest(int test_id) {
		String output ="";
		output = "<table border=\"1\">"
				+ "<tr><th>Item Code</th><th>Item Name</th><th>Item Price</th>"
				+ "<th>Item Description</th><th>Update</th><th>Remove</th></tr>";
		String sql = "Select * from testsnrooms where test_id ="+test_id;
		Test t = new Test();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			System.out.println(test_id);
			if(rs.next()) {
				
			/*	t.setTest_id(rs.getInt(1));
				t.setTest_name(rs.getString(2));
				t.setTest_cost(rs.getString(3));
				t.setTest_desc(rs.getString(4));
				t.setRoom_no(rs.getString(5));
				t.setHospital_name(rs.getString(6));*/
				String id = rs.getString(1);
				String name = rs.getString(2);
				String cost= rs.getString(3);
				String des= rs.getString(4);
				String room= rs.getString(5);
				String hos= rs.getString(6);
				
			
				output +=  "<tr><td><input id='hidItemIDUpdate' "
						+ "name='hidItemIDUpdate' type='hidden' "
						+ "value='" + id + "'>" 
						+ name + "</td>";
				output += "<td>" + cost + "</td>";
				output += "<td>" + des + "</td>";
				output += "<td>" + room + "</td>";
				output += "<td>" + hos + "</td>";
				
				// Add into the html table
				
				// buttons
				output += "<td><input name='btnUpdate' type='button'value='Update'"
						+ "class='btnUpdate btn btn-secondary'></td><td>"
						+ "<form><input name='btnRemove' type='button' value='"
						+ "Remove'class='btnRemove btn btn-danger' value='" + id + "'></form>" + "</td></tr>";
				
				output += "</table>";}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return output;
	}

	public String insertTest(String test_name, String test_cost, String test_desc, String room_no,String hospital_name) {
		String sql = "Insert into testsnrooms (test_name,test_cost,test_desc,room_no,hospital_name) values (?,?,?,?,?) ";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,test_name);
	
			st.setString(2,test_cost);
			st.setString(3,test_desc);
			st.setString(4,room_no);
			st.setString(5,hospital_name);
				
			st.execute();
				
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "inserted";
	}
	
	public String updateTest(Test t1) {
		String sql = "update testsnrooms set test_name=?,test_cost=?,test_desc=?,room_no=?,hospital_name=? where test_id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,t1.getTest_name());
			st.setString(2,t1.getTest_cost());
			st.setString(3,t1.getTest_desc());
			st.setString(4,t1.getRoom_no());
			st.setString(5,t1.getHospital_name());
			st.setInt(6,t1.getTest_id());
			//st.setInt(6,Integer.parseInt(id));
			st.executeUpdate();
			//System.out.println(id);	
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "Updated...";
	}

	public String  deleteTest(String itemID) {
		String sql = "delete from testsnrooms where test_id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,Integer.parseInt(itemID));
			
			st.execute();
				System.out.println(itemID);
			
		}
		catch(Exception e) {
			System.out.println(e);
			System.err.println(e.getMessage());
			System.out.println(Integer.parseInt(itemID));
		}
		return "called delete method";
	}
	
	
	public String deleteItem(String itemID)
	{
	String output = "";
	try
	{
		connectionBuilder cb = new connectionBuilder();
	Connection con = cb.connect();
	if (con == null)
	{
	return "Error while connecting to the database for deleting.";
	}
	// create a prepared statement
	String query = "delete from testsnrooms where test_id=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(itemID));
	// execute the statement
	System.out.println(itemID);
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = "Error while deleting the item.";
	System.err.println(e.getMessage());
	
	}
	return output;
	}
	
	public  String delete(String id)
	{
	String output = "";
	try
	{
	
	if (con == null)
	{return "Error while connecting to the database for deleting."; }
	// create a prepared statement
	String query = "delete from testsnrooms where test_id=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(id));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = "Error while deleting the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	}


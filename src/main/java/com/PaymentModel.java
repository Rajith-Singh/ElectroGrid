package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PaymentModel {
	

		
		private Connection connect() 
		 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.cj.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root",""); 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
		 } 
		public String insertPaymentService(String accountType, String amount, String accountNo) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 // create a prepared statement
		 String query = " insert into paymentadding (`id`,`accountType`,`amount`,`accountNo`)"
		 + " values (?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, accountType); 
		 preparedStmt.setString(3, amount); 
		 preparedStmt.setString(4, accountNo); 


		 // execute the statement
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the Details"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		public String readService() 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>User Id</th>"+
	     "<th>AccountType</th>" +
		 "<th>Amount</th>" + 
		 "<th>AccountNo</th>" +

		 "<th>Update</th>"
		 + "<th>Remove</th></tr>"; 
		 
		 String query = "select * from  paymentadding"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String id = Integer.toString(rs.getInt("id")); 
		 String accType = rs.getString("accountType"); 
		 String amount = rs.getString("amount"); 
		 String accNo = rs.getString("accountNo");

		 
		 // Add into the html table
		 output += "<tr><td>" + id + "</td>"; 
		 output += "<td>" + accType + "</td>"; 
		 output += "<td>" + amount + "</td>"; 
		 output += "<td>" + accNo + "</td>";

		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + id 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the Details"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }

		public String updatePaymentService(String id, String accountType, String amount, String accountNo) {
			String output = "";
			try {
			Connection con = connect();
			if (con == null) {
			return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE paymentadding SET accountType=?, amount=?, accountNo=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, accountType);
			preparedStmt.setString(2, amount);
			preparedStmt.setString(3, accountNo);
			preparedStmt.setInt(4, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			} catch (Exception e) {
			output = "Error while updating the details";
			System.err.println(e.getMessage());
			}
			return output;
			}
		
		public String deletePaymentService(String id) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from paymentadding where id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1,Integer.parseInt(id)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the details"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }

	}

	
	


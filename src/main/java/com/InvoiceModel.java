package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InvoiceModel {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertService(String cus_nic, String month, Double unit_calculation) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "insert into invoice (`id`,`cus_nic`,`month`,`unit_calculation`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cus_nic);
			preparedStmt.setString(3, month);
//			preparedStmt.setDouble(4, unit_calculation);
			
			if (unit_calculation <= 0) {
				preparedStmt.setDouble(4, unit_calculation*0);
			}
			else if (unit_calculation <= 66) {
				preparedStmt.setDouble(4, unit_calculation*7.85);
			}
			else if (unit_calculation <= 99) {
				preparedStmt.setDouble(4, 66*7.85 + ((unit_calculation-66)*10));
			}
			else if (unit_calculation <= 132) {
				preparedStmt.setDouble(4, 66*7.85 + (33*10 + ((unit_calculation-99)*22.75)));
			}
			else if (unit_calculation <= 198) {
				preparedStmt.setDouble(4, 66*7.85 + (33*10 + (33*27.75 + ((unit_calculation-132)*32))));
			}
			else {
				preparedStmt.setDouble(4, 66*7.85 + (33*10 + (33*27.75 + (66*32 + ((unit_calculation-198)*45)))));
			}
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Details";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readService() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed
			output = "<table border='1'>"
					+ "<th> ID </th>"
					+ "<th> Name </th>"
					+ "<th> Address </th>"
					+ "<th>Customer NIC</th>"
					+ "<th>Month</th>" 
					+ "<th>Total Amount</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";

			String query = "select p.address, p.name, i.id, i.cus_nic, i.month, i.unit_calculation from person p, invoice i where p.nic = i.cus_nic";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String name = rs.getString("p.name");
				String address = rs.getString("p.address");;				
				String cus_nic = rs.getString("i.cus_nic");
				String month = rs.getString("i.month");
				String unit_calculation = rs.getString("i.unit_calculation");
				// Add into the HTML table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + cus_nic + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + unit_calculation + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + id + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Details";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readByID(String cus_nic) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed
			output = "<table border='1'>"
					+ "<th> Name </th>"
					+ "<th> Address </th>"
					+ "<th>Month</th>" 
					+ "<th>Total Amount</th></tr>";

			String query = "select * from person p, invoice i where p.nic = i.cus_nic and i.cus_nic = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,cus_nic);
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
			ResultSet rs = ps.executeQuery();
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String name = rs.getString("name");
				String address = rs.getString("address");;				
//				String cus_nic = rs.getString("cus_nic");
				String month = rs.getString("month");
				String unit_calculation = rs.getString("unit_calculation");
				// Add into the HTML table
				output += "<tr><td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + unit_calculation + "</td>";
				// buttons
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Details";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateService(String id, String cus_nic, String month, Double unit_calculation) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE invoice SET cus_nic=?, month=?, unit_calculation=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, cus_nic);
			preparedStmt.setString(2, month);
//			preparedStmt.setDouble(3, unit_calculation);
			
			if (unit_calculation <= 0) {
				preparedStmt.setDouble(3, unit_calculation*0);
			}
			else if (unit_calculation <= 66) {
				preparedStmt.setDouble(3, unit_calculation*7.85);
			}
			else if (unit_calculation <= 99) {
				preparedStmt.setDouble(3, 66*7.85 + ((unit_calculation-66)*10));
			}
			else if (unit_calculation <= 132) {
				preparedStmt.setDouble(3, 66*7.85 + (33*10 + ((unit_calculation-99)*22.75)));
			}
			else if (unit_calculation <= 198) {
				preparedStmt.setDouble(3, 66*7.85 + (33*10 + (33*27.75 + ((unit_calculation-132)*32))));
			}
			else {
				preparedStmt.setDouble(3, 66*7.85 + (33*10 + (33*27.75 + (66*32 + ((unit_calculation-198)*45)))));
			}
			
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

	
	public String deleteService(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from invoice where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the details";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String readBlackList() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed
			output = "<table border='1'>"
					+ "<th> Name </th>"
					+ "<th> Address </th>"
					+ "<th> Customer NIC </th>"
					+ "<th> No.of Months </th>"
					+ "<th> Total Amount </th></tr>";

			String query = "SELECT p.name, p.nic, p.address, i.cus_nic, COUNT(i.cus_nic) AS noOfMonths, SUM(i.unit_calculation) as totAmount FROM person p INNER JOIN invoice i ON p.nic = i.cus_nic GROUP BY p.name, p.nic, p.address HAVING noOfMonths > 2";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String name = rs.getString("p.name");
				String address = rs.getString("p.address");;				
				String cus_nic = rs.getString("i.cus_nic");
				String noOfMonths = rs.getString("noOfMonths");
				String totAmount = rs.getString("totAmount");
				// Add into the HTML table
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + cus_nic + "</td>";
				output += "<td>" + noOfMonths + "</td>";
				output += "<td>" + totAmount + "</td>";
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Details";
			System.err.println(e.getMessage());
		}
		return output;
	}

}

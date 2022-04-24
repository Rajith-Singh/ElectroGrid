package org.restapi.crud.crud.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.restapi.crud.crud.model.unitmodel;

public class unitservice {

	
	
	Connection con;
	
	public unitservice() {
		try { 
			String url = String.format("jdbc:mysql://localhost:3306/electrogrid");
			String uname ="root";
			String pwd = "";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,pwd);
		} catch(Exception e) {
			System.out.println(e+"data insert unsuccess.");
		}
	}
	
	public unitmodel insertUnit(unitmodel unit) {
		String insert = "insert into unit(unit_id,no_of_units,unit_price) values(?,?,?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, unit.getUnit_id());
			ps.setInt(2, unit.getNo_of_units());
			ps.setDouble(3, unit.getUnit_price());
			
			ps.execute();
		}catch(Exception e) {
			System.out.println(e +"data insert unsuccess");
		}
		
		return unit;
	}
	
//	public ArrayList<crudmodel> getUser() throws SQLException{
//	
//		ArrayList<crudmodel> data = new ArrayList<crudmodel>();
//		
//		String select = "select * from person";
//		PreparedStatement ps = con.prepareStatement(select);
//		ResultSet rs = ps.executeQuery();
//		
//		while(rs.next()) {
//			crudmodel model = new crudmodel();
//			
//			model.setId(rs.getInt("id")); //column name
//			model.setName(rs.getString("name")); 
//			model.setAge(rs.getInt("age"));
//			
//			data.add(model);
//			
//		}
//		
//		return data;
//	}
//	
//	
//	public ArrayList<crudmodel> getUserById(int id) throws SQLException{
//		
//		ArrayList<crudmodel> data = new ArrayList<crudmodel>();
//		
//		String select = "select * from person where id =?";
//		PreparedStatement ps = con.prepareStatement(select);
//		ps.setInt(1,id);
//		ResultSet rs = ps.executeQuery();
//		
//		while(rs.next()) {
//			crudmodel model = new crudmodel();
//			
//			model.setId(rs.getInt("id")); //column name
//			model.setName(rs.getString("name"));
//			model.setAge(rs.getInt("age"));
//			
//			data.add(model);
//			
//		}
//		
//		return data;
//	}
//	
//	
//	public crudmodel updateUser(crudmodel user) {
//		String insert = "update person set name=?, age=? where id=?";
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(insert);
//			ps.setInt(1, user.getId());
//			ps.setString(2, user.getName());
//			ps.setLong(3, user.getAge());
//			
//			ps.executeUpdate();
//		}catch(Exception e) {
//			System.out.println(e +"data update unsuccess");
//		}
//		
//		return user;
//	}
	
//	public crudmodel updateUser(crudmodel user) {
//		String insert = "update person set name=?, age=? where id=?";
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(insert);
//			ps.setInt(1, user.getId());
//			ps.setString(2, user.getName());
//			ps.setLong(3, user.getAge());
//			
//			ps.executeUpdate();
//		}catch(Exception e) {
//			System.out.println(e +"data update unsuccess");
//		}
//		
//		return user;
//	}
	
	
	
	
	
	
	
	
}

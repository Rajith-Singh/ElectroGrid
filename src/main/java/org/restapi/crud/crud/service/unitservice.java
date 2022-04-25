package org.restapi.crud.crud.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.restapi.crud.crud.model.calcmodel;
import org.restapi.crud.crud.model.crudmodel;
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
	
	public ArrayList<unitmodel> getUnit() throws SQLException{
	
		ArrayList<unitmodel> data = new ArrayList<unitmodel>();
		
		String select = "select * from unit";
		PreparedStatement ps = con.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			unitmodel model = new unitmodel();
			
			model.setUnit_id(rs.getString("unit_id")); //column name
			model.setNo_of_units(rs.getInt("no_of_units")); 
			model.setUnit_price(rs.getDouble("unit_price"));
			
			data.add(model);
			
		}
		
		return data;
	}
	
	
	public ArrayList<calcmodel> getUnitsById(int id) throws SQLException{
		
		ArrayList<calcmodel> data = new ArrayList<calcmodel>();
		
		String select = "select * from consumption where id =?";
		PreparedStatement ps = con.prepareStatement(select);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			calcmodel model = new calcmodel();
			
			model.setUnits(rs.getInt("consumeUnits")); //column name
			
			data.add(model);
			
		}
		System.out.println(data);

		return data;
	}
	
//	public ArrayList<calcmodel> getUnitsById(int id) throws SQLException{
//		
//		ArrayList<calcmodel> data = new ArrayList<>();
//		
//		String select = "select * from consumption where id =?";
//		PreparedStatement ps = con.prepareStatement(select);
//		ps.setInt(1,id);
//		ResultSet rs = ps.executeQuery();
//		
//		if(rs.next()) {
//			int consumeunits = rs.getInt(5);
//			
//			CalculateFactoty fac =new CalculateFactoty();
//			UnitCalculation obj = fac.getUnitCalculation(consumeunits);
//			double answ = obj.UnitCalculation(consumeunits);
//			calcmodel data = new calcmodel(answ);
//			
//			data.add(data);
//		}
//		System.out.println(data);
//
//		return data;
//	}
	
	
//	public ArrayList<calcmodel> getUnitsById(int id) throws SQLException{
//	
//	ArrayList<calcmodel> data = new ArrayList<calcmodel>();
//	
//	String select = "select * from consumption where id =?";
//	PreparedStatement ps = con.prepareStatement(select);
//	ps.setInt(1,id);
//	ResultSet rs = ps.executeQuery();
//	
//	if(rs.next()) {
//		calcmodel model = new calcmodel();
//		
//		model.setUnits(rs.getInt("consumeUnits")); //column name
//		
//		data.add(model);
//		
//	}
//	System.out.println(data);
//
//	return data;
//}
	
	
	
	
	
	
	
	
	
	
	
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

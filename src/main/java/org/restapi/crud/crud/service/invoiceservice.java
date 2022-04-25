package org.restapi.crud.crud.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.restapi.crud.crud.model.invoicemodel;
import org.restapi.crud.crud.model.unitmodel;

public class invoiceservice {

	Connection con;
	
	public invoiceservice() {
		try { 
			String url = String.format("jdbc:mysql://localhost:3306/electrogrid");
			String uname ="root";
			String pwd = "";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,pwd);
		} catch(Exception e) {
			System.out.println(e+"database connection unsuccess.");
		}
	}
	
	
	
//	public invoicemodel insertInvoice(invoicemodel invoice) {
//		String insert = "insert into invoice(invoice_id,cus_nic,month,no_of_units) values(?,?,?,?)";
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(insert);
//			ps.setString(1, invoice.getInvoice_id());
//			ps.setString(2, invoice.getCus_nic());
//			ps.setString(3, invoice.getMonth());
//			ps.setInt(4, invoice.getNo_of_units());


	
	
	
	
	
	
	
	public invoicemodel insertInvoice(invoicemodel invoice) {
		String insert = "insert into invoice(invoice_id,cus_nic,month,unit_calculation) values(?,?,?,?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, invoice.getInvoice_id());
			ps.setString(2, invoice.getCus_nic());
			ps.setString(3, invoice.getMonth());

			if (invoice.getUnit_calculation()<=0) {
				ps.setDouble(4, invoice.getUnit_calculation()*0);
			} 
			
			else if (invoice.getUnit_calculation()<=66) {
				ps.setDouble(4, invoice.getUnit_calculation()*7.85);
			}
			
			else if (invoice.getUnit_calculation()<=99) {
				ps.setDouble(4, (66*7.85 + ((invoice.getUnit_calculation()-66)*10)));	
			} 
			
			else if (invoice.getUnit_calculation()<=132) {
				ps.setDouble(4, (66*7.85 + (33*10 + ((invoice.getUnit_calculation()-99)*27.75))));	
			} 
			
			else if (invoice.getUnit_calculation()<=198) {
				ps.setDouble(4, (66*7.85 + (33*10 + (33*27.75 + ((invoice.getUnit_calculation()-132)*32)))));
			}
			
			else {
				ps.setDouble(4, (66*7.85 + (33*10 + (33*27.75 + (66*32 + ((invoice.getUnit_calculation()-198)*45))))));
			}
			
			ps.execute();
		}catch(Exception e) {
			System.out.println(e +"data insert unsuccess");
		}
		
		return invoice;
	}
	
	
	
}



package org.restapi.crud.crud.model;

public class invoicemodel {
	
	private String invoice_id;
	private String cus_nic;
	private String month;
	private double unit_calculation;	
	public invoicemodel() {
		
	}
	public invoicemodel(String invoice_id, String cus_nic, String month, double unit_calculation) {
		super();
		this.invoice_id = invoice_id;
		this.cus_nic = cus_nic;
		this.month = month;
		this.unit_calculation = unit_calculation;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getCus_nic() {
		return cus_nic;
	}
	public void setCus_nic(String cus_nic) {
		this.cus_nic = cus_nic;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getUnit_calculation() {
		return unit_calculation;
	}
	public void setUnit_calculation(double unit_calculation) {
		this.unit_calculation = unit_calculation;
	}


}

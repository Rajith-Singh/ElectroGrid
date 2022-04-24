package org.restapi.crud.crud.model;

public class unitmodel {

	private String	unit_id;
	private int	no_of_units;
	private double unit_price;

	public unitmodel() {

	}
	
	public unitmodel(String unit_id, int no_of_units, double unit_price) {
		super();
		this.unit_id = unit_id;
		this.no_of_units = no_of_units;
		this.unit_price = unit_price;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public int getNo_of_units() {
		return no_of_units;
	}

	public void setNo_of_units(int no_of_units) {
		this.no_of_units = no_of_units;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	
	
}	

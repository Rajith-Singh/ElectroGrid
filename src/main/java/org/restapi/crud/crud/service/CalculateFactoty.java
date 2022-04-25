package org.restapi.crud.crud.service;

public class CalculateFactoty {
	public UnitCalculation getUnitCalculation(int consumeunits) {
		UnitCalculation obj = null;
		
		if(consumeunits > 66) {
			obj = new _66_Units();
		}
		
		return obj;
	}
}

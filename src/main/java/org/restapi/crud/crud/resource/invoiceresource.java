package org.restapi.crud.crud.resource;


import java.sql.SQLException;
import java.util.ArrayList;

import org.restapi.crud.crud.model.invoicemodel;
import org.restapi.crud.crud.service.invoiceservice;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/crud")
public class invoiceresource {
	invoiceservice service = new invoiceservice();
	
	@Path("/invoice_insertion")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public invoicemodel addInvoice(invoicemodel invoice) {
		return service.insertInvoice(invoice);
	}
	
	@Path("/retrieve_invoice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<unitmodel> getUnit() throws SQLException {
//		return service.getUnit();
//	}
//	
//	@Path("/unit_retrieveById/{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<calcmodel> getUnits(@PathParam("id") int id) throws SQLException {
//		return service.getUnitsById(id);
//	}
	
//	@Path("/unit_retrieveById/{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<calcmodel> getUnits(@PathParam("id") int id) throws SQLException {
//		return service.getUnitsById(id);
//	}
	
//	@Path("/updateUser/{id}")
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	public unitmodel updateUser(crudmodel user) {
//		return service.updateUser(user);
//	}
}

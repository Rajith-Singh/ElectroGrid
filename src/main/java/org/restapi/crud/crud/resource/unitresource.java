package org.restapi.crud.crud.resource;


import java.sql.SQLException;
import java.util.ArrayList;

import org.restapi.crud.crud.model.crudmodel;
import org.restapi.crud.crud.model.unitmodel;
import org.restapi.crud.crud.service.crudservice;
import org.restapi.crud.crud.service.unitservice;

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
public class unitresource {
	unitservice service = new unitservice();
	
	@Path("/unit_insertion")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public unitmodel addUnit(unitmodel unit) {
		return service.insertUnit(unit);
	}
	
//	@Path("/retrieve")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<crudmodel> getUser() throws SQLException {
//		return service.getUser();
//	}
//	
//	@Path("/retrieveById/{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<crudmodel> getUser(@PathParam("id") int id) throws SQLException {
//		return service.getUserById(id);
//	}
//	
//	@Path("/updateUser/{id}")
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	public unitmodel updateUser(crudmodel user) {
//		return service.updateUser(user);
//	}
}

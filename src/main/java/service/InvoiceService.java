package service;

import com.InvoiceModel;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/InvoiceService")
public class InvoiceService {

	InvoiceModel serviceInvoice = new InvoiceModel();

	@POST
	@Path("/insertInvoice")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertService(@FormParam("cus_nic") String cus_nic, @FormParam("month") String month,
			@FormParam("unit_calculation") Double unit_calculation) {
		String output = serviceInvoice.insertService(cus_nic, month, unit_calculation);
		return output;
	}

	@GET
	@Path("/readInvoice")
	@Produces(MediaType.TEXT_HTML)
	public String readInvoiceService() {
		return serviceInvoice.readService();
	}
	
//	@GET
//	@Path("/readByID/{cus_nic}")
//	@Produces(MediaType.TEXT_HTML)
//	public String readByID() {
//		return serviceObj.readByID();
//	}

	@PUT
	@Path("/updateInvoice")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateService(String serviceData) {
		// Convert the input string to a JSON object
		JsonObject serviceObject = new JsonParser().parse(serviceData).getAsJsonObject();
		// Read the values from the JSON object
		String id = serviceObject.get("id").getAsString();
		String cus_nic = serviceObject.get("cus_nic").getAsString();
		String month = serviceObject.get("month").getAsString();
		Double unit_calculation = serviceObject.get("unit_calculation").getAsDouble();
		String output = serviceInvoice.updateService(id, cus_nic, month, unit_calculation);
		return output;
	}

	
	@DELETE
	@Path("/deleteInvoice")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteService(String serviceData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(serviceData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String id = doc.select("id").text();
		String output = serviceInvoice.deleteService(id);
		return output;
	}
	
	
	@GET
	@Path("/readBlackList")
	@Produces(MediaType.TEXT_HTML)
	public String readBlackList() {
		return serviceInvoice.readBlackList();
	}

}

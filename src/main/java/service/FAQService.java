package service;

import com.FAQModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/FAQService")
public class FAQService {
	FAQModel serviceObjFAQ = new FAQModel();

	@POST
	@Path("/insertFAQ")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertService(@FormParam("question") String question, @FormParam("answer") String answer,
			@FormParam("date") String date) {
		String output = serviceObjFAQ.insertService(question, answer, date);
		return output;
	}

	@GET
	@Path("/readFAQ")
	@Produces(MediaType.TEXT_HTML)
	public String readService() {
		return serviceObjFAQ.readService();
	}

	@PUT
	@Path("/updateFAQ")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateService(String serviceData) {
		// Convert the input string to a JSON object
		JsonObject serviceObject = new JsonParser().parse(serviceData).getAsJsonObject();
		// Read the values from the JSON object
		String id = serviceObject.get("id").getAsString();
		String question = serviceObject.get("question").getAsString();
		String answer = serviceObject.get("answer").getAsString();
		String date = serviceObject.get("date").getAsString();
		String output = serviceObjFAQ.updateService(id, question, answer, date);
		return output;
	}

	@DELETE
	@Path("/deleteFAQ")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteService(String serviceData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(serviceData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String id = doc.select("id").text();
		String output = serviceObjFAQ.deleteService(id);
		return output;
	}
}

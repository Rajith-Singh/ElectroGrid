package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.ConsumeModel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/ConsumeService")
public class CosumeService {

	
	ConsumeModel serviceObj = new ConsumeModel();
	
	@POST
	@Path("/consumeinsert") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertService
	(@FormParam("month") String month, 
	 @FormParam("pastUnits") String pastUnits, 
	 @FormParam("currentUnits") String currentUnits, 
	 @FormParam("consumeUnits") String consumeUnits
	)
	{ 
	 String output = serviceObj.insertService(month, pastUnits, currentUnits, consumeUnits ); 
	return output; 
	}
	
	@GET
	@Path("/consumeread") 
	@Produces(MediaType.TEXT_HTML) 
	public String readService() 
	 { 
	 return serviceObj.readService(); 
	}
	
	@PUT
	@Path("/consumeupdate") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateService(String serviceData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject serviceObject = new JsonParser().parse(serviceData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String id = serviceObject.get("id").getAsString(); 
	 String month = serviceObject.get("month").getAsString(); 
	 String pastUnits = serviceObject.get("pastUnits").getAsString(); 
	 String currentUnits = serviceObject.get("currentUnits").getAsString(); 
	 String consumeUnits = serviceObject.get("consumeUnits").getAsString();
	 String output = serviceObj.updateService(id, month, pastUnits, currentUnits, consumeUnits ); 
	 return output; 
	}

	@DELETE
	@Path("/consumedelete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteService(String serviceData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(serviceData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String id = doc.select("id").text(); 
	 String output = serviceObj.deleteService(id); 
	return output; 
	}	
	
}

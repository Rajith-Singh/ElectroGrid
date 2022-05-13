package service;


import com.PaymentModel;

import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/PayService")
public class PaymentService {
	
	



PaymentModel PayserviceObj = new PaymentModel();
	
	@POST
	@Path("/insertPayment") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED )
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertPaymentService
	(@FormParam("accountType") String accountType, 
	 @FormParam("amount") String amount, 
	 @FormParam("accountNo") String accountNo)
	{ 
	 String output = PayserviceObj.insertPaymentService(accountType, amount, accountNo); 
	return output; 
	}
	
	@GET
	@Path("/readPayment") 
	@Produces(MediaType.TEXT_HTML) 
	public String PayreadService() 
	 { 
	 return PayserviceObj.readService(); 
	}
	
	@PUT
	@Path("/updatePayment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePaymentService(String serviceData) {
	// Convert the input string to a JSON object
	JsonObject serviceObject = new JsonParser().parse(serviceData).getAsJsonObject();
	// Read the values from the JSON object
	String id = serviceObject.get("id").getAsString();
	String accountType = serviceObject.get("accountType").getAsString();
	String amount = serviceObject.get("amount").getAsString();
	String accountNo = serviceObject.get("accountNo").getAsString();
	String output = PayserviceObj.updatePaymentService(id, accountType, amount, accountNo);
	return output;
	}

	@DELETE
	@Path("/deletePayment") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePaymentService(String serviceData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(serviceData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID> 
	String id = doc.select("id").text(); 
	 String output = PayserviceObj.deletePaymentService(id); 
	return output; 
	}


}

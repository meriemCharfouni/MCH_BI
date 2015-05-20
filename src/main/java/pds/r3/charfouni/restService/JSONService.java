package pds.r3.charfouni.restService;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pds.r3.charfouni.beans.*;
import pds.r3.charfouni.dao.*;

@Path("/json/")
public class JSONService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<List<String>> getServiceInJSON() {
		
		ServiceDAO p=new ServiceDAO();
		
		List<List<String>> list= p.getServices();
		
		return list;

	}


	
	@POST
	@Path("/postCall")
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Response insertionServices(List<List<String>> Service) {
		
		ServiceDAO p=new ServiceDAO();
		
		
		for (int i=0;i<Service.size();i++) 
		{
			
			System.out.println(Service.get(i));
			ServiceBean b=new ServiceBean();
			b.setHealth_territory(Service.get(i).get(1));
			b.setHospital(Service.get(i).get(2));
			b.setService(Service.get(i).get(3));
			b.setSpeciality(Service.get(i).get(4));
			
			p.insertServices(b);
			
		}
		
		
		String result = " saved : " + Service;
		return Response.status(201).entity(result).build();
		
	}
}
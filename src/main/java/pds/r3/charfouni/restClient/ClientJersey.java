package pds.r3.charfouni.restClient;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClientJersey {

	public ClientJersey() {
		// TODO Auto-generated constructor stub
	}
	
	public void getPostServices(){
		
try {
			Client client = Client.create();
			WebResource webResource2 = client.resource("http://localhost:8081/Meriem_Charfouni_REST_R3/rest/json/get");			
			WebResource webResource = client.resource("http://localhost:8081/Meriem_Charfouni_REST_R3/rest/json/postCall");
			ClientResponse response2 = webResource2.accept("application/json").get(ClientResponse.class);
			String output = response2.getEntity(String.class);
			persist(output);
			//ClientResponse response = webResource.type("application/json").post(ClientResponse.class, output);
			//if (response.getStatus() != 201) { throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());}
			

		} catch (Exception e) {

e.printStackTrace();

		}
	}
	
	void persist(String json) {
		JSONArray array = (JSONArray)JSONValue.parse(json);
		for(int i=0; i<array.size(); i++) {
			JSONArray ar = (JSONArray)array.get(i);
			for(int j=0; j<ar.size(); j++) {
				System.out.print(ar.get(j)+", ");
			}
			System.out.println();
		}
	}
	
	
	
	
	
	
	
	
	
	
}

package client;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {
	
	 public static void main(String[] args) {

			try {

				Client client = Client.create();

				WebResource webResource = client.resource("http://localhost:8081/OFT/user/getUser");

				String input = "{\"name\":\"Metallica\",\"uname\":\"Fade To Black\",\"pass\":\"Fade To Black\"}";

				ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
				BaseRespone responseServer = response.getEntity(BaseRespone.class);
				Gson gson = new Gson();
				//String tt=	"{\"master\":"+responseServer.getResponse().toString()+"}";
				Type collectionType = new TypeToken<Collection<User>>(){}.getType();
				Collection<User> enums = gson.fromJson(responseServer.getResponse().toString(), collectionType);
				
				System.out.println("Output from Server .... \n");
				System.out.println(responseServer.getTag());
				System.out.println(responseServer.isStatus());
				
                 for (User user : enums) {
					System.out.println(user.getName());
					System.out.println(user.getUname());
					System.out.println(user.getPass());
				} 
				
			   			
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					     + response.getStatus());
				}

				

			  } catch (Exception e) {

				e.printStackTrace();

			  }

			}

}

package client;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {
	
	 public static void main(String[] args) {

			post();
			
			get();

			}



	private static void get() {
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8081/OFT/menu/getmenu");

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }
	}


	private static  void post() {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8081/OFT/user/getuser");

			String input = "{\"name\":\"Metallica\",\"uname\":\"Fade To Black\",\"pass\":\"Fade To Black\"}";

		    
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
			BaseRespone responseServer = response.getEntity(BaseRespone.class);
			Gson gson = new Gson();
			System.out.println("Output from Server .... \n");
			System.out.println(responseServer.getTag());
			System.out.println(responseServer.isStatus());
			
			if(responseServer.getResponse()!=null)
			{
			Type collectionType = new TypeToken<ArrayList<User>>(){}.getType();
			ArrayList<User> enums = gson.fromJson(responseServer.getResponse().toString(), collectionType);
			 for (User user : enums) {
					System.out.println(user.getName());
					System.out.println(user.getUname());
					System.out.println(user.getPass());
				} 
			}
			else if(responseServer.getError_msg()!=null){
			Type collectionType = new TypeToken<ArrayList<StackTraceElement>>(){}.getType();
			ArrayList<StackTraceElement> stackTrace =gson.fromJson(responseServer.getError_msg(), collectionType);
			System.out.println(stackTrace.toString());
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

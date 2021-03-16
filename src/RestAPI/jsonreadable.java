package RestAPI;

import io.restassured.path.json.JsonPath;

public class jsonreadable {
	
	public static JsonPath jsonread(String response)
	
	{
		JsonPath js1 = new JsonPath(response);
	
		return js1;
		
	}

}

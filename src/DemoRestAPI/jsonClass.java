package DemoRestAPI;

import io.restassured.path.json.JsonPath;

public class jsonClass {
	
	public static JsonPath jsonResRead(String response)
	
	{
		
		JsonPath res = new JsonPath(response);
		
		return res;
	}

}

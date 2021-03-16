import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class SpaceDetails {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "http://api.open-notify.org";
		
		String response = given().when().get("/astros.json").asString();
		
		//System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		
		//String name = js.get("message");
		
		int Size = js.get("people.size()");
		
		System.out.println(Size);
		
		//System.out.println(name);

	}

}

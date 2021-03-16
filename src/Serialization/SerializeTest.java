package Serialization;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

	public static void main(String[] args)

	{
         
		AddPlace a = new AddPlace();
		
		a.setAccuracy(50);

		a.setName("Frontline house");

		a.setAddress("29, side layout, cohen 09");

		a.setPhone_number("(+91) 983 893 3937");

		a.setLanguage("French-IN");

		a.setWebsite("http://google.com");

		Location l = new Location();

		l.setLat(38.383494);

		l.setLng(33.427362);

		a.setLocation(l);

		List<String> mylist = new ArrayList<String>();

		mylist.add("shoe park");

		mylist.add("shop");

		a.setTypes(mylist);

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String Response = given().queryParam("key", "qaclick123").body(a).when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println(Response);
	}

}

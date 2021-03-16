package Serialization;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilder {

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

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		RequestSpecification details1 = given().spec(req).body(a);

		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();

		String response = details1.when().post("/maps/api/place/add/json").then().spec(res).extract().response()
				.asString();

		System.out.println(response);
	}
	
	

}

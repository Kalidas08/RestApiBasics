package DemoRestAPI;

import static io.restassured.RestAssured.given;

public class addPlace {

	public static void addplace() {

		urlAPI.url();

		DeSerialization Response = given().queryParam("key", "qaclick123")

				.when().body(bodyDetails.addplace()).post("/maps/api/place/add/json").as(DeSerialization.class);

		System.out.println(Response.getReference());
		
	System.out.println(Response.getId());
	
	Response.getScope();

	}
	public static void main(String[] args) {

		addplace();

	}

}

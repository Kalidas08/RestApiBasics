package RestAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.bodyDetails;

public class postRequest {

	public static void main(String[] args) {

		// given - input details
		// when - submit the api, resources
		// then - validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(bodyDetails.bodyRest()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response()
				.asString();

		System.out.println(response);

		JsonPath js = new JsonPath(response); // for parsing Json

		String place_id = js.getString("place_id");

		System.out.println(place_id);

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "	\r\n" + "	\"place_id\":\"" + place_id + "\",\r\n"
						+ "\"address\":\"70 winter walk, USA\",\r\n" + "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		String response1 = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", "" + place_id + "").when().get("maps/api/place/get/json").then().log().all()
				.extract().response().asString();

		String newaddress = "70 winter walk, USA";

		JsonPath js1 = jsonreadable.jsonread(response1);

		String updatedaddress = js1.getString("address");

		Assert.assertEquals(updatedaddress, newaddress);

		System.out.println("New Address details--> " + newaddress);

	}

}

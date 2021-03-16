package DemoRestAPI;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class SampleWorks {

	static String Id;

	static String ActualAddress;

	public static void addplace() {

		urlAPI.url();

		String Response = given().log().all().queryParam("key", "qaclick123")

				.when().body(bodyDetails.addplace()).post("/maps/api/place/add/json").then().log().all().assertThat()

				.statusCode(200).extract().response().asString();

		System.out.println(Response);

		JsonPath js = new JsonPath(Response);

		Id = js.getString("place_id");

		System.out.println(Id);
		
		System.out.println(js);

		// String reference = js.getString("reference");

	}

	/*public static void getPlace()

	{

		urlAPI.url();

		String getResponse = given().queryParam("key", "qaclick123").queryParam("place_id", "" + Id + "")
				.body(bodyDetails.addplace()).get("/maps/api/place/get/json").then().assertThat().statusCode(200)
				.body("name", equalTo("Frontline house")).extract().response().asString();

		System.out.println(getResponse);

		JsonPath res = jsonClass.jsonResRead(getResponse);

		System.out.println(res.getString("accuracy"));

		System.out.println(res.getString("location.latitude"));

		String expected = res.getString("address");

		Assert.assertEquals(ActualAddress, expected);

	}

	public static void deletePlace()

	{

		urlAPI.url();

		String delResponse = given().queryParam("key", "qaclick123")
				.body("{\r\n" + "   	\r\n" + "   	\"place_id\":\"" + Id + "\" \r\n" + "   }\r\n" + "").when()
				.delete("/maps/api/place/delete/json").then().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath res1 = new JsonPath(delResponse);

		System.out.println(res1.getString("status"));

	}

	public static void updatePlace()

	{
		urlAPI.url();

		ActualAddress = "70 Street, India";

		String addressResponse = given().queryParam("key", "qaclick123")
				.body("{\r\n" + "	\r\n" + "	\"place_id\":\"" + Id + "\",\r\n" + "\"address\":\"" + ActualAddress
						+ "\",\r\n" + "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("/maps/api/place/update/json").then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println(addressResponse);

	}

	public static void main(String[] args) {

		addplace();

		updatePlace();

		getPlace();

		// deletePlace();

	}*/

}

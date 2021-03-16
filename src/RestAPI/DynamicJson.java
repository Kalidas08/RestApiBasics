package RestAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.bodyDetails;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	static String id;

	@Test(dataProvider = "getdata", priority = 1)

	public static void addbook(String isbn, String aisle)

	{

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String Response = given().header("Content-Type", "application/json").body(bodyDetails.addBook(isbn, aisle))
				.when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath js = jsonreadable.jsonread(Response);

		id = js.get("ID");

		System.out.println(id);

	}
	
	//Delete book

	@Test(dataProvider = "getdata", priority = 2)

	public static void removeBook(String isbn, String aisle)

	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().header("Content-Type", "application/json").body(bodyDetails.deleteBook(isbn, aisle))
				.when().delete("/Library/DeleteBook.php").then().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath js = jsonreadable.jsonread(response);

		String msg = js.get("msg");

		System.out.println(msg);

	}

	@DataProvider(name = "getdata")

	public Object[][] getdata()

	{

		return new Object[][] { { "stun", "22020" }, {"moon","43567"} };

	}

}

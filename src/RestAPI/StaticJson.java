package RestAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticJson {

	@Test

	public void addBook() throws IOException {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String resp = given().

				header("Content-Type", "application/json").

				body(GenerateStringFromResource("C:\\Users\\kalid\\Documents\\File.json")).

				when().

				post("/Library/Addbook.php").

				then().assertThat().statusCode(200).

				extract().response().asString();

		JsonPath js = jsonreadable.jsonread(resp);

		String id = js.get("ID");

		System.out.println(id);

	}

	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}

}

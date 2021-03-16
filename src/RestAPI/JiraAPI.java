package RestAPI;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraAPI {

	public static void main(String[] args) {

		// Login Session

		RestAssured.baseURI = "http://localhost:8080";

		SessionFilter session = new SessionFilter();

		String response = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"Kalidassganesan94\", \"password\": \"Happy@2020\" }").filter(session).when()
				.post("/rest/auth/1/session").then().extract().response().asString();

		// Adding comment

		String message = "Hello, How are you";

		String commentResponse = given().log().all().pathParam("key", "10201")
				.header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"" + message + "\",\r\n" + "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
						+ "}")
				.filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat()
				.statusCode(201).extract().response().asString();

		JsonPath js = new JsonPath(commentResponse);

		String commentId = js.get("id");

		System.out.println(commentId);

		// Adding attachment
		given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("key", "10201")
				.header("Content-Type", "multipart/form-data")
				.multiPart("file", new File("E:\\RestAPI\\RestAPI_Basic\\src\\RestAPI\\file.txt")).when()
				.post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

		// Get issue
		String filedsDetails = given().filter(session).pathParam("key", "10201").queryParam("fields", "comment").when()
				.get("/rest/api/2/issue/{key}").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		// System.out.println(filedsDetails);
		
		JsonPath js1 = new JsonPath(filedsDetails);

		int responseSize = js1.getInt("fields.comment.comments.size()");

		System.out.println(responseSize);

		for (int i = 0; i < responseSize; i++)

		{
			String commentIdissue = js1.get("fields.comment.comments[" + i + "].id").toString();

			if (commentIdissue.equalsIgnoreCase(commentId))

			{

				String addcommentmessage = js1.get("fields.comment.comments[" + i + "].body").toString();

				System.out.println(addcommentmessage);
			}

		}

	}

}

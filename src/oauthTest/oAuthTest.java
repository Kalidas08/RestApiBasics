package oauthTest;

import static io.restassured.RestAssured.*;

import java.util.List;

import PojoClasses.GetCourses;
import PojoClasses.WebAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0gGgO42JTxxlvUvmaMEonsGflLG4IlmyQzwuNO5SsyN_cyFDg-a4oOu9IWplCLLHhPUoLkHdOzHdU2LlJrsWLgg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=1&prompt=none#";

		String partialurl = url.split("code=")[1];

		String code = partialurl.split("&scope")[0];

		System.out.println(code);

		String get_access_token = given().urlEncodingEnabled(false).queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(get_access_token);

		String access_token = js.getString("access_token");

		GetCourses gc = given().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);

		System.out.println(gc.getInstructor());

		System.out.println(gc.getLinkedIn());

		List<WebAutomation> webautomation = gc.getCourses().getWebAutomation();

		for (int i = 0; i < webautomation.size(); i++)

		{

			String wa_courseTitle = gc.getCourses().getWebAutomation().get(i).getCourseTitle();

			System.out.println(wa_courseTitle);

		}
		
		for (int j=0; j<webautomation.size(); j++)
			
			
		{
			
			String wa_price = gc.getCourses().getWebAutomation().get(j).getPrice();
			
			System.out.println(wa_price);
			
		}

		// System.out.println(Response);

	}

}

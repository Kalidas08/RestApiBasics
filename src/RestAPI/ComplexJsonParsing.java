package RestAPI;

import files.bodyDetails;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {

	public static void main(String[] args) {

		JsonPath js3 = new JsonPath(bodyDetails.CoursePrice());

		// Print no of courses return by API

		int Course_Count = js3.getInt("courses.size()");

		System.out.println(Course_Count);

		// Print purchase amount

		int purchase_amount = js3.getInt("dashboard.purchaseAmount");

		System.out.println(purchase_amount);

		// Print 1st course title

		String title = js3.get("courses[0].title");

		System.out.println(title);

		// print all the courses & prices

		for (int i = 0; i < Course_Count; i++) {

			String total_courses = js3.get("courses[" + i + "].title");

			System.out.println(total_courses);

			System.out.println(js3.get("courses[" + i + "].price").toString());

			// print RPA copies
		}

		for (int i = 0; i < Course_Count; i++) {

			String total_title = js3.get("courses[" + i + "].title");

			if (total_title.equals("RPA"))

			{

				int copies = js3.get("courses[" + i + "].copies");

				System.out.println(copies);

				break;

			}

		}

	}

}

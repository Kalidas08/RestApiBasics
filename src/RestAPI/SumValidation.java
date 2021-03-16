package RestAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.bodyDetails;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test

	public void sumValidation()

	{

		int sum = 0;

		JsonPath js3 = new JsonPath(bodyDetails.CoursePrice());

		int Course_Count = js3.getInt("courses.size()");

		for (int i = 0; i < Course_Count; i++)

		{

			int price = js3.get("courses[" + i + "].price");

			int copies = js3.getInt("courses[" + i + "].copies");

			int amount = price * copies;

			System.out.println(amount);

			sum = sum + amount;

		}

		System.out.println(sum);
		
		int purchase_amount = js3.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(purchase_amount, sum);

	}

}

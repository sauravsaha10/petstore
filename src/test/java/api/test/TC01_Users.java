package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class TC01_Users {

	User userPayload;
	Faker faker;
	String userName;

	@BeforeClass
	public void dataSetup() {
		faker = new Faker();
		userPayload = new User();
		userName = faker.name().username();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setUsername(userName);
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test(priority = 1)
	public void createUser() {
		Response response = UserEndPoints.createUser(userPayload);
		System.out.println("Message Code (User ID )# " + response.body().jsonPath().get("message").toString());
		AssertJUnit.assertEquals(response.statusCode(), 200);
	}

	@Test(priority = 2, dependsOnMethods = { "createUser" })
	public void getUser() {
		Response response = UserEndPoints.getUser(userName);
		System.out.println("User Details # " + response.asPrettyString());
		Assert.assertNotEquals(response.body().jsonPath().get("message"), "User not found");
		AssertJUnit.assertEquals(response.statusCode(), 200);
	}

	@Test(priority = 3, dependsOnMethods = { "getUser" })
	public void updateUser() {
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		Response response = UserEndPoints.updateUser(userName, userPayload);
		AssertJUnit.assertEquals(response.statusCode(), 200);
		response = UserEndPoints.getUser(userName);
		System.out.println("Updated User Details # " + response.body().asPrettyString());
		
	}

	@Test(priority = 4, dependsOnMethods = { "getUser" })
	public void deleteUser() {
		Response response = UserEndPoints.deleteUser(userName);
		System.out.println("Deleted User Details # " + response.body().asPrettyString());
		AssertJUnit.assertEquals(response.statusCode(), 200);
	}
}

package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(User payload) {
		Response response = given()
								.accept("application/json")
								.contentType("application/json")
								.body(payload)
							.when()
								.post(Routes.createUser);
		return response;
	}

	public static Response getUser(String userName) {
		Response response = given()
								.accept("application/json")
								.pathParam("userName", userName)
							.when()
								.get(Routes.getUser);
		return response;
	}

	public static Response updateUser(String userName, User payload) {
		Response response = given()
								.accept("application/json")
								.contentType("application/json")
								.pathParam("userName", userName)
								.body(payload)
							.when()
								.put(Routes.updateUser);
		return response;
	}

	public static Response deleteUser(String userName) {
		Response response = given()
								.accept("application/json")
								.pathParam("userName", userName)
							.when()
								.delete(Routes.deleteUser);
		return response;
	}

}

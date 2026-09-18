package testcases;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import routes.Routes;

import static io.restassured.RestAssured.*;
import org.hamcrest.*;
import org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import java.util.List;
import org.testng.ITestContext;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.User;

public class UserTests extends BaseClass {

	// 1) Fetch all the users

	@Test
	public void getAllUsers() {
		given().when().get(Routes.GET_ALL_USERS).then().statusCode(200).log().body().contentType(ContentType.JSON)
				.body("size()", greaterThan(0));

	}
	// 2) Test to fetch a specific user by ID

	@Test
	public void getUserById() {
		int userId = configReader.getIntProperty("userId");
		given().pathParam("id", userId).when().get(Routes.GET_USER_BY_ID).then().log().body().statusCode(200);

	}

	// 3) Test to fetch a limited number of users
	@Test
	public void testGetUsersByLimit() {
		int limitUser = configReader.getIntProperty("limit");
		given().pathParam("limit", limitUser).when().get(Routes.GET_USERS_WITH_LIMIT).then().log().body()
				.statusCode(200).body("size()", equalTo(limitUser));

	}

	// 4) Test to fetch users sorted in descending order
	@Test
	public void testGetUsersSorted() {
		Response response = given().pathParams("order", "desc").when().get(Routes.GET_USERS_SORTED).then()
				.statusCode(200).log().body().extract().response();
		List<Integer> userIds = response.jsonPath().getList("id", Integer.class);
		assertThat(isSortedDescending(userIds), is(true));
	}
	// 5) Test to fetch users sorted in ascending order

	@Test
	public void testGetUsersSortedAsending() {
		Response response = given().pathParams("order", "asc").when().get(Routes.GET_USERS_SORTED).then()
				.statusCode(200).log().body().extract().response();
		List<Integer> userIds = response.jsonPath().getList("id", Integer.class);
		assertThat(isSortedAscending(userIds), is(true));
	}

	// 6) Test to create a new user
	@Test
	public void testCreateUser() {
		User userPayload = Payload.userPayload();
		int userId = given().contentType(ContentType.JSON).body(userPayload).when().post(Routes.CREATE_USER).then()
				.log().body().body("id", notNullValue()).statusCode(201).extract().jsonPath().getInt("id");
		System.out.println("User id Created " + userId);

	}

	// 7) Test to update user
	@Test
	public void testUpdateUser() {
		int userId = configReader.getIntProperty("productId");
		User updatePayload = Payload.userPayload();
		given().contentType(ContentType.JSON).body(updatePayload).pathParam("id", userId).when().put(Routes.UPDATE_USER)
				.then().statusCode(200).log().body().body("username", equalTo(updatePayload.getUsername()));

	}

	// 8) delete user
	@Test
	public void testDeleteUser() {
		int userId = configReader.getIntProperty("productId");
		given()
		.pathParam("id",userId )
		.when()
		.delete(Routes.DELETE_USER)
		.then()
		.statusCode(200);
		

	}

}

package tests;

import static helper.Utilities.getBundle;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import helper.Utilities;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import requests.Requests;

/**
 * All the tests can be added here.
 * 
 * @author Anand Chandran
 */

public class Tests {
	private Requests request;
	SoftAssert softAssertion = new SoftAssert();
	/**
	 * Logger object to add logs
	 */
	private static Logger log = LogManager.getLogger(Tests.class.getName());

	/**
	 * Method to run before a test. initialize instance of PetModule class
	 */
	@BeforeTest(alwaysRun = true)
	public void testSetup() {
		request = new Requests();
	}

	/**
	 * test for validating work flow
	 */
	@Test
	public void commentEmailsTest() {
		Response userResponse = request.getUserDetails(getBundle().get("USER_NAME"));

		// verifying the status code for user details response
		softAssertion.assertEquals(userResponse.getStatusCode(), 200, "response code does not match expected");
		log.debug("user details for " + getBundle().get("USER_NAME") + " fetched successfully");
		String userId = userResponse.jsonPath().getList("id").get(0).toString();

		Response postResponse = request.getPosts(userId);

		// verifying the status code for post details response
		softAssertion.assertEquals(postResponse.getStatusCode(), 200, "response code does not match expected");
		log.debug("All post details by user " + getBundle().get("USER_NAME") + " fetched successfully");
		List<Integer> postIds = new ArrayList<>();
		postIds.addAll(postResponse.path("id"));

		for (int postId : postIds) {
			Response commentResponse = request.getComments(postId);

			// verifying the status code for comments details response
			softAssertion.assertEquals(commentResponse.getStatusCode(), 200, "response code does not match expected");
			log.debug("comment details for post with id " + postId + " fetched successfully");
			List<String> commentEmails = new ArrayList<>();
			commentEmails.addAll(commentResponse.path("email"));

			for (String email : commentEmails) {

				// verifying the email formats
				softAssertion.assertTrue(Utilities.isValidEmail(email),
						"The email " + email + " is not in right format");
				log.debug("email " + email + " validated successfully");
			}

		}

		softAssertion.assertAll();

	}
	/**
	 * test for validating user details api response schema
	 */
	@Test
	public void validateUserDetailsAPISchema() {
		Response userResponse = request.getUserDetails(getBundle().get("USER_NAME"));
		userResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("userdetails.json"));
		log.debug("The response Json Schema is valid");
	}
}

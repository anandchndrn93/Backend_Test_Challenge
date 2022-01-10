package requests;

import java.util.HashMap;
import java.util.Map;

import endpoints.EndPoints;
import helper.RequestWrapper;
import io.restassured.response.Response;

/**
 * This class is meant to put together all data for making a specific api call.
 * Each method would refer to a specific api. To make an api call the method can
 * be accessed from test classes using instance of this class.
 * 
 * @author Anand Chandran
 */

public class Requests {
	private RequestWrapper req;
	private EndPoints endpoint;

	/**
	 * Constructor
	 */
	public Requests() {
		req = new RequestWrapper();
	}

	/**
	 * api to search user using user name.
	 * 
	 * @param strUserName user name of the user to be searched
	 * @return api response
	 */
	public Response getUserDetails(String strUserName) {

		endpoint = EndPoints.valueOf("users");
		Map<String, String> userDetails = new HashMap<>();
		userDetails.put("username", strUserName);
		return req.getRequestWithQueryParams(endpoint.getResource(), null, userDetails);
	}

	/**
	 * api to search posts by a user
	 * 
	 * @param strUserID id of the user whose posts are to be searched
	 * @return api response
	 */

	public Response getPosts(String strUserID) {

		endpoint = EndPoints.valueOf("posts");
		Map<String, String> postDetails = new HashMap<>();
		postDetails.put("userId", strUserID);
		return req.getRequestWithQueryParams(endpoint.getResource(), null, postDetails);

	}

	/**
	 * api to search comments under a post
	 * 
	 * @param strPostID id of the post where comments are to be searched
	 * @return api response
	 */
	public Response getComments(String strPostID) {
		endpoint = EndPoints.valueOf("comments");
		Map<String, String> commentDetails = new HashMap<>();
		commentDetails.put("postId", strPostID);
		return req.getRequestWithQueryParams(endpoint.getResource(), null, commentDetails);

	}

}

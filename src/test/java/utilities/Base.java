package utilities;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import pojoclass.TestcaseWrapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base {
	
	public RequestSpecification request ;
	public Response response ;
	
	public RequestSpecification createRequest() throws IOException {
		
		request = given()
				  .header("Content-Type", "application/json")
		          .baseUri(configReader.getProperty("BaseURL"));	        		
		return request;
		
		
	}
	
	public RequestSpecification createTokenRequest() throws IOException {
	    return given()
	    		.log().all()
	            .header("Content-Type", "application/json")
	            .header("Authorization", "Bearer " + Token.token)
	            .baseUri(configReader.getProperty("BaseURL"));
	}
	
	
	public static TestcaseWrapper getTestData() {
		
		return  JsonReader.readAllModules("src/test/resources/TestDataforLMS.json");
		 
		}
	
	
}

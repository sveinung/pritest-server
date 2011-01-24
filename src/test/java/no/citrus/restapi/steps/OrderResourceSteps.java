package no.citrus.restapi.steps;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

public class OrderResourceSteps {
	private ClientResponse testorderResponse;
	
	@Given ("^I am about to run my tests$")
    public void iAmAboutToRunMyTests() {
    }
    
    @When ("^I GET a test order from \"([^\"]*)\"$")
    public void iGetATestOrderFromTestorder1(String resource) {
    	Client client = Client.create();
    	WebResource webResource = client.resource("http://localhost:8090" + resource);
    	testorderResponse = webResource.get(ClientResponse.class);
    }
    
    @Then ("^it should return status \"([^\"]*)\" OK$")
    public void itShouldReturnStatus200OK(String statusCode) throws Exception {
    	if (testorderResponse.getStatus() != Integer.parseInt(statusCode)) throw new Exception();
    }
    
    @Then ("^the response should be a list of test classes to run as \"([^\"]*)\"$")
    public void theResponseShouldBeAListOfTestClassesToRunAsApplicationJSON(String arg1) {
    }
}

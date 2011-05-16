package no.citrus.restapi.steps;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

public class ChangeResourceSteps {
    private String changeRecording;
    private ClientResponse changeResponse;

    @Given("^I have a valid change recording:$")
    public void i_have_avalid_change_recording_with_string(String string) {
        changeRecording = string;
    }

    @When("^I POST the change recording as \"([^\"]*)\" to \"([^\"]*)\"$")
    public void post_change_recording_content_type_to_resource(String mime, String resource) {
        Client client = Client.create();
        WebResource webResource = client.resource(WebappConstants.WEBAPP_BASEURL + resource);
        changeResponse = webResource.type(mime).post(ClientResponse.class, changeRecording);
    }

    @Then("^It should return change status \"([^\"]*)\".*?$")
    public void it_should_return_change_status(String statusCode) throws Exception {
        if (changeResponse.getStatus() != Integer.parseInt(statusCode)) throw new Exception();
    }
}

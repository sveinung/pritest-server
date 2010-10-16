package no.citrus.restapi.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.notNull;
import no.citrus.restapi.DAOFactory;
import no.citrus.restapi.FailedCukeException;
import no.citrus.restapi.MeasureDAO;
import no.citrus.restapi.model.Measure;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

public class AppSteps {
    private String recording;
    private ClientResponse response;

    @Given("^I have a valid recording:$")
    public void iHaveAvalidRecordingWithString(String string) {
        recording = string;
    }

    @When("^I POST the recording as \"([^\"]*)\" to \"([^\"]*)\"$")
    public void postRecordingContentTypeToResource(String mime, String resource) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8090" + resource);
        response = webResource.type(mime).post(ClientResponse.class, recording);
    }

    @Then("^It should return status \"([^\"]*)\".*?$")
    public void itShouldReturnStatus(String statusCode) {
        if (response.getStatus() != Integer.parseInt(statusCode)) throw new FailedCukeException();
    }

    @Then("^the DB should contain measure with name \"([^\"]*)\"$")
    public void dbShouldContain(String name) {
    	MeasureDAO dao = DAOFactory.getDatabase().getMeasureDAO();
        Measure measure = dao.get(name);
        assertThat(measure, is(notNull()));
        assertThat(measure.getName(), equalTo(name));


    }


}

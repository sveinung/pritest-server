/**
    This file is part of Pritest.

    Pritest is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Pritest is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

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

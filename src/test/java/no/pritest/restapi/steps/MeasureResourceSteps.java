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

package no.pritest.restapi.steps;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import no.pritest.restapi.DAOFactory;
import no.pritest.restapi.MeasureDAO;
import no.pritest.restapi.model.Measure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.notNull;

public class MeasureResourceSteps {
    private String measureRecording;
    private ClientResponse measureResponse;

    @Given("^I have a valid measure recording:$")
    public void i_have_a_valid_measure_recording_with_string(String string) {
        measureRecording = string;
    }

    @When("^I POST the measure recording as \"([^\"]*)\" to \"([^\"]*)\"$")
    public void post_measure_recording_content_type_to_resource(String mime, String resource) {
        Client client = Client.create();
        WebResource webResource = client.resource(WebappConstants.WEBAPP_BASEURL + resource);
        measureResponse = webResource.type(mime).post(ClientResponse.class, measureRecording);
    }

    @Then("^It should return status \"([^\"]*)\".*?$")
    public void it_should_return_status(String statusCode) throws Exception {
        if (measureResponse.getStatus() != Integer.parseInt(statusCode)) throw new Exception();
    }

    @Then("^the DB should contain measure with name \"([^\"]*)\"$")
    public void db_should_contain_measure(String name) {
        MeasureDAO dao = DAOFactory.getDatabase().getMeasureDAO();
        Measure measure = dao.get(name);
        assertThat(measure, is(notNull()));
        assertThat(measure.getName(), equalTo(name));
    }
}

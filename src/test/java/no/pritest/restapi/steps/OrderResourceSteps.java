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

public class OrderResourceSteps {
    private ClientResponse testorderResponse;

    @Given("^I am about to run my tests$")
    public void i_am_about_to_run_my_tests() {
    }

    @When("^I GET a test order from \"([^\"]*)\"$")
    public void i_get_a_test_order_from_testorder_1(String resource) {
        Client client = Client.create();
        WebResource webResource = client.resource(WebappConstants.WEBAPP_BASEURL + resource);
        testorderResponse = webResource.get(ClientResponse.class);
    }

    @Then("^it should return status \"([^\"]*)\" OK$")
    public void it_should_return_status_200_ok(String statusCode) throws Exception {
        if (testorderResponse.getStatus() != Integer.parseInt(statusCode)) throw new Exception();
    }

    @Then("^the response should be a list of test classes to run as \"([^\"]*)\"$")
    public void the_response_should_be_a_list_of_test_classes_to_run_as_application_json(String arg1) {
    }
}

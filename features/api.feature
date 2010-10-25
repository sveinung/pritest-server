Feature: API
  ...

  Scenario: Upload new XML measure
	Given I have a valid measure recording:
    """
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <measure>
      <name>xmlRecording</name>
    </measure>
    """
    When I POST the measure recording as "application/xml" to "/measure"
    Then It should return status "200" OK

  Scenario: Upload new JSON measure
    Given I have a valid measure recording:
    """
    {
      "name":"jsonRecording"
    }
    """
    When I POST the measure recording as "application/json" to "/measure"
    Then It should return status "200" OK

  Scenario: Upload new JSON change
    Given I have a valid change recording:
    """
    {
	  "before": "5aef35982fb2d34e9d9d4502f6ede1072793222d",
	  "after": "de8251ff97ee194a289832576287d6f8ad74e3d0",
	  "repository": {
	    "url": "http://github.com/defunkt/github",
	    "name": "github",
	    "description": "You're lookin' at it.",
	    "watchers": 5,
	    "forks": 2,
	    "private": 1
	  }
	}
    """
    When I POST the change recording as "application/json" to "/change"
    Then It should return change status "200" OK
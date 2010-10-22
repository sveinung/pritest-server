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

  Scenario: Upload new XML change
    Given I have a valid change recording:
    """
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <change>
      <name>xmlRecording</name>
    </change>
    """
    When I POST the change recording as "application/xml" to "/change"
    Then It should return change status "200" OK

  Scenario: Upload new JSON change
    Given I have a valid change recording:
    """
    {
      "name":"jsonRecording"
    }
    """
    When I POST the change recording as "application/json" to "/change"
    Then It should return change status "200" OK
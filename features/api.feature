Feature: API
  ...

  Scenario: Upload new XML measure
    Given I have a valid recording:
    """
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <measure>
      <name>xmlRecording</name>
    </measure>
    """
    When I POST the recording as "application/xml" to "/measure"
    Then It should return status "200" OK

  Scenario: Upload new JSON measure
    Given I have a valid recording:
    """
    {
      "name":"jsonRecording"
    }
    """
    When I POST the recording as "application/json" to "/measure"
    Then It should return status "200" OK

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
    payload {
	  "before": "5aef35982fb2d34e9d9d4502f6ede1072793222d",
	  "after": "de8251ff97ee194a289832576287d6f8ad74e3d0",
	  "commits": [
        {
          "added": [
            "README"
          ],
          "author": {
            "email": "oyvindvol@gmail.com", 
            "name": "\u00d8yvind Voldsund"
          },
          "id": "c117410bae42b0cf7f97c6d5d1a09feeba02779c", 
          "message": "Added README to test1",
          "modified": [], 
          "removed": [],
          "timestamp": "2010-10-24T02:35:15-07:00", 
          "url": "http:\/\/github.com\/oyvindvol\/citrus-exp\/commit\/c117410bae42b0cf7f97c6d5d1a09feeba02779c"
        }
      ],
	  "ref": "refs\/heads\/master",
	  "compare": "http:\/\/github.com\/oyvindvol\/citrus-exp\/compare\/4222fbe...c117410",
	  "forced": false,
	  "pusher": {
        "email": "oyvindvol@gmail.com", 
        "name": "oyvindvol"
      },
	  "repository": {
        "created_at": "2010\/10\/23 03:57:17 -0700", 
        "description": "Experiment repository for the citrus project", 
        "fork": false, 
        "forks": 0, 
        "has_downloads": true, 
        "has_issues": true, 
        "has_wiki": true, 
        "homepage": "test", 
        "name": "citrus-exp", 
        "open_issues": 0, 
        "owner": {
          "email": "oyvindvol@gmail.com", 
          "name": "oyvindvol"
   	    },
        "private": false, 
        "pushed_at": "2010\/10\/24 02:35:41 -0700", 
        "url": "http:\/\/github.com\/oyvindvol\/citrus-exp", 
        "watchers": 4
      }
	}
    """
    When I POST the change recording as "application/json" to "/change"
    Then It should return change status "200" OK
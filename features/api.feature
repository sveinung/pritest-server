Feature: API
  ...

  Scenario: Upload new XML measure
	Given I have a valid measure recording:
    """
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <measures>
    <measure>
      <source>no.muda.domain.Sist</source>
      <name>xmlRecording1</name>
      <value>12</value>
      <date>2010-10-13-12:24:22+1</date>
      <category>testSuite</category>
      <children>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateNestedClasses</name>
          <value>1.0012</value>
          <date>2010-10-13-12:24:22+1</date>
          <failed>true</failed>
          <children></children>
        </measure>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateUnNestedClasses</name>
          <value>1.0312</value>
          <date>2010-10-13-12:24:21+1</date>
          <failed>true</failed>
          <children></children>
        </measure>
      </children>
    </measure>
    <measure>
      <source>no.muda.domain.Fyrst</source>
      <name>xmlRecording1</name>
      <value>12</value>
      <date>2010-10-13-12:24:22+1</date>
      <category>testSuite</category>
      <children>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateNestedClasses</name>
          <value>1.0012</value>
          <date>2010-10-13-12:24:22+1</date>
          <failed>false</failed>
          <children></children>
        </measure>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateUnNestedClasses</name>
          <value>1.0312</value>
          <date>2010-10-13-12:24:21+1</date>
          <failed>false</failed>
          <children></children>
        </measure>
      </children>
    </measure>
    <measure>
      <source>no.muda.domain.Midten</source>
      <name>xmlRecording2</name>
      <value>12</value>
      <date>2010-10-13-12:24:22+1</date>
      <category>testSuite</category>
      <children>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateNestedClasses</name>
          <value>1.0012</value>
          <date>2010-10-13-12:24:22+1</date>
          <failed>true</failed>
          <children></children>
        </measure>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateUnNestedClasses</name>
          <value>1.0312</value>
          <date>2010-10-13-12:24:21+1</date>
          <failed>false</failed>
          <children></children>
        </measure>
      </children>
    </measure>
    </measures>
    """
    When I POST the measure recording as "application/xml" to "/measure"
    Then It should return status "200" OK

  Scenario: Upload new JSON change
    Given I have a valid change recording:
    """
    payload=%7b%22pusher%22%3a%7b%22email%22%3a%22oyvindvol%40gmail.com%22%2c%22name%22%3a%22oyvindvol%22%7d%2c%22repository%22%3a%7b%22description%22%3a%22Experiment%20repository%20for%20the%20citrus%20project%22%2c%22url%22%3a%22http%3a%2f%2fgithub.com%2foyvindvol%2fcitrus-exp%22%2c%22has_downloads%22%3atrue%2c%22pushed_at%22%3a%222010%2f10%2f26%2002%3a29%3a43%20-0700%22%2c%22fork%22%3afalse%2c%22homepage%22%3a%22%22%2c%22watchers%22%3a4%2c%22has_wiki%22%3atrue%2c%22private%22%3afalse%2c%22created_at%22%3a%222010%2f10%2f23%2003%3a57%3a17%20-0700%22%2c%22open_issues%22%3a0%2c%22owner%22%3a%7b%22email%22%3a%22oyvindvol%40gmail.com%22%2c%22name%22%3a%22oyvindvol%22%7d%2c%22name%22%3a%22citrus-exp%22%2c%22forks%22%3a0%2c%22has_issues%22%3atrue%7d%2c%22compare%22%3a%22http%3a%2f%2fgithub.com%2foyvindvol%2fcitrus-exp%2fcompare%2f442680a...587703f%22%2c%22before%22%3a%22442680a76050987ccb3d57c5d6e101b1baea4ba4%22%2c%22after%22%3a%22587703f47e0f4f205ae1776aeb07f3ad20bedfef%22%2c%22ref%22%3a%22refs%2fheads%2fmaster%22%2c%22forced%22%3afalse%2c%22commits%22%3a%5b%7b%22added%22%3a%5b%22README%22%5d%2c%22url%22%3a%22http%3a%2f%2fgithub.com%2foyvindvol%2fcitrus-exp%2fcommit%2f587703f47e0f4f205ae1776aeb07f3ad20bedfef%22%2c%22message%22%3a%22Added%20Readme%20again.%22%2c%22removed%22%3a%5b%5d%2c%22author%22%3a%7b%22email%22%3a%22oyvindvol%40gmail.com%22%2c%22name%22%3a%22%c3%98yvind%20Voldsund%22%7d%2c%22modified%22%3a%5b%5d%2c%22timestamp%22%3a%222010-10-26T02%3a29%3a10-07%3a00%22%2c%22id%22%3a%22587703f47e0f4f205ae1776aeb07f3ad20bedfef%22%7d%5d%7d
    """
    When I POST the change recording as "application/x-www-form-urlencoded" to "/change"
    Then It should return change status "200" OK
    
  Scenario: Request a prioritized list of tests using method 1
    Given I am about to run my tests
    When I GET a test order from "/testorder/1"
    Then it should return status "200" OK
    And the response should be a list of test classes to run as "application/json"

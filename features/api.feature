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
      <date>2010-11-08T01:28:24-08:00</date>
      <category>testSuite</category>
      <children>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateNestedClasses</name>
          <value>1.0012</value>
          <date>2010-11-08T01:28:24-08:00</date>
          <failed>true</failed>
          <children></children>
        </measure>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateUnNestedClasses</name>
          <value>1.0312</value>
          <date>2010-11-08T01:28:24-08:00</date>
          <failed>true</failed>
          <children></children>
        </measure>
      </children>
    </measure>
    <measure>
      <source>no.muda.domain.Midten</source>
      <name>xmlRecording2</name>
      <value>12</value>
      <date>2010-11-11T01:28:24-08:00</date>
      <category>testSuite</category>
      <children>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateNestedClasses</name>
          <value>1.0012</value>
          <date>2010-11-11T01:28:24-08:00</date>
          <failed>true</failed>
          <children></children>
        </measure>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateUnNestedClasses</name>
          <value>1.0312</value>
          <date>2010-11-11T01:28:24-08:00</date>
          <failed>false</failed>
          <children></children>
        </measure>
      </children>
    </measure>
    <measure>
      <source>no.muda.domain.Fyrst</source>
      <name>xmlRecording1</name>
      <value>12</value>
      <date>2010-11-11T01:28:24-08:00</date>
      <category>testSuite</category>
      <children>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateNestedClasses</name>
          <value>1.0012</value>
          <date>2010-11-11T01:28:24-08:00</date>
          <failed>false</failed>
          <children></children>
        </measure>
        <measure>
          <source>no.muda.domain.KlassTest</source>
          <category>testCase</category>
          <name>shouldValidateUnNestedClasses</name>
          <value>1.0312</value>
          <date>2010-11-11T01:28:24-08:00</date>
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
    payload=%7B%0A%20%20%22after%22%3A%20%227b8fde56dd73910f664784331d58438a447a9243%22%2C%20%0A%20%20%22before%22%3A%20%2236cb8e61e42a3431dbd6421a7ff217f08468a757%22%2C%20%0A%20%20%22commits%22%3A%20%5B%0A%20%20%20%20%7B%0A%20%20%20%20%20%20%22added%22%3A%20%5B%0A%20%20%20%20%20%20%20%20%22test1%5C%2Fsrc%5C%2Fmain%5C%2Fjava%5C%2Fno%5C%2Fcitrus%5C%2FMain.java%22%0A%20%20%20%20%20%20%5D%2C%20%0A%20%20%20%20%20%20%22author%22%3A%20%7B%0A%20%20%20%20%20%20%20%20%22email%22%3A%20%22oyvindvol%40gmail.com%22%2C%20%0A%20%20%20%20%20%20%20%20%22name%22%3A%20%22%5Cu00d8yvind%20Voldsund%22%2C%20%0A%20%20%20%20%20%20%20%20%22username%22%3A%20%22oyvindvol%22%0A%20%20%20%20%20%20%7D%2C%20%0A%20%20%20%20%20%20%22id%22%3A%20%227b8fde56dd73910f664784331d58438a447a9243%22%2C%20%0A%20%20%20%20%20%20%22message%22%3A%20%22la%20til%20Main.java.%22%2C%20%0A%20%20%20%20%20%20%22modified%22%3A%20%5B%5D%2C%20%0A%20%20%20%20%20%20%22removed%22%3A%20%5B%5D%2C%20%0A%20%20%20%20%20%20%22timestamp%22%3A%20%222010-11-10T01%3A28%3A24-08%3A00%22%2C%20%0A%20%20%20%20%20%20%22url%22%3A%20%22https%3A%5C%2F%5C%2Fgithub.com%5C%2Foyvindvol%5C%2Fcitrus-exp%5C%2Fcommit%5C%2F7b8fde56dd73910f664784331d58438a447a9243%22%0A%20%20%20%20%7D%0A%20%20%5D%2C%20%0A%20%20%22compare%22%3A%20%22https%3A%5C%2F%5C%2Fgithub.com%5C%2Foyvindvol%5C%2Fcitrus-exp%5C%2Fcompare%5C%2F36cb8e6...7b8fde5%22%2C%20%0A%20%20%22forced%22%3A%20false%2C%20%0A%20%20%22pusher%22%3A%20%7B%0A%20%20%20%20%22email%22%3A%20%22oyvindvol%40gmail.com%22%2C%20%0A%20%20%20%20%22name%22%3A%20%22oyvindvol%22%0A%20%20%7D%2C%20%0A%20%20%22ref%22%3A%20%22refs%5C%2Fheads%5C%2Fmaster%22%2C%20%0A%20%20%22repository%22%3A%20%7B%0A%20%20%20%20%22created_at%22%3A%20%222010%5C%2F10%5C%2F23%2003%3A57%3A17%20-0700%22%2C%20%0A%20%20%20%20%22description%22%3A%20%22Experiment%20repository%20for%20the%20citrus%20project%22%2C%20%0A%20%20%20%20%22fork%22%3A%20false%2C%20%0A%20%20%20%20%22forks%22%3A%201%2C%20%0A%20%20%20%20%22has_downloads%22%3A%20true%2C%20%0A%20%20%20%20%22has_issues%22%3A%20true%2C%20%0A%20%20%20%20%22has_wiki%22%3A%20true%2C%20%0A%20%20%20%20%22homepage%22%3A%20%22%22%2C%20%0A%20%20%20%20%22name%22%3A%20%22citrus-exp%22%2C%20%0A%20%20%20%20%22open_issues%22%3A%200%2C%20%0A%20%20%20%20%22owner%22%3A%20%7B%0A%20%20%20%20%20%20%22email%22%3A%20%22oyvindvol%40gmail.com%22%2C%20%0A%20%20%20%20%20%20%22name%22%3A%20%22oyvindvol%22%0A%20%20%20%20%7D%2C%20%0A%20%20%20%20%22private%22%3A%20false%2C%20%0A%20%20%20%20%22pushed_at%22%3A%20%222010%5C%2F11%5C%2F10%2001%3A29%3A40%20-0800%22%2C%20%0A%20%20%20%20%22url%22%3A%20%22https%3A%5C%2F%5C%2Fgithub.com%5C%2Foyvindvol%5C%2Fcitrus-exp%22%2C%20%0A%20%20%20%20%22watchers%22%3A%204%0A%20%20%7D%0A%7D
    """
    When I POST the change recording as "application/x-www-form-urlencoded" to "/change"
    Then It should return change status "200" OK
    
  Scenario: Request a prioritized list of tests using method 1
    Given I am about to run my tests
    When I GET a test order from "/testorder/1"
    Then it should return status "200" OK
    And the response should be a list of test classes to run as "application/json"

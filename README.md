Pritest has been renamed to NewOrder and moved to https://github.com/NewOrder/neworder

Requirements:
* Java 6
* Maven 2 (or 3)
* citrus-core (https://github.com/sdalatun/citrus-core)
* mongodb
* Technique 3 requires a post-recieve hook from github pointing to [url to running citrus-server]/change 

Configuration:
  The Citrus server can be configured in the citrus.properties file.
  Has the following properties:
  * DB_Type: Database type, currently just "Mongo"
  * DB_Port: Database port
  * DB_Name: Database name
  * DB_URL: Database URL


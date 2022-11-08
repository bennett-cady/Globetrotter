# Globetrotter
## Overview
The weekly forecast looks grim and you devide to get out of town ASAP! But where should you go?
This web app allows impulsive travelers to enter their ideal weather conditions and returns destinations that might be a good match.

This application sends API requests to get the 7-day forecast of various locations to help users plan their last minute vacations.
This app uses API requests provided by weatherapi.com

## Routes
* /current/city=[enter city name here]
  * This simple route simply pulls up the current temperature of the given city
  * The temperature is given in the body of the request
* /forecast/city=[enter city name here]&idealTemp=[enter ideal temperature here]
  * This route sends an API request to get the 7-day forecast of the entered city
  * One field returned by the api is the average temperature of each day
  * This application calculates the avaerage of average temperatures. If this calculation is within 5 degress (f) of the ideal temperature entered, the body of the response informs the user the weather of this location will be ideal.
    * Otherwise, the response body will inform the user whether it will be too hot or too cold for them
* /findCityWith/idealTemp=[enter ideal temperature here]&margin=[enter temperature margin here]
  * This route takes the ideal temperature, then adds and subtracts the margin to compute the temperature range the user prefers
    * For example, idealTemp=80 and margin=5, means the user is looking for destinations with a weekly forecasted average temperature between 75 and 85 degrees (f)
  * The application goes through cities, pulling their weekly forecast, and calculating whether or not it's weekly average temperature falls between this range.
  * The response body is a list of all destinations that satisfy the user's ideal temperature.
  
* Screenshots of sample requests and responses can be found here: 
  
## Services, Controllers, and other classes
* Source code for this API can be found in src/main/java/com/SimpleProject
  * Controllers - LocationController, ForecastController
    * These options take in http requests and call the appropriate method from Service classes. Then, using the output from that method, returns the response to the user
  * Services - ForecastService
    * Calls ApiCaller object, then makes calculations based on the results received
 * ApiCaller - sends api calls to weatherapi.com, and returns the result
 * Note: Location controller skips calling a service and calls ApiCaller
 * Location- object for storing data about the locations


## To-do:
* Give users the option to enter temperatures in celsius
* Sort output of /findCityWith/ by how close their average temperature is to the ideal temperature given
* Add feature to /forecast/city=[enter city name here]&idealTemp=[enter ideal temperature here] that gives users the option to change the margin
  * default is 5
* Create endpoints that deal with more than just temperature, such as:
  * User does not care about temperature, just wants to be somewhere sunny
  * User is going skiing and wants to find the place with the most snowfall in the coming week
* Integrate with Relational Database:
  * currently using adhoc approach of manually creating list of locations
  * A relational database would store much more locations
  * Users could have the option to filter locations by region, country, continent
    * User enters preferred region(s), such as Caribbean and Mediterranean
    * Relational database returns list of locations that satisfy the following query:
      * SELECT * FROM [database_name] WHERE region='Caribbean' OR region='Mediterranean'
    * My API returns Caribbean and Mediterranean locations with forecast that fits the user's ideal weather conditons
* Build Docker image, push to Dockerhub


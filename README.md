<h2> How to run it?

You start the server by running WorldpayCodingSpringApplication class.
The application listens to http://localhost:8080

<h2> Operations

* (POST) **/offer** to create a new offer 
* (PUT) **/offer** to update an offer
* (GET) **/offer/{id}** to get a specific offer
* (GET) **/offers** to get all the offers
* (DELETE) **/offer/{id}** to delete a an offer

<h2> Assumptions

An offer will expire when current date is passed expiresOn.

An offer can only be delete if it is not expired.

<h2> Improvements

At the moment the API is returning all of the offers.

* A new endpoint can be implemented to get only the valid (not expired) offer.
* A new endpoint can be implemented to get only the expired offers.

Probably LocalDateTime is better to be used instead of Date.

For testing the delete method, the Date object should be mocked but I could not found how. 
Again, maybe using LocalDateTime will solve this problem.


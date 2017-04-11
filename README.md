# _Barber-Shop_

#### _Application for employees of a barbershop to manage clients & employees., 03/27/17_

#### By _**Colin Bloom**_

## Description

_This application brings the user to a dashboard where they can see a list of stylists as well as a list of clients that have no preferred stylists. They can click on stylists and clients names to view details, update information, or delete them. Unassigned clients have the option to add them to a stylist. If you delete a stylist that has clients assigned to them, those clients will be listed in the 'unclaimed clients' queue._

## Setup/Installation Requirements

* _Install JRE (Java Runtime Enviroment)._
* _Install postgres/MySql._
* _Install Gradle._
* _Clone repository from github.com/colinjb._

##Database Setup
* _Run postgres in terminal._
* _In new termianl tab, run psql._
* _Type 'CREATE DATABASE hair_salon;' inside of command line psql._
* _Type 'CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, number varchar);' in psql._
* _Type 'CREATE TABLE clients (id serial PRIMARY KEY, name varchar, number varchar, stylist_id int);' in psql._
* _Run 'psql hair_salon < hair_salon.sql' in command line to populate database._

* _Use 'gradle run' to compile and run application in command line._
* _Navigate to 'localhost:4567' in internet browser._

## No Known Bugs

## Support and contact details

_Feel free to add or comment on my code! You can reach me at colinjbloom113@gmail.com_

## Technologies Used

_Java, Gradle, Spark, Velocity, jUnit, Postgres, PSQL_

## Specifications
|Behaviors|Input|Output|
|---------|-----|------|
|Creates instance of stylist with properties|new Stylist(Colin, 678-555-4444)|newStylist|
|Creates instance of client with properties|new Client(Caitlin, 678-555-4446, 1)|newClient|
|Returns Stylists name|newStylist.getName()|Colin|
|Returns Clients name|newClient.getName()|Caitlin|
|Returns Stylist number|newStylist.getNumber()|678-555-4444|
|Returns Client number|newClient.getNumber()|678-555-4446|
|Saves clients and stylits into database|newClient.save()|-|
|Returns all clients or all stylists|Stylist.all()|Colin, Grace, Derrico|
|Find stylist or find client with id|Stylist.find(newStylist.getId())|newStylist|
|Returns all clients of a stylist|newStylist.getClients()|Caitlin, Deborah, Brian|
|Updates name and number of clients or stylits|newStylist.update(Joe, 444)|newStylist.getName() = Joe|
|Deletes client or stylist|newClient.delete()|-|
|Moves deleted stylist's clients into client queue|newClient.addToQueue()| newClient.stylist_id = 0|
|Assigns queued client to stylist|newClient.assign(newClient.getId(),1)|newClient.stylist_id = 1|




### License

*MIT*

Copyright (c) 2017 **_Colin Bloom_**

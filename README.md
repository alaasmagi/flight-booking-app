# flight-booking-webapp

## Description

* UI language: Estonian
* Development year: **2025**
* Languages and technologies: **Backend: Spring Boot, Java, JPA, SQLite Frontend: Svelte, TypeScript**

## How to run

### Prerequisites

* Java SDK 24
* Gradle v8.13
* Node.js 
* Modern web browser

### Running the app

After meeting all prerequisites above - 
* backend can be run via terminal/cmd by command
```bash
gradle run
```
* frontend can be run via terminal/cmd by commands
```bash
npm i; npm run dev 
```

## Structure

### Backend structure

* **FlightController** - Responsible for fetching all flights from DB, it is also capable of filtering the flights by origin, destination, date, departure and arrival time and by price. If there are no parameters it fetches all the flights.
* **SeatController** - Responsible for fetching and booking available seats, it is capable of filtering the seats by class, next to window, extra legroom, near exit.
* **SeatService**- Responsible for finding adjacent seats (seats next to each other) by the amount of seats to be booked.
* **DBIntializer** - Responsible for resetting the DB state to default state after every time the backend restarts.

### DB Entities
There are two entities and two tables in the DB. Entities are not related to eachother in any way. Everytime the app launches, the backend resets its database entities, seats' availability is set randomly.

* **Flight:**

```java
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
```
  
* **Seat:**

```java
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rowNumber;
    private ESeatClass seatClass;
    private char seatLetter;
    private boolean isBooked;
    private boolean isWindow;
    private boolean hasExtraLegroom;
    private boolean isNearExit;
    private double extraFee;
```

* **ESeatClass:**

```java
public enum ESeatClass {
    ECONOMY,
    BUSINESS,
    FIRSTCLASS
}
```


## Design choices

### Database  
I went for a simple approach by keeping data offline using SQLite. I tried to make database as minimal as possible by keeping things uncomplicated and the number of entities minimal. For seat class I like to use enums, because the values are set strongly and there is less chance of a mistake to occur. Each flight has a fligh-specific fee and seat has an extra fee. Extra fee depends on the class of the seat. And the total price of the journey is the sum of flight fee and seat(s') fee(s).

### Backend
Even though it's my first time writing Java and Spring Boot web app, I took the challenge and found that there is some similarities between Spring Boot and my current tech stack involving mostly the world of .NET. Java has JPAs, which were useful, so I used them and it made communication with DB a lot easier (almost as easy as with .NET Entity Framework). The overall design approach (controllers, DB entities, services) is the same I use always with my web applications. It keeps things untangled and relatively easy.

### Frontend
I have been searching for opportunity to use a new frontend framework called Svelte, which is currently not so popular, but it's simplicty is making it gain more popularity over time. 

## Scaling possibilities

### Mobile Application
* Create a dedicated iOS/Android application containing the web UI, while the backend runs on a separate server.

### Cloud Integration
* Host the backend on platforms like AWS, Azure, or GCP for scalability and reliability.
* Store the SQLite database in a cloud-hosted database service such as AWS RDS or Azure SQL Database.

## Illustrative images
### Console UI (gameboard):
![Screenshot 2025-01-11 131715](https://github.com/user-attachments/assets/6f876e79-1432-4158-87e9-b731acc44618)
### Web UI (gameboard):
![Screenshot 2025-01-11 131948](https://github.com/user-attachments/assets/2da6a125-d13e-4a65-a746-49d6112ee2a8)
### Web UI (game rules):
![Screenshot 2025-01-11 131851](https://github.com/user-attachments/assets/4970cb3a-94c7-4439-bcfb-8057b37077d2)



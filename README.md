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

## Explanation of the structure

### Backend structure

* **FlightController** - Responsible for fetching all flights from DB, it is also capable of filtering the flights by origin, destination, date, departure and arrival time and by price. If there are no parameters it fetches all the flights.
* **SeatController** - Responsible for fetching and booking available seats, it is capable of filtering the seats by class, window criteria, 

### Data Transfer Objects (DTOs) and DB Entities
DTOs are used for communicating with Power Automate (via HTTP) and SQLite database

* **GameConfig:**

```csharp
public class GameConfig
{
    public int Id { get; set; }
    public string Name { get; set; } = default!;
    public int GamePiecesPerPlayer { get; set; } = 4;
    public int BoardWidth { get; set; } = 5;
    public int BoardHeight { get; set; } = 5;
    public int GridSizeAndWinCondition { get; set; } = 3;
    public int GridStartPosX { get; set; } = 0;
    public int GridStartPosY { get; set; } = 0;
    public int RelocatePiecesAfterMoves { get; set; } = 4;

    public string ToJsonString()
    {
        return System.Text.Json.JsonSerializer.Serialize(this);
    }
}   
```
  
* **GameState:**

```csharp
public class GameState
{ 
    public int Id { get; set; }
    public EGamePiece [][] GameBoard { get; set; }
    public EGamePiece NextMoveBy { get; set; } = EGamePiece.O;
    public EGameGrid [][] GameGrid { get; set; }
    public EGameStatus CurrentStatus { get; set; }
    public GameConfig GameConfiguration { get; set; }
    public int XPiecesCount { get; set; }
    public int OPiecesCount { get; set; }

    public GameState(EGamePiece[][] gameBoard, EGameGrid[][] gameGrid, GameConfig gameConfiguration, EGameStatus currentStatus, int xPiecesCount, int oPiecesCount)
    {
        GameBoard = gameBoard;
        GameGrid = gameGrid;
        GameConfiguration = gameConfiguration;
        CurrentStatus = currentStatus;
        XPiecesCount = xPiecesCount;
        OPiecesCount = oPiecesCount;
    }

    public string ToJsonString()
    {
        return System.Text.Json.JsonSerializer.Serialize(this);
    }
}
```

* **Database entities:**

```csharp
public class ConfigurationEntity
{
    public int Id { get; set; }
    public string GameConfigName { get; set; } = "";
    public string SerializedJsonString { get; set; } = "";
}

public class SaveGameEntity
{
    public int Id { get; set; }
    public string SaveGameName { get; set; } = "";
    public string PlayerAName { get; set; } = "";
    public string PlayerBName { get; set; } = "";
    public EGameMode GameMode { get; set; }
    public string SerializedJsonString { get; set; } = "";
}
```

* **Enums:**

```csharp
public enum EGameStatus
{
    XWins,
    OWins,
    Tie,
    UnFinished
}
```

```csharp
public enum EGamePiece
{
    Empty,
    X,
    O,
}
```

```csharp
public enum EGameMode
{
    PlayerVsAi,
    PlayerVsPlayer,
    AiVsAi
}
```

```csharp
public enum EGameGrid
{
    Empty,
    Grid
}
```

### Data management
The application uses two different approaches:  
* File system approach
* Database approach

In both approaches, configuration and gamestate data is stored as serialized JSON string. Data management has an interface and both file system and database approach implement it. So the method can be changed with by changing just the few lines of code.

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



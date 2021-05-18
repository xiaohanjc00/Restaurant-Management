# Restaurant Reservation Management System

A reservation management system program based on JavaFX and H2 database implementation

Reservations are viewed in a table

## Functionality

### Main functions include:
  - Add new reservations to a selected date and time (name, table number, etc)
  - Delete reservations (same from above)
  - Edit reservations (same from above)
 
 ### Data and time functions include:
  - Choose date from date picker menu
  - Choose day or night for the corresponding reservation
  - Select corresponding range of time from a drop down list (according to day or night)
  
 ### Saving functions include:
 - Saving data into H2 embedded database
 - Separate database for deleted reservations
 
 ### Coming Soon:
 - Save each day's data into separate CSV files for portability
 - Change table view to a more visual view (displaying a map of the restaurant with tables in their locations)
 - Setting up an independent executable file for the program 

# Occupancy Optimization Tool
Occupancy Optimization Tool is a service which provides booking information
## Endpoints:
### /status
A basic health check 
### /calculateOccupancy
GET endpoint with two Integer parameters 'economyRooms' and 'premiumRooms'.
It calculates revenue and occupancy for each type of rooms.

## Quick start
This is a spring boot application. When the application is running call eg.:
http://localhost:8080/api/calculateOccupancy?premiumRooms=3&economyRooms=4
to test it.

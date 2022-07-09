# Restaurant voting system
Design and implement a REST API using Hibernate/Spring/SpringMVC  without frontend.

a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user vote again the same day:
- If it is before 11:00 we assume that he changed his mind.
- If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides a new menu each day.

## GET
- api/v1/restaurants    Get all restaurants 
- api/v1/restaurants/id Get info about restaurant with id 
- api/v1/restaurants/id/menu  today menu(need to think)
- api/v1/restaurants/id/votes -votes for the restaurant


## POST
- api/v1/restaurants/id/vote vote for the restaurant
- api/v1/admin/restaurants    add restaurant
- api/v1/admin/restaurants/id/menu  add menu


## PUT
- api/v1/admin/restaurants/id change restaurant
- api/v1/admin/restaurants/id/menu change menu
## PATCH

## DELETE
- api/v1/admin/restaurants/id
- api/v1/admin/users/id


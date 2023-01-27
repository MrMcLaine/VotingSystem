## Back end project "Voting System"
Author - [Volodymyr Lipovskiy](https://github.com/MrMcLaine)
## Running the application
 * Build package with Maven `mvn package`
### The Terms of reference for the project
Build a voting system for deciding where to have lunch.
* 2 types of users: admin and regular users Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price). 
* Menu changes each day (admins do the updates). 
* Users can vote for a restaurant they want to have lunch at today. 
* Only one vote counted per user.
* If user votes again the same day: If it is before 11:00 we assume that he changed his mind. 
* If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.
### Application description
Admins manage restaurants and upload it's menus daily.
 * There are 2 user roles: admins and regular users. 
 * Admin section is accessible by `/rest/admin/` path and user section under `/rest/profile/` path.
 * The base entities:
    - restaurants;
    - menuItems;
    - users;
    - votes
 * Admins have the following functions:
    - viewing, adding, updating, enabling/disabling and deleting users.
    - viewing, adding, updating, deleting restaurants.
    - viewing, adding, updating, deleting menuItems.
    - viewing votes of any user.
 * Users (and admins have some users) have the following abilities:
    - viewing restaurants and their menuItems.
    - voting for a restaurant.
    - viewing own actual vote.
    - changing own actual vote before 11 p.m.
    - viewing vote results.
Time is defined by default server system timezone.
### This project has security, so to get access you need to enter a login and password.

| Role  | Login(email)      | Password |
|-------|-------------------|----------|
| User  | user@gmail.com    | password |
| Admin | admin@gmail.com   | admin    |
| James | james21@gmail.com | james    |
| David | david_D@gmail.com | david    |

For simple visualization, the index.jsp is presented at the address [http://localhost:8036/voting-system/](http://localhost:8036/voting-system/)
### Curl commands to REST API
#### AdminRestController

| Role | Path                 |  Method | Description   |
|------|----------------------|---------|---------------|
| Admin| [`/rest/admin/users`]|   `GET` | Get all users |
* **Example:**

`curl -s http://localhost:8036/rest/admin/users --user admin@gmail.com:admin`

| Role | Path                      |  Method | Description    |
|------|---------------------------|---------|----------------|
| Admin| [`/rest/admin/users/{id}`]|   `GET` | Get user by id |
* **Example:**

`curl -s http://localhost:8036/rest/admin/users/100000 --user admin@gmail.com:admin`

| Role | Path                 |  Method  | Description |
|------|----------------------|----------|-------------|
| Admin| [`/rest/admin/users`]|   `POST` | Create user |
* **Example:**

`curl -s -X POST -d '{"name":"testUser","email":"test@gmail.com","password":"testPas","enabled":"true","roles":["USER"]}' -H 'Content-Type:application/json' http://localhost:8036/rest/admin/users --user admin@gmail.com:admin`\

| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| Admin| [`/rest/admin/users/{id}`]|   `PUT` | Update user |
* **Example:**

`curl -s -X PUT -d '{"name":"testTestUser","email":"test@gmail.com","password":"testPas","enabled":"true","roles":["USER"]}' -H 'Content-type: application/json' http://localhost:8036/rest/admin/users/100020 --user admin@gmail.com:admin`


| Role | Path                           |  Method | Description    |
|------|--------------------------------|---------|----------------|
| Admin| [`/rest/admin/users/by-email`] |   `GET` | Get user by id |
* **Example:**

`curl -s http://localhost:8036/rest/admin/users/by-email?email=user@gmail.com --user admin@gmail.com:admin`


| Role | Path                      |  Method   | Description        |
|------|---------------------------|-----------|--------------------|
| Admin| [`/rest/admin/users/{id}`]|   `PATCH` | En-/disable user   |

* **URL Params**\
    enabled=boolean
* **Example:**

`curl -s -X PATCH http://localhost:8036/rest/admin/users/100000?enabled=false --user admin@gmail.com:admin`

| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| Admin| [`/rest/admin/users/{id}`]|`DELETE` | Delete user |
* **Example:**

`curl -s -X DELETE http://localhost:8036/rest/admin/users/100000 --user admin@gmail.com:admin`

#### MealRestController

| Role | Path                                   | Method | Description                          |
|------|----------------------------------------|--------|--------------------------------------|
| Admin| [`/rest/admin/restaurants/{id}/menuItems`] | `POST` | Create new Meal for restaurant by id |

* **Example:**

`curl -s -X POST -d '{"description":"Some Meal","price":8}' -H 'Content-Type:application/json' http://localhost:8036/rest/admin/restaurants/100006/menuItems --user admin@gmail.com:admin`\


| Role | Path                                            |  Method | Description                      |
|------|-------------------------------------------------|---------|----------------------------------|
| Admin| [`/rest/admin/restaurants/{id}/menuItems/{mealId}`] |   `PUT` | Update Meal for restaurant by id |
* **Example:**

`curl -s -X PUT -d '{""dateMeal": "2023-01-09","description": "gazpacho11111","price": 28}' -H 'Content-type: application/json' http://localhost:8036/rest/admin/restaurants/100005/menuItems/100010 --user admin@gmail.com:admin`


| Role | Path                                            |  Method | Description                      |
|------|-------------------------------------------------|---------|----------------------------------|
| Admin| [`/rest/admin/restaurants/{id}/menuItems/{mealId}`] |`DELETE` | Delete Meal for restaurant by id |
* **Example:**

`curl -s -X DELETE http://localhost:8036/rest/admin/restaurants/100006/menuItems/100013 --user admin@gmail.com:admin`


| Role | Path                                   |  Method | Description                          |
|------|----------------------------------------|---------|--------------------------------------|
| Admin| [`/rest/admin/restaurants/{id}/menuItems`] |   `GET` | Get actual menu for restaurant by id |
* **Example:**

`curl -s http://localhost:8036/rest/admin/restaurants/100005/menuItems --user admin@gmail.com:admin`


| Role | Path                                            |  Method | Description                              |
|------|-------------------------------------------------|---------|------------------------------------------|
| Admin| [`/rest/admin/restaurants/{id}/menuItems/{mealId}`] |   `GET` | Get menuItems by mealId for restaurant by id |
* **Example:**

`curl -s http://localhost:8036/rest/admin/restaurants/100005/menuItems/100022 --user admin@gmail.com:admin`

#### RestaurantRestController

| Role | Path                       |  Method  | Description       |
|------|----------------------------|----------|-------------------|
| Admin| [`/rest/admin/restaurants`]|   `POST` | Create restaurant |
* **Example:**

`curl -s -X POST -d '{"name":"New 11Rest"}' -H 'Content-Type:application/json' http://localhost:8036/rest/admin/restaurants --user admin@gmail.com:admin`\

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}`]|   `PUT` | Update restaurant |
* **Example:**

`curl -s -X PUT -d '{"name":"updateName"}' -H 'Content-type: application/json' http://localhost:8036/rest/admin/restaurants/100021 --user admin@gmail.com:admin`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}`]|`DELETE` | Delete restaurant |
* **Example:**

`curl -s -X DELETE http://localhost:8036/rest/admin/restaurants/100023 --user admin@gmail.com:admin`

| Role | Path                         |  Method | Description         |
|------|------------------------------|---------|---------------------|
| Admin| [`/rest/admin/restaurants`]  |   `GET` | Get all restaurants |

* **Example:**

`curl -s http://localhost:8036/rest/admin/restaurants --user admin@gmail.com:admin`

| Role | Path                                     |  Method | Description                                |
|------|------------------------------------------|---------|--------------------------------------------|
| Admin| [`/rest/admin/restaurants/{id}/history`] |   `GET` | Get restaurant by id with history of Meals |
* **Example:**

`curl -s http://localhost:8036/rest/admin/restaurants/100006/history --user admin@gmail.com:admin`

| Role | Path                                            |  Method | Description                                |
|------|-------------------------------------------------|---------|--------------------------------------------|
| Admin| [`/rest/admin/restaurants/{id}/history/{date}`] |   `GET` | Get restaurant by id with history of Meals |
* **Example:**

`curl -s http://localhost:8036/rest/admin/restaurants/100006/history/2022-12-31 --user admin@gmail.com:admin`

#### ProfileRestController

| Role | Path              |  Method | Description    |
|------|-------------------|---------|----------------|
| User | [`/rest/profile`] |   `GET` | Get user by id |
* **Example:**

`curl -s http://localhost:8036/rest/profile --user user@gmail.com:password`

| Role | Path             |  Method | Description |
|------|------------------|---------|-------------|
| User | [`/rest/profile`]|   `PUT` | Update user |
* **Example:**

`curl -s -X PUT -d '{"name":"testTestUser","email":"test@gmail.com","password":"testPas","enabled":"true","roles":["USER"]}' -H 'Content-type: application/json' http://localhost:8036/rest/profile --user user@gmail.com:password`

| Role | Path             |  Method | Description |
|------|------------------|---------|-------------|
| User | [`/rest/profile`]|`DELETE` | Delete user |
* **Example:**

`curl -s -X DELETE http://localhost:8036/rest/profile --user user@gmail.com:password`

#### RestaurantProfileRestController


| Role | Path                          |  Method | Description         |
|------|-------------------------------|---------|---------------------|
| User | [`/rest/profile/restaurants`] |   `GET` | Get all restaurants |

* **Example:**

`curl -s http://localhost:8036/rest/profile/restaurants --user user@gmail.com:password`


| Role | Path                                    |  Method | Description                    |
|------|-----------------------------------------|---------|--------------------------------|
| User | [`/rest/profile/restaurants/withMeals`] |   `GET` | Get all restaurants with Meals |

* **Example:**

`curl -s http://localhost:8036/rest/profile/restaurants/withMeals --user user@gmail.com:password`


| Role | Path                               |  Method | Description |
|------|------------------------------------|---------|-------------|
| User | [`/rest/profile/restaurants/{id}`] |   `GET` | Get by id   |

* **Example:**

`curl -s http://localhost:8036/rest/profile/restaurants/100005 --user user@gmail.com:password`


| Role | Path                                         |  Method | Description          |
|------|----------------------------------------------|---------|----------------------|
| User | [`/rest/profile/restaurants/{id}/withMeals`] |   `GET` | Get by id with Meals |

* **Example:**

`curl -s http://localhost:8036/rest/profile/restaurants/100004/withMeals --user user@gmail.com:password`

#### VoteRestController

| Role | Path                         |  Method  | Description |
|------|------------------------------|----------|-------------|
| User | [`/rest/profile/votes/{id}`] |   `POST` | Vote        |
* **Example:**

`curl -s -X POST -d '' -H 'Content-Type:application/json' http://localhost:8036/rest/profile/votes/100005 --user user@gmail.com:password`\


| Role | Path                         |  Method | Description |
|------|------------------------------|---------|-------------|
| User | [`/rest/profile/votes/{id}`] |   `PUT` | Update vote |
* **Example:**

`curl -s -X PUT -d '' -H 'Content-type: application/json' http://localhost:8036/rest/profile/votes/100006 --user user@gmail.com:password`


| Role | Path                          |  Method | Description    |
|------|-------------------------------|---------|----------------|
| User | [`/rest/profile/votes/today`] |   `GET` | Get today vote |

* **Example:**

`curl -s http://localhost:8036/rest/profile/votes/today --user user@gmail.com:password`


| Role | Path                         |  Method | Description                       |
|------|------------------------------|---------|-----------------------------------|
| User | [`/rest/profile/votes/{id}`] |   `GET` | Get all today votes by restaurant |

* **Example:**

`curl -s http://localhost:8036/rest/profile/votes/100006 --user user@gmail.com:password`

Technology stack:
- Rest APi
- Spring Web MVC
- Spring Data JPA
- Spring Security
- HSQLDB
- Junit5
- The logging
- Hibernate
- ehcache

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <style>
        a {
            border-style: ridge;
            border-color: brown;
            background-color: lightgoldenrodyellow;
            border-radius: 5px;
        }

        a:hover {
            background-color: deepskyblue;
        }

        li {
            margin: 10px;
        }

        .div {
            height: 90%;
            width: 49%;
            float: left;
            border-style: solid;
            border-color: skyblue;
        }

        .user {
            background-color: antiquewhite;
        }

        .admin {
            background-color: moccasin;
        }

        .deal {
            background-color: greenyellow;
        }

        .dealWithoutVisual {
            background-color: limegreen;
        }
    </style>
</head>

<body>

<div class="user div">
    <h2>Options for User</h2>
    <li><a href="/rest/profile" class="deal">User profile</a></li>
    <li><a href="" class="dealWithoutVisual">Update user's profile</a></li>
    <li><a href="" class="dealWithoutVisual">Delete user's profile</a></li>
    <li><a href="/rest/restaurants" class="deal">All restaurants</a></li>
    <li><a href="/rest/restaurants/withMenu" class="deal">The restaurants with
        menu</a></li>
    <li><a href="/rest/restaurants/100005" class="deal">Get restaurant by
        ID(for example => ID = 100005)</a></li>
    <li><a href="/rest/restaurants/100004/withMenu" class="deal">Get restaurant by ID
        with menu(for example - 100005)</a></li>
    <br>
    <li><a href="" class="dealWithoutVisual">Vote to restaurantId</a></li>
    <li><a href="" class="dealWithoutVisual">Change vote to restaurantId</a></li>
    <li><a href="/rest/profile/votes/today" class="deal">Today user's vote</a></li>
    <li><a href="/rest/profile/votes?restaurantId=100005" class="deal">All today votes by restaurant(for example - restaurant with
        ID=100005)</a></li>
    <br>
    <li><a href="/rest/profile/votes?restaurantId=100005" class="deal">History user's votes by restaurant(for example - 1000005)</a>
    </li>
</div>

<div class="admin div">
    <h2>Options for Admin</h2>
    <li><a href="" class="dealWithoutVisual">Create User</a></li>
    <li><a href="" class="dealWithoutVisual">Update User</a></li>
    <li><a href="" class="dealWithoutVisual">Delete User</a></li>
    <li><a href="/rest/admin/users/100003" class="deal">Get User by ID(for example 100003</a></li>
    <li><a href="/rest/admin/users/by-email?email=david_D@gmail.com" class="deal">Get User by Email(for example -
        david_D@gmail.com)</a></li>
    <li><a href="/rest/admin/users" class="deal">All users</a></li>
    <br>
    <li><a href="" class="dealWithoutVisual">Create restaurant</a></li>
    <li><a href="" class="dealWithoutVisual">Update restaurant</a></li>
    <li><a href="" class="dealWithoutVisual">Delete restaurant</a></li>
    <li><a href="/rest/admin/restaurants" class="deal">Get all restaurants</a></li>
    <br>
    <li><a href="" class="dealWithoutVisual">Create menuItem</a></li>
    <li><a href="" class="dealWithoutVisual">Update menuItem</a></li>
    <li><a href="" class="dealWithoutVisual">Delete menuItem</a></li>
    <li><a href="/rest/admin/restaurants/100005/menu" class="deal">Get actual menuItems (for example - by restaurant with
        ID = 100005)</a></li>
    <li><a href="/rest/admin/restaurants/100005/menu/100010" class="deal">Get menuItem by ID (for example - by restaurant
        with ID = 100005, menuItem ID = 100010)</a></li>
    <br>
    <li><a href="/rest/admin/restaurants/100006/history" class="deal">Get history of restaurant's menu by restaurant's
        ID(for example - 100006)</a></li>
    <li><a href="/rest/admin/restaurants/100006/history/2022-12-31" class="deal">Get history of restaurant's menu by
        restaurant's ID by date(for example - 100006 & 2022-12-31)</a></li>
    <br>
</div>
</body>
</html>

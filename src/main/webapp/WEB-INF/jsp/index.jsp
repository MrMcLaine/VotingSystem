<%@ page contentType="text/html;charset=UTF-8" %>
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

        div {
            height: 80%;
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
            background-color: green;
        }
    </style>
</head>

<body>

<div class="user">
    <h2>Options for User</h2>
    <li><a href="${pageContext.request.contextPath}/rest/profile">User profile</a></li>
    <li><a href="">Update user's profile</a></li>
    <li><a href="">Delete user's profile</a></li>
    <li><a href="${pageContext.request.contextPath}/rest/profile/restaurants" class="deal">All restaurants</a></li>
    <li><a href="${pageContext.request.contextPath}/rest/profile/restaurants?withMenu=true">The restaurants with
        menu</a></li>
    <li><a href="${pageContext.request.contextPath}/rest/profile/restaurants/100005" class="deal">Get restaurant by
        ID(for example => ID = 100005)</a></li>
    <li><a href="${pageContext.request.contextPath}/rest/profile/restaurants/{id}?withMenu=true" class="deal">Get restaurant by ID
        with menu</a></li>
    <br>
    <li><a href="">Give vote by restaurantId</a></li>
    <li><a href="/rest/profile/votes/today">Today user's vote</a></li>
    <li><a href="">User's votes</a></li>
    <br>
    <li><a href="">Get history of restaurant's menu by restaurant's ID</a></li>
    <li><a href="">Get history of restaurant's menu by restaurant's ID by date</a></li>
    <br>
    <li><a href="">History user's votes</a></li>
    <li><a href="">History user's votes by date</a></li>
</div>

<div class="admin">
    <h2>Options for Admin</h2>
    <li><a href="">Create User</a></li>
    <li><a href="">Update User</a></li>
    <li><a href="">Delete User</a></li>
    <li><a href="${pageContext.request.contextPath}/rest/admin/users/100003" class="deal">Get User by ID(for evample 100003</a></li>
    <li><a href="${pageContext.request.contextPath}/rest/admin/users/by-email?email=david_D@gmail.com" class="deal">Get User by Email(for example - david_D@gmail.com)</a></li>
    <li><a href="${pageContext.request.contextPath}/rest/admin/users" class="deal">All users</a></li>
    <br>
    <li><a href="">Create restaurant</a></li>
    <li><a href="">Update restaurant</a></li>
    <li><a href="">Delete restaurant</a></li>
    <br>
    <li><a href="">Create meal</a></li>
    <li><a href="">Update meal</a></li>
    <li><a href="">Delete meal</a></li>
    <li><a href="/rest/admin/restaurants/100005/meals" class="deal">Get actual meals (for example - by restaurant with ID = 100005)</a></li>
    <li><a href="/rest/admin/restaurants/100005/meals/100010" class="deal">Get meal by ID (for example - by restaurant with ID = 100005, meal ID = 100010)</a></li>
</div>

</body>
</html>

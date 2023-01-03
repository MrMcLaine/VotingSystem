<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <style>
        a {
            margin: 20px;
            border-style: dotted;
            border-bottom-color: aqua;
        }
    </style>
</head>
<body>

<a href="${pageContext.request.contextPath}/rest/admin/users">Users</a>
<a href="${pageContext.request.contextPath}/rest/profile/restaurants">The restaurants</a>
<a href="${pageContext.request.contextPath}/rest/profile/restaurants?withMenu=true">The restaurants with menu</a>


</body>
</html>

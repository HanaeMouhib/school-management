<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Teachers</title>
</head>
<body>
    <h1>Teacher List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <c:forEach var="teacher" items="${teachers}">
            <tr>
                <td>${teacher.id}</td>
                <td>${teacher.name}</td>
                <td>${teacher.email}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Add Teacher</h2>
    <form method="post" action="teachers">
        Name: <input type="text" name="name" required><br>
        Email: <input type="email" name="email" required><br>
        <button type="submit">Add</button>
    </form>
</body>
</html>

<html>
<head>
    <title>Courses</title>
</head>
<body>
    <h1>Course List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
        </tr>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td>${course.id}</td>
                <td>${course.name}</td>
                <td>${course.description}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Add Course</h2>
    <form method="post" action="courses">
        Name: <input type="text" name="name"><br>
        Description: <input type="text" name="description"><br>
        <button type="submit">Add</button>
    </form>
</body>
</html>
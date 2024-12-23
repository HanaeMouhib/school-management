
<html>
<head>
    <title>Students</title>
</head>
<body>
    <h1>Student List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Age</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.email}</td>
                <td>${student.age}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Add Student</h2>
    <form method="post" action="students">
        Name: <input type="text" name="name" required><br>
        Email: <input type="email" name="email" required><br>
        Age: <input type="number" name="age" required><br>
        <button type="submit">Add</button>
    </form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.labs.domain.Country" %>

<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Страны</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Curren</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- jQuery -->
    <script defer src="js/jquery-3.6.4.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid">
        <div class="row justify-content-start ">
            <div class="col-8 border bg-light px-4">
                <h3>Список стран</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Код</th>
                        <th scope="col">Короткое название</th>
                        <th scope="col">Длинное название</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:useBean id="countries" scope="request" type="java.util.List"/>
                    <c:forEach var="country" items="${countries}">
                        <tr>
                            <td>${country.getId()}</td>
                            <td>${country.getFullName()}</td>
                            <td>${country.getShortName()}</td>
                            <td width="20"><a href="#" role="button" class="btn btn-outline-primary">
                                <img alt="Редактировать" src="/images/icon-edit.png" width="20" height="20"></a></td>
                            <td width="20"><a href="#" role="button" class="btn btn-outline-primary">
                                <img alt="Удалить" src="/images/icon-delete.png" width="20" height="20"></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4">
                <form method="POST" action="">
                    <h3>Новая страна</h3>
                    <div class="mb-1">
                        <br> <label for="fullName"
                                    class="col-sm-3 col-form-label">Full Name</label>
                        <div class="col-sm-6">
                            <input type="text" name="inputFullName" class="form-control" id="fullName" />
                        </div>
                    </div>
                    <div class="mb-1">
                        <br> <label for="shortName"
                                    class="col-sm-3 col-form-label">Short Name</label>
                        <div class="col-sm-6">
                            <input type="text" name="inputShortName" class="form-control" id="shortName" />
                        </div>
                    </div>

                    <p>
                        <br> <br> <br>
                        <button type="submit" class="btn btn-primary">Добавить</button>
                        <br>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>

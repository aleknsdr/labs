<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.labs.domain.Country" %>

<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Города</title>
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
                <h3>Список адрессов</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Код</th>
                        <th scope="col">Имя</th>
                        <th scope="col">Улица</th>
                        <th scope="col">Строение</th>
                        <th scope="col">Офис</th>
                        <th scope="col">Город</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:useBean id="addresses" scope="request" type="java.util.List"/>
                    <c:forEach var="address" items="${addresses}">
                        <tr>
                            <td>${address.getId()}</td>
                            <td>${address.getPerson()}</td>
                            <td>${address.getStreet()}</td>
                            <td>${address.getBuilding()}</td>
                            <td>${address.getOffice()}</td>
                            <td>${address.getCity()}</td>
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
                    <h3>Новый адресс</h3>
                    <div class="mb-1">
                        <br> <label for="person"
                                    class="col-sm-3 col-form-label">Имя</label>
                        <div class="col-sm-6">
                            <input type="text" name="inputPerson" class="form-control" id="person" />
                        </div>
                    </div>
                    <div class="mb-1">
                        <br> <label for="street"
                                    class="col-sm-3 col-form-label">Улица</label>
                        <div class="col-sm-6">
                            <input type="text" name="inputStreet" class="form-control" id="street" />
                        </div>
                    </div>
                    <div class="mb-1">
                        <br> <label for="building"
                                    class="col-sm-3 col-form-label">Стронение</label>
                        <div class="col-sm-6">
                            <input type="text" name="inputBuilding" class="form-control" id="building" />
                        </div>
                    </div>
                    <div class="mb-1">
                        <br> <label for="office"
                                    class="col-sm-3 col-form-label">Офис</label>
                        <div class="col-sm-6">
                            <input type="text" name="inputOffice" class="form-control" id="office" />
                        </div>
                    </div>
                    <div class="mb-1">
                        <br> <label for="cityId"
                                    class="col-sm-3 col-form-label">Код города</label>
                        <div class="col-sm-6">
                            <input type="text" name="inputCityId" class="form-control" id="cityId" />
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Регионы</title>
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
                <h3>Список регионов</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Код</th>
                        <th scope="col">Название</th>
                        <th scope="col">Страна</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:useBean id="regions" scope="request" type="java.util.List"/>
                    <c:forEach var="region" items="${regions}">
                        <tr>
                            <td>${region.getId()}</td>
                            <td>${region.getNameRegion()}</td>
                            <td>${region.getCountry()}</td>
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
                    <h3>Новый регион</h3>
                    <div class="mb-1">
                        <br> <label for="nameRegion"
                                    class="col-sm-3 col-form-label">Название</label>
                        <div class="col-sm-6">
                            <input type="text" name="inputNameRegion" class="form-control" id="nameRegion" />
                        </div>
                    </div>
                    <!--<div class="mb-1">
                        <br> <label for="countryId"
                                    class="col-sm-3 col-form-label">Код страны</label>
                        <div class="col-sm-6">
                            <input type="text" name="inputCountryId" class="form-control" id="countryId" />
                        </div>
                    </div>-->
                    <div class="mb-1">
                        <br> <label for="country"
                                    class="col-sm-3 col-form-label">Страна</label>
                        <div class="col-sm-6">
                           <select name="country" class="form-control" id="country">
                               <option>Выберите страну</option>
                               <jsp:useBean id="countries" scope="request" type="java.util.List"/>
                               <c:forEach var="country" items="${countries}">
                                   <option value="${country}">
                                       <c:out value="${country.getFullName()}"></c:out>
                                   </option>
                               </c:forEach>
                           </select>
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

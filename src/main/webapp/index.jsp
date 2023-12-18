<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css/bootstrap.min.css">
<head>
  <title>Ведение адресов клиентов</title>
</head>
<body>
<div class="container-fluid">
  <jsp:include page="views/header.jsp"/>
  <div class="container">
    <br><br><br>
    <div class="list-group text-center py-3 px-3">
      <h2>Функции системы</h2>
      <ul class="list-group list-group-flush">
        <li class="list-group-item list-group-item-primary"><a href="/labs_war_exploded/address-servlet">Адресса</a></li>
        <li class="list-group-item list-group-item-primary"><a href="/labs_war_exploded/country-servlet">Страны</a></li>
        <li class="list-group-item list-group-item-primary"><a href="/labs_war_exploded/region-servlet">Регионы</a></li>
        <li class="list-group-item list-group-item-primary"><a href="/labs_war_exploded/city-servlet">Города</a></li>
      </ul>
    </div>
  </div>
  <br><br>
</div>
<jsp:include page="views/footer.jsp"/>
<script defer src="js/jquery-3.7.1.js"></script>
<script defer src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="Rent" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<Rent:url value='/resources/js/jquery-3.7.1.min.js' />"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="<Rent:url value="/resources/css/color_orange.css" />">
<title>결제 성공</title>
	<style>
        body{
            margin:300px auto;
            text-align:center;
        }
        h2{
            margin:40px 0 40px 0;
        }
    </style>
</head>
<body>
	<img src="<Rent:url value="/resources/img/succes.png" />" alt="결제 성공!">
    <h2>결제에 성공했습니다.</h2>
    <form action="index.jsp">
        <button type="submit" class="btn btn-primary">메인 페이지 이동</button>
    </form>
</body>
</html>
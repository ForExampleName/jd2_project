<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Ошибка</title>
        <link rel="icon" type="image/x-icon" href='<c:url value="/resources/data/icon.ico"/>'>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,700;1,100;1,300;1,400&display=swap" rel="stylesheet"> 
        <link href='<c:url value="/resources/css/login.css"/>' rel="stylesheet">
    </head>
    
    <body>
        <div class="error-page">
            <div class="main-container">
                <div class="header">
                    <h1 class="page-name">Ошибка</h1>
                </div>
                <div class="message-container">
                    <p class="message">
                        Извините, произошла непредвиденная ошибка. Попробуйте еще раз
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>
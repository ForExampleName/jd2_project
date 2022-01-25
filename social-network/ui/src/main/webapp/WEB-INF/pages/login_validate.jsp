<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Подтверждение</title>
        <link rel="icon" type="image/x-icon" href='<c:url value="/resources/data/icon.ico"/>'>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,700;1,100;1,300;1,400&display=swap" rel="stylesheet"> 
        <link href='<c:url value="/resources/css/login.css"/>' rel="stylesheet">
    </head>
    <body>
        <div class="validation-page">
            <div class="container">
                <div class="header">
                    <h1 class="page-name">Подтверждение</h1>
                </div>
                
                <f:form class="form" method="post" autocomplete="off" action="/ui/user_recovery/first_phase.do" modelAttribute="login" novalidate="novalidate">
                    <label for="answer">Введите ваш логин:</label>
                    <input id="answer" type="text" name="login" value="" maxlength="255" placeholder="Логин" required="required"/>
                    <input class="submit-btn" type="submit" value="Подтвердить"> 
                </f:form>  
            </div>
        </div>
        <script src='<c:url value="/resources/js/user_validate.js"/>' charset="utf-8"></script>

        <c:if test="${not empty error}">
            <div id="notification-modal" class="modal">
                <div class="modal-window">
                    <div class="header">
                        <p class="description">Ошибка</p>
                    </div>
    
                    <p class="modal-message">${error}</p>
                    <button id="notification-btn" class="close-btn">ОК</button> 
                </div>
            </div>
            <script src='<c:url value="/resources/js/modal.js"/>'></script>
        </c:if>
    </body>
</html>
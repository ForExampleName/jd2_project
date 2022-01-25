<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Авторизация</title>
        <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
        <link rel="icon" type="image/x-icon" href='<c:url value="/resources/data/icon.ico"/>'>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,700;1,100;1,300;1,400&display=swap" rel="stylesheet"> 
        <link href='<c:url value="/resources/css/login.css"/>' rel="stylesheet">
    </head>    
    <body>
        <div class="login-page">
            <div class="container">
                <p class="logo">Share</p>
                <h1 class="page-name">Авторизация</h1>
                <f:form class="form" method="post" action="/ui/login.do" autocomplete="off" modelAttribute="loginCommand">
                    <div class="form-item">
                        <i class="material-icons form-icon">person</i>
                        <f:input type="text" path="login" name="login" value="" maxlength="255" placeholder="Логин" required="required"/>
                    </div>
    
                    <div class="form-item">
                        <i class="material-icons form-icon">lock</i>
                        <f:input type="password" path="password" name="password" value="" minlength="8" maxlength="255" placeholder="Пароль" required="required"/>
                    </div>
    
                    <a class="custom-link password-change" href="/ui/user_recovery/first_phase.html">
                       Забыли пароль?
                    </a>
                    
                    <input class="signin-btn" type="submit" value="Войти">
                </f:form>
                
                <div class="signup-invitation">
                    <p class="description">Еще не зарегистрированы?</p>
                    <a class="custom-link" href="/ui/signup.html">Регистрация</a>
                </div>        
            </div>
        </div>    

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
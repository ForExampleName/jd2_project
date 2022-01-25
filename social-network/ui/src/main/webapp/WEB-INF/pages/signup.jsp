<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
        <title>Регистрация</title>

        <link rel="icon" type="image/x-icon" href='<c:url value="/resources/data/icon.ico"/>'>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,700;1,100;1,300;1,400&display=swap" rel="stylesheet"> 
        <link href='<c:url value="/resources/css/login.css"/>' rel="stylesheet">
    </head>
    <body>
        <div class="signup-page">
            <div class="main-container">
                <h1 class="page-name">Регистрация</h1>
                <f:form id="signup-form" class="form" method="post" autocomplete="off" enctype="multipart/form-data" action="signup.do" modelAttribute="signUpCommand" accept-charset="utf-8" novalidate="novalidate">
                    <section class="signup-section">
                        <h2 class="section-name">Персональные данные</h2>
                        <div class="inputs-row-container">
                            <div class="form-item">
                                <f:label for="firstName" path="firstName">Имя* :</f:label>
                                <f:input id="firstName" type="text" path="firstName" name="firstName" value="" required="required" minlength="1" maxlength="255" placeholder="Имя"/>  
                            </div>
                            <div class="form-item">
                                <f:label for="lastName" path="lastName">Фамилия* :</f:label>
                                <f:input id="lastName" type="text" path="lastName" name="lastName" value="" required="required" minlength="1" maxlength="255" placeholder="Фамилия"/>
                            </div>
                        </div>
                        <div class="warning-container">
                            <p id="first-name-warn" class="warning"></p>
                            <p id="last-name-warn" class="warning"></p> 
                        </div>
                        
                        <div class="inputs-row-container">
                            <div class="form-item">
                                <f:label for="birthday" path="birthday">Дата рождения* :</f:label>
                                <f:input id="birthday" type="date" path="birthday" name="birthday" value="" required="required" placeholder="День рождения"/>
                            </div>
                            <div class="form-item">
                                <f:label for="gender" path="gender">Пол* :</f:label>
                                <f:select id="gender" path="gender" name="gender" required="required">
                                    <f:option value="" selected="selected">Не указан</f:option>
                                    <f:option value="Мужской">Мужской</f:option>
                                    <f:option value="Женский">Женский</f:option>
                                </f:select> 
                            </div>
                        </div>
                        <div class="warning-container">
                            <p id="birthday-warn" class="warning"></p>
                            <p id="gender-warn" class="warning"></p>
                        </div>
        
                        
                        <div class="inputs-row-container">
                            <div class="form-item">
                                <f:label for="email" path="email">E-mail* :</f:label>
                                <f:input id="email" type="text" path="email" name="email" value="" required="required" minlength="5" maxlength="255" placeholder="E-mail" pattern="[a-zA-Z]+@[a-zA-Z]+\.[a-zA-Z]+"/>
                            </div>
                            <div class="form-item">
                                <f:label for="phone" path="phone">Телефон* :</f:label>
                                <f:input id="phone" type="text" path="phone" name="phone" value="" required="required" minlength="9" maxlength="17" placeholder="Телефон" pattern="(\+\d{1,3}\(\d{2}\))?\d{3}-\d{2}-\d{2}"/>
                            </div>
                        </div>
                        <div class="warning-container">
                            <p id="e-mail-warn" class="warning"></p>
                            <p id="phone-warn" class="warning"></p>
                        </div>
                        
                        <div class="inputs-row-container">
                            <div class="form-item">
                                <label for="avatar" >Аватар:</label>
                                <input id="avatar" type="file" name="avatar" value="" accept=".jpeg, .jpg"/>
                            </div>
                            <div class="form-item">
                                <button id="avatar-reset-btn">Сбросить</button>
                            </div>
                        </div>
                        <div class="warning-container">
                            <p id="avatar-warn" class="warning"></p>
                        </div>
                        
                        <div class="inputs-row-container">
                            <div class="form-item">
                                <f:label for="country" path="country">Страна:</f:label>
                                <f:input id="country" type="text" path="country" name="country" value="" minlength="1" maxlength="255" placeholder="Страна"/>
                            </div>
                            <div class="form-item">
                                <f:label for="city" path="city">Город:</f:label>
                                <f:input id="city" type="text" path="city" name="city" value="" minlength="1" maxlength="255" placeholder="Город"/>
                            </div>
                        </div>
                        <div class="warning-container">
                            <p id="country-warn" class="warning"></p>
                            <p id="city-warn" class="warning"></p>
                        </div>
                    </section>
    
                    <div class="horizontal-border"></div>
    
                    <section  class="security-section">
                        <h2 class="section-name">Безопастность</h2>
                        <div class="inputs-row-container">
                            <div class="form-item" style="width: 100%;">
                                <f:label for="login" path="login">Логин* :</f:label>
                                <f:input id="login" type="text" path="login" name="login" value="" required="required" minlength="1" maxlength="255" placeholder="Логин" pattern="[\da-zA-Z_]+"/>
                            </div>
                        </div>
                        <div class="warning-container">
                            <p id="login-warn" class="warning"></p>
                        </div>
                        
                        <div class="inputs-row-container">
                            <div class="form-item">
                                <f:label for="password" path="password">Пароль* :</f:label>
                                <f:input id="password" type="password" path="password" name="password" value="" required="required" minlength="8" maxlength="255" placeholder="Новый пароль" pattern="[\da-zA-Z_]+"/>
                            </div>
                            <div class="form-item">
                                <f:label for="repeatPassword" path="repeatPassword">Повторите* :</f:label>
                                <f:input id="repeatPassword" type="password" path="repeatPassword" name="repeatPassword" value="" required="required" minlength="8" maxlength="255" placeholder="Новый пароль" pattern="[\da-zA-Z_]+"/>
                            </div>
                        </div>
                        <div class="warning-container">
                            <p id="password-warn" class="warning"></p>
                            <p id="repeat-warn" class="warning"></p>
                        </div>
    
                        <div class="inputs-row-container">
                            <div class="form-item" style="width: 100%;">
                                <f:label for="question" path="questionId">Секретный вопрос* :</f:label>
                                <f:select id="question" path="questionId" name="question" required="required">
                                    <f:option value="" selected="selected">Не указан</f:option>
                                    <c:forEach var="item" items="${questions}">
                                        <f:option value="${item.id}">${item.question}</f:option>
                                    </c:forEach>
                                </f:select> 
                            </div>
                        </div>
                        <div class="warning-container">
                            <p id="question-warn" class="warning"></p>
                        </div>
    
                        <div class="inputs-row-container">
                            <div class="form-item">
                                <f:label for="answer" path="answer">Ответ* :</f:label>
                                <f:input id="answer" type="text" path="answer" name="answer" value="" required="required" minlength="1" maxlength="255" placeholder="Ответ"/>
                            </div>
                        </div>
                        <div class="warning-container">
                            <p id="answer-warn" class="warning"></p>
                        </div>
                    </section>
                </f:form> 
    
                <input class="submit-btn" type="submit" form="signup-form" value="Зарегистрироваться"> 
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
            <script src="resources/js/modal.js"></script>
        </c:if>

        
        <!-- <script src='<c:url value="/resources/js/input_validator.js"/>' charset="utf-8"></script>
        <script src='<c:url value="/resources/js/signup.js"/>' charset="utf-8"></script> -->
    </body>
</html>
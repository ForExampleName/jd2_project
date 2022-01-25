<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Подписки</title>
        <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
        <link rel="icon" type="image/x-icon" href='<c:url value="/resources/data/icon.ico"/>'>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,700;1,100;1,300;1,400&display=swap" rel="stylesheet"> 
        <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet">
    </head>
    <body>
        <div class="mobile-nav">
            <div id="mobile-menu-button" class="menu-button">
                <i class="material-icons menu-icon">menu</i>
            </div>
            <div class="vertical-border"></div>
            <p class="mobile-nav-logo">Share</p>
            <span class="right-side-container">
                <span class="light-mode-container">
                    <i class="material-icons dark-mode-icon">dark_mode</i>
                    <i class="material-icons bright-mode-icon">light_mode</i>
                    <input type="checkbox" class="light-mode-switch" id="light-mode-switch-mobile">
                    <div class='ball'></div>    
                </span>
            </span>
        </div>

        <div id="sidebar" class="sidebar">
            <p class="sidebar-logo">Share</p>
            <div class="horizontal-border"></div>
            <nav class="navigation-list">
                <a class="nav-item" href="/ui/my_page.do">
                    <i class="material-icons sidebar-icon">home</i>
                    <p class="description">Моя страница</p>
                </a>
                <a class="nav-item" href="/ui/my_subscriptions.do">
                    <i class="material-icons sidebar-icon">people</i>
                    <p class="description">Подписки</p>
                </a>
                <a class="nav-item" href="/ui/search.do">
                    <i class="material-icons sidebar-icon">groups</i>
                    <p class="description">Пользователи</p>
                </a>
            </nav>

            <div class="account-utils">
                <div class="account-managment">
                    <a id="account-exit" class="management-item" href="/ui/logout.do">
                        <span class="description">Выйти</span>
                    </a>
                    <div id="account-dropdown" class="management-item">
                        <img class="sidebar-avatar" src="${avatar}" width="32" height="32">
                        <span class="description">Аккаунт</span>
                        <i class="material-icons sidebar-icon">expand_more</i>
                    </div>
                </div>
                <div class="light-mode">
                    <p class="description">Изменить тему:</p>
                    <div class="light-mode-container">
                        <i class="material-icons dark-mode-icon">dark_mode</i>
                        <i class="material-icons bright-mode-icon">light_mode</i>
                        <input type="checkbox" class="light-mode-switch" id="light-mode-switch-sidebar">
                        <div class='ball'></div>    
                    </div>
                </div>
            </div>
        </div>

        <main class="content users-page">
            <div class="content-item">
                <div class="user-list-container">
                    <div class="header">
                        <p class="description">Подписки</p>
                    </div>
                    <c:choose>
                        <c:when test="${not empty users}">
                            <div class="user-list">
                                <c:forEach var="user" items="${users}">
                                    <a class="user-item" href="/ui/${user.getId()}/user_page.html">
                                        <img class="avatar" src="${user.getAvatar()}" width="120" height="120">
                                        <div class="name-container">
                                            <p class="name-item">${user.getFirstName()}</p>
                                            <p class="name-item">${user.getLastName()}</p>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="user-list-empty">
                                <p class="description">Список пустой</p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div id="pagination-block" class="content-item">
                <div class="pagination-container">
                    <a class="pagination-item" style="pointer-events: none">
                        <i class="material-icons">arrow_back</i>
                    </a>
                    <a class="pagination-item">
                        <p>1</p>
                    </a>
                    <a class="pagination-item" style="pointer-events: none">
                        <p>...</p>
                    </a>
                    <a class="pagination-item">
                        <p>1</p>
                    </a>
                    <a class="pagination-item active" style="pointer-events: none">
                        <p>2</p>
                    </a>
                    <a class="pagination-item">
                        <p>3</p>
                    </a>
                    <a class="pagination-item" style="pointer-events: none">
                        <p>...</p>
                    </a>
                    <a class="pagination-item">
                        <p>50</p>
                    </a>
                    <a class="pagination-item" style="pointer-events: none">
                        <i class="material-icons">arrow_forward</i>
                    </a>
                </div>
            </div> 
        
        </main>
        
        <script src='<c:url value="/resources/js/common.js"/>'></script>
    </body>
</html> 
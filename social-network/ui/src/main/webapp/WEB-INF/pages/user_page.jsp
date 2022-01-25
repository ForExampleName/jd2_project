<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Профиль</title>
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

        <main class="content home-page">

            <c:if test = "${adminDto.isShowAdminPanel()}">
                <div class="content-item">
                    <div class="admin-panel-container">
                        <div class="header">
                            <p class="description">Панель администратора</p>
                        </div>
                        <div class="admin-panel">
                            <div class="admin-item">
                                <p class="user-status">Статус пользователя:</p>
                                <c:choose>
                                    <c:when test="${adminDto.isUserBlocked()}">
                                        <p class="blocked-user">Заблокирован</p>
                                        <a class="unblock-user-link" href="/ui/${profileDto.getId()}/unblocking.do">Разблокировать</a>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="active-user">Активен</p>
                                        <a class="block-user-link" href="/ui/${profileDto.getId()}/blocking.do">Заблокировать</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            

            <div class="layout-block">
                <div id="main-content" class="content-item">
                    <div class="main-content-container">
                        <img class="main-avatar" src="${profileDto.getAvatar()}" width="300" height="300">
                        <div class="username-container">
                            <p class="username-item">${profileDto.getFirstName()}</p>
                            <p class="username-item">${profileDto.getLastName()}</p>
                        </div>
                        
                    </div>
                </div>

                <div id="info-content" class="content-item">
                    <div class="information-container">
                        <div class="header">
                            <p class="description">Личная информация</p>
                        </div>
                        <div class="information-description">
                            <div class="description-item">
                                <p class="label">День рождения:</p>
                                <p class="value">${profileDto.getBirthday()}</p>
                            </div>
                            <div class="horizontal-border"></div>
                            <div class="description-item">
                                <p class="label">Телефон:</p>
                                <p class="value">${profileDto.getPhone()}</p>
                                <br>
                                <p class="label">E-mail:</p>
                                <p class="value">${profileDto.getEmail()}</p>
                            </div>
                            <div class="horizontal-border"></div>
                            <div class="description-item">
                                <p class="label">Страна:</p>
                                <p class="value">${profileDto.getCountry()}</p>
                                <br>
                                <p class="label">Город:</p>
                                <p class="value">${profileDto.getCity()}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
            <div class="content-item">
                <div class="records-invitation-container">
                    <p class="description">Записи</p>
                    <c:if test="${recordDto.isShowControls()}">
                    <button id="add-record-btn" class="add-record-btn">
                        <i class="material-icons add-icon">add</i>
                        <p class="description">Добавить запись</p>
                    </button> 
                </c:if>
                </div>
            </div> 

            <c:if test = "${recordDto.isShowControls()}">
                <div id="record-form-content-item" class="content-item">
                    <div class="record-form-container">
                        <div class="header">
                            <p class="description">Создание записи</p>
                        </div>
    
                        <f:form id="record-form" class="record-form" method="post" autocomplete="off" action="/ui/create_record.do" enctype="multipart/form-data" modelAttribute="recordCommand" novalidate="novalidate">
                            <f:input id="record-header" class="record-header" type="text" path="header" name="record-header" value="" minlength="1" maxlength="255" required="required" placeholder="Заголовок записи (обязательно)"/>
                            <div class="warning-container">
                                <p id="record-header-warning" class="warning"></p>
                            </div>

                            <f:textarea id="record-description" class="description" path="description" name="record-description" value="" minlength="1" maxlength="255" placeholder="Основное содержание записи (обязательно)"></f:textarea>
                            <div class="warning-container">
                                <p id="record-description-warning" class="warning"></p>
                            </div>
    
                            <f:label class="file-label" for="record-file-input" path="picture">Изображение (необязательно):</f:label><br>
                            <f:input id="record-file-input" class="file-input" type="file" path="picture" name="record-image" value="" accept=".jpg, .jpeg"/> 
                            <button id="record-file-clean-btn" class="file-clean-btn">Очистить файл</button>
                            <div class="warning-container">
                                <p id="record-file-warning" class="warning"></p>
                            </div>
    
                            <div class="button-container">
                                <input id="record-submit-btn" class="submit" type="submit" value="Создать">
                                <input id="record-reset-btn" class="reset" type="reset" value="Отмена">
                            </div>
                        </f:form>
                    </div>
                </div>
            </c:if>
            
            <c:choose>
                <c:when test="${not empty recordDto.getRecords()}">
                    <div class="content-item">
                        <c:forEach items="${recordDto.getRecords()}" var="record">
                            <div class="record-container">
                                <c:if test ="${not empty record.getHeader()}">
                                    <p class="record-header">${record.getHeader()}</p>
                                </c:if>
                                <p class="record-info">${record.getDescription()}</p>
                                <c:if test="${not empty record.getPicture()}">
                                    <div class="horizontal-border"></div>
                                    <img class="record-image" src="${record.getPicture()}" width="500" height="400">
                                </c:if>
                                <div class="horizontal-border"></div>
                                <div class="bottom-container">
                                    <div class="management-container">
                                        <p class="record-date">${record.getTime()}</p>
                                        <c:if test="${recordDto.isShowControls()}">
                                            <a class="record-delete-container" href="/ui/${record.getId()}/delete_record.do">
                                                <i class="material-icons delete-icon">delete</i>
                                            </a>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="content-item">
                        <div class="records-description-empty">
                            <div class="description-container">
                                <p class="description">Пока пусто</p>
                            </div>
                        </div>
                    </div> 
                </c:otherwise>
            </c:choose>

            <c:if test = "${recordDto.getPagination().isShowPagination()}">
                <div id="pagination-block" class="content-item">
                    <div class="pagination-container">
                        <c:if test = "${recordDto.getPagination().isShowLeftArrow()}">
                            <a class="pagination-item" href="/ui/${profileDto.getId()}/${recordDto.getPagination().getPageNumber() - 1}/user_page.html">
                                <i class="material-icons">arrow_back</i>
                            </a>
                        </c:if>
                        <a class="pagination-item" style="pointer-events: none" href="#">
                            <p>${recordDto.getPagination().getPageNumber() + 1}</p>
                        </a>
                        <c:if test = "${recordDto.getPagination().isShowRightArrow()}">
                            <a class="pagination-item" href="/ui/${profileDto.getId()}/${recordDto.getPagination().getPageNumber() + 1}/user_page.html">
                                <i class="material-icons">arrow_forward</i>
                            </a>
                        </c:if>
                    </div>
                </div> 
            </c:if>
        </main>

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

        <script src='<c:url value="/resources/js/common.js"/>'></script>
        <script src='<c:url value="/resources/js/input_validator.js"/>'></script>
        <script src='<c:url value="/resources/js/user_page.js"/>'></script>
    </body>
</html> 
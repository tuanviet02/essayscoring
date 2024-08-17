<%-- 
    Document   : criteria
    Created on : Apr 17, 2024, 4:19:09 PM
    Author     : AnhTH
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./style/criteria.css">
    <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700"/>
        <!-- Nucleo Icons -->
        <link href="./assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="./assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet"/>
        <!-- CSS Files -->
        <link id="pagestyle" href="./assets/css/material-dashboard.css?v=3.0.0" rel="stylesheet"/>
    </head>
    <body>
        <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark"id="sidenav-main" >
      <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0" href="./" target="_blank">
          <img src="./assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo"/>
          <span class="ms-1 font-weight-bold text-white">Feedback Criteria</span>
        </a>
      </div>
      <hr class="horizontal light mt-0 mb-2" />
    <%@include file="evaluator/sidebarEvaluator.jsp" %>
    </aside>
        <div id="page">
            <header>
                <h2>Essay Criteria</h2>
                 <c:if test="${mess != null}">
                                        <div class=" alert-danger text-success" role="alert">
                                            <h3>${mess}</h1>
                                        </div>
                                    </c:if>
            </header>
            <main style="text-align: center">
                <div id="tabs">
                    <div class="tab-links">
                        <c:forEach var="criteria" items="${criteriaList}">
                            <button data-id="criteria${criteria.criteriaID}" data-default>${criteria.criteriaName}</button>
                        </c:forEach>
                    </div>

                    <div class="tab-content">
                        <c:forEach var="criteria" items="${criteriaList}">
                            <section id="criteria${criteria.criteriaID}"  data-default>
                                <h4>${criteria.criteriaName}</h4>
                                <p>${criteria.description}</p>
                                <form action="topicCriteria" method="post">
                                    <input type="hidden" name="evaluator_essay_id" value="${param.evaluator_essay_id}">  
                                    <input type="hidden" name="writerEssayId" value="${param.writerEssayId}">  
                                    <input type="hidden" name="criteria_id" value="${criteria.criteriaID}">  
                                    <label>Feedback content:</label>
                                    <input type="text" class="textarea" name ="feedback_content" required>
                                    <c:if test="${messSuccess != null}">
                                        <div class=" alert-success" role="alert">
                                            <h3>${messSuccess}</h1>
                                        </div>
                                    </c:if>
                                    <c:if test="${messError != null}">
                                        <div class=" alert-danger text-success" role="alert">
                                            <h3>${messError}</h1>
                                        </div>
                                    </c:if>
                                    <button  type="submit">Save</button>
                                </form>
                            </section>
                        </c:forEach>
                    </div>
                </div>
            </main>
        </div>
        <script>
            const $ = document.querySelector.bind(document);

            function TabNavigation() {
                const html = {
                    links: [...$('.tab-links').children],
                    contents: [...$('.tab-content').children],
                    openTab: $('[data-default]'),
                }

                function hideAllTabContent() {
                    html.contents.forEach(section => {
                        section.style.display = "none";
                    });
                }

                function removeAllActiveClas() {
                    html.links.forEach(tab => {
                        tab.className = tab.className.replace(' active', '');
                    })
                }

                function showCurrentTab(id) {
                    const tabcontent = $('#' + id);
                    tabcontent.style.display = 'block';
                }

                function selecttab(event) {
                    hideAllTabContent();
                    removeAllActiveClas();

                    const target = event.currentTarget;
                    showCurrentTab(target.dataset.id);

                    target.className += ' active';
                }

                function listenForChange() {
                    html.links.forEach(tab => {
                        tab.addEventListener('click', selecttab);
                    });
                }

                function init() {
                    hideAllTabContent();
                    listenForChange();

                    html.openTab.click();
                }

                return {
                    init
                }
            }

            window.addEventListener('load', () => {
                const tabNavigation = TabNavigation();
                tabNavigation.init();
            })
        </script>
         <!--   Core JS Files   -->
    <script src="./assets/js/core/popper.min.js"></script>
    <script src="./assets/js/core/bootstrap.min.js"></script>
    <script src="./assets/js/plugins/perfect-scrollbar.min.js"></script>
    <script src="./assets/js/plugins/smooth-scrollbar.min.js"></script>
    <script>
      var win = navigator.platform.indexOf("Win") > -1;
      if (win && document.querySelector("#sidenav-scrollbar")) {
        var options = {
          damping: "0.5",
        };
        Scrollbar.init(document.querySelector("#sidenav-scrollbar"), options);
      }
    </script>
    <!-- Github buttons -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
    <script src="./assets/js/material-dashboard.min.js?v=3.0.0"></script>
    </body>
</html>

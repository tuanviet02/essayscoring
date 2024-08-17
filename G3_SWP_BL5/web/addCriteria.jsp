<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Criteria</title>
    <link href="./style/addCriteria.css" rel="stylesheet" />    <!--     Fonts and icons     -->
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
          <span class="ms-1 font-weight-bold text-white">Add Criteria</span>
        </a>
      </div>
      <hr class="horizontal light mt-0 mb-2" />
    <%@include file="evaluator/sidebarEvaluator.jsp" %>
    </aside>
    <div class="container">
    <h1>Add New Criteria</h1>
    <form action="addCriteria" method="post">
        <label for="criteriaName">Criteria Name:</label><br>
        <input type="text" id="criteriaName" name="criteriaName" required><br><br>
        
        <label for="description">Description:</label><br>
        <textarea id="description" name="description" required></textarea><br><br>
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
        <input type="submit" value="Add Criteria">
        <a href="listCriteria" > Back list </a>
    </form>
    </div>
</body>
</html>

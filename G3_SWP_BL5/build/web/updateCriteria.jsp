<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Criteria</title>
     <link href="./style/addCriteria.css" rel="stylesheet" />
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
    <body >
        <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark"id="sidenav-main" >
      <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0" href="./" target="_blank">
          <img src="./assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo"/>
          <span class="ms-1 font-weight-bold text-white">Graded List</span>
        </a>
      </div>
      <hr class="horizontal light mt-0 mb-2" />
    <%@include file="evaluator/sidebarEvaluator.jsp" %>
    </aside>
    <div class="container">
    <h1>Update Criteria</h1>
    <form action="updateCriteria" method="post">
        
        <input type="hidden" id="criteriaId" name="criteriaId"  value="${criteria.criteriaID}">
        
        <label for="criteriaName">New Criteria Name:</label><br>
        <input type="text" id="criteriaName" name="criteriaName" value="${criteria.criteriaName}" required><br><br>
        
        <label for="description">New Description:</label><br>
        <textarea id="description" name="description" required>${criteria.description}</textarea><br><br>
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
        <input type="submit" value="Update Criteria">
           <a href="listCriteria" > Back list </a>
    </form>
    </div>
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

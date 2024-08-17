<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="../assets/img/favicon.png">
        <title>
            View Your Essay
        </title>
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
        <!-- Nucleo Icons -->
        <link href="./assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="./assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
        <!-- CSS Files -->
        <link id="pagestyle" href="./assets/css/material-dashboard.css?v=3.0.0" rel="stylesheet" />
    </head>

    <body class="g-sidenav-show  bg-gray-200">
        <%@include file="common/dSide.jsp" %>
        <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
            <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" navbar-scroll="true">
                <div class="container-fluid py-1 px-3">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                            <li class="breadcrumb-item text-sm">
                                <a class="opacity-5 text-dark" href="#">Pages</a>
                            </li>
                            <li class="breadcrumb-item text-sm text-dark active" aria-current="page">
                                <a class="opacity-5 font-weight-bolder">Home Page</a>
                            </li>
                        </ol>
                    </nav>

                    <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
                        <div class="ms-md-auto pe-md-3 d-flex align-items-center"></div>
                        <ul class="navbar-nav justify-content-end">
                            <li class="nav-item d-flex align-items-center">
                                <a href="javascript:;" class="nav-link text-body font-weight-bold px-0">
                                    <a href="./user_profile">
                                        <i class="fa fa-user me-sm-1"></i>
                                    </a>
                                    <span class="d-sm-inline d-none"><a href="./Logout">Logout</a></span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-12">
                        <div class="card my-4">
                            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                                <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3 row">
                                    <div class="col">
                                        <h6 class="text-white text-capitalize ps-3">View your score</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body px-0 pb-2">
                                <div class="row" style="padding: 16px">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="card">
                                    <div class="card-body" style="height: 350px">
                                        <form action="ShowAllEssayPageController" method="GET" class="d-flex align-items-center" style="border-radius: 20px; padding: 10px; background-color: #f8f9fa;">
                                            <input type="number" name="minEssays" placeholder="From" class="form-control mr-2 rounded-pill" style="border-radius: 20px; width: 100px;" required>
                                            <span class="mx-2">-</span>
                                            <input type="number" name="maxEssays" placeholder="To" class="form-control mr-2 rounded-pill" style="border-radius: 20px; width: 100px;" required>
                                            <button type="submit" class="btn btn-primary" style="border-radius: 20px; min-width: 150px; margin-left: 300px; margin-top: 20px">Filter</button>
                                        </form>
                                        <h2 class="card-title">After ${n} essays, your average score is:</h5>
                                            <h1 <span style="color: red; font-size: 80px">${averageScoreInRange}</span></h1>
                                                <c:if test="${averageScoreInRange < 4}">
                                                <p>You're doing poorly in your essays. You need to study harder!</p>
                                            </c:if>
                                            <c:if test="${averageScoreInRange < 4}">
                                                <p style="color: red; font-size: 18px; font-weight: bold;">You're doing poorly in your essays. You need to study harder!</p>
                                            </c:if>
                                            <c:if test="${averageScoreInRange >= 4 && averageScoreInRange < 6}">
                                                <p style="color: red; font-size: 18px; font-weight: bold;">Your performance in your essays is average. Keep up the good work!</p>
                                            </c:if>
                                            <c:if test="${averageScoreInRange >= 6 && averageScoreInRange <= 8}">
                                                <p style="color: red; font-size: 18px; font-weight: bold;">Your performance in your essays is average. Keep up the good work!</p>
                                            </c:if>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-6">
                                <div class="card">
                                    <div class="card-body" style="height: 350px">
                                        <h2 class="card-title">After ${nTotal} essays, your average score is: </h5>
                                            <h1><span style="color: red;  font-size: 80px;margin-left: 40px; margin-top: 50px">${averageScoreOfRecent}</span></h1>
                                    </div>
                                </div>
                            </div>
                        </div>



                    </div>

                </div>
            </div>
        </div>

        <%@include file="common/dFooter.jsp" %>
    </div>
</main>

<script src="./assets/js/core/popper.min.js"></script>
<script src="./assets/js/core/bootstrap.min.js"></script>
<script src="./assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="./assets/js/plugins/smooth-scrollbar.min.js"></script>
<script src="./assets/js/plugins/chartjs.min.js"></script>
<script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
        var options = {
            damping: '0.5'
        }
        Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
</script>
<!-- Github buttons -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
<script src="./assets/js/material-dashboard.min.js?v=3.0.0"></script>
<script>
    // Restore select value from localStorage
    window.onload = function () {
        var sortSelect = document.getElementById('sortSelect');
        var selectedValue = localStorage.getItem('selectedSortField');
        if (selectedValue) {
            sortSelect.value = selectedValue;
        }
    };

    // Save select value to localStorage when changed
    document.getElementById('sortSelect').addEventListener('change', function () {
        var selectedValue = this.value;
        localStorage.setItem('selectedSortField', selectedValue);
    });
</script>
</body>

</html>

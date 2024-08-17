<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="../assets/img/favicon.png">
        <title>
            Home Page
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
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                        <form class="row g-3" action="GetHomePageController" method="GET" >
                                            <div class="col-md-6" style="border-color: #ced4da; padding: 10px;">
                                                <select class="form-select" id="filterSelect" name="filterOption">
                                                    <option value="popular">Most popular</option>
                                                    <option value="writer">Most writer</option>
                                                </select>
                                            </div>
                                            <div class="col-md-6">
                                                <button type="submit" class="btn btn-primary">Filter</button>
                                            </div>
                                        </form>



                                        <div class="row">
                                            <c:if test="${not empty popularEssays}">
                                                <div class="col-md-12">
                                                    <div class="card mb-3">
                                                        <div class="card-body">
                                                            <h5 class="card-title">Popular Essays</h5>
                                                            <div class="row row-cols-1 row-cols-md-2 g-4">
                                                                <c:forEach var="essay" items="${popularEssays}">
                                                                    <div class="col">
                                                                        <div class="card">
                                                                            <div class="card-body">
                                                                                <h6 class="card-subtitle mb-2 text-muted">ID: ${essay.writerEssay.writerEsssayId}</h6>
                                                                                <p class="card-text">${essay.writerEssay.contentEssay}</p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>


                                            <c:if test="${not empty topEssays}">
                                                <div class="col-md-12">
                                                    <div class="card mb-3">
                                                        <div class="card-body">
                                                            <h5 class="card-title">Top Essays by Number of Writers</h5>
                                                            <div class="row row-cols-1 row-cols-md-2 g-4">
                                                                <c:forEach var="essay" items="${topEssays}">
                                                                    <div class="col">
                                                                        <div class="card">
                                                                            <div class="card-body">
                                                                                <h6 class="card-subtitle mb-2 text-muted">ID: ${essay.writerEsssayId}</h6>
                                                                                <p class="card-text">${essay.contentEssay}</p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>

                                        </div>
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

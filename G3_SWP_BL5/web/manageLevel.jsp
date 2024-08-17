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
            Level List
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
        <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark"id="sidenav-main" >
            <div class="sidenav-header">
                <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
                <a class="navbar-brand m-0" href="./" target="_blank">
                    <img src="./assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo"/>
                    <span class="ms-1 font-weight-bold text-white">Level</span>
                </a>
            </div>
            <hr class="horizontal light mt-0 mb-2" />
            <%@include file="evaluator/sidebarEvaluator.jsp" %>
        </aside>
        <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
            <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" navbar-scroll="true">
                <div class="container-fluid py-1 px-3">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                            <li class="breadcrumb-item text-sm">
                                <a class="opacity-5 text-dark" href="#">Pages</a>
                            </li>
                            <li class="breadcrumb-item text-sm text-dark active" aria-current="page">
                                <a class="opacity-5 font-weight-bolder">Level List</a>
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
                                        <h6 class="text-white text-capitalize ps-3">Manage Level</h6>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body px-0 pb-2">
                                <div class="row" style="padding: 16px">
                                    <!-- Thanh tìm kiếm theo tên -->
                                    <div class="col">
                                        <form action="SearchLevel" method="GET" style="display: flex;">
                                            <div class="input-group">
                                                <input type="text" name="searchByName" class="form-control rounded-left-0" placeholder="Search by Name" style="border: 2px solid #ccc; padding: 8px 10px; border-radius: 10px; height: 40px;">
                                                <button type="submit" class="btn btn-primary">
                                                    <i class="fas fa-search"></i> Search
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- Thanh sắp xếp -->
                                    <div class="col">
                                        <form action="SortLevel" method="get" style="display: flex;">
                                            <div class="input-group">
                                                <select class="form-control" id="sortSelect" name="sortField" style="border: 2px solid #ccc; padding: 6px 8px; border-radius: 10px; height: 40px;">
                                                    <option value="numb" ${param.sortField == 'numb' ? 'selected' : ''}>Sort by Numerical Order</option>
                                                    <option value="nameee" ${param.sortField == 'nameee' ? 'selected' : ''}>Sort by Name</option>
                                                </select>
                                                <button type="submit" class="btn btn-primary">
                                                    <c:choose>
                                                        <c:when test="${sessionScope.descendingOrder}">
                                                            <i class="fas fa-sort-down"></i> Descending Order
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="fas fa-sort-up"></i> Ascending Order
                                                        </c:otherwise>
                                                    </c:choose>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- Nút tạo mới -->
                                    <div class="col ml-auto">
                                        <a href="createLevel.jsp" class="btn btn-primary" style="float: right;">
                                            <i class="fas fa-plus"></i> Create 
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row">
                                            <!-- Bắt đầu vòng lặp foreach để hiển thị từng phần tử trong list -->
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Numerical Order</th>
                                                        <th>Level Name</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <div>
                                                    <p style="font-weight: bold; color: red;">${errorMessage}</p>
                                                </div>

                                                <tbody>
                                                    <c:forEach var="t" items="${tList}">
                                                        <tr>
                                                            <td>${t.levelId}</td>
                                                            <td>${t.levelName}</td>
                                                            <td>
                                                                <div class="button-group">
                                                                    <form action="EditLevel" method="get">
                                                                        <input type="hidden" name="levelId" value="${t.levelId}">
                                                                        <button class="btn btn-primary">Edit</button>
                                                                    </form>
                                                                    <form id="deleteForm${t.levelId}" action="DeleteLevelController" method="" onsubmit="return confirmDelete(${t.levelId})">
                                                                        <input type="hidden" name="levelId" value="${t.levelId}">
                                                                        <button type="submit" class="btn btn-danger" onclick="return confirmDelete(${t.levelId})">Delete</button>
                                                                    </form>

                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>

                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="common/pagination.jsp" %>
        <%@include file="common/dFooter.jsp" %>
    </div>
</main>
<!-- Modal -->
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

<script>
</script>
<script>
    function confirmDelete(levelId) {
        // Hiển thị hộp thoại xác nhận
        if (!confirm("Are you sure you want to delete this level?")) {
            return false; // Ngăn chặn việc gửi form nếu người dùng không xác nhận
        }
        // Nếu người dùng xác nhận xóa, không cần thêm hành động
        return true;
    }

</script>
</body>

</html>

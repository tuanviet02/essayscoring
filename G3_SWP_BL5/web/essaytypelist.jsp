

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png"/>
        <link rel="icon" type="image/png" href="./assets/img/favicon.png" />
        <title>Essay Type List</title>
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

    <body class="g-sidenav-show bg-gray-200">
        <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark"id="sidenav-main" >
            <div class="sidenav-header">
                <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
                <a class="navbar-brand m-0" href="./" target="_blank">
                    <img src="./assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo"/>
                    <span class="ms-1 font-weight-bold text-white">Topic List</span>
                </a>
            </div>
            <hr class="horizontal light mt-0 mb-2" />
            <%@include file="evaluator/sidebarEvaluator.jsp" %>
        </aside>

        <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg">
            <!-- Navbar -->
            <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" navbar-scroll="true">
                <div class="container-fluid py-1 px-3">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                            <li class="breadcrumb-item text-sm">
                                <a class="opacity-5 text-dark" href="#">Pages</a>
                            </li>
                            <li class="breadcrumb-item text-sm text-dark active" aria-current="page">
                                <a class="opacity-5 font-weight-bolder" href="essaytypelist">Essay Type List</a>
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
            <!-- End Navbar -->
            <div class="container-fluid py-4">
                <div class="row ">
                    <div class="col-xl-6">
                        <div class="row">

                            <form action="essaytypesort" class="col-md-3 " id="sort">                 
                                <label>Sort</label>
                                <div class="input-group">
                                    <select name="sort" onchange="document.getElementById('sort').submit()" value="${requestScope.sort}" class="form-select" aria-label="Default select example">
                                        <option <c:if test="${requestScope.sort.equals('ID')}">selected</c:if> value="ID" style="text-align: center">ID</option>
                                        <option <c:if test="${requestScope.sort.equals('Name')}">selected</c:if> value="Name" style="text-align: center">Essay type name</option>
                                        </select>
                                    </div>             
                                </form>
                                <form action="essaytypesearch" class="col-md-6 ">                 
                                    <label>Search</label>
                                    <div class="input-group">
                                        <input type="text" name="search" value="${requestScope.search}" class="form-control border"placeholder=" Type here... "/>
                                </div>             
                            </form>
                        </div>
                    </div>
                </div>

                <div class="row ">
                    <div class="col-lg-12">
                        <div class="row d-flex justify-content-center align-items-center">
                            <div class="col-md-10 mb-lg-0 mb-4">
                                <div class="card mt-4">
                                    <div class="card-header pb-0 p-3">
                                        <div class="row">
                                            <div class="col-6 d-flex align-items-center">
                                                <h5 class="mb-0">Essay Type List</h5>
                                            </div>
                                            <div class="col-6 text-end">
                                                <a class="btn bg-gradient-dark mb-0" href="addessaytype">
                                                    Add New Type
                                                </a>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="card-body p-3">
                                        <div class="table-responsive p-0">
                                            <table class="table align-items-center mb-0">
                                                <thead>
                                                    <tr>
                                                        <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> ID</th>
                                                        <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> Essay type name</th>
                                                        <th class="text-secondary opacity-7"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requestScope.listEssayType}" var="t">
                                                        <tr>
                                                            <td>
                                                                <p class="text-secondary mb-0">${t.typeId}</p> 
                                                            </td>
                                                            <td>
                                                                <p class="text-secondary mb-0">${t.typeName}</p>
                                                            </td>
                                                            <td class="align-middle">
                                                                <a href="editessaytype?id=${t.typeId}"  class="text-secondary font-weight-bold" data-toggle="tooltip" data-original-title="Edit user">
                                                                    View
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>

                                            <c:if test="${requestScope.check.equals('list')}">  
                                                <div aria-label="Page navigation ">
                                                    <ul class="pagination justify-content-center">
                                                        <c:if test="${requestScope.index>1}">      <!--previous button-->
                                                            <li class="page-item"><a class="page-link" href="essaytypelist?index=${requestScope.index-1}">&laquo;</a></li>
                                                            </c:if>
                                                            <c:forEach begin="1" end="${requestScope.page}" var="i">
                                                            <li class="page-item <c:if test="${i.equals(requestScope.index)}">active</c:if>">
                                                                <a class="page-link" href="essaytypelist?index=${i}">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                        <c:if test="${requestScope.index<requestScope.page}">
                                                            <li class="page-item"><a class="page-link" href="essaytypelist?index=${requestScope.index+1}">&raquo;</a></li>
                                                            </c:if>
                                                    </ul>
                                                </div>
                                            </c:if> 
                                            <c:if test="${requestScope.check.equals('search')}">  
                                                <div aria-label="Page navigation ">
                                                    <ul class="pagination justify-content-center">
                                                        <c:if test="${requestScope.index>1}">      <!--previous button-->
                                                            <li class="page-item"><a class="page-link" href="essaytypesearch?search=${requestScope.search}&index=${requestScope.index-1}">&laquo;</a></li>
                                                            </c:if>
                                                            <c:forEach begin="1" end="${requestScope.page}" var="i">
                                                            <li class="page-item <c:if test="${i.equals(requestScope.index)}">active</c:if>">
                                                                <a class="page-link" href="essaytypesearch?search=${requestScope.search}&index=${i}">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                        <c:if test="${requestScope.index<requestScope.page}">
                                                            <li class="page-item"><a class="page-link" href="essaytypesearch?search=${requestScope.search}&index=${requestScope.index+1}">&raquo;</a></li>
                                                            </c:if>
                                                    </ul>
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.check.equals('sort')}">  
                                                <div aria-label="Page navigation ">
                                                    <ul class="pagination justify-content-center">
                                                        <c:if test="${requestScope.index>1}">      <!--previous button-->
                                                            <li class="page-item"><a class="page-link" href="essaytypesort?sort=${requestScope.sort}&index=${requestScope.index-1}">&laquo;</a></li>
                                                            </c:if>
                                                            <c:forEach begin="1" end="${requestScope.page}" var="i">
                                                            <li class="page-item <c:if test="${i.equals(requestScope.index)}">active</c:if>">
                                                                <a class="page-link" href="essaytypesort?sort=${requestScope.sort}&index=${i}">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                        <c:if test="${requestScope.index<requestScope.page}">
                                                            <li class="page-item"><a class="page-link" href="essaytypesort?sort=${requestScope.sort}&index=${requestScope.index+1}">&raquo;</a></li>
                                                            </c:if>
                                                    </ul>
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
        </main>

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




<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png"/>
        <link rel="icon" type="image/png" href="./assets/img/favicon.png" />
        <title>Edit Essay Type</title>
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
                <a class="navbar-brand m-0" target="_blank">
                    <img src="./assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo"/>
                    <span class="ms-1 font-weight-bold text-white">Edit Type</span>
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
                                <a class="opacity-5 font-weight-bolder">Edit Type</a>
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

                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-md-10 ">
                        <div class="card ">
                            <div class="card-header">
                                <h5 class="card-title">Essay Detail</h5>
                            </div>
                            <c:if test="${requestScope.error!=null}">
                                <h6 style="color: red">&nbsp;&nbsp;${requestScope.error}</h6>
                            </c:if>
                            <div class="card-body">
                                <form action="editessaytype" method="post" >
                                    <div class="row form-group">
                                        <label class="col-form-label col-md-2">Essay Type Name</label>
                                        <input type="hidden" name="id" value="${requestScope.essayType.typeId}"/>
                                        <div class="col-md-12 " >
                                            <input name="name" type="text" class="form-control border" value="${requestScope.essayType.typeName}" required/>
                                        </div>
                                    </div><br>
                                    <div class="form-group mb-0 row">                   
                                        <div class="col-md-5">
                                            <a href="essaytypelist"><button class="btn btn-danger" type="button">
                                                    Cancel
                                                </button></a>  
                                            <input class="btn btn-success" type="submit" value="Edit">

                                        </div>
                                    </div>
                                </form>
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
        <script type="text/javascript">
            </body>
            </html>


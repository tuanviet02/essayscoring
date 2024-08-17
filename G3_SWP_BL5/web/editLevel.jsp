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
            Edit Level
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
                    <span class="ms-1 font-weight-bold text-white">Edit Level</span>
                </a>
            </div>
            <hr class="horizontal light mt-0 mb-2" />
            <%@include file="evaluator/sidebarEvaluator.jsp" %>
        </aside>
        <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
            <%@include file="common/dNavbar.jsp" %>

            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-12">
                        <div class="card my-4">
                            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                                <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3 row">
                                    <h6 class="text-white text-capitalize ps-3">Edit Level</h6>
                                </div>
                            </div>
                            <div class="card-body px-0 pb-2">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="card">
                                            <div class="card-body">
                                                <div class="row">
                                                    <!-- Bắt đầu vòng lặp foreach để hiển thị từng phần tử trong list -->
                                                    <!-- Add input field for adding new element -->
                                                   <form action="EditLevel" method="post">
                                <div class="col-lg-6 mb-6">
                                               <label for="newElementName" style="display: block;">Name to Update</label>
                                                <div class="input-group">
                                        <input type="hidden" name="levelId" value="${level.level_id}">
                                        <input type="text" id="newElementName" name="levelName" class="form-control" value="${level.level_name}" style="border: 2px solid #ccc; padding: 8px 10px; border-radius: 10px; height: 40px;">
                                           <button type="submit" class="btn btn-primary">Edit</button>
                                           <a href="ManageLevel" class="btn btn-secondary">Cancel</a>
                                                 </div>
                                                        </div>
                                                        </form>

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
    </body>

</html>

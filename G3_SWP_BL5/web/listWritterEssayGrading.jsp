
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png"/>
        <link rel="icon" type="image/png" href="./assets/img/favicon.png" />
        <title>List of Writer Essays</title>
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

</head>

<body class="g-sidenav-show bg-gray-200">
    <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark"id="sidenav-main" >
        <div class="sidenav-header">
            <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
            <a class="navbar-brand m-0" href="./" target="_blank">
                <img src="./assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo"/>
                <span class="ms-1 font-weight-bold text-white">List of Writer Essays</span>
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
                            <a class=" font-weight-bolder">Grading List</a>
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
            <div class="row">
                <div class="col-xl-6">
                    <div class="row">
                        <form method="post" action="listWritterEssayGrading">
                            Keyword: <input type="text" name="keyword" class="border" placeholder=" Type here... ">
                            Status: 
                            <select name="status" class="border">
                                <option value="">All</option>
                                <option value="done">Done</option>
                                <option value="pending">Pending</option>


                            </select>
                            <button  type="submit"class="btn btn-secondary" >Search</button>
                        </form>




                    </div>
                </div>
            </div>
            <a class="btn btn-white" href="listWritterEssayGrading">Sort</a>
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-md-12 mb-lg-0 mb-4">
                            <div class="card mt-4">
                                <div class="card-header pb-0 p-3">
                                    <div class="row">
                                        <div class="col-6 d-flex align-items-center">
                                            <h5 class="mb-0">Grading List</h5>
                                        </div>
                                        <div class="col-6 text-end">
                                            <a class="btn bg-gradient-dark mb-0" href="#">
                                                <!--                                                Add New Grading-->
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
                                                    <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> Writer Name</th>
                                                    <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> Topic</th>
                                                    <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> Level</th>
                                                    <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> Type</th>
                                                    <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> Essay submitted</th>

                                                    <th class="text-center text-uppercase text-secondary font-weight-bolder opacity-7"> Status</th>
                                                    <th class="text-center text-uppercase text-secondary font-weight-bolder opacity-7">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="writerEssay" items="${writerEssays}">
                                                    <tr>
                                                        <td>
                                                            <p class="text-secondary mb-0">${writerEssay.writerEsssayId}</p> 
                                                        </td>
                                                        <td>
                                                            <p class="text-secondary mb-0">${writerEssay.writer.fullName}</p>
                                                        </td>

                                                        <td>
                                                            <p class="text-secondary mb-0">${writerEssay.essay.title}</p>
                                                        </td>
                                                        <td>
                                                            <p class="text-secondary mb-0">${writerEssay.essay.level.levelName}</p>
                                                        </td>
                                                        <td>
                                                            <p class="text-secondary mb-0">${writerEssay.essay.type.typeName}</p>
                                                        </td>
                                                        <td>
                                                            <p class="text-secondary mb-0">${fn:substring(writerEssay.contentEssay, 0, 100)}</p>
                                                        </td>


                                                        <td class="align-middle text-center">
                                                            <p class="text-secondary mb-0">${writerEssay.status}</p>
                                                        </td>
                                                        <td class="align-middle">
                                                            <a href="grading?writerEssayId=${writerEssay.writerEsssayId}"  class="text-secondary font-weight-bold" data-toggle="tooltip" data-original-title="Grading user">
                                                                View
                                                            </a>

                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <!-- Nút phân trang -->
                                        <c:if test="${pageNo > 1}">
                                            <a href="listWritterEssayGrading?pageNo=${pageNo - 1}">Previous</a>
                                        </c:if>

                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <c:if test="${i != pageNo}">
                                                <a href="listWritterEssayGrading?pageNo=${i}">${i}</a>
                                            </c:if>
                                            <c:if test="${i == pageNo}">[${i}]</c:if>
                                        </c:forEach>

                                        <c:if test="${pageNo < totalPages}">
                                            <a href="listWritterEssayGrading?pageNo=${pageNo + 1}">Next</a>
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

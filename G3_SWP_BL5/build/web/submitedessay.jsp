
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png"/>
    <link rel="icon" type="image/png" href="./assets/img/favicon.png" />
    <title>Submited Essay</title>
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
            <%@include file="common/dSide.jsp" %>


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
                                <a class="opacity-5 font-weight-bolder">Submitted Essay</a>
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
              <form action="submitedessaysearch" class="col-md-4 col-4">                 
                <label>Search</label>
                <div class="input-group">
                    <input type="text" name="search" value="${requestScope.search}" class="form-control border"placeholder=" Type here... "/>
                </div>             
              </form>
                
              <%--<form action="essayfilter" id="filter" class="row">  
              <div class="col-md-3 col-3">
                <label>Level</label>
                <select name="level" onchange="document.getElementById('filter').submit()" value="${requestScope.level}" class="form-select" aria-label="Default select example">
                    <option value="" style="text-align: center">All</option>
                  <option value="1" <c:if test="${requestScope.level.equals('1')}">selected</c:if> style="text-align: center">Beginner</option>
                  <option value="2" <c:if test="${requestScope.level.equals('2')}">selected</c:if> style="text-align: center">Intermediate</option>
                  <option value="3" <c:if test="${requestScope.level.equals('3')}">selected</c:if> style="text-align: center">Advanced</option>
                  <option value="4" <c:if test="${requestScope.level.equals('4')}">selected</c:if> style="text-align: center">Business</option>
                </select>
              </div>
              <div class="col-md-3 col-3">
                <label>Type</label>
                <select name="type" onchange="document.getElementById('filter').submit()" value="${requestScope.type}" class="form-select" aria-label="Default select example">
                    <option value="" style="text-align: center">All</option>
                    <option value="1" <c:if test="${requestScope.type.equals('1')}">selected</c:if> style="text-align: center">Workbooks</option>
                    <option value="2" <c:if test="${requestScope.type.equals('2')}">selected</c:if> style="text-align: center">Test zone</option>
                </select>
              </div>
              </form> --%>
                
              
                
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-12">
            <div class="row">
              <div class="col-md-12 mb-lg-0 mb-4">
                <div class="card mt-4">
                  <div class="card-header pb-0 p-3">
                    <div class="row">
                      <div class="col-6 d-flex align-items-center">
                        <h5 class="mb-0">Submited Essays</h5>
                      </div>
                      <%--<div class="col-6 text-end">
                        <a class="btn bg-gradient-dark mb-0" href="#">
                          Add New Essay
                        </a>
                      </div>--%>
                    </div>
                  </div>
                  <div class="card-body p-3">
                    <div class="table-responsive p-0">
                      <table class="table align-items-center mb-0">
                        <thead>
                          <tr>
                            <!--<th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> ID</th>-->
                            <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> Title</th>
                            <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> Level</th>
                            <th class="text-uppercase text-secondary font-weight-bolder opacity-7 ps-2"> Type</th>
                            <th class="text-center text-uppercase text-secondary font-weight-bolder opacity-7"> Status</th>
                            <th class="text-secondary opacity-7"></th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.listTopic}" var="t">
                          <tr>
                            <%--<td>
                              <p class="text-secondary mb-0">${t.topicId}</p> 
                            </td>--%>
                            <td>
                              <p class="text-secondary mb-0">${t.essay.title}</p>
                            </td>
                            <td>
                              <p class="text-secondary mb-0">${t.essay.level.levelName}</p>
                            </td>
                            <td>
                              <p class="text-secondary mb-0">${t.essay.type.typeName}</p>
                            </td>
                            <td class="align-middle text-center">
                                <c:if test="${t.status.equals('Pending')}">
                                    <span class="badge badge-sm bg-gradient-success">Pending</span>
                                </c:if>
                                <c:if test="${t.status.equals('Done')}">
                                    <span class="badge badge-sm bg-gradient-danger">Done</span>
                                </c:if>    
                            </td>
                            <td class="align-middle">
                              <a href="viewEssayDetailGraded?writterEssayID=${t.essay.topicId}"  class="text-secondary font-weight-bold" data-toggle="tooltip" data-original-title="Edit user">
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
                                <li class="page-item"><a class="page-link" href="submitedessaylist?index=${requestScope.index-1}">&laquo;</a></li>
                            </c:if>
                            <c:forEach begin="1" end="${requestScope.page}" var="i">
                                <li class="page-item <c:if test="${i.equals(requestScope.index)}">active</c:if>">
                                    <a class="page-link" href="submitedessaylist?index=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${requestScope.index<requestScope.page}">
                                <li class="page-item"><a class="page-link" href="submitedessaylist?index=${requestScope.index+1}">&raquo;</a></li>
                            </c:if>
                        </ul>
                      </div>
                      </c:if> 
                      
                      <c:if test="${requestScope.check.equals('search')}">  
                      <div aria-label="Page navigation ">
                        <ul class="pagination justify-content-center">
                            <c:if test="${requestScope.index>1}">      <!--previous button-->
                                <li class="page-item"><a class="page-link" href="submitedessaysearch?search=${requestScope.search}&index=${requestScope.index-1}">&laquo;</a></li>
                            </c:if>
                            <c:forEach begin="1" end="${requestScope.page}" var="i">
                                <li class="page-item <c:if test="${i.equals(requestScope.index)}">active</c:if>">
                                    <a class="page-link" href="submitedessaysearch?search=${requestScope.search}&index=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${requestScope.index<requestScope.page}">
                                <li class="page-item"><a class="page-link" href="submitedessaysearch?search=${requestScope.search}&index=${requestScope.index+1}">&raquo;</a></li>
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



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png"/>
    <link rel="icon" type="image/png" href="./assets/img/favicon.png" />
    <title>Topic</title>
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
      <div class="collapse navbar-collapse w-auto max-height-vh-100" id="sidenav-collapse-main">
        <ul class="navbar-nav">
          
          <li class="nav-item">
            <a class="nav-link text-white active bg-gradient-primary" href="approvaltopiclist">
              <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                <i class="material-icons opacity-10">receipt_long</i>
              </div>
              <span class="nav-link-text ms-1">Approval Topic</span>
            </a>
          </li>
          
        </ul>
      </div>
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
                  <a class="opacity-5 font-weight-bolder" >Topic Detail</a>
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
          <div class="col-md-11 ">
            <div class="card ">
              <div class="card-header">
                <h5 class="card-title">Topic detail</h5>
              </div>
              <div class="card-body">
                <form action="" method="post">
                  <input type="hidden" name="id" value="${requestScope.topic.topicId}" /> <br/>
                  <div class="row form-group">
                    <label class="col-form-label col-md-2">Title</label>
                    <div class="col-md-12 " >
                        <input name="title" type="text" class="form-control border" value="${requestScope.topic.title}" readonly required/>
                    </div>
                  </div><br>
                  
                  <div class="row form-group">
                    <label class="col-form-label col-md-2">Created by</label>
                    <div class="col-md-12 " >
                        <input name="name" type="text" class="form-control border" value="${requestScope.topic.user.fullName}" readonly required/>
                    </div>
                  </div><br>
                  
                  <div class="row form-group">
                    <label class="col-form-label col-md-2">Created Date</label>
                    <div class="col-md-12 " >
                        <input name="date" type="text" class="form-control border" value="${requestScope.topic.createdDate}" readonly required/>
                    </div>
                  </div><br>
                  
                  <div class="row form-group">
                    <label class="col-form-label col-md-2">Updated Date</label>
                    <div class="col-md-12 " >
                        <input name="date" type="text" class="form-control border" value="${requestScope.topic.updatedDate}" readonly required/>
                    </div>
                  </div><br>
                  
                  <div class="row form-group">
                    <label class="col-form-label col-md-2">Level</label>
                    <div class="col-md-12">
                        <select class="form-select" name="level" value="${requestScope.topic.level.levelId}" disabled>
                            <c:forEach items="${requestScope.listLevel}" var="l">
                                <option value="${l.levelId}" <c:if test="${requestScope.topic.level.levelId==l.levelId}">selected</c:if>>  
                                    ${l.levelName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                  </div><br>

                  <div class="row form-group">
                    <label class="col-form-label col-md-2">Type</label>
                    <div class="col-md-12">
                        <select class="form-select" name="type" value="${requestScope.topic.type.typeId}" disabled>
                            <c:forEach items="${requestScope.listType}" var="t">
                                <option value="${t.typeId}" <c:if test="${requestScope.topic.type.typeId==t.typeId}">selected</c:if>>  
                                    ${t.typeName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                  </div><br>
                  <div class="row form-group">
                    <label class="col-form-label col-md-2">Criteria</label>
                    <div class="col-md-12">
                      <div class="checkbox">
                          <c:forEach items="${requestScope.listCriteria}" var="lc">
                              <input type="checkbox" name="criteria" disabled
                                     <c:forEach items="${requestScope.topic.criteriaList}" var="c">
                                         ${(c.criteriaID eq lc.criteriaID)?"checked":""}
                                     </c:forEach>
                                     
                              value="${lc.criteriaID}"/> ${lc.criteriaName}<br>
                          </c:forEach>
                      </div>
                      
                    </div>
                  </div>
                  <div class="row form-group">
                    <label class="col-form-label col-md-2">Description</label>
                    <div class="col-md-12">
                        <textarea name="description" rows="5" cols="5" class="form-control border" placeholder="  Enter text here" readonly required>${requestScope.topic.description}</textarea>
                    </div>
                  </div><br>
                  <div class="form-group mb-0 row">                   
                    <div class="col-md-5">
                        <a href="approvaltopiclist"><button class="btn btn-secondary" type="button">Back</button></a>               
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
    
  </body>
</html>


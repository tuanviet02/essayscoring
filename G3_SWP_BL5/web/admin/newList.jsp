<%-- 
    Document   : admin
    Created on : Apr 17, 2024, 6:54:13 PM
    Author     : nguye
--%>
<%@page import="Dao.*"%>
<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <%@include file="/admin/layout/head.jsp"  %>

    <body class="g-sidenav-show  bg-gray-200">
        <%@include file="/admin/layout/header.jsp"  %>    
        <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
            <%@include file="/admin/layout/navbar.jsp"  %>
            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-md-12 mb-lg-0 mb-4">
                                <div class="card mt-4">
                                    <div class="card-header pb-0 p-3">
                                        <div class="row">
                                            <div class="col-6 d-flex align-items-center">
                                                <h5 class="mb-0">News List</h5>
                                            </div>
                                            <div class="col-6 text-end">
                                                <a class="btn bg-gradient-dark mb-0" href="./news-create">
                                                    Create New
                                                </a>
                                            </div>


                                            <form action="./news" id="filter" class="row">  
                                                <div class="col-md-2 col-3">
                                                    <label>User</label>
                                                    <select name="userId" onchange="document.getElementById('filter').submit()" class="form-select" >
                                                        <option value="" class="text-center">All</option>
                                                        <c:forEach items="${users}" var="user" >
                                                            <option ${userId == user.userID?"selected":""} class="text-center" value="${user.userID}" > ${user.fullName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-md-2 col-3">
                                                    <label>Status</label>
                                                    <select name="status" onchange="document.getElementById('filter').submit()" class="form-select" >
                                                        <option value="" class="text-center">All</option>
                                                        <option ${status == "Active" ? "selected" : ""} class="text-center" value="Active" > Active</option>         
                                                        <option ${status == "Inactive" ? "selected" : ""} class="text-center" value="Inactive" >Inactive</option>

                                                    </select>
                                                </div>
                                                <div class="col-md-5 col-5">
                                                    <label>Search</label>
                                                    <div class="input-group">
                                                        <input type="text" name="title"  value="${title}" class="form-control border px-2"placeholder="Search news..."/>
                                                    </div>
                                                </div>
                                            </form> 

                                        </div>

                                    </div>
                                    <div class="card-body p-3">
                                        <div class="table-responsive p-0">

                                            <table class="table align-items-center mb-0">
                                                <thead>
                                                    <tr>
                                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 w-10">Id</th>
                                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">title</th>
                                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">user</th>                                     
                                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Status</th>
                                                        <th class="text-secondary opacity-7"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach  items ="${news}" var = "newDetail">
                                                        <tr>
                                                            <!--id-->
                                                            <td class="align-middle text-center">
                                                                <span class="text-secondary text-xs font-weight-bold">${newDetail.newId}</span>
                                                            </td>
                                                            <!--Email-->
                                                            <td class="align-middle text-center">
                                                                <span class="text-secondary text-xs font-weight-bold">${newDetail.title}</span>
                                                            </td>
                                                            <!--Role-->
                                                            <td class="align-middle text-center">
                                                                <span class="badge badge-sm bg-gradient-faded-info w-auto">${newDetail.user.fullName}</span>
                                                            </td>
                                                            <!--Full Name-->
                                                            <td class="align-middle text-center">
                                                                <span class="badge badge-sm w-auto ${newDetail.status =="Active"?"bg-gradient-success":"bg-gradient-faded-warning"}  bg-gradient-success"> ${newDetail.status}</span>
                                                            </td>

                                                            <td class="align-middle">
                                                                <a href="./news-update?id=${newDetail.newId}"  class="text-secondary font-weight-bold " data-toggle="tooltip" data-original-title="Edit user">
                                                                    Edit
                                                                </a>
                                                                     <a href="./news-delete?id=${newDetail.newId}"  class=" mx-3 text-danger font-weight-bold  " data-toggle="tooltip"  onclick="return confirm('Are you sure you want to delete the post?')" data-original-title="Edit user">
                                                                    Delete
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <!--paging-->
                                            <div aria-label="Page navigation ">
                                                <ul class="pagination justify-content-center">
                                                    <c:if test="${page>1}">      <!--previous button-->
                                                        <li class="page-item"><a class="page-link" href="./news?userId=${userId}&status=${status}&title=${title}&page=${page-1}">&laquo;</a></li>
                                                        </c:if>
                                                        <c:forEach begin="1" end="${totalPage}" var="i">
                                                        <li class="page-item <c:if test="${i.equals(page)}">active</c:if>">
                                                            <a class="page-link" href="./news?userId=${userId}&status=${status}&title=${title}&page=${i}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${page<totalPage}">
                                                        <li class="page-item"><a class="page-link" href="./news?userId=${userId}&status=${status}&title=${title}&page=${page+1}">&raquo;</a></li>
                                                        </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@include file="/admin/layout/script.jsp"  %>
</body>

</html>
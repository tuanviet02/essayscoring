<%-- 
   Document   : admin
   Created on : Apr 21, 2024, 6:54:13 PM
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

                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-md-11 ">
                        <div class="card ">
                            <div class="card-header">
                                <h5 class="card-title">Create News </h5>
                            </div>
                            <div class="card-body">
                                <form action="${action}" method="post">
                                    <input name="id" value="${id}" hidden type="text" />
                                    <div class="row">
                                        <!--email-->    
                                        <div class=" form-group col-md-12">
                                            <label class="col-form-label">Title</label>
                                            <div class="col-md-12" >
                                                <input name="title" value="${title}"   type="text" class="form-control border" />
                                                <c:if test="${not empty errTitle}">
                                                    <h6 class="text-danger px-2">${errTitle}</h6>
                                                </c:if>
                                            </div>
                                        </div>
                                        <!--content--> 
                                        <div class=" form-group col-md-12">
                                            <label class="col-form-label">Content</label>
                                            <div class="col-md-12">
                                                <textarea name="content" rows="5" cols="5" class="form-control border" placeholder="Enter content here..." >${content}</textarea>
                                                <c:if test="${not empty errContent}">
                                                    <h6 class="text-danger px-2">${errContent}</h6>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <!--role_id-->
                                        <div class=" form-group col-md-4">
                                            <label class="col-form-label">User</label>
                                            <div class="col-md-12" >
                                                <select class="form-select"  name="userId" ">
                                                    <c:forEach items="${users}" var="user" >
                                                        <option ${useId == user.userID?"selected":""} class="text-center" value="${user.userID}" > ${user.fullName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <!--status--> 
                                        <div class=" form-group col-md-4">
                                            <label class="col-form-label">Status</label>
                                            <div class="col-md-12" >
                                                <div class="row">
                                                    <div class="form-check col-md-6">
                                                        <input class="form-check-input" checked="" type="radio" name="status" id="active" value="Active" ${status =="Active" || status == ""   ? "checked":""} >
                                                        <label class="form-check-label" for="male">Active</label>
                                                    </div>
                                                    <div class="form-check col-md-6">
                                                        <input class="form-check-input" type="radio" name="status" id="inactive" value="Inactive" ${status == "Inactive"? "checked":""} >
                                                        <label class="form-check-label" for="female">Inactive</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="form-group col-md-6 ">                   
                                            <a href="./news"><button class="btn btn-danger" type="button">
                                                    Cancel
                                                </button></a>  
                                            <input class="btn btn-success" type="submit" value=" ${action != "./news-create" ? "Update":"Create"}">
                                        </div>
                                    </div>
                            </div>
                        </div>
                        </form>
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
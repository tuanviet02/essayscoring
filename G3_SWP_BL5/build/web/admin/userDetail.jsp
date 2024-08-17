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
                                <h5 class="card-title">Create new user</h5>
                            </div>
                            <div class="card-body">
                                <form action="${action}" method="post">
                                    <input name="id" value="${id}" hidden type="text" />
                                    <div class="row">
                                        <!--email-->    
                                        <div class=" form-group col-md-4">
                                            <label class="col-form-label">Email</label>
                                            <div class="col-md-12" >
                                                <input name="email" value="${email}" ${action != "./users-create" ? "disabled":""}   type="text" class="form-control border" />
                                                <c:if test="${not empty errEmail}">
                                                    <h6 class="text-danger px-2">${errEmail}</h6>
                                                </c:if>
                                            </div>
                                        </div>
                                        <!--password--> 
                                        <div class=" form-group col-md-4">
                                            <label class="col-form-label">Password</label>
                                            <div class="col-md-12" >
                                                <input name="password" value="${password}" ${action != "./users-create" ? "disabled":""} type="password" class="form-control border" />
                                                <c:if test="${not empty errPassword}">
                                                    <h6 class="text-danger px-2">${errPassword}</h6>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <!--fullname-->
                                        <div class=" form-group col-md-4">
                                            <label class="col-form-label">Full Name</label>
                                            <div class="col-md-12" >
                                                <input name="fullname" ${action != "./users-create" ? "disabled":""} value="${fullname}" type="text" class="form-control border" />
                                                <c:if test="${not empty errFullname}">
                                                    <h6 class="text-danger px-2">${errFullname}</h6>
                                                </c:if>
                                            </div>
                                        </div>
                                        <!--phone--> 
                                        <div class=" form-group col-md-4">
                                            <label class="col-form-label">Phone</label>
                                            <div class="col-md-12" >
                                                <input name="phone" ${action != "./users-create" ? "disabled":""} value="${phone}" type="text" class="form-control border" />

                                                <c:if test="${not empty errPhone}">
                                                    <h6 class="text-danger px-2">${errPhone}</h6>
                                                </c:if>
                                            </div>
                                        </div>
                                        <!--gneder--> 
                                        <div class=" form-group col-md-4">
                                            <label class="col-form-label">Gender</label>
                                            <div class="col-md-12" >
                                                <div class="row">
                                                    <div class="form-check col-md-6">
                                                        <input class="form-check-input" ${action != "./users-create" ? "disabled":""} type="radio" name="gender" id="male" value="1" ${gender ? "checked":""} >
                                                        <label class="form-check-label"  for="male">Male</label>
                                                    </div>
                                                    <div class="form-check col-md-6">
                                                        <input class="form-check-input" ${action != "./users-create" ? "disabled":""} type="radio" name="gender" id="female" value="0" ${gender != "true" ? "checked":""} >
                                                        <label class="form-check-label" for="female">Female</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <!--role_id-->
                                        <div class=" form-group col-md-4">
                                            <label class="col-form-label">Role</label>
                                            <div class="col-md-12" >
                                                <select class="form-select"  name="roleId" ">
                                                    <c:forEach items="${roles}" var="role" >
                                                        <option ${roleId == role.role_id?"selected":""} class="text-center" value="${role.role_id}" > ${role.role_name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <!--status--> 
                                        <div class=" form-group col-md-4 ${action == "./users-create" ? "d-none":""}">
                                            <label class="col-form-label">Status</label>
                                            <div class="col-md-12" >
                                                <div class="row">
                                                    <div class="form-check col-md-6">
                                                        <input class="form-check-input" type="radio" name="status" id="active" value="1" ${status !="1"? "checked":""} >
                                                        <label class="form-check-label" for="male">Active</label>
                                                    </div>
                                                    <div class="form-check col-md-6">
                                                        <input class="form-check-input" type="radio" name="status" id="inactive" value="0" ${status == "0"? "checked":""} >
                                                        <label class="form-check-label" for="female">Inactive</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="form-group col-md-6 ">                   
                                            <a href="./users"><button class="btn btn-danger" type="button">
                                                    Cancel
                                                </button></a>  
                                            <input class="btn btn-success" type="submit" value=" ${action != "./users-create" ? "Update":"Create"}">
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
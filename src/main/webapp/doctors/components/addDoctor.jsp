<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 3/4/2023
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="doctor" scope="request" class="com.hospital.hospital.vao.Doctor"></jsp:useBean>

<form class="form"
      action="${pageContext.request.getContextPath()}${doctor.getId() != 0 ? "/doctorDetails" : "/doctors"}"
      method="POST">
    <h1>${doctor.getId() != 0 ? "Update doctor" : "Add doctor"}</h1>

    <input id="id" type="hidden" name="id" value="${doctor.id}">
    <div class="form-group">
        <label for="fname">First name</label>
        <input id="fname" class="form-control" name="fname" value="${doctor != null ? doctor.fname : ""}">
    </div>
    <div class="form-group">
        <label for="lname">Last name</label>
        <input id="lname" class="form-control" name="lname" value="${doctor != null ? doctor.lname : ""}">
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input id="email" class="form-control" name="email" value="${doctor != null ? doctor.email : ""}">
    </div>
    <div class="form-group">
        <label for="phone">Phone</label>
        <input id="phone" class="form-control" name="phone" value="${doctor != null ? doctor.phone : ""}">
    </div>
    <div class="form-group">
        <label for="dob">Dob</label>
        <input id="dob" class="form-control" name="dob" value="${doctor != null ? doctor.dob : ""}">
    </div>
    <div class="form-group">
        <label for="dob">Patient quota</label>
        <input id="patient_quota" class="form-control" name="patient_quota"
               value="${doctor != null ? doctor.maxPatients : null}">
    </div>

    <button class="btn btn-primary" onclick="saveDoctor()">Save</button>
</form>
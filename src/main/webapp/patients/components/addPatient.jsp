<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 3/4/2023
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="patient" scope="request" class="com.hospital.hospital.vao.Patient"></jsp:useBean>
<jsp:useBean id="doctors" scope="request" class="java.util.ArrayList"></jsp:useBean>

<form class="form"
      action="${pageContext.request.getContextPath()}${patient.getId() != 0 ? "/patientDetails" : "/addPatient"}"
      method="POST">
    <h1>${patient.getId() != 0 ? "Update patient" : "Add patient"}</h1>

    <input id="id" type="hidden" name="id" value="${patient.id}">
    <div class="form-group">
        <label for="fname">First name</label>
        <input id="fname" class="form-control" name="fname" value="${patient != null ? patient.fname : ""}">
    </div>
    <div class="form-group">
        <label for="lname">Last name</label>
        <input id="lname" class="form-control" name="lname" value="${patient != null ? patient.lname : ""}">
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input id="email" class="form-control" name="email" value="${patient != null ? patient.email : ""}">
    </div>
    <div class="form-group">
        <label for="phone">Phone</label>
        <input id="phone" class="form-control" name="phone" value="${patient != null ? patient.phone : ""}">
    </div>
    <div class="form-group">
        <label for="dob">Dob</label>
        <input id="dob" class="form-control" name="dob" value="${patient != null ? patient.dob : ""}">
    </div>
    <div class="form-group">
        <label for="note">Note</label>
        <textarea id="note" class="form-control" rows="30"
                  name="note">${patient != null ? patient.note : ""}</textarea>
    </div>
    <div class="form-group">
        <label for="doctor">Doctor</label>
        <select id="doctor" class="form-control" name="doctor_id">
            <option>None</option>
            <c:forEach items="${doctors}" var="doctor">
                <option value="${doctor.getId()}" ${patient.doctor.id == doctor.id ? "selected" : ""}>${doctor.getFname()} ${doctor.getLname()}</option>
            </c:forEach>

        </select>
    </div>
    <button class="btn btn-primary" onclick="savePatient()">Save</button>
</form>
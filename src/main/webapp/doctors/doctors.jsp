<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 3/4/2023
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Doctors</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="${pageContext.request.getContextPath()}/css/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.getContextPath()}/doctors/scripts/doctorActions.js"></script>
</head>
<body>
<jsp:include page="../shared/header.jsp"></jsp:include>

<jsp:useBean id="doctors" scope="request" class="java.util.Vector"></jsp:useBean>

<jsp:include page="./components/addDoctor.jsp"></jsp:include>


<div class="doctors">
    <c:forEach var="doctor" items="${doctors}">
        <div class="doctor">
        <span class="doctor-name">Name: <c:out value="${doctor.getFname()}"></c:out> <c:out
                value="${doctor.getLname()}"></c:out></span>
            <span class="doctor-email">Email: <c:out value="${doctor.getEmail()}"></c:out></span>
            <span class="doctor-phone">Phone: <c:out value="${doctor.getPhone()}"></c:out></span>
            <span class="doctor-dob">Date of birth: <c:out value="${doctor.getDob()}"></c:out></span>
            <span class="doctor-quota">Patient quota: <c:out value="${doctor.getMaxPatients()}"></c:out></span>
            <span class="doctor-num-patients">Number of patients: <c:out
                    value="${doctor.getPatients().size()}"></c:out></span>
            <div class="doctor-actions">
                <a class="doctor-action warning"
                   href="${pageContext.request.getContextPath()}/doctorDetails?id=${doctor.getId()}"><i
                        class="fas fa-eye"></i></a>
                <a class="doctor-action danger"
                   onclick="prepareDelete(${doctor.getId()})"><i
                        class="fas fa-trash"></i></a>
            </div>
        </div>
    </c:forEach>
</div>

<div class="modal-container" id="modal">
    <div class="modal">
        <div class="modal-header">
            <h1>Delete doctor</h1>

            <button class="btn bg-none txt-info txt-xlg m-0 p-0" onclick="cancelDelete()"><i class="fas fa-times"></i>
            </button>
        </div>
        <div class="modal-body">
            <p>Are you sure you want to delete the user? This will delete the doctor and all of the data associated
                with
                them?</p>
        </div>
        <div class="modal-footer">
            <a class="btn primary m-0" onclick="deletePatient('${pageContext.request.getContextPath()}')">Delete
            </a>
            <button class="btn info m-0" onclick="cancelDelete()">Cancel</button>
        </div>
    </div>
</div>
</body>
</html>

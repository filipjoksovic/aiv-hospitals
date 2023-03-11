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
    <title>Patients</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="${pageContext.request.getContextPath()}/css/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.getContextPath()}/patients/scripts/patientActions.js"></script>
</head>
<body>
<jsp:include page="../shared/header.jsp"></jsp:include>

<jsp:useBean id="patients" scope="request" class="java.util.Vector"></jsp:useBean>

<jsp:include page="./components/addPatient.jsp"></jsp:include>


<div class="patients">
    <c:forEach var="patient" items="${patients}">
        <div class="patient">
        <span class="patient-name">Name: <c:out value="${patient.getFname()}"></c:out> <c:out
                value="${patient.getLname()}"></c:out></span>
            <span class="patient-email">Email: <c:out value="${patient.getEmail()}"></c:out></span>
            <span class="patient-phone">Phone: <c:out value="${patient.getPhone()}"></c:out></span>
            <span class="patient-dob">Date of birth: <c:out value="${patient.getDob()}"></c:out></span>
            <div class="patient-actions">
                <a class="patient-action warning"
                   href="${pageContext.request.getContextPath()}/patientDetails?id=${patient.getId()}"><i
                        class="fas fa-eye"></i></a>
                <a class="patient-action danger"
                   onclick="prepareDelete(${patient.getId()})"><i
                        class="fas fa-trash"></i></a>
            </div>
        </div>
    </c:forEach>
</div>

<div class="modal-container" id="modal">
    <div class="modal">
        <div class="modal-header">
            <h1>Delete patient</h1>

            <button class="btn bg-none txt-info txt-xlg m-0 p-0" onclick="cancelDelete()"><i class="fas fa-times"></i>
            </button>
        </div>
        <div class="modal-body">
            <p>Are you sure you want to delete the user? This will delete the patient and all of the data associated
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

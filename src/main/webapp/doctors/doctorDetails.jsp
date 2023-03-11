<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 3/4/2023
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../shared/header.jsp"></jsp:include>

    <title>Patient details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="${pageContext.request.getContextPath()}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:useBean id="patients" scope="request" class="java.util.ArrayList"></jsp:useBean>
<jsp:useBean id="doctor" scope="request" class="com.hospital.hospital.vao.Doctor"></jsp:useBean>


<jsp:include page="components/addDoctor.jsp"></jsp:include>


<div class="patients">
    <c:forEach items="${patients}" var="patient">
        <div class="patient">
            <span>${patient.fname} ${patient.lname}</span>
            <div class="patient-actions">
                <a class="patient-action danger" title="Remove patient from doctor's list of patients"
                   href="${pageContext.request.getContextPath()}/removePatient?patientId=${patient.id}&doctorId=${doctor.id}"><i
                        class="fas fa-times"></i></a>
            </div>
        </div>
    </c:forEach>

</div>

</body>
</html>

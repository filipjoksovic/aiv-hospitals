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
    <title>Patient doctor data report</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="${pageContext.request.getContextPath()}/css/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.getContextPath()}/patients/scripts/patientActions.js"></script>
</head>
<body>
<jsp:include page="../shared/header.jsp"></jsp:include>


<jsp:useBean id="report" scope="request" class="java.util.HashMap"></jsp:useBean>

<div class="container">
    <h1 style="margin-bottom: 1rem;">View patient's data about selected doctors</h1>
    <c:forEach items="${report.keySet()}" var="mapKey">
        <h2>${mapKey}: ${report.get(mapKey)}</h2>
    </c:forEach>

</div>
</body>
</html>

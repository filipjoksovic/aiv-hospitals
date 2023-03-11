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
    <title>Patient details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="${pageContext.request.getContextPath()}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../shared/header.jsp"></jsp:include>

<jsp:include page="components/addPatient.jsp"></jsp:include>

</body>
</html>

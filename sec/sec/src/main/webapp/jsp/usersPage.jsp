<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- Welcome to User message Page!!!


${user }<br>
${user.userName }<br> --%>


<div class="container">
<table class="table table-hover">
  <thead>
    <tr>
    <th >User Id</th>
     <th >Users</th>
     
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${usersList}" var="user">
    <tr>
    <td>${user.userId }</td>
    <td>
    <a href="${pageContext.request.contextPath}/${user_role}/showUser/${user.userId}">${user.userName}</a>
    </td>
    
    </tr>
    </c:forEach>
    </tbody>
    </table>
       


<a href="${pageContext.request.contextPath}/${user_role}/addUser"><button name="addUser" class="btn btn-primary">Add User</button></a>
</div>
</body>
</html>
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
<div class="container">
 
 <p class="h3">The User is ${retrievedUser.userName }</p>
 
 <table class="table table-hover">
  <thead>
   <tr><th>User Details</th><th>User Details</th></tr>
  </thead>
  <tbody>
 
    <tr>
    <td>User Id</td>
    <td>${retrievedUser.userId }</td>
    </tr>
   <tr>
    <td>User Name</td>
    <td>${retrievedUser.userName }</td>
    </tr>
   <tr>
    <td>User Roles</td>
    <td> <%-- <c:forEach items=" ${retrievedUserRoles}" var="role">
    ${role.roleName }<br>
    </c:forEach> --%> working on
   </td>
    </tr>
     <tr>
    <td>User Messages</td>
    <td> <%-- <c:forEach items=" ${retrievedUserMessages}" var="message">
    ${message.message }<br>
    </c:forEach> --%> working on 
   </td>
    </tr>
   
   
    </tbody>
    </table>
       
 
 </div>
<br>




</body>
</html>
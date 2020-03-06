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
 <p class="h3">Users Messages of Logged In User</p>
 <table class="table table-hover">
  <thead>
    <tr>
    <th>Message Id</th>
     <th>Messages</th>
     <th></th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${user_messages}" var="message">
    <tr>
    <td>${message.messageId }</td>
    <td>
    <a href="${pageContext.request.contextPath}/${user_role}/${message.messageId}">${message.message}</a>
    </td>
    <td> <a href="${pageContext.request.contextPath}/${user_role}/${message.messageId}/editMessage" class="btn btn-info btn-lg">
          <span class="glyphicon glyphicon-remove"></span> </a></td>
    </tr>
    </c:forEach>
 
    </tbody>
    </table>
 


        
        

</div>
</body>
</html>
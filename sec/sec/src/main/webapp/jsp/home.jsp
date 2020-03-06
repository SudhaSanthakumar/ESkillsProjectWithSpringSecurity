<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>


  
<div class="container-fluid">
 <jsp:include page="header.jsp"></jsp:include>
 <div class="container">
 <sec:authorize access="!isAuthenticated()">  
 <p class="h3"> HomePage for ${user}<br></p>
 <br>
 </sec:authorize>
 
 </div>
 <sec:authorize access="hasRole('USER')">  
<div class="container">
 <p class="h3">User DashBoard Page For Logged In User</p>
 <p class="h5">Write a Message...${func}</p>
 <%-- <c:set var="add" value="add"></c:set>
 <c:if test="${func==add}"> --%>
<!--Textarea with icon prefix-->
<div class="md-form">
  <i class="fas fa-pencil-alt prefix"></i>
  <form action="${pageContext.request.contextPath}/user/${user.userId}/addMessage" method="post">
  <textarea name="message" id="form10" class="sm-textarea form-control" rows="3" cols="50"></textarea>
  
  <button class="btn btn-info btn-lg" type="submit">
          <span class="glyphicon glyphicon-plus-sign"></span> 
        </button>
       
   </form>
  </div>
  <%-- </c:if>
  <c:set var="edit" value="edit"></c:set>
   <c:if test="${func== edit}">
  <div class="md-form">
  <i class="fas fa-pencil-alt prefix"></i>
  <form action="${pageContext.request.contextPath}/user/${message.messageId}/editMessage" method="post">
  <textarea name="message" id="form10" class="sm-textarea form-control" rows="3" cols="50" value=${message.message}></textarea>
  
  <button class="btn btn-info btn-lg" type="submit">
          <span class="glyphicon glyphicon-plus-sign"></span> 
        </button>
       
       
   </form>
  </div>
  </c:if>
 --%></div>
</sec:authorize> 
 
 
 <sec:authorize access="hasRole('ADMIN')">  
<div class="container">
Welcome to Admin Page!!!

<br>

Use User Tab to List and Add User<br>



<%-- ${user }
${user.userName } --%>
</div>
</sec:authorize> 
 
</div>


</body>
</html>

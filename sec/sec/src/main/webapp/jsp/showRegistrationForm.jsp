<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
      <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <style>
  .loginform{
  	display:table;
  	margin-left:auto;
  	margin-right:auto;
  	
  }
  .error{
  color:red;
  }
  </style>
</head>
<body>
<div class="container-fluid">
 <jsp:include page="header.jsp"></jsp:include>
 <div class="loginform">
 <sec:authorize access="!isAuthenticated()">  
<form:form action="${pageContext.request.contextPath}/doRegister" method="post" modelAttribute="newUser">
  <div class="form-group">
    <form:label path="userName">UserName</form:label>
    <form:input type="text" path="userName" name="userName" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"/>
    <form:errors path="userName" class="error"/>
  </div>
  <div class="form-group">
    <form:label path="password">Password</form:label>
    <form:input type="password" path="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password"/>
<form:errors path="password" class="error"/>
  </div>
  <div class="form-group">
    <form:label path="confirmPassword">Retype Password</form:label>
    <form:input type="password" path="confirmPassword" name="confirmPassword" class="form-control" id="exampleInputPassword1" placeholder="Confirm Password"/>
 <form:errors path="confirmPassword" class="error"/>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
  </sec:authorize >
  <sec:authorize access="hasRole('ADMIN')">  
  <form:form action="${pageContext.request.contextPath}/addNewUser" method="post" modelAttribute="newUser">
  <div class="form-group">
    <form:label path="userName">UserName</form:label>
    <form:input type="text" path="userName" name="userName" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"/>
    <form:errors path="userName" class="error"/>
  </div>
  <div class="form-group">
    <form:label path="password">Password</form:label>
    <form:input type="password" path="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password"/>
<form:errors path="userName" class="error"/>
  </div>
  <div class="form-group">
    <form:label path="confirmPassword">Retype Password</form:label>
    <form:input type="password" path="confirmPassword" name="confirmPassword" class="form-control" id="exampleInputPassword1" placeholder="Confirm Password"/>
 <form:errors path="confirmPassword" class="error"/>
  </div>
  <div class="form-group">
    <label path="roles">Role</label>
    <input type="text" path="roles" name="role" class="form-control" id="exampleInputPassword1" placeholder="Role"/>
 <%-- <form:errors path="roles" class="error"/> --%>
  </div>
   <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
  </sec:authorize>
 
</div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body>

<nav class="navbar navbar-light " style="background-color: #e3f2fd;">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">RoleManagement</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    
      <ul class="nav navbar-nav">
      <c:if test="${user!=null }">
     <sec:authorize access="isAuthenticated()">
     <c:forEach items="${user_pages }" var ="page">
        <li><a href="${pageContext.request.contextPath}/${user_role}/${page.pageName}">${page.pageName}</a></li>
        </c:forEach>
    </sec:authorize>
     </c:if>
     <sec:authorize access="!isAuthenticated()">
        <li><a href="/">Home</a></li>
        <li><a href="about">About</a></li>
      </sec:authorize>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <sec:authorize access="!isAuthenticated()">
  <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span>  Login</a></li>
   <li><a href="showRegistrationForm"><span class="glyphicon glyphicon-user"></span> SignUp</a></li>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
   <li><a href="${pageContext.request.contextPath}/${user_role}/showUser/${user.userId}"><span class="glyphicon glyphicon-user"></span> ${user.userName}</a></li>
   <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
</sec:authorize>
       
       
       
        
      
      </ul>
    </div>
  </div>
</nav>
</body>
</html>
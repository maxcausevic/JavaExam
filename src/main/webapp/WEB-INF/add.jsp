<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>CREATE TASK</title>
</head>
<body>
	<p>
		<form:errors path="task.*" />
	</p>
	<div class="container center mt-5">
		<div class="row">
			<div class="col border border-primary">
<h1>Create a Task!</h1>
<p><form:errors path="task.*"/></p>
	<form:form class="form-group push" method="POST"
		action="/createTask" modelAttribute="task">
		<p>
			<form:label path="name">Name:</form:label>
			<form:input type="name" path="name" />
		</p>
		
		<form:label path="assignees">Assignee:</form:label>
		<select name="assignees" id="assignees">
  		<c:forEach items="${assignees}" var="assignee">
    	<option value="${assignee.id}">
    	
    	<c:out value="${assignee.firstName} ${assignee.lastName}"/>
    	
    	</option>
    	</c:forEach>
   		</select>
   		
		<form:label path="priority">Priority:</form:label>
		<form:select name="priorities" id="priority" path="priority">
			<option value="high">High</option>
			<option value="medium">Medium</option>
			<option value="low">Low</option>

		</form:select>

		<input class="btn btn-success mt-3" type="submit" value="Create Task!" />
		
		
	</form:form>
</div>
</div>
</div>
	<div class="container center mt-5">
	<a class="btn btn-primary" href="/dashboard">Back to Task Dashboard</a>
		<a class="btn btn-danger m-10" href="/logout">Logout</a>
	</div>
</body>
</html>
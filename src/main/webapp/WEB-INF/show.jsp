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
<title>Show</title>
</head>
<body>
	<div class="container center mt-5">
		<div class="row">
			<div class="col border border-primary">
				<h3 class="m-5 text-info">

					Task: <c:out value="${task.name}" />
				</h3>
				<ul class="m-5 text-info">
					<li>Creator: <c:out value="${task.creator.firstName}" /></li>
					<c:forEach items="${task.assignees}" var="assignee">
					<li>Assignee: <c:out value="${assignee.firstName}" /></li>
					</c:forEach>
					<li>Priority: <c:out value="${task.priority}" /></li>
				</ul>
			</div>
			
		</div>
		 
		<a class="btn btn-primary m-10" href="/taskEdit/${task.id}">Edit</a>
		
		<form action="/delete/${task.id}" method="post">
		<input type="hidden" value="delete" name="_method">
		<input type="submit" value="Delete Task">
        <%-- <a class="btn btn-dark m-10" href="/delete/${task.id}">Delete</a> --%>
        </form>
		
	</div>
	<div class="container center mt-5">
		<a class="btn btn-danger m-10" href="/logout">Logout</a>
	</div>
</body>
</html>
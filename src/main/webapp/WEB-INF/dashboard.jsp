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
<title>Welcome to the Task Dashboard!</title>
</head>
<body>
	<h1 class="m-5 text-info">Welcome to the Task Dashboard!</h1>
	<h1 class="m-5 text-info">
		Welcome
		<c:out value="${user.firstName}" />
	</h1>
	<div class="container center mt-5">
		<div class="row">
			<div class="col border border-primary">
	<table class='table col-6 align-center'>
	<thead>
		<tr>

			<th scope="col">"Task"</th>
			<th scope="col">"Creator"</th>
			<th scope="col">"Assignee"</th>
			<th scope="col">"Priority"</th>
		</tr>
		</thead>
		<c:forEach items="${tasks}" var="task">
		
			<tr>
				<td><a href="/showTask/${task.id}">${task.name}</a></td>
				<td>${task.creator.firstName}</td>
				<c:forEach items="${task.assignees}" var="assignee">
				<td>${assignee.firstName} ${assignee.lastName}</td>
				</c:forEach>
				
				<td>${task.priority}"</td>
				<%-- <td><form action="/songs/delete/${task.priority}" method="post">
						<input type="hidden" value="delete" name="_method"> <input
							type="submit" value="Delete Song"> --%>
					<%-- </form></td> --%>

			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	</div>
	<div class="container center mt-5">
		<a class="btn btn-success m-10" href="/createTaskPage">Create Task</a>
	</div>
	<div class="container center mt-5">
		<a class="btn btn-danger m-10" href="/logout">Logout</a>
	</div>
</body>
</html>
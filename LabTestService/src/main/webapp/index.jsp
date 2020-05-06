<%@page import="com.Test"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lab Test Srvice</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>

</head>
<body>
<div class="container">
	<div class="row" >
		<div class="col-6">
			<h1>Lab Tests</h1>
			
			<form id="formItem" name="formItem" method="post" action="index.jsp">

				Test name:
				<input id="testName" name="testName" type="text" class="form-control form-control-sm">
				<br>
				 
				Test cost:
				<input id="testCost" name="testCost" type="text" class="form-control form-control-sm">
				<br>
				 
				Test description:
				<input id="testDesc" name="testDesc" type="text" class="form-control form-control-sm">
				<br>
				
				Lab No:
				<input id="labNo" name="labNo" type="text" class="form-control form-control-sm">
				<br>
				
				Hospital Name:
				<input id="hosname" name="hosName" type="text" class="form-control form-control-sm">
				<br>
				
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				<input type="hidden" id="IDSave" name="IDSave" value="">
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>

			<div id="divItemsGrid">
				<%
					Test Testobj = new Test();
					out.print(Testobj.readAllTests());
					
				%>
			</div>
		</div>
	</div>
</div>
</body>
</html>
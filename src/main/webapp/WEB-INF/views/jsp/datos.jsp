<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> 
</head>
<body>
<div class="container">
	<h1>Registro de contactos </h1>
	<br/>
	<%-- Todos lo datos se vuelcan en el objeto modelAttribute="contacto"  --%>
	<f:form action="alta" method="POST" class="form-horizontal" modelAttribute="contacto">
		<div class="form-group">
			<label class="control-label col-sm-2">Nombre:</label>
			<%-- Spring llama al método setNombre() en el objeto contacto con el valor enviado --%>
			<f:input path="nombre" class="form-control" style="width:30%" />
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">Email:</label>
			<f:input path="email" class="form-control" style="width:30%" required="required" />
		</div>	
		<div class="form-group">	
			<label class="control-label col-sm-2">Telefono:</label>
			<f:input path="telefono" class="form-control" style="width:30%" />	
		</div>	
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default" style ="width:30%">Enviar</button>
            </div>    
		</div>
	</f:form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contactos</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> 
</head>
<body>

    <div class="container">
    <div style="margin-top: 50px;">
        <h2>Listado de Contactos</h2>
        <p>En esta página se muestran los contactos almacenados en la aplicación.</p>
        
        <c:if test="${not empty mensaje}">
            <div class="alert alert-success">
                <strong>Aviso: </strong> ${mensaje}
            </div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <strong>Error: </strong> ${error}
            </div>
        </c:if>
    	<div>
    		<table border="1" class="table table-striped table-bordered" >
    		    <thead><tr>
		    		    <th>Nombre</th>
		    		    <th>Email</th>
		    		    <th>Teléfono</th>
		    		    <th>Acciones</th>
    		    		</tr>
    		   </thead>
    		   <tbody>
    		   <%-- Forma explicita de acceder a los atributos almacenados en el objeto HttpServletRequest--%>
    		     <c:forEach var="ct" items="${requestScope.contactos}">	
    		        <tr>
    		           <td>${ct.nombre}</td>
    		           <td>${ct.email}</td>
    		           <td>${ct.telefono}</td>
    		           <td><a href="eliminar?idContacto=${ct.idContacto}">Eliminar</a></td>
    		       	</tr>
    		       	</c:forEach>
    		   </tbody>
    		   </table>
    	</div>
    	<br/><br/>
    	<a href="volver">Volver</a>
    </div>
    </div>

</body>
</html>
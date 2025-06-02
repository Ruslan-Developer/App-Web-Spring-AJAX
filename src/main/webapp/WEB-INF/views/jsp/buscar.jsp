<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Buscador de contactos</title>
    <!-- Asegúrate de tener jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <!-- Tu JS externo -->
    <script src="<c:url value='/resources/js/buscar.js'/>"></script>
</head>
<body>
<div class="container">
    <h1>Web de búsqueda de contactos</h1>

    <div class="form-group">
        <label class="control-label col-sm-2" for="nombre">
            Introduce nombre:
        </label>
        <input 
            type="text" 
            id="nombre" 
            class="form-control" 
            style="width:30%" 
            placeholder="Nombre a buscar" />
    </div>

    <div id="resultado"></div>

    <br/><br/>
    <a href="volver">Volver</a>
</div>
</body>
</html>
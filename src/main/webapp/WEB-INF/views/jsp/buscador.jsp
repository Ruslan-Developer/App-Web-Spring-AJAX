<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buscador AJAX Spring</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> 

<script type="text/javascript">
	var objAjax = new XMLHttpRequest();
	function buscar(){
       //Obtenemos el valor introducido en el campo texto para mandarlo como parametro de la petición
       var texto = document.getElementById("nombre").value;
       //Configuramos la url con el parámetro de búsqueda a enviar al controlador
       var url = "buscar";
       url += "?nombre=" + texto;
       //Configurar datos de la petición
       objAjax.open("GET", url, true);
       //Definir función de retrollamada que se ejecutará cuando se reciba la respuesta del servidor
       objAjax.onreadystatechange=procesarRespuesta;
       //Enviar la petición
       objAjax.send(null);
	}
	function procesarRespuesta(){
        //Si la respuesta esta disponible, se procesa
        if(objAjax.readyState==4){
            //Transforma una cadena de texto en una estrucura JSON
            var json = JSON.parse(objAjax.responseText);
            var respuesta="<ul>";
            //Recorremos el array de objetos JSON y para cada uno de ellos generamos un elemento de lista
            json.forEach(function(objeto) {
                respuesta += "<li>" + objeto.nombre + " - " + objeto.email + " - " + objeto.telefono + "</li>";
            });
            respuesta += "</ul>";
            document.getElementById("resultado").innerHTML = respuesta;
        }
	}
</script>	
</head>
<body>
<div class="container">
	<h1>Web de busqueda de contactos</h1>
	<br/>
	<div class="form-group">
		<label class="control-label col-sm-2">Introduce nombre:</label>
		<input type="text" id="nombre" onkeyup="buscar();" class="form-control" style="width:30%" />
	</div>
	<br/>
	<div id="resultado">
	</div>
	<br/><br/>
	<a href="volver">Volver</a>"
</div>


</body>
</html>
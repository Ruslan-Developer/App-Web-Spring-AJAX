/**
 * En este código lo que se hace es una petición a url: /buscar:
 * En este caso es un punto de acceso endpoint mapeado a un método especifico 
 * dentro de un controlador en el servidor. El controlador procesa la petición
 * y devuelve los datos JSON, el controlador es una parte de la app del lado del servidor.
 * 
 */

function buscar() {
	var texto = $('#nombre').val(); //Toma el valor del input con id 'nombre'
	$.ajax({
		url: 'buscar', //URL del controller donde se procesa la búsqueda
		data: {nombre: texto}, //Envía el valor del input como parámetro
		dataType: 'json', //Especifica que se espera una respuesta en formato JSON
		success: function(data) { //Función que se ejecuta si la petición es exitosa
			var respuesta ='<ul>';
			//Data es un array de objetos que viene de la peticion AJAX como respuesta
			$.each(data, function(i, objeto) {
				respuesta += '<li>'
				+ objeto.id + ' - ' + objeto.nombre + ' - ' + objeto.email + ' - ' + objeto.telefono
				+ '</li>'; //Construye una lista con los resultados
			});
			respuesta += '</ul>';
			$('#resultado').html(respuesta); //Vuelca el HTML generado en el elemento con id 'resultado'
			},
			error: function() { //Función que se ejecuta si hay un error en la petición
				$('#resultado').html('<p>Error al buscar</p>'); //Muestra un mensaje de error
				
				}
			});
		}
		
		$(document).ready(function(){
			$('#nombre').on('keyup', buscar);
		});

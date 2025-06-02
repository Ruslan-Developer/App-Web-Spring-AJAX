package com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.model.Contacto;
import com.agenda.service.ContactosService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 * Esta clase se encarga de recibir las solicitudes HTTP y delegar la lógica de negocio
 * a la capa de servicio correspondiente.
 * La llamada al controlador se realiza desde la vista (JSP) mediante un formulario utilizando el método POST.
 * @author [Ruslan Tejerina] 
 */

@Controller
public class ContactosController {
	
	@Autowired
	ContactosService contService;
	
	@GetMapping("paginaAlta")
	public String preAltaContacto(Model model) {
		//Creamos el objeto Contacto y lo almacenamos como ModelAttribute
		//Para que Spring guarde los parametros de la petición en el objeto Contacto 
		Contacto contacto = new Contacto();
		model.addAttribute("contacto", contacto); // Añade el objeto contacto al modelo para que esté disponible en la vista
		return "datos"; // Devuelve el nombre de la vista "datos" que se mostrará al usuario para ingresar los datos del formulario
	}
	
	
	/**
	 * Método que maneja la solicitud de alta de un nuevo contacto.
	 * se le debe pasar el nombre, email y teléfono del contacto a crear.
	 * @param nombre
	 * @param email
	 * @param telefono
	 * @return
	 */
	@PostMapping("alta")
	public String nuevoContacto(@ModelAttribute("contacto") Contacto contacto, 
			RedirectAttributes redirectAttrs) {
		// No se necesita crear el objeto Contacto aquí, viene ya inyectado por el ModelAttribute
		//Contacto contacto = new Contacto(0, nombre, email, telefono);
		if(contService.nuevoContacto(contacto)) {
			redirectAttrs.addFlashAttribute("mensaje", "Contacto guardado correctamente"); // Añade un mensaje de éxito a los atributos de redirección
			return "redirect:/recuperar"; // Redirige a la página de inicio si el contacto se guarda correctamente
		}
		redirectAttrs.addFlashAttribute("error", "El contacto ya existe"); // Añade un mensaje de error a los atributos de redirección
		return "redirect:/repetido"; // Redirige a la página de error si el contacto ya existe
	}
	/**
	 * Necesitamos guardar la lista de contactos que recuperamos del Service procedente de la base de datos
	 * en un atributo almacenados en el objeto HttpServletRequest que despues se accederá desde la vista (JSP)
	 * @param request
	 * @return
	 */
	@GetMapping(value= "recuperar") //especifica la ruta o URL que el método manejará cuando se realice una solicitud HTTP GET
	public ModelAndView recuperarContactos(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("contactos"); // Crea un objeto ModelAndView para la vista "contactos"
		try {
			List<Contacto> listaContactos = contService.obtenerContactos(); // Obtiene la lista de contactos del servicio
			mav.addObject("contactos", listaContactos); // Añade la lista de contactos al modelo de la vista
			mav.addObject("titulo", "Lista de Contactos"); // Añade un título al modelo de la vista
			//request.setAttribute("contactos", contService.obtenerContactos()); // Establece el atributo "contactos" con la lista de contactos obtenida del servicio
		} catch (Exception e) {
		
			mav.setViewName("error"); // Si ocurre una excepción, redirige a la vista de error
			mav.addObject("mensaje", "Error al recuperar los contactos"); // Añade un mensaje de error al modelo de la vista
			mav.addObject("error", e.getMessage()); // Añade el mensaje de la excepción al modelo de la vista
			//request.setAttribute("contactos", null); // Si ocurre una excepción, establece el atributo "contactos" como null
		}
	
		return mav; // Redirige a la página de contactos
	}
	
	@GetMapping(value= "eliminar")
	public String eliminarContacto(@RequestParam("idContacto") int idContacto) {
		if(contService.eliminarContacto(idContacto)) {
			return "forward:/recuperar"; // Transfiere la solicitud al controlador con URL /recuperar para mostrar la lista actualizada de contactos
		}
		return "noexiste"; // Redirige a la página de error si el contacto no existe
	}
	
	/**
	 * La lista de objetos Contacto serán transformados a un array de objetos JSON automáticamente por Spring
	 * La anotación @ResponseBody indica a Spring que el valor de retorno debe ser serializado directamente en el cuerpo de la respuesta HTTP, en vez de ser interpretado como un nombre de vista
	 * @RequestParam("nombre") String nombre: Captura el parámetro "nombre" de la URL. Por ejemplo, en una petición a "/buscar?nombre=Juan", el parámetro "nombre" tendría el valor "Juan".
	 * @param nombre
	 * @return
	 * Este controlador proporciona una API REST que permite buscar contactos por nombre y devuelve los resultados en formato JSON, 
	 * ideal para aplicaciones que necesitan consumir estos datos de forma asíncrona (como aplicaciones JavaScript/AJAX).
	 */
	
	@GetMapping(value= "buscar", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Contacto> buscarContactos(@RequestParam("nombre") String nombre) {
		// Busca contactos por nombre y devuelve una lista de contactos que coinciden con el nombre proporcionado
		return contService.buscarContactos(nombre);
	}

}

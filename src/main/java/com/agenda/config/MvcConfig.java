package com.agenda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan(basePackages = {"com.agenda.controller"})
@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	/**
	 * Configuración de los recursos estáticos de la aplicación.
	 * Permite servir archivos estáticos como CSS, JavaScript e imágenes desde el directorio /resources/.
	 */
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}
	
	/**
	 * Establecemos las navegaciones estáticas de la aplicación
	 * para que no se necesite un controlador para cada una de ellas.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("inicio"); // Mapeo de la ruta raíz a la vista index.jsp
		registry.addViewController("/volver").setViewName("inicio"); // Mapeo de la ruta /volver a la vista contactos.jsp
		registry.addViewController("/paginaBuscador").setViewName("buscar"); //Asociamos la ruta /paginaBuscador que esta en inicio.jsp a la vista buscador.jsp
	}

}

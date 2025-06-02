package com.agenda.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
@ComponentScan(basePackages = {"com.agenda.repository", "com.agenda.service"})
@Configuration
@PropertySource("classpath:application.properties")
public class SpringConfig {
	
	@Value("${ref-jndi}")
	private String refDataSource;
	/*
	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		dataSource.setUrl("jdbc:mysql://localhost:3306/agenda"); // URL de la base de datos
		dataSource.setUsername("root"); 
		dataSource.setPassword("toor");
		return dataSource;			
	}
	*/
	
	/**
	 * Método que permite conectarse a la base de datos usando JNDI.
	 * De este modo, las aplicaciones no tienen su propia configuración de conexión a la base de datos,
	 * sino que utilizan las propias del servidor de aplicaciones (como Tomcat, WildFly, etc.). 
	 * @return
	 */
	
	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true); // Indica que se busca la referencia JNDI que se ha inyectado en la variable refDataSource 
		return dsLookup.getDataSource(refDataSource); // Busca el DataSource en el contexto JNDI usando la referencia proporcionada en application.properties
	}
	
	/**
	 * Método que crea un bean de JdbcTemplate para realizar operaciones en la base de datos.
	 * Se inyecta el DataSource configurado anteriormente para establecer la conexión a la base de datos.
	 * @param dataSource permite establecer la conexión a la base de datos.
	 * @return 
	 */
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}

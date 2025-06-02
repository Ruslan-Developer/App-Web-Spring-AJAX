package com.agenda.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.agenda.model.Contacto;
/**
 * Implementación de la interfaz ContactosRepository para manejar la persistencia
 * de contactos en una base de datos utilizando JdbcTemplate.
 * Las clases de este paquete son responsables de la interacción con la base de datos.
 *  * @author [Ruslan Tejerina] 
 */
@Repository
public class ContactosRepositoryImpl implements ContactosRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate; // Inyección de dependencias de JdbcTemplate para realizar operaciones en la base de datos

	@Override
	public void guardarContacto(Contacto contacto) {
		String sql = "INSERT INTO contactos (nombre, email, telefono) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, contacto.getNombre(), contacto.getEmail(), contacto.getTelefono());

	}

	@Override
	public Contacto buscarContactoEmail(String email) {
		String sql = "SELECT * FROM contactos WHERE email = ?";
		List<Contacto> listaContactosByEmail = jdbcTemplate.query(sql, (rs, f) -> new Contacto(
				rs.getInt("idContacto"), //Columna idContacto de la tabla contactos
				rs.getString("nombre"), 
				rs.getString("email"), 
				rs.getInt("telefono")), 
				email); // Se usa el email como parámetro de búsqueda
		return listaContactosByEmail.isEmpty() ? null : listaContactosByEmail.get(0);

	}

	@Override
	public Contacto buscarContactoPorId(int idContacto) {
		String sql = "SELECT * FROM contactos WHERE idContacto = ?";
		List<Contacto> contactos = jdbcTemplate.query(sql, (rs, rowNum) -> new Contacto(
				rs.getInt("idContacto"), //Es un valor que se pasa como parámetro y se usa como un valor en el PreparedStatement
				rs.getString("nombre"),
				rs.getString("email"),
				rs.getInt("telefono")),
				idContacto); // Se usa el id como parámetro de búsqueda
		return contactos.size()>0?contactos.get(0):null;
	}

	@Override
	public List<Contacto> listarContactos() {
		String sql = "SELECT * FROM contactos";
		//Por cada fila de la tabla contactos, se crea un objeto Contacto utilizando los valores obtenidos de las columnas de la fila
		List<Contacto> listaContactos = jdbcTemplate.query(sql, (rs, rowNum) -> new Contacto( //RowMapper 
				rs.getInt("idContacto"), 
				rs.getString("nombre"), 
				rs.getString("email"), 
				rs.getInt("telefono")));
		return listaContactos; //Devuelve una lista de objetos Contacto

	}

	@Override
	public void eliminarContacto(int idContacto) {
		String sql = "DELETE FROM contactos WHERE idContacto = ?";
		jdbcTemplate.update(sql, idContacto);

	}

}

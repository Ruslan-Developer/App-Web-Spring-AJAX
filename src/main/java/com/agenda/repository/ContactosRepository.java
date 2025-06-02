package com.agenda.repository;

import java.util.List;

import com.agenda.model.Contacto;

public interface ContactosRepository {
	
	public void guardarContacto(Contacto contacto);		
	
	public Contacto buscarContactoEmail(String email);
	
	public Contacto buscarContactoPorId(int idContacto);
	
	public List<Contacto> listarContactos();
	
	public void eliminarContacto(int id);

}

package com.agenda.service;

import java.util.List;

import com.agenda.model.Contacto;

public interface ContactosService {
	
	boolean nuevoContacto(Contacto contacto);
	List<Contacto> obtenerContactos();
	boolean eliminarContacto(int id);
	List<Contacto> buscarContactos(String nombre);

}

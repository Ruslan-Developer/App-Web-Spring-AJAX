package com.agenda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.model.Contacto;
import com.agenda.repository.ContactosRepository;

@Service
public class ContactosServiceImpl implements ContactosService {
	/**
	 * Esta capa inyecta la capa de persistencia (repositorio) y se encarga de la
	 * la lógica de negocio para la gestión de contactos.
	 * IMPORTANTE: se inyecta la interfaz ContactosRepository, no la implementación.
	 */
	@Autowired
	ContactosRepository contRepository;
	
	@Override
	public boolean nuevoContacto(Contacto contacto) {
		if(contRepository.buscarContactoEmail(contacto.getEmail()) == null) {
			contRepository.guardarContacto(contacto);
			return true;
		} else {
			System.out.println("El contacto ya existe");
			return false;
		}
	
	}

	@Override
	public List<Contacto> obtenerContactos() {
		List<Contacto> contactos = contRepository.listarContactos();
		try {
			if(contactos == null || contactos.isEmpty()) {
				throw new Exception("No hay contactos en la base de datos");
			}
			return contactos;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<Contacto>(); // Devuelve una lista vacía si no hay contactos
		}
		
	}

	@Override
	public boolean eliminarContacto(int id) {
		if(contRepository.buscarContactoPorId(id) != null) {
			contRepository.eliminarContacto(id);
			return true;
		}
		return false;
	}

	@Override
	public List<Contacto> buscarContactos(String nombre) {
		return contRepository.listarContactos()
				.stream()
				.filter(c->c.getNombre().contains(nombre)) //Selecciona solo los contactos cuyo nombre contiene el texto buscado
				.collect(Collectors.toList()); //Transforma el Stream en una lista 
	}

}

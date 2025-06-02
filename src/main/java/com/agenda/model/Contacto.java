package com.agenda.model;

public class Contacto {
	
	private int idContacto;
	private String nombre;
	private String email;
	private int telefono;
	
	public Contacto() {
	}
	
	public Contacto(int id, String nombre, String email, int telefono) {
		this.idContacto = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	

}

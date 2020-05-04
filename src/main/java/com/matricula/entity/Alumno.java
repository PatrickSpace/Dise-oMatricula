package com.matricula.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Alumno")
public class Alumno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false, length = 150)
	private String password;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Size(max = 8, min = 8, message = "Su dni debe tener 8 digitos")
	@Column(name = "dni", nullable = false)
	private String dni;
	
	@Email
	@Column(name = "email", nullable = true)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Alumno(Long id, @NotEmpty(message = "Ingrese el nombre de usuario") String username,
			@NotEmpty(message = "Ingrese la contraseña") String password, String nombre,
			@Size(max = 8, min = 8, message = "Su dni debe tener 8 digitos") String dni, @Email String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.dni = dni;
		this.email = email;
	}

	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

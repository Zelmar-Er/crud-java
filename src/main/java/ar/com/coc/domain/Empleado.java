package ar.com.coc.domain;

public class Empleado {
	// DNI(id-PK)INT - Apellido String - Nombre String - Deipto_id(FK)
	private int id;
	private String apellido;
	private String nombre;
	private Long departamento;
	
	// Constructor
	public Empleado(int id,String nombre, String apellido, Long departamento) {
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.departamento = departamento;
	}
	
	// Metodos
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Long getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Long depto) {
		this.departamento = depto;
	}
}

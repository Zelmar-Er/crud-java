package ar.com.coc.domain;

public class Departamento {
	private Long id; // private actua como public para la clase misma, pero es invisible para el resto
	private String nombre;
	private Double presupuesto;
	
	// Constructor
	public Departamento(Long id,String nombre,double presupuesto) {
		this.id = id; // El id esta vacio, va a estar lleno con un dato que viene de un SELECT * FROM
		this.nombre = nombre;
		this.presupuesto = presupuesto;
	}
	// Metodos
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Double presupuesto) {
		this.presupuesto = presupuesto;
	}
	@Override
	public String toString() {
		return "Departamento [id= "+id+", nombre= "+nombre+", presupuesto= "+presupuesto+"]";
	}
}

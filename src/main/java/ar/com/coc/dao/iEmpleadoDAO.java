package ar.com.coc.dao;

import java.util.List;

import ar.com.coc.domain.Empleado;

public interface iEmpleadoDAO {
	/*
	 	Definimos los metodos de acceso a la tabla de empleados
	 
	  	CRUD
	 	getById() = obtener por ID
	 	find() = encontrar
	 	delete() = eliminar
	 	update() = actualizar
	 	create() = crear
	 
	 */
	
	// SELECT * FROM empleados WHERE id = id;
	// en este caso el id es el dni
	public Empleado getById(int id) throws Exception; 
	
	// SELECT * FROM empleados;
	// nos devuelve una java list de objetos Empleado
	public List<Empleado> findAll() throws Exception;
	
	// DELETE FROM empelados WHERE dni = dni;
	public void delete(int id) throws Exception;
	
	// UPDATE empleados SET nombre = nombre, apellido = apellido, dni = dni, departamento = departamento WHERE dni = dni;
	public void update(Empleado empleado) throws Exception;
	
	// INSERT TO empleados(camp1..camp2..camp3..) VALUES (newEmpleado.camp1 newEmpleado.camp2 newEmpleado.camp3)
	public void create(Empleado newEmpleado) throws Exception;
	
	// SELECT * FROM empleados WHERE clave LIKE '%clave%';
	public List<Empleado> search(String clave) throws Exception; // search bar
	
}

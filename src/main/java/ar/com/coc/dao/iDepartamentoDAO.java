package ar.com.coc.dao;

import java.util.List;

import ar.com.coc.domain.Departamento;

public interface iDepartamentoDAO {
	// Definimos metodos de acceso a la tabla departamentos
		/* Las interfaces por si solas no hacen nada, solo son una estructura.
		 Se deben implementar en una clase, es decir en una clase utilizamos los metodos aca definidos */
	
	/* CRUD
	 	getById() = obtener por ID
	 	find() = encontrar
	 	delete() = eliminar
	 	update() = actualizar
	 	create() = crear
	 								*/
	
	// SELECT * FROM departamentos WHERE id = id;
	public Departamento getById(Long id) throws Exception; // retorna de un id todos los campos
	
	// cambiamos el array por una lista de java
	// SELECT * FROM departamentos;
	public List<Departamento> findAll() throws Exception; // retorna todos los registros de dicha tabla
	
	// DELETE FROM departamentos WHERE id = id;
	public void delete(Long id) throws Exception; // elimina un registro coincidente con el id
	
	// UPDATE departamentos SET nombre = nombre, presupuesto = presupuesto WHERE id = departamento.id;
	public void update(Departamento depto) throws Exception; // le pasamos un objeto
	
	// INSERT TO departamentos(campo1..campo2..campo3) VALUES (newDepto.campo1 newDepto.campo2 newDepto.campo3..)
	public void create(Departamento newDepto) throws Exception;
	
	// SELECT * FROM departamentos WHERE clave LIKE '%clave%';
	public List<Departamento> search(String clave) throws Exception; // search bar
	
}

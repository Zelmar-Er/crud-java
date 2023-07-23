package ar.com.coc.dao.implement;

import ar.com.coc.dao.iEmpleadoDAO;
import ar.com.coc.db.AdminConnectionDB;
import ar.com.coc.domain.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/*
 *  La interface List tiene una serie de metodos como:
 *  	add(element)
 *  	remove(element)
 *  Estos nos permiten trabajar como si fueran vectores dinamicos como en js, dependiendo de la memoria
 */
/*
 *  En esta clase heredamos de la interface DAO, que define los metodos para interactuar con la base de datos
 */

public class EmpleadoDAOMysqlImpl implements iEmpleadoDAO{
	@Override
	public Empleado getById(int id) throws Exception{
		// creamos la connection a la db
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "SELECT * FROM empleados WHERE dni = " + id;
		Statement statement = connection.createStatement();
		// obtenemos el resultado
		ResultSet resultSet = statement.executeQuery(sql);
		// El resultSet devuelve un registro de la tabla
		// Primero verificamos si hay datos
		if(resultSet.next()) {
			// Obtenemos el dato del campo dni/id coincidente
			int idBd = resultSet.getInt("dni");
			String apellidoBd = resultSet.getString("apellido");
			String nombreBd = resultSet.getString("nombre");
			Long idDepto = resultSet.getLong("depto_id");
			return new Empleado(idBd,apellidoBd,nombreBd,idDepto);
		}
		cerrar(connection);
		return null; // Si no encuentra coincidencias no retorna nada		
	}
	
	@Override
	public List<Empleado> findAll() throws Exception{
		// creamos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "SELECT * FROM empleados"; // consult
		Statement statement = connection.createStatement();
		// obtenemos el resultSet
		ResultSet resultSet = statement.executeQuery(sql);
		/*
		 *  El resultSet retorna todos los datos de la table
		 *  verificamos si hay datos y creamos una java List, del tipo Empleado, si los hay
		 */
		List<Empleado> empleados = new ArrayList<Empleado>();
		// mientras alla resultados, los agregamos a la lista
		while(resultSet.next()) {
			// Obtenemos los datos de los campos
			int idBd = resultSet.getInt("dni");
			String nombreBd = resultSet.getString("nombre");
			String apellidoBd = resultSet.getString("apellido");
			Long idDepto = resultSet.getLong("depto_id");
			// creamos el empleado y lo agregamos a la list
			Empleado empleado = new Empleado(idBd,nombreBd,apellidoBd,idDepto);
			empleados.add(empleado);
		}
		cerrar(connection);
		// retornamos la list
		return empleados;
	}
	
	@Override
	public void delete(int id) throws Exception{
		// creamos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "DELETE FROM empleados WHERE dni = "+id; // consulta
		Statement statement = connection.createStatement();
		// return 0/1, no hace falta verificar
		statement.execute(sql);
		cerrar(connection);
	}
	
	@Override // Creamos un Empleado con los datos modificados del empleado
	public void update(Empleado empleado) throws Exception{
		// creamos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "UPDATE empleados SET nombre = ?, apellido = ?, depto_id = ? WHERE dni = ?";
		PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1,empleado.getNombre());
		statement.setString(2,empleado.getApellido());
		statement.setLong(3,empleado.getDepartamento());
		statement.setInt(4,empleado.getId());
		// retorna 0/1 no necesita verificacion
		statement.execute();
		cerrar(connection);
	}
	
	@Override
	public void create(Empleado empleado) throws Exception{
		// cramos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "INSERT INTO empleados (dni,nombre,apellido,depto_id) VALUES (?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setInt(1,empleado.getId());
		statement.setString(2, empleado.getNombre());
		statement.setString(3, empleado.getApellido());
		statement.setLong(4, empleado.getDepartamento());
		// retorna 0/1 no necesita verificacion
		statement.execute();
		ResultSet res = statement.getGeneratedKeys(); // RETORNA LA KEY QUE SE GENERO
		if(res.next()) {
			System.out.println("Empleado creado de manera exitosa");
		}
		cerrar(connection);
	}
	
	private void cerrar(Connection con) throws Exception{
		con.close();
	}
	
	@Override
	public List<Empleado> search(String clave) throws Exception{
		// creamos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "SELECT * FROM empleados WHERE dni LIKE ? OR nombre LIKE ? OR apellido LIKE ? OR depto_id LIKE ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		// seteamos los valores en remplazo del los ?
		statement.setString(1,"%"+clave+"%");
		statement.setString(2,"%"+clave+"%");
		statement.setString(3,"%"+clave+"%");
		statement.setString(4,"%"+clave+"%");
		// obtenemos el resultSet
		ResultSet resultSet = statement.executeQuery();
		// creamos la List de coincidencias
		List<Empleado> empleados = new ArrayList<Empleado>();
		// verificamos si tenemos coincidencis, mientras tengamos las agregamos a la lista
		while(resultSet.next()) {
			empleados.add(this.crearEmpleado(resultSet));
		}
		cerrar(connection);
		return empleados;
	}
	
	private Empleado crearEmpleado(ResultSet resultSet) throws Exception{
		// obtenemos los campos
		int dniDb = resultSet.getInt("dni");
		String nombreDb = resultSet.getString("nombre");
		String apellidoDb = resultSet.getString("apellido");
		Long depto_idDb = resultSet.getLong("depto_id");
		
		return new Empleado(dniDb,nombreDb,apellidoDb,depto_idDb);
	}
	
	
}

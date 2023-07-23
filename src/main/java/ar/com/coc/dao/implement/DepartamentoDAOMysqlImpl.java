package ar.com.coc.dao.implement;

import ar.com.coc.dao.iDepartamentoDAO;
import ar.com.coc.db.AdminConnectionDB;
import ar.com.coc.domain.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/*
 	La interface List tiene una serie de metodos que son:
 		add(element)
 		remove(element)
 	Estos permiten trabajar como si fueran vectores dinamicos como en js, dependiendo de la memoria
*/
/*
 	En esta clase heredamos de la interface DAO, iDepartamentoDAO, que
 	define los metodos para interactuar con la base
 */
public class DepartamentoDAOMysqlImpl implements iDepartamentoDAO{
	@Override
	public Departamento getById(Long id) throws Exception{
		// creamos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "SELECT * FROM departamentos WHERE id = "+id; // consulta
		Statement statement = connection.createStatement();
		// obtenemos el resultSet
		ResultSet resultSet = statement.executeQuery(sql);
		// El resultSet devuelve un registro de la tabla
		// Primero verificamos si hay datos
		if(resultSet.next()) {
			// Obtenemos el dato del campo id coincidente
			Long idBd = resultSet.getLong("id");
			String nombreBd = resultSet.getString("nombre");
			Double presupuestoBd = resultSet.getDouble("presupuesto");
			return new Departamento(idBd,nombreBd,presupuestoBd);
		}
		cerrar(connection);
		return null; // Si no hay resultset no retorna nada
	}
	
	@Override
	public List<Departamento> findAll() throws Exception{
		// creamos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "SELECT * FROM departamentos"; // consulta
		Statement statement = connection.createStatement();
		// obtenemos el resultSet
		ResultSet resultSet = statement.executeQuery(sql);
		// El resultSet retorna todos los datos de la tabla
		// Primero verificamos si hay datos
		// Creamos una lista de departamentos
		List<Departamento> departamentos = new ArrayList<Departamento>();
		// Mientras encontremos resultados de la BD
		while(resultSet.next()) {
			// Obtenemos los datos de los campos
			Long idBd = resultSet.getLong("id");
			String nombreBd = resultSet.getString("nombre");
			Double presupuestoBd = resultSet.getDouble("presupuesto");
			// Cremos el departamento con los datos y lo agregamos a la lista
			Departamento department = new Departamento(idBd,nombreBd,presupuestoBd);
			departamentos.add(department);
		}
		cerrar(connection);
		// retornamos la lista departamentos
		return departamentos;
	}
	
	@Override
	public void delete(Long id) throws Exception{
		// creamos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "DELETE FROM departamentos WHERE id ="+id; // consulta
		Statement statement = connection.createStatement();
		// return 1/0, no hace falta que verifiquemos
		statement.executeUpdate(sql);
		cerrar(connection);
	}
	
	@Override
	public void update(Departamento depto) throws Exception{
		// Creamos un Departamento con los datos modificados del departamento
		// creamos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "UPDATE departamentos SET nombre = ?, presupuesto = ? WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1,depto.getNombre());
		statement.setDouble(2,depto.getPresupuesto());
		statement.setLong(3,depto.getId());
		// retorna 1/0, no hace falta verificar
		statement.execute();
		cerrar(connection);
	}
	
	@Override
	public void create(Departamento newDepto) throws Exception{
		// creamos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "INSERT INTO departamentos (id,nombre,presupuesto) VALUES (?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setLong(1,newDepto.getId());
		statement.setString(2,newDepto.getNombre());
		statement.setDouble(3,newDepto.getPresupuesto());
		// retorna 1/0, no hace falta verificar
		statement.execute();
		ResultSet res = statement.getGeneratedKeys(); // RETORNA LA KEY QUE SE GENERO
		if(res.next()) {
			System.out.println("Se creo el departamento correctamente");
		}
		cerrar(connection);
	}
	
	@Override
	public List<Departamento> search(String clave) throws Exception{
		// cramos la connection a la DB
		Connection connection = AdminConnectionDB.getConnection();
		// creamos el statement
		String sql = "SELECT * FROM departamentos WHERE nombre LIKE ? OR id LIKE ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		// seteamos el valor que va en remplazo del "?"
		statement.setString(1,"%"+clave+"%");
		statement.setString(2,"%"+clave+"%");
		// obtenemos el resultSet
		ResultSet resultSet = statement.executeQuery();
		// Interface i = new ClaseQueImplementaLaInterface();
		List<Departamento> depto = new ArrayList<Departamento>();
		// verificamos si hay datos
		while(resultSet.next()) {
			depto.add(this.crearDepto(resultSet));
		}
		cerrar(connection);
		return depto;
	}
	
	private void cerrar(Connection con) throws Exception{
		con.close();
	}
	
	private Departamento crearDepto(ResultSet resultSet) throws Exception {
		// obtenemos el dato del campo id
		Long idDb = resultSet.getLong("id");
		String nombre = resultSet.getString("nombre");
		Double presupuesto = resultSet.getDouble("presupuesto");
		
		return new Departamento(idDb,nombre,presupuesto);
	}
	
	//TODO implementar un metodo que busqu por nombre y que retorne una lista de dapartamentos
}

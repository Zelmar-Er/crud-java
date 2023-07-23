package ar.com.coc.empleados.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.coc.dao.iEmpleadoDAO;
import ar.com.coc.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.coc.domain.Empleado;

@WebServlet("/UpdateEmpleadoController")
public class UpdateEmpleadoController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		// capturamos los parametros que vienen en el req desde el form, en los input name
		String dni = req.getParameter("dni");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String depto_id = req.getParameter("depto_id");
		// interface = new class que implementa la interface
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		
		Empleado empleado = new Empleado(Integer.parseInt(dni),nombre,apellido,Long.parseLong(depto_id));
		try {
			dao.update(empleado);
			// send msj de exito como List
			req.setAttribute("seccess",List.of("El empleado: "+empleado.getApellido()+" DNI: "+empleado.getId()+" se actualizo correctamente"));
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("errors",List.of("Error al actualizar el empleado: "+empleado.getApellido()+" "+e.getMessage()));
		}
		// redirect
		getServletContext().getRequestDispatcher("/FindAllEmpleadoController").forward(req, res);
	}
	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		String dni = req.getParameter("dni");
		//TODO validaciones para los datos obtenidos
		//interface = new class que implementa la interface
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		Empleado empleado = null;
		// cargamos los datos
		try {
			empleado = dao.getById(Integer.parseInt(dni));
		}catch (Exception e) {
			e.printStackTrace();
		}
		// guardamos al empleado en req y lo pasamos a la jsp para editarlo en el doPost
		req.setAttribute("empleado", empleado);
		// redirect
		getServletContext().getRequestDispatcher("/editarEmpleado.jsp").forward(req, res);
	}
}

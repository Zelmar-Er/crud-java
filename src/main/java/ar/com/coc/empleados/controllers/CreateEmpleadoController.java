package ar.com.coc.empleados.controllers;

import ar.com.coc.dao.iEmpleadoDAO;
import ar.com.coc.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.coc.domain.Empleado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/CreateEmpleadoController")
public class CreateEmpleadoController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		// capturamos los parametros que vienen en el req,
		// desde el form a traves del name del input de cada uno
		String dni = req.getParameter("dni");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String depto_id = req.getParameter("depto_id");
		
		// validaciones
		List<String> errores = new ArrayList<>();
		if(dni == null || "".equals(dni)) {
			errores.add("DNI vacío");
		}
		if(nombre == null || "".equals(nombre)) {
			errores.add("Nombre vacío");
		}
		if(apellido == null || "".equals(apellido)) {
			errores.add("Apellido vacío");
		}
		if(depto_id == null || "".equals(depto_id)) {
			errores.add("Departamento vacío");
		}
		//TODO agrefar otras validaciones
		if(!errores.isEmpty()) {
			req.setAttribute("errores", errores);
			getServletContext().getRequestDispatcher("nuevoEmpleado.jsp").forward(req, res);
			return;
		}
		// interface = new class que implementara la interface
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		Empleado empleado;
		// instanciamos el obj empleado, con los valores del input dni y depto_id necesitan ser parseados
		empleado = new Empleado(Integer.parseInt(dni),nombre,apellido,Long.parseLong(depto_id));
		try {
			dao.create(empleado);
			req.setAttribute("success",List.of("Empleado agregado de forma exitosa"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/FindAllEmpleadoController").forward(req,res);
	}
}

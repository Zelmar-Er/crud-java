package ar.com.coc.empleados.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import ar.com.coc.dao.iEmpleadoDAO;
import ar.com.coc.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.coc.domain.Empleado;

@WebServlet("/SearchEmpleadoController")
public class SearchEmpleadoController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		// interface = new class que implementa la interface
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		// obtenemos la clave desde el formulario
		String clave = req.getParameter("claveBusqueda");
		//TODO validaciones
		// creamos la List a la que gregaremos las coincidencias
		List<Empleado> empleado;
		try {
			empleado = dao.search(clave);
		}catch(Exception e) {
			empleado = List.of();
			e.printStackTrace();
		}
		req.setAttribute("nomina", empleado);
		getServletContext().getRequestDispatcher("/empleados.jsp").forward(req, res);
	}
}

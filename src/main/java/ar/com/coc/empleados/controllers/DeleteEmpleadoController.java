package ar.com.coc.empleados.controllers;

import ar.com.coc.dao.iEmpleadoDAO;
import ar.com.coc.dao.implement.EmpleadoDAOMysqlImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEmpleadoController")
public class DeleteEmpleadoController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		Integer dni = Integer.parseInt(req.getParameter("id"));
		//interface = new class que implementa la interface
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		//eliminamos
		try {
			dao.delete(dni);
			//msg de exito
			req.setAttribute("success",List.of("Se ha eliminado el producto con la id: "+dni));
		}catch (Exception e) {
			e.printStackTrace();
			// error msg
			req.setAttribute("errors",List.of("No se elimino el producto: "+e.getMessage()));
		}
		// redirect
		getServletContext().getRequestDispatcher("/FindAllEmpleadoController").forward(req, res);
	}

}

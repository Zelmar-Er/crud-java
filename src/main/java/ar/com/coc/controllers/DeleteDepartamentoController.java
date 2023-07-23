package ar.com.coc.controllers;

import ar.com.coc.dao.iDepartamentoDAO;
import ar.com.coc.dao.implement.DepartamentoDAOMysqlImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteDepartamentoController")
public class DeleteDepartamentoController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("idDepto")); // viene como String -> Long.parseLong()
		// interface = new class que implementa la interface
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		// eliminar
		try {
			dao.delete(id);
			// mensaje de exito
			req.setAttribute("success",List.of("Se ha eliminado el producto con la id: "+id));
		} catch(Exception e) {
			e.printStackTrace();
			// error msj
			req.setAttribute("errors",List.of("No se elimino el producto: "+e.getMessage()));
		}
		// redirect
		getServletContext().getRequestDispatcher("/FindAllDepartamentoController").forward(req, res);
	}
}

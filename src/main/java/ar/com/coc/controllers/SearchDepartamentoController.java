package ar.com.coc.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.coc.dao.iDepartamentoDAO;
import ar.com.coc.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.coc.domain.Departamento;

@WebServlet("/SearchDepartamentoController")
public class SearchDepartamentoController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		// interface = new class que implementa la interface
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		// obtenemos la clave desde el formulario que se encuentra en navbar.jsp
		String clave = req.getParameter("claveBusqueda");
		//TODO validaciones
		
		// buscamos
		List<Departamento> depto;
		try {
			depto = dao.search(clave);
		}catch(Exception e) {
			depto = List.of(); // creamos una lista vacia
			e.printStackTrace();
		}
		//TODO guardar en el req, los datos encontrados en la busqueda
		//TODO antes de irnos a la nueva pag, guardar en el req, los datos que pueda necesitar la JSP
		// key, value
		req.setAttribute("listado", depto);
		// Este bloque de codigo, lo utilizaremos seguido
		getServletContext().getRequestDispatcher("/listado.jsp").forward(req, res);
	}
}

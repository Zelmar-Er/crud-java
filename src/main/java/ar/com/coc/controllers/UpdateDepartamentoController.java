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

@WebServlet("/UpdateDepartamentoController")
public class UpdateDepartamentoController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		// capturamos los parametros que vienen en el req desde el form
		String nombre = req.getParameter("nombre"); // name del input
		String id = req.getParameter("id"); // id del input
		String presupuesto = req.getParameter("presupuesto"); // presupuesto del input
		// interface = new class que implementa la interface
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		
		Departamento department;
		department = new Departamento(Long.parseLong(id),nombre,Double.parseDouble(presupuesto));
		// si no utilizamos try catch podemos poner throws Exception arriba
		try {
			dao.update(department);
			// mandamos un msj de exito, pero como lista
			req.setAttribute("success",List.of("Departamento id: "+department.getId()+" actualizado correctamente"));
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errors",List.of("Error al actualizar Departamento: "+e.getMessage()));
		}
		// redireccionamos
		getServletContext().getRequestDispatcher("/FindAllDepartamentoController").forward(req, res);
	}
	// cargamos el departamento y lo enviamos a la jsp que va a editar los datos
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		//TODO validaciones para los datos obtenidos
		// interface = new class que implementa la interface
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		Departamento department = null;
		// cargamos los datos
		try {
			department = dao.getById(Long.parseLong(id));
		}catch (Exception e){
			e.printStackTrace();	
		}
		// guardamos el producto en req y pasamos a dicho producto a la jsp
		req.setAttribute("departamento",department);
		//redireccionamos
		getServletContext().getRequestDispatcher("/editar.jsp").forward(req, res);
	}
}

package ar.com.coc.controllers;

import ar.com.coc.dao.iDepartamentoDAO;
import ar.com.coc.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.coc.domain.Departamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateDepartamentoController")
public class CreateDepartamentoController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// capturamos los parametros que vienen en el req, desde el form
		String nombre = req.getParameter("nombre"); // name del input
		String id = req.getParameter("id"); // id del input
		String presupuesto = req.getParameter("presupuesto"); // presupuesto del input
		
		//Validaciones
		List<String> errores = new ArrayList<>();
		if(nombre == null || "".equals(nombre)) {
			errores.add("Nombre vacío");
		}
		if(id == null || "".equals(id)) {
			errores.add("ID vacío");
		}
		if(presupuesto == null || "".equals(presupuesto)) {
			errores.add("Presupuesto vacío");
		}
		//TODO agregar otras validaciones
		if(!errores.isEmpty()) {
			req.setAttribute("errores", errores);
			// volvemos a la jsp con la lista de errores cargada
			getServletContext().getRequestDispatcher("/nuevo.jsp").forward(req, res);
			return;
		}
		// interface = new class que implementara la interface
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		// como llegamos a la BD si queremos pedir datos de un departamento?
		Departamento department;
		department = new Departamento(Long.parseLong(id),nombre,Double.parseDouble(presupuesto));
		// si no usaramos try catch, podriamos poner throws Exception
		try {
			dao.create(department);
			req.setAttribute("success",List.of("Alta de producto exitosa"));			
		}catch (Exception e) {
			// si fallara retornamos a nuevo.jsp
			e.printStackTrace();
		}
		// redireccionamos
		getServletContext().getRequestDispatcher("/FindAllDepartamentoController").forward(req,res);
	}

}

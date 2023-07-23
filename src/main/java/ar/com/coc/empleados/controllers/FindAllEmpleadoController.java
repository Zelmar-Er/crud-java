package ar.com.coc.empleados.controllers;

import ar.com.coc.dao.iEmpleadoDAO;
import ar.com.coc.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.coc.domain.Empleado;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  Un servlet es una clase que extiende de HttpServlet
 *  // http://localhost:8080/app-web//FindAllEmpleadoControlloer
 *  
 */

@WebServlet("/FindAllEmpleadoController")

public class FindAllEmpleadoController extends HttpServlet{
	/*
	 	Tienen metodos importantes como son los:
	 	doGet()
	 	doPost()
	 */
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		// interface = new class que implementa la interface
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		List<Empleado> nomina = new ArrayList<>();
		try {
			nomina = dao.findAll();
		}catch(Exception e) {
			e.printStackTrace(); // muestra por consola el recorrido del error
		}
		// enviamos la List nomina con setAtribute(1,2) 1-nombre del Obj / 2-objeto ;
		req.setAttribute("nomina", nomina);
		// definimos el contexto o endPoint al que sera enviada la req
		getServletContext().getRequestDispatcher("/empleados.jsp").forward(req,res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}

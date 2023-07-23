package ar.com.coc.controllers;

import ar.com.coc.dao.iDepartamentoDAO;
import ar.com.coc.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.coc.domain.Departamento;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Un servlet es una clase que extiende de HttpServlet
// http://localhost:8080/app-web//FindAllDepartamentoControlloer
@WebServlet("/FindAllDepartamentoController")

public class FindAllDepartamentoController extends HttpServlet {
	/*
	 	Tienen metodos importantes como son los:
	 	doGet()
	 	doPost()
	 */
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		// interface = new class que implementa la interface
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		List<Departamento> departamentos = new ArrayList<>();
		try {
			departamentos = dao.findAll();
		}catch (Exception e) {
			e.printStackTrace(); // muestra por consola el error
		}
		req.setAttribute("listado",departamentos);
		// este bloque de codigo lo utilizaremos seguido, redirrecciona al endpoint en este caso listado.jsp
		getServletContext().getRequestDispatcher("/listado.jsp").forward(req,res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}

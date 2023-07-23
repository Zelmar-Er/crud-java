package ar.com.coc.controllers;

import ar.com.coc.dao.iDepartamentoDAO;
import ar.com.coc.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.coc.domain.Departamento;

public class GetDepartamentoController {
	public static void main(String[]args) {
		// interface = new class que implementa la interface
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		// como llegamos a la DB si queremos padir datos de un departamento?
		Long id = 111L;
		Departamento department;
		// si no utilizamos try cathc podemos arriba poner throws Exception
		try {
			department = dao.getById(id);
		}catch (Exception e) {
			e.printStackTrace();
			department = null;
		}
		if (department!=null) {
			System.out.println(department);
		}else {
			System.err.println("No existe el id: "+id);
		}
	}
}

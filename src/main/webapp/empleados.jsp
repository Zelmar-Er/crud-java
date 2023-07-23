<%@page import="ar.com.coc.domain.Empleado"%>
<%@page import="java.util.List"%>
<!doctype html>
<html lang="es">
 <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--animated-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <!--logos-->
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    
    <title>Nomina Empleados</title>
  </head>
	
	<body>
		<!-- ACA VA EL NAVBAR  include file="navbar.jsp" %> -->
		<div class="container bg-ligth">
		 <!-- aca va el navbar.jsp -->
		 <jsp:include page="navbar.jsp"/>
		</div>
		
		<main>
			<div class="container bg-light mt-5">
				<section>
					<h1>Listado de departamentos</h1>
					<table class="table">
					  <thead>
					    <tr>
					      <th scope="col">DNI</th>
					      <th scope="col">Apellido</th>
					      <th scope="col">Nombre</th>
					      <th scope="col">Departamento</th>
					      <th scope="col">ACCIONES</th>
					   
					    </tr>
					  </thead>
					  <% 
					  	//codigo java
					  	//obtener el listado desde el request
					  	//se guardo bajo el nombre de "departamentos"
					  	List<Empleado> nomina = (List<Empleado>)request.getAttribute("nomina");
					  %>
					  <tbody>
					   <!-- ESTO SE REPITE TANTA CANDTIDAD DE VECES COMO ARTICULOS TENGA -->
					   <%
					   	for( Empleado  unEmpleado : nomina) {
					   %>
					    <tr>
						      <th scope="row"> <%=unEmpleado.getId()%> </th>
						      <td><%=unEmpleado.getApellido() %></td>
						      <td><%=unEmpleado.getNombre() %></td>
						   	  <td><%=unEmpleado.getDepartamento() %></td>
						      <td>
						      	
							      	<a class="btn btn-info" 
							      	   role="button" 
							      	   href="<%=request.getContextPath()%>/UpdateEmpleadoController?dni=<%=unEmpleado.getId()%>">
							      	   Editar
							      	</a> | 
						      		<!-- Button trigger modal -->
									<button type="button" class="btn btn-danger" 
										data-bs-toggle="modal" 
										data-bs-target="#exampleModal" 
										onclick="setEmpleadoId(<%=unEmpleado.getId()%>)">
									  Eliminar
									</button>
							  </td>
					    
					    </tr>
					   <%
					   	}
					   %>
					  </tbody>
					</table>
				</section>
			</div>
		</main>
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		    	<form action="<%=request.getContextPath()%>/DeleteEmpleadoController">
		    	  <input type="hidden" name="id" id="id">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Eliminar Departamento</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        ¿Confirma que desea eliminar? 
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
			        <button type="submit" class="btn btn-danger">Eliminar</button>
			      </div>
		    	</form>
		    </div>
		  </div>
		</div>
	</body>	
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	

	  <script>
			function setEmpleadoId(id) {
				document.getElementById('id').value=id;
			}
	  </script>
</html>
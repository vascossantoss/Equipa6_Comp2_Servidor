package rest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import financialApp.Test;
import financialApp.UserManagement;
import financialApp.Userr;

@Path("/user")
public class UserRestManagement {
	
	private UserManagement userM = new UserManagement(Test.getEM());
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(Request a) {
		return "REST Server : User Controller";
	}
	
	@GET
	@Path("/getUsers")
	public Response getUsers() {		
		List<Userr> users = userM.findAllUsers();

		return Response.status(Response.Status.OK)
				.entity(users)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@GET
	@Path("/getUser/{id}")
	public Response getUser(@PathParam("id") int id) {
		Userr userResponse = userM.findUser(id);
		
		return Response.status(Response.Status.OK)
				.entity(userResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@POST
	@Path("/addUser")
	public Response addUser(Userr user) {		
		Userr userResponse =userM.addUser(user);
		
		return Response.status(Response.Status.CREATED)
				.entity(userResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	
	@PUT
	@Path("/updateUser")
	public Response updateUser(Userr user) {
		Userr userResponse = userM.updateUser(user);
		
		return Response.status(Response.Status.OK)
				.entity(userResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@DELETE
	@Path("/deleteUser/{id}")
	public Response deleteUser(@PathParam("id") int id) {
		boolean userResponse = userM.removeUser(id);
		
		return Response.status(Response.Status.OK)
				.entity(userResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}

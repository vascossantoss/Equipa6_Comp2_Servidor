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
import javax.ws.rs.core.Response;
import financialApp.GoalManagement;
import financialApp.Goal;
import financialApp.Test;

@Path("/goal")
public class GoalRestManagement {
	
	private GoalManagement goalM = new  GoalManagement (Test.getEM());
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "REST Server : Goal Controller";
	}
	
	@GET
	@Path("/getGoals")
	public Response getGoals() {		
		List<Goal> goals = goalM.findAllGoals();

		return Response.status(Response.Status.OK)
				.entity(goals)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	
	@POST
	@Path("/addGoal")
	public Response addGoal(Goal goal) {		
		Goal goalResponse = goalM.addGoal(goal);
		
		return Response.status(Response.Status.CREATED)
				.entity(goalResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@GET
	@Path("/getGoal/{id}")
	public Response getGoal(@PathParam("id") int id) {
		Goal goalResponse = goalM.findGoal(id);
		
		return Response.status(Response.Status.OK)
				.entity(goalResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@PUT
	@Path("/updateGoal")
	public Response updateGoal(Goal goal) {
		Goal goalResponse = goalM.updateGoal(goal);
		
		return Response.status(Response.Status.OK)
				.entity(goalResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@DELETE
	@Path("/deleteGoal/{id}")
	public Response deleteGoal(@PathParam("id") int id) {
		boolean goalResponse = goalM.removeGoal(id);
		
		return Response.status(Response.Status.OK)
				.entity(goalResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

	
	
}
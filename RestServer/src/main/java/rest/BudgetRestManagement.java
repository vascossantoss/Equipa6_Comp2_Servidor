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
import financialApp.Budget;
import financialApp.BudgetManagement;



@Path("/budget")
public class BudgetRestManagement{
	
	private static final String PERSISTENCE_UNIT_NAME = "FinancialAppJPA";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private static EntityManager em = factory.createEntityManager();;
	
	private BudgetManagement budgetM = new BudgetManagement(em);
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "REST Server : Budget Controller";
	}
	
	@GET
	@Path("/getBudgets")
	public Response getBudgets() {		
		List<Budget> budgets = budgetM.findAllBudgets();

		return Response.status(Response.Status.OK)
				.entity(budgets)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@GET
	@Path("/getBudget/{id}")
	public Response getBudget(@PathParam("id") int id) {
		Budget budgetResponse = budgetM.findBudget(id);
		
		return Response.status(Response.Status.OK)
				.entity(budgetResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@POST
	@Path("/addBudget")
	public Response addBudget(Budget budget) {		
		Budget budgetResponse = budgetM .addBudget(budget);
		
		return Response.status(Response.Status.CREATED)
				.entity(budgetResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@PUT
	@Path("/updateBudget")
	public Response updateBudget(Budget b) {
		Budget budgetResponse = budgetM.updateBudget(b);
		
		return Response.status(Response.Status.OK)
				.entity(budgetResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	@DELETE
	@Path("/deleteBudget/{id}")
	public Response deleteBudget(@PathParam("id") int id) {
		boolean budgetResponse = budgetM.removeBudget(id);
		
		return Response.status(Response.Status.OK)
				.entity(budgetResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	
}
package rest;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import financialApp.TransactionManagement;
import financialApp.Transactionn;

@Path("/transaction")
public class TransactionRestManagement {
	
	private static final String PERSISTENCE_UNIT_NAME = "FinancialAppJPA";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private static EntityManager em = factory.createEntityManager();;
	
	private TransactionManagement tranM = new TransactionManagement(em);
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(Request a) {
		return "REST Server : Transactions Controller";
	}
	
	@GET
	@Path("/getTransactions")
	public Response getTransactions() {		
		List<Transactionn> transactions = tranM.findAllTransactions();

		return Response.status(Response.Status.OK)
				.entity(transactions)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@GET
	@Path("/getTransaction/{id}")
	public Response getBook(@PathParam("id") int id) {
		Transactionn transactionResponse = tranM.findTransaction(id);
		
		return Response.status(Response.Status.OK)
				.entity(transactionResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@POST
	@Path("/addBook")
	public Response addBook(int id, Double amount, String date, String notes, String type) {		
		Transactionn transactionResponse = tranM.addTransaction(id, amount, date, notes, type);
		
		return Response.status(Response.Status.CREATED)
				.entity(transactionResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@PUT
	@Path("/updateBook")
	public Response updateBook(int id, Double amount, String date, String notes, String type) {
		Transactionn transactionResponse = tranM.updateTransaction(id, amount, date, notes, type);
		
		return Response.status(Response.Status.OK)
				.entity(transactionResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}

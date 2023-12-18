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
import javax.ws.rs.core.Response;

import financialApp.Test;
import financialApp.TransactionManagement;
import financialApp.Transactionn;

@Path("/transaction")
public class TransactionRestManagement {
	
	private TransactionManagement tranM = new TransactionManagement(Test.getEM());
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
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
	public Response getTransaction(@PathParam("id") int id) {
		Transactionn transactionResponse = tranM.findTransaction(id);
		
		return Response.status(Response.Status.OK)
				.entity(transactionResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@POST
	@Path("/addTransaction")
	public Response addTransaction(Transactionn t) {		
		Transactionn transactionResponse = tranM.addTransaction(t);
		
		return Response.status(Response.Status.CREATED)
				.entity(transactionResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@PUT
	@Path("/updateTransaction")
	public Response updateTransaction(Transactionn t) {
		Transactionn transactionResponse = tranM.updateTransaction(t);
		
		return Response.status(Response.Status.OK)
				.entity(transactionResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}

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
import financialApp.CategoryManagement;
import financialApp.Test;
import financialApp.Category;

@Path("/category")
public class CategoryRestManagement {

	private CategoryManagement catM = new CategoryManagement(Test.getEM());

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "REST Server : Category Controller";
	}
	
	@GET
	@Path("/getCategories")
	public Response getCategories() {		
		List<Category> categories = catM.findAllCategories();

		return Response.status(Response.Status.OK)
				.entity(categories)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@GET
	@Path("/getCategory/{id}")
	public Response getCategory(@PathParam("id") int id) {
		Category categoryResponse = catM.findCategory(id);
		
		return Response.status(Response.Status.OK)
				.entity(categoryResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}	
	@POST
	@Path("/addCategory")
	public Response addCategory(Category cat) {		
		Category categoryResponse = catM.addCategory(cat);
		
		return Response.status(Response.Status.CREATED)
				.entity(categoryResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@PUT
	@Path("/updateCategory")
	public Response updateCategory(Category cat) {
		Category categoryResponse = catM.updateCategory(cat);
		
		return Response.status(Response.Status.OK)
				.entity(categoryResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@DELETE
	@Path("/deleteCategory/{id}")
	public Response deleteCategory(@PathParam("id") int id) {
		boolean categoryResponse = catM.removeCategory(id);
		
		return Response.status(Response.Status.OK)
				.entity(categoryResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
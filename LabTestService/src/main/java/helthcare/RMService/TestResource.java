package helthcare.RMService;



import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jsoup.*;
import org.jsoup.parser.*;



import org.jsoup.nodes.Document;

@Path("/tests")
public class TestResource {
	
	TestRepository repo = new TestRepository();
	@GET
	@Produces(MediaType.APPLICATION_XML)
	//public List<Test> getTest() {
		public String getTest() {
		
		
		System.out.println("getTest called..");
		
		return repo.getTests();
		
	}
	@GET
	//@Path("/test/{test_id}")
	@Produces(MediaType.APPLICATION_XML)
	//public String getTestDetails(@PathParam("test_id") int test_id) {
		public String getTestDetails( int test_id) {
		//return repo.getTest(test_id);
		return repo.getTest(test_id);
	}
	
	@POST
	@Path("testpost")
	@Consumes(MediaType.APPLICATION_XML)
	public Test InsertTests(Test t1) {
		System.out.println(t1);
		//repo.insertTest(t1);
		return t1;
	}
	@PUT
	@Path("testupdate/{id}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Test UpdateTests(@PathParam("id") String id,Test t2) {
		System.out.println(t2);
		//if(repo.getTest(t2.getTest_id()).getTest_id()==0) {
			//repo.insertTest(t2);
		t2.setTest_id(Integer.parseInt(id));
	//	}else {
		repo.getTest(Integer.parseInt(id));
		repo.updateTest(t2);
	//	}
		return t2;
	}
	/*@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateAppoinment(@PathParam("id")int id,AppoinmentDTO appoinmentDTO) {
		appoinmentDTO.setApp_patient_id(id);
		if(appm.UpdateAppoinment(appoinmentDTO)) {
			return Response.ok().build();
		}else {
		return Response.notModified().build();
		}
	}*/
	@DELETE
	//@Path("delete/{test_id}")
	@Consumes(MediaType.APPLICATION_XML)
	public String deleteTest(String test_id) {
		//Test t = repo.getTest(test_id);
		//if(t.getTest_id()!=0) {
		System.out.println("getTest called.. delete");
		repo.deleteTest(test_id);
		String t ="deleted";
	//	}
		return t;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String deleteItem( String itemData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String itemID = doc.select("itemID").text();
	String output = repo.deleteItem(itemID);
	
	
	return output;
	}
	
	/*@DELETE
	@Path("/del/{test_id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String itemID = doc.select("itemID").text();
	String output = repo.delete(itemID);
	return output;
	}*/
	
	
	
}

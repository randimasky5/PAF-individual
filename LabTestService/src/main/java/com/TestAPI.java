package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helthcare.RMService.TestRepository;
import helthcare.RMService.TestResource;

/**
 * Servlet implementation class TestAPI
 */
@WebServlet("/TestServlet")
public class TestAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("searchid");
		//<form action="TestServlet" method = 'post'><input type="text" name ="searchid"><button type="submit">search</button></form>
        //System.out.println("username: " + id);
       
        TestRepository itemObj = new TestRepository();
       // out.print(itemObj.getTestDetails());
       itemObj.getTest( Integer.parseInt(id));
       System.out.println( itemObj.getTest( Integer.parseInt(id)));
		response.getWriter().append("Served at: "+itemObj.getTest( Integer.parseInt(id))).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TestRepository test = new TestRepository();
		String testname=request.getParameter("test_name");
		String testcost =request.getParameter("test_cost");
		String testdesc = request.getParameter("test_desc");
		String roomNo =request.getParameter("room_no");
		String hosp = request.getParameter("Hospital_name");
		String output = test.insertTest(testname,testcost,testdesc,roomNo,hosp);
		//System.out.println(testname);
				response.getWriter().write(output);
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("c de");
		String id =req.getParameter("btnremove");
		//String id =req.getParameter("id");
		 TestRepository itemObj = new TestRepository();
	       // out.print(itemObj.getTestDetails());
	       itemObj.deleteTest(id);
	       System.out.println("id"+id);
	       resp.getWriter().append("Served at: "+id).append(req.getContextPath());
		super.doDelete(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
}

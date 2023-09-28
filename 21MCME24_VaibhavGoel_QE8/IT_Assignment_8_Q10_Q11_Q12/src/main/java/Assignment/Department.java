package Assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import Assignment.DatabaseConnection;

@WebServlet("/department")
public class Department extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("In department");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("departmentForm.html");
		dispatcher.include(req, resp);
		
		String docType = "<!DOCTYPE HTML>\n";
		
		String url = "jdbc:mysql://localhost:3306/COMPANY?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "demo";
		String password = "password";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection(url,user,password)) {
			
			PreparedStatement st = con.prepareStatement("INSERT INTO Department values (?,?,?)");
			
			st.setInt(1, Integer.valueOf(req.getParameter("departmentId")));
			
			st.setString(2, req.getParameter("name"));
			
			st.setString(3, req.getParameter("departmentLocation"));
			
			st.executeUpdate();
			
			st.close();
			con.close();
			
			out.println("<html><body><h1><b>Successfully Inserted!!"
					+ "</b></h1></body></html>");
			
			
		}
		catch(Exception e) {
			out.println("<html><body><h1><b>Insertion of Data Failed!!</b><ul><li>Maybe Duplicate Primary key is present.</li></ul>"
					+ "</h1></body></html>");
			e.printStackTrace();
		}
		
	}
	
}

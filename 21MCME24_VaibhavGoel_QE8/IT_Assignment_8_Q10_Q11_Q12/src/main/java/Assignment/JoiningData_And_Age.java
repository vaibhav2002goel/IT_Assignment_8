package Assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import Assignment.DatabaseConnection;

@WebServlet("/query1")
public class JoiningData_And_Age extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In Query1");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("Q8(Servlets).html");
		dispatcher.include(req, resp);
		
		String docType = "<!DOCTYPE HTML>\n";
		String title = "Output";
		out.println(docType+"<html>\n" + "<head><title>" + title + "</title>" + "<style>\n" + "" + "</style>\n" + "</head>" + "<body>" + "<h1 align=center>" + "RESULT OF QUERY" + "</h1>\n");
		
		
		String url = "jdbc:mysql://localhost:3306/COMPANY?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "demo";
		String password = "password";
		
		String employeeQuery = "select * from Employees";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection(url,user,password)) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(employeeQuery);
			
			out.println("<table border = \"4\">\n"
					+ "		<caption>Complete Employee Table</caption>\n"
					+ "		<tr>\n"
					+ "			<th> Employee ID </th>\n"
					+ "			<th> Employee Name</th>\n"
					+ "			<th> Job Title </th>\n"
					+ "			<th> Date of Birth </th>\n"
					+ "			<th> Joining Date </th>\n"
					+ "			<th> Salary </th>\n"
					+ "			<th> Department ID </th>\n"
					+ "		</tr>");
			
			while(rs.next()) {
				out.println("<tr>\n"
						+ "			<td> "+ rs.getInt("EmployeeId") +"</td>\n"
						+ "			<td> "+ rs.getString("EmployeeName") +"  </td>\n"
						+ "			<td> "+ rs.getString("JobTitle") +" </td>\n"
						+ "			<td> "+ rs.getDate("DateOfBirth").toString() +"  </td>\n"
						+ "			<td> "+ rs.getDate("JoiningDate").toString() +" </td>\n"
						+ "			<td> "+ rs.getDouble("Salary") +" </td>\n"
						+ "			<td> "+ rs.getInt("DepartmentID") +" </td>\n"
						+ "		</tr>");
			}
			
			out.println("</table>");
			
			out.println("<br> <br>");
			
			
			String joinDateInitial = req.getParameter("joinDate");
			int minAge = Integer.valueOf(req.getParameter("age"));
			
			// Create a SimpleDateFormat to parse the user-input join date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date join_DateFormat = dateFormat.parse(joinDateInitial);

            // Calculate the birthdate cutoff using the input age
            java.util.Date cutoffDate = new Date();
            cutoffDate.setYear(cutoffDate.getYear() - minAge);

            // SQL query to retrieve employees 
            String sqlQuery = "SELECT * FROM Employees WHERE JoiningDate >= ? AND DateOfBirth <= ?";

            
            PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
            preparedStatement.setDate(1, new java.sql.Date(join_DateFormat.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(cutoffDate.getTime()));

            // Execute the query and retrieve the result set
            ResultSet rs1 = preparedStatement.executeQuery();
            
            
            out.println("<table border = \"4\">\n"
					+ "		<caption>Resultant Employee Table</caption>\n"
					+ "		<tr>\n"
					+ "			<th> Employee ID </th>\n"
					+ "			<th> Employee Name</th>\n"
					+ "			<th> Job Title </th>\n"
					+ "			<th> Date of Birth </th>\n"
					+ "			<th> Joining Date </th>\n"
					+ "			<th> Salary </th>\n"
					+ "			<th> Department ID </th>\n"
					+ "		</tr>");
			
			while(rs1.next()) {
				out.println("<tr>\n"
						+ "			<td> "+ rs1.getInt("EmployeeId") +"</td>\n"
						+ "			<td> "+ rs1.getString("EmployeeName") +"  </td>\n"
						+ "			<td> "+ rs1.getString("JobTitle") +" </td>\n"
						+ "			<td> "+ rs1.getDate("DateOfBirth").toString() +"  </td>\n"
						+ "			<td> "+ rs1.getDate("JoiningDate").toString() +" </td>\n"
						+ "			<td> "+ rs1.getDouble("Salary") +" </td>\n"
						+ "			<td> "+ rs1.getInt("DepartmentID") +" </td>\n"
						+ "		</tr>");
			}
			
			out.println("</table>");
			
			out.println("</body></html>");
			
			
			
		}
		catch(Exception e) {
			out.println("<html><body><h1><b>Invalid Input has been Entered!!</b>"
					+ "</h1></body></html>");
			e.printStackTrace();
		}
		
	}
	
}

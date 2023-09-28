package temp;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class Q8 {
	 private static Properties getConnectionData() {

	        Properties props = new Properties();

	        String fileName = "src/temp/db.properties";
//	        String fileName = "db.properties";

	        try (FileInputStream in = new FileInputStream(fileName)) {
	            props.load(in);
	        } catch (IOException ex) {
	            Logger lgr = Logger.getLogger(Q7.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	        }

	        return props;
	    }
	 
	 	/*
	     * Database Used : COMPANY
	     * Tables Created : Employees Department
	    */
	 
	 public static void main(String[] args) {
			Properties props = getConnectionData();

	        String url = props.getProperty("db.url");
	        String user = props.getProperty("db.user");
	        String password = props.getProperty("db.passwd");
	        
	        String employeeQuery = "select * from Employees";
	        String departmentQuery = "select * from Department"; 
	        
	        Scanner sc = new Scanner(System.in);
	        
	        try (Connection con = DriverManager.getConnection(url, user, password);) {
	        	
	        	Statement st = con.createStatement();
    			ResultSet rs = st.executeQuery(employeeQuery);
    			
    			System.out.println("\nEmployee Table:");
    			while(rs.next()) {
    				System.out.printf("Employee ID: %d, Employee Name: %s, Job Title: %s, Birth Date: %s, Joining Date: %s, Salary: %f, Department ID: %d \n",
    						rs.getInt("EmployeeId"), rs.getString("employeeName"), rs.getString("jobTitle"), (rs.getDate("dateOfBirth")).toString(), (rs.getDate("joiningDate")).toString(), rs.getDouble("salary"), rs.getInt("departmentId"));
    			}
	        	
    			System.out.println();
    			System.out.println();
    			
     		   System.out.print("Enter the join date (YYYY-MM-DD): ");
	           String joinDateInitial = sc.nextLine();
  
               System.out.print("Enter the minimum required age: ");
               int minAge = sc.nextInt();
  
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
        		
	        	
//	        	Statement st1 = con.createStatement();
//	        	ResultSet rs1 = st1.executeQuery(template);
	        	
	        	while(rs1.next()) {
    				System.out.printf("Employee ID: %d, Employee Name: %s, Job Title: %s, Birth Date: %s, Joining Date: %s, Salary: %f, Department ID: %d \n",
    						rs1.getInt("employeeId"), rs1.getString("employeeName"), rs1.getString("jobTitle"), (rs1.getDate("dateOfBirth")).toString(), (rs1.getDate("joiningDate")).toString(), rs1.getDouble("salary"), rs1.getInt("departmentId"));
    			}
	        	
	        }
	        catch(SQLException ex) {
	        	Logger lgr = Logger.getLogger(Q7.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	        } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        sc.close();
	        
	 }
}



package temp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class Q9 {
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
    						rs.getInt("employeeId"), rs.getString("employeeName"), rs.getString("jobTitle"), (rs.getDate("dateOfBirth")).toString(), (rs.getDate("joiningDate")).toString(), rs.getDouble("salary"), rs.getInt("departmentId"));
    			}
	        	
    			System.out.println();
    			System.out.println();
    			System.out.println();
    			
    			System.out.println("*********Each Employee is being verified/interviewed for the Management roles(Senior Most employees are decided according to there Joining Date)*********\n");
    			
    			String query = "select * from Employees ORDER BY JoiningDate ASC LIMIT 3";
	        	
//    			Statement st1 = con.createStatement();
	        	ResultSet rs1 = st.executeQuery(query);

	        	
	        	String updateEmployeesSQL = "UPDATE Employees SET DepartmentID = ? WHERE EmployeeID = ?";
                PreparedStatement updateEmployee = con.prepareStatement(updateEmployeesSQL);
                int x = 4;
                while(rs1.next()) {
                	int employeeID = rs1.getInt("EmployeeId");
                	updateEmployee.setInt(1, x);
                	updateEmployee.setInt(2, employeeID);
                	updateEmployee.executeUpdate();
                	System.out.printf("Employee ID: %d, Employee Name: %s, Job Title: %s, Birth Date: %s, Joining Date: %s, Salary: %f, Department ID: %d \n",
    						rs1.getInt("employeeId"), rs1.getString("employeeName"), rs1.getString("jobTitle"), (rs1.getDate("dateOfBirth")).toString(), (rs1.getDate("joiningDate")).toString(), rs1.getDouble("salary"), rs1.getInt("departmentId"));
                	
                }
                
                System.out.println();
                System.out.println();
                
                System.out.println("Employee ID has been succussfully updated in Database as Well\n");
                

                
                
	        }
	        catch(SQLException ex) {
	        	Logger lgr = Logger.getLogger(Q7.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	        }
	        
	        sc.close();
	        
	 }
}

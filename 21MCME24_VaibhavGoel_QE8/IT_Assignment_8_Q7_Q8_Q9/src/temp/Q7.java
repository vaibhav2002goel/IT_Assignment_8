package temp;

import java.sql.Connection;
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

public class Q7 {
	
    private static Properties getConnectionData() {

        Properties props = new Properties();

        String fileName = "src/temp/db.properties";
//        String fileName = "db.properties";

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
        	
       	
        	while(true) 
        	{
        		System.out.println();
        		System.out.println();
        		System.out.println("Enter 1 to insert Employee Data in Employee table ");
            	System.out.println("Enter 2 to insert Department Data in Department table");
            	System.out.println("Enter 3 to display Data for all Employees");
            	System.out.println("Enter 4 to display Data for all Departments");
            	System.out.println("Enter 5 to exit from the program.");
            	
            	
            	
            	System.out.print("Enter your option : ");
            	int option = sc.nextInt();
	        	if(option==1) {
	        		String template = "INSERT INTO Employees Values(?,?,?,?,?,?,?)";
	        		PreparedStatement inserter = con.prepareStatement(template);
	        		
	        		System.out.println("\n/**********EMPLOYEE DATA**********/");
	        		
	        		System.out.print("Enter Employee Id : ");
	        		int id = sc.nextInt();
	        		inserter.setInt(1, id);
	
	        		
	        		System.out.print("Enter the employee name : ");
	        		sc.nextLine();
	        		String name = sc.nextLine();
	        		inserter.setString(2, name);
	        		
	        		
	        		System.out.print("Enter employee Job title : ");
	        		String title = sc.nextLine();
	        		inserter.setString(3, title);
	        		
	        		System.out.print("Enter Date of Birth (Format : yyyy-mm-dd) : ");
	        		inserter.setDate(4, java.sql.Date.valueOf(sc.next()));
	
	        		System.out.print("Enter Joining Date (Format : yyyy-mm-dd) : ");
	        		inserter.setDate(5, java.sql.Date.valueOf(sc.next()));
	        		
	        		System.out.print("Enter Salary of person : ");
	        		double salary = sc.nextDouble();
	        		inserter.setDouble(6, salary);
	        		
	        		System.out.print("Enter department Id : ");
	        		int id_D = sc.nextInt();
	        		inserter.setInt(7, id_D);
	        		
	        		inserter.executeUpdate();
	        		inserter.close();
	        		
	        		Statement st = con.createStatement();
	    			ResultSet rs = st.executeQuery(employeeQuery);
	    			
	    			System.out.println("\nEmployee Table:");
	    			while(rs.next()) {
	    				System.out.printf("Employee ID: %d, Employee Name: %s, Job Title: %s, Birth Date: %s, Joining Date: %s, Salary: %f, Department ID: %d \n",
	    						rs.getInt("employeeId"), rs.getString("employeeName"), rs.getString("jobTitle"), (rs.getDate("dateOfBirth")).toString(), (rs.getDate("joiningDate")).toString(), rs.getDouble("salary"), rs.getInt("departmentId"));
	    			}
	    			
	        	}
	        		
	        	else if(option==2) {
	        		String template1 = "INSERT INTO Department Values(?,?,?)";
	        		PreparedStatement inserter1 = con.prepareStatement(template1);
	        		
	        		System.out.println("\n/**********DEPARTMENT DATA**********/");
	        		
	        		System.out.print("Enter Department Id : ");
	        		int D_id = sc.nextInt();
	        		inserter1.setInt(1, D_id);
	        		
	        		System.out.print("Enter Department Name : ");
	        		sc.nextLine();
	        		String D_name = sc.nextLine();
	        		inserter1.setString(2, D_name);
	        		
	        		System.out.print("Enter Department Location : ");
	        		String location = sc.nextLine();
	        		inserter1.setString(3, location);
	        		
	        		inserter1.executeUpdate();
	        		inserter1.close();
	        		
	        		Statement st = con.createStatement();
	    			ResultSet rs = st.executeQuery(employeeQuery);
	        		
					rs = st.executeQuery(departmentQuery);
	    			
	    			System.out.println("\nDepartment Table:");
	    			while(rs.next()) {
	    				System.out.printf("Department ID: %d, Department Name: %s, Department Location: %s \n",
	    						rs.getInt("departmentId"), rs.getString("departmentName"), rs.getString("departmentLocation"));
	    			}
	        	}
	        	
	        	else if(option==3) {
	        		Statement st = con.createStatement();
	    			ResultSet rs = st.executeQuery(employeeQuery);
	    			
	    			System.out.println("\nEmployee Table:");
	    			while(rs.next()) {
	    				System.out.printf("Employee ID: %d, Employee Name: %s, Job Title: %s, Birth Date: %s, Joining Date: %s, Salary: %f, Department ID: %d \n",
	    						rs.getInt("employeeId"), rs.getString("employeeName"), rs.getString("jobTitle"), (rs.getDate("dateOfBirth")).toString(), (rs.getDate("joiningDate")).toString(), rs.getDouble("salary"), rs.getInt("departmentId"));
	    			}
	        	}
	        	
	        	else if(option==4) {
	        		Statement st = con.createStatement();
	    			ResultSet rs = st.executeQuery(employeeQuery);
	        		
					rs = st.executeQuery(departmentQuery);
	    			
	    			System.out.println("\nDepartment Table:");
	    			while(rs.next()) {
	    				System.out.printf("Department ID: %d, Department Name: %s, Department Location: %s \n",
	    						rs.getInt("departmentId"), rs.getString("departmentName"), rs.getString("departmentLocation"));
	    			}
	        	}
	        	
	        	else if(option==5) {
	        		System.out.println("EXITING FROM HERE!!");
	        		break;
	        	}
	        	else {
	        		System.out.println("Wrong option has been selected!!");
	        	}
        	}
        }
        catch(SQLException ex) {
        	Logger lgr = Logger.getLogger(Q7.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        sc.close();
	}
}

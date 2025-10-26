package com.curd;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dto.EmployeeDTO;

public class Curd {
	
	public Curd() {
		createTable();
	}
	
	public void createTable() {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql ="CREATE TABLE IF NOT EXISTS employee(sn INT AUTO_INCREMENT PRIMARY KEY,"
					+ "USERNAME varchar(30) NOT NULL UNIQUE,"
					+ "PASSWORD varchar(30),"
					+ "FULLNAME VARCHAR(30),"
					+ "ADDRESS varchar(200),"
					+ "SALARY int );";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public void insert(EmployeeDTO employeeDTO) {
		
		Connection connection =null;
		Statement statement =null;
		
		try {
			String sql="insert into employee(username,password,fullname,address,salary) "
					+ "values('"+employeeDTO.getUsername()+"','"+employeeDTO.getPassword()+"','"+employeeDTO.getFullname()+"','"+employeeDTO.getAddress()+"',"+employeeDTO.getSalary()+")";
			
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			System.out.println("DATA INSERTED");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
	
	public void read(String username, String password) {
		Connection connection =null;
		Statement statement =null;
		
		try {
			String sql="select * from employee where username='"+username+"' and password='"+password+"'";
			
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				
			int getSn = resultSet.getInt("sn");
			String getUsername = resultSet.getString("username");
			String getPassword = resultSet.getString("password");
			String getFullname = resultSet.getString("fullname");
			String getAddress = resultSet.getString("address");
			int getSalary = resultSet.getInt("salary");
			
			System.out.println("SN: "+getSn);
			System.out.println("USERNAME: "+getUsername);
			System.out.println("PASSWORD: "+getPassword);
			System.out.println("FULLNAME: "+getFullname);
			System.out.println("ADDRESS: "+getAddress);
			System.out.println("SALARY: "+getSalary);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
	
	public void update(String username, int salary) {
		
		Connection connection =null;
		Statement statement =null;
		
		try {
			String sql = "UPDATE employee SET salary=" + salary + " WHERE username='" + username + "'";
			
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			int executeUpdate = statement.executeUpdate(sql);
			
			if(executeUpdate>0) {
				System.out.println("DATA UPDATED");
				}
				else {
					System.out.println("USER NOT FOUND");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
	}
	
	public void delete(String username) {
		
		Connection connection =null;
		Statement statement =null;
		
		try {
			String sql = "DELETE FROM employee WHERE username='" + username + "'";
			
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			int executeUpdate = statement.executeUpdate(sql);
			if(executeUpdate>0) {
			System.out.println("DATA DELETED");
			}
			else {
				System.out.println("USER NOT FOUND");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

}

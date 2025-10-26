package com.main;
import java.util.Scanner;

import com.curd.Curd;
import com.dto.EmployeeDTO;

public class Launch {
	
	private final int INSERT_DATA=1;
	private final int READ_DATA=2;
	private final int UPDATE_DATA=3;
	private final int DELETE_DATA=4;
	private final int EXIT=5;
	private static int MAX_ATTEMPTS=3;
	
	private String username;
	private String password;
	private String fullname;
	private String address;
	private int salary;
	
	private Curd curd;
	public Launch() {
		curd = new Curd();
	}
	
	public void doStart() {
		
	Scanner scan = new Scanner(System.in);
	Curd curd = new Curd();
	int attempt=0;
	
	while(true) {
		
		System.out.println("P1->Insert\nP2->Read\nP3->Update\nP4->Delete\nP5->Exit");
		int choice = 0;
		try {
			choice=scan.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Input!, Please take a number from 1 to 5.\n");
			scan.nextLine();
			attempt++;
			if(attempt>=MAX_ATTEMPTS) {
				System.out.println("You have reached the limit");
				return;
			}
			continue;
		}
		
		if(choice<1||choice>5) {
			System.out.println("Invalid Input!, Please take a number from 1 to 5.\n");
			attempt++;
			if(attempt>=MAX_ATTEMPTS) {
				System.out.println("You have reached the limit");
				return;
			}
		}
		
		switch (choice) {
		
		case INSERT_DATA: 
			System.out.println("Insert Data");
			
			System.out.println("ENTER USERNAME");
			String username=scan.next();
			System.out.println("ENTER PASSWORD");
			String password = scan.next();
			
			scan.nextLine();
			
			System.out.println("ENTER FULLNAME");
			String fullname = scan.nextLine();
			System.out.println("ENTER ADDRESS");
			String address=scan.nextLine();
			System.out.println("ENTER SALARY");
			int salary = scan.nextInt();
			
			EmployeeDTO employeeDTO = new EmployeeDTO(username, password, fullname, address, salary);
			
			curd.insert(employeeDTO);
			break;
			
		case READ_DATA:
			System.out.println("Read data\n");
			System.out.println("ENTER USERNAME");
			username=scan.next();
			System.out.println("ENTER PASSWORD");
			password = scan.next();
			
			curd.read(username,password);
			break;
			
		case UPDATE_DATA:
			System.out.println("Update data\n");
			System.out.println("ENTER USERNAME");
			username=scan.next();
			System.out.println("ENTER SALARY");
			salary = scan.nextInt();
			curd.update(username, salary);
			break;
			
		case DELETE_DATA:
			System.out.println("Delete data\n");
			System.out.println("ENTER USERNAME");
			username=scan.next();
			curd.delete(username);
			break;
			
		case EXIT:
			System.out.println("Thanks for using !\n");
			return;
			
	}
  }
}
}

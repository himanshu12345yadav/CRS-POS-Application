package com.signify.client;

import java.util.Scanner;

import com.signify.service.CustomerInterface;
import com.signify.service.CustomerService;

public class CustomerClient {

	public static void main(String[] args) {
		// create the instance of the customer
		
		
		Scanner sc = new Scanner(System.in);
		CustomerInterface cs = new CustomerService();
		
		boolean flag = true;
		
		do {
			cs.showServices();
			int type = sc.nextInt();

			switch(type) {
			case 1:
				// create customer
				cs.createCustomer();
				break;
			case 2:
				System.out.println("Index of customer to Delete -> ");
				cs.deleteCustomer(sc.nextInt());
				// delete customer
				break;
			case 3:
				System.out.println("Index of customer to update -> ");
				cs.updateCustomer(sc.nextInt());
				// update customer
				break;
			case 4:
				// list customer
				cs.listCustomers();
				break;
			case 5:
				// EXIT
				flag = false;
				break;
			default:
				System.out.println("SERVICE NOT FOUND TRY AGAIN!");
				break;
			}
		}while(flag);
	}
}

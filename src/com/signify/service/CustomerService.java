/**
 * 
 */
package com.signify.service;

import java.util.Scanner;

import com.signify.bean.Customer;
/**
 * @author Training
 *
 */
public class CustomerService implements CustomerInterface {
	// customer operations
	int count;
	int next = 0;
	Customer customers[] = new Customer[]{new Customer() , new Customer() , new Customer() , new Customer() , new Customer()};
	
	public void showServices() {
		System.out.println("PRESS 1 -> TO ADD CUSTOMER\nPRESS 2 -> TO DELETE CUSTOMER \nPRESS 3 -> TO UPDATE CUSTOMER \nPRESS 4 -> TO LIST CUSTOMERS \nPRESS 5 -> TO EXIT");
	}
	
	public void createCustomer() {
		System.out.println("CREATING CUSTOMER \n");
					
		Scanner sc = new Scanner(System.in);
		
		for(Customer i : customers) {
			System.out.print("Customer Id: ");
			int id = sc.nextInt();
			System.out.print("Customer Name: ");
			String name = sc.next();
			System.out.print("Customer Address: ");
			String address = sc.next();
			
			System.out.println();
			i.setCustomerId(id);
			i.setCustomerName(name);
			i.setCustomerAddress(address);
			next++;
		}
		
//		sc.close();
	}
	
	public void deleteCustomer(int index) {
		System.out.println("DELETING CUSTOMER WITH INDEX -> " + index);
		
		if(index >= count) {
			System.out.println("Can't Delete customer\n");
		}
		else {
			for(int i = index ; i < next; i++) {
				customers[i] = customers[i+1];
			}
			next--;
		}
	}
	
	public void updateCustomer(int index) {
		Scanner sc = new Scanner(System.in);
		
		if(index >= next) {
			System.out.println("Cannot update customer\n");
			return;
		}
		System.out.println("UPDATING CUSTOMER \n");
		System.out.print("NEW ID -> ");
		int newId = sc.nextInt();
		System.out.print("\nNEW NAME -> ");
		String newName = sc.next();
		System.out.print("\nNEW ADDRESS -> ");
		String newAddress = sc.next();
		
		customers[index].setCustomerId(newId);
		customers[index].setCustomerName(newName);
		customers[index].setCustomerAddress(newAddress);
		
	}
	
	public void listCustomers() {
		System.out.println("Listing Customer\n");
	
		for(Customer i : customers) {
			System.out.println("Customer Details-> " + "-" + i.getCustomerName() + "-" + i.getCustomerId() + "-" + i.getCustomerAddress());
		}
		System.out.println("\n");
	}
}

package com.signify.service;

// This interface is using the declaration of the customer service.
public interface CustomerInterface {
	public void showServices();
	public void createCustomer();
	public void updateCustomer(int customerId);
	public void deleteCustomer(int customerId);
	public void listCustomers();
}

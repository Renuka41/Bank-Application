package com.dxc.service;

import java.util.List;

import com.dxc.pojos.Customer;

public interface IAdminService {
	public boolean loginadmin( String adminname, String password);
	public void addaccount(Customer bs);
	public boolean getCustomer(int accountnum);
	 public List<Customer> getDetailesdisplay(int accountnum);
	
	 public boolean findaccountnum(int accountnum);
	 public void UpdateCustomer(Customer s) ;
		public List<Integer> getbalance(int accountnum);

	 public boolean removeacc( int accountnum);
	 public List<Customer> getDetailesdisplay();

	 


}

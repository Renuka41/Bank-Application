package com.dxc.dao;

import java.util.List;

import com.dxc.pojos.Customer;

public interface IAdminDao {
	public boolean loginadmin(String adminname,String password);
	public void addaccount(Customer bs);
	public boolean getCustomer(int accountnum);
	 public List<Customer> getDetailesdisplay(int accountnum);
	 public boolean findaccountnum(int accountnum);
	 public void UpdateCustomer(Customer s);
		public List<Integer> getbalance(int accountnum);

		 public List<Customer> getDetailesdisplay();

	 public boolean removeacc(int accountnum);
	 

}

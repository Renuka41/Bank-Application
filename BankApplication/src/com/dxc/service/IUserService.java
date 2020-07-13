package com.dxc.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.dxc.pojos.Customer;
import com.dxc.pojos.Transection;

public interface IUserService {
	public int getAccountnum(String name,String password);
	public boolean loginuser( String name, String password);
	public boolean deposit(int accountnum,int accountbal);
	public boolean passwordChange(String password,String s2,String s);

		public boolean withdraw(int accountnum2,int accountbal2);
		public boolean getCustomer(int accountnum);
		 public List<Customer> getDetailesdisplay(int accountnum);
			public String transferMoney(int accountnumber1,int accountnumber2,int balance);
			public List<Transection> getTransectionDetailes(String cid);




	

	






}	     




















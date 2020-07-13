package com.dxc.service;

import java.util.List;

import com.dxc.dao.IUserDao;
import com.dxc.dao.UserDaoImpl;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Transection;

public class UserServiceImpl implements IUserService {
	IUserDao dao=new UserDaoImpl();
	public int getAccountnum(String name,String password) {
		return dao.getAccountnum(name,password);
	}

	
	public boolean loginuser( String name, String password) {
		return dao.loginuser(name, password);
	}
	public boolean deposit(int accountnum,int accountbal) {
		return dao.deposit(accountnum, accountbal);
		
	
	 }
		public boolean withdraw(int accountnum2,int accountbal2) 
		{
			return dao.withdraw(accountnum2, accountbal2);
		
		}
		public boolean passwordChange(String password,String s2,String s) {
			return dao.passwordChange(password, s2, s);
		}
		public boolean getCustomer(int accountnum) {
			return dao.getCustomer(accountnum);
		}
		 public List<Customer> getDetailesdisplay(int accountnum){
			 return dao.getDetailesdisplay(accountnum);
		 }
			public String transferMoney(int accountnumber1,int accountnumber2,int balance)
			{
		       return dao.transferMoney(accountnumber1, accountnumber2,balance);   
			}
			public List<Transection> getTransectionDetailes(String cid) {
				return dao.getTransectionDetailes(cid);
			}


			


}

	





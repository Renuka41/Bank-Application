package com.dxc.pojos;

public class Customer {
	private int accountnum;
	private String accountname;
	private int accountbal;
	private String password;
	public Customer() {}
	public Customer(int accountnum, String accountname, int accountbal,String password) {
		super();
		this.accountnum = accountnum;
		this.accountname = accountname;
		this.accountbal = accountbal;
		this.password=password;
	}
	public int getAccountnum() {
		return accountnum;
	}
	public void setAccountnum(int accountnum) {
		this.accountnum = accountnum;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public int getAccountbal() {
		return accountbal;
	}
	public void setAccountbal(int accountbal) {
		this.accountbal = accountbal;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [accountnum=" + accountnum + ", accountname=" + accountname + ", accountbal=" + accountbal
				+ ", password=" + password + "]";
	}
	
	

}

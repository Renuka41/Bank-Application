package com.dxc.pojos;

public class Transection {
private String transectiontype;
private int accountbal;
private int accountnum;
public Transection() {}
public Transection(String transectiontype, int accountbal, int accountnum) {
	super();
	this.transectiontype = transectiontype;
	this.accountbal = accountbal;
	this.accountnum = accountnum;
}
public String getTransectiontype() {
	return transectiontype;
}
public void setTransectiontype(String transectiontype) {
	this.transectiontype = transectiontype;
}
public int getAccountbal() {
	return accountbal;
}
public void setAccountbal(int accountbal) {
	this.accountbal = accountbal;
}
public int getAccountnum() {
	return accountnum;
}
public void setAccountnum(int accountnum) {
	this.accountnum = accountnum;
}



}


package com.dxc.service;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.Customer;

public class AdminServiceImpl implements IAdminService{
	IAdminDao dao=new AdminDaoImpl();
	public boolean loginadmin(String adminname,String password) {
		return dao.loginadmin(adminname,password);
	}
public void addaccount(Customer bs) {
	dao.addaccount(bs);
}
public boolean getCustomer(int accountnum) {
return	dao.getCustomer(accountnum);
}
public List<Customer> getDetailesdisplay(int accountnum){
	return dao.getDetailesdisplay(accountnum);

}
public boolean findaccountnum(int accountnum ) {
	 return dao.findaccountnum(accountnum);
}
public void UpdateCustomer(Customer s) {
	dao.UpdateCustomer(s);
}
public List<Integer> getbalance(int accountnum){
	return dao.getbalance(accountnum );
}

public boolean removeacc( int accountnum) {
	return dao.removeacc(accountnum);
}
public List<Customer> getDetailesdisplay(){
	return dao.getDetailesdisplay();

}

}

package com.dxc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dxc.pojos.Customer;
import com.dxc.service.AdminServiceImpl;
import com.dxc.service.IAdminService;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet 
{
	IAdminService ias=new AdminServiceImpl();
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	String message;
	String action="";
	String temp=request.getParameter("btn");
	if(temp!=null)
		action=request.getParameter("btn");
	HttpSession session=request.getSession();
	if(action.equals("login admin"))
	{   
		String adminname=request.getParameter("adminname");
		String password=request.getParameter("password");
		
		boolean loginStatus=ias.loginadmin(adminname, password);
		if(loginStatus==true) 
		{
           response.sendRedirect("adminlogin.jsp");
		} 
		else 
		{
			
			message="login failure";
			 session.setAttribute("message", message);
		response.sendRedirect("view.jsp");
		}
	}
	else if(action.equals("add account"))
	{	
		int accountnum=Integer.parseInt(request.getParameter("accountnum"));
		String accountname=request.getParameter("accountname");
		int accountbal=Integer.parseInt(request.getParameter("accountbal"));
		String password=request.getParameter("password");

		Customer bs=new Customer(accountnum,accountname,accountbal,password);
		ias.addaccount(bs);
		message="account added";
		session.setAttribute("message", message);
		response.sendRedirect("view.jsp");
	}
	else if(action.equals("searchcustomer"))
	{
		int accountnum=Integer.parseInt(request.getParameter("accountnum"));
		boolean anum=ias.getCustomer(accountnum);
		if(anum==true) 
		{
			message="search detailes";
			List<Customer> list=ias.getDetailesdisplay( accountnum);
			session.setAttribute("list",list );
			response.sendRedirect("showdetailes.jsp");
		} 
		else 
		{
			
			 message="search detailes not found";
				session.setAttribute("message",message );
				response.sendRedirect("view.jsp");
			 
		}
		
		}
	else if(action.equals("find"))
	{
		int accountnum =Integer.parseInt(request.getParameter("accountnum"));
		session.setAttribute("accountnum", accountnum);
		boolean findStatus=ias.findaccountnum(accountnum);
		if(findStatus==true)
		{
			response.sendRedirect("updatecustomer.jsp");
		}
		else {
			message="customer not found";
			session.setAttribute("message", message);
			response.sendRedirect("view.jsp");	
		}}
	else if(action.equals("update"))
	{

			int accountnum=(int)session.getAttribute("accountnum");
			String accountname=request.getParameter("accountname");
			int accountbal=Integer.parseInt(request.getParameter("accountbal"));
			String password=request.getParameter("password");

			
			Customer s=new Customer(accountnum,accountname,accountbal,password);
			ias.UpdateCustomer(s);
			message=" detailes  updated";
			session.setAttribute("message", message);
			response.sendRedirect("view.jsp");
		}
	else if(action.equals("balance"))
	{		int accountnum=Integer.parseInt(request.getParameter("accountnum"));

		List<Integer> list=ias.getbalance(accountnum);
		if(list.isEmpty())
			
		{
			message ="invalid account number";
			session.setAttribute("message",message);
			response.sendRedirect("view.jsp");
			}
		else
		{
		session.setAttribute("message",list );
		response.sendRedirect("view.jsp");
		}
	}
	else if(action.equals("remove")) 
	{
		int accountnum=Integer.parseInt(request.getParameter("accountnum"));
		boolean removeStatus=ias.removeacc(accountnum);
		if(removeStatus==true)
		{
			message="customer removed";
		}
		else 
		{
			message="invalid account";
		}
			session.setAttribute("message", message);
			response.sendRedirect("view.jsp");	
		}
		
	else {
		List<Customer> list=ias.getDetailesdisplay();
		session.setAttribute("list",list );
		response.sendRedirect("showdetailes.jsp");
		
	}
		
		
	}
	}
		

	

	
package com.dxc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.Customer;
import com.dxc.pojos.Transection;
import com.dxc.service.IUserService;
import com.dxc.service.UserServiceImpl;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	int accountnum,accountnum1;
	List<Transection> l=new ArrayList<>();
	IUserService ius= new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		{
			String message;
			String action="";
			String temp=request.getParameter("btn");
			if(temp!=null)
				action=request.getParameter("btn");
			HttpSession session=request.getSession();
		if(action.equals("login user"))
		{   
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			session.setAttribute("uname",name);

			accountnum=ius.getAccountnum(name,password);
			
			boolean loginStatus=ius.loginuser(name, password);
			if(loginStatus==true) 
			{
			       response.sendRedirect("loginuser.jsp");

			} 
			else 
			{
				
				message="login failure";
				 session.setAttribute("message", message);
			response.sendRedirect("view.jsp");
			}
			
		}
		else if(action.equals("deposit"))
		{	            String cid=(String)session.getAttribute("uname");

			int accountnum1=Integer.parseInt(request.getParameter("accountnum"));
					
			int accountbal1=Integer.parseInt(request.getParameter("accountbal"));
			boolean d=ius.deposit(accountnum1,accountbal1);
			System.out.println(d);
			if(d==true)
			{
				message="deposited";
			 session.setAttribute("message", message);
		response.sendRedirect("view.jsp");


			}
			else{response.getWriter().println("Something Wrong!!!");}

		}
			
	
				else if(action.equals("withdraw")) 
		{
			int accountnum2=Integer.parseInt(request.getParameter("accountnum"));
			String password2=request.getParameter("password");
			int accountbal2=Integer.parseInt(request.getParameter("accountbal"));
			boolean w=ius.withdraw(accountnum2,accountbal2);
			if(w==true)
			{
				message="amount withdraw";
				 session.setAttribute("message", message);
			response.sendRedirect("view.jsp");
			}
			else {
				
				message="insufficientbalance";
				 session.setAttribute("message", message);
			response.sendRedirect("view.jsp");
			}}
				else if(action.equals("password"))
				{
					String s=(String)request.getParameter("accountname");
					String s1=(String)request.getParameter("password");
					String s2=(String)request.getParameter("password1");
					String s3=(String)request.getParameter("password2");
					boolean b=ius.passwordChange(s1,s2,s);
					if(b==true&&s2.equals(s3))
					{
						message="password Changed!!";
						session.setAttribute("message", message);
						response.sendRedirect("view.jsp");
					}
					else if(b==false)
					{
						message="password incorrect";
						session.setAttribute("message", message);
						response.sendRedirect("view.jsp");
					}
					else 
					{
						message="reenter password incorrect";
						session.setAttribute("message", message);
						response.sendRedirect("view.jsp");
					}
				}
				else if(action.equals("balancedisplay"))
				{
					int accountnum=Integer.parseInt(request.getParameter("accountnum"));
					boolean anum=ius.getCustomer(accountnum);
					if(anum==true) 
					{
						message="search detailes";
						List<Customer> list=ius.getDetailesdisplay( accountnum);
						session.setAttribute("list",list );
						response.sendRedirect("showdetailes.jsp");
					} else 
					{

						message="accountnum not found";
						session.setAttribute("message", message);
						response.sendRedirect("view.jsp");
					}
			
				} else if (action.equals("transfer")) {

					System.out.println(" transfering  money");

					int user1 = Integer.parseInt(request.getParameter("accountnumber1"));

					int user2 = Integer.parseInt(request.getParameter("accountnumber2"));

					int balance = Integer.parseInt(request.getParameter("accountbal"));

					String str = ius.transferMoney(user1, user2, balance);

					session.setAttribute("str", str);

					response.sendRedirect("transfermoney.jsp");

		}

		 else 
	        {
	            String cid=(String)session.getAttribute("uname");
	             l=ius.getTransectionDetailes(cid);
	             session.setAttribute("list",l);
	             System.out.println();
	             response.sendRedirect("view2.jsp");
	        }
}
}
		
	
	
	
	
	
	
	
	
	
	
	}
package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dxc.pojos.Customer;


public class AdminDaoImpl  implements IAdminDao{
	private static Connection con;

	static {
		try {

			Class.forName("org.postgresql.Driver");
			System.out.println("driver loaded");
			 con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/WorkDB","postgres","1234");
			System.out.println("connected to database..");
		
		     } 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public boolean loginadmin(String adminname,String password)
	
			{
				boolean status=false;
				try {
					PreparedStatement pstmt=con.prepareStatement("select * from badmin");
					ResultSet rs=pstmt.executeQuery();
					while(rs.next())
					{
						if(adminname.equals(rs.getString(1))&&password.equals(rs.getString(2)))
						{
							return true;
						}
						else
						{
							return false;
						}
					}
				}
				catch(Exception e) 
				{
					e.printStackTrace();
				}
				return status;
			}
	public void addaccount(Customer bs)
	{

		try {
	
		PreparedStatement pstmt=con.prepareStatement("insert into customer values(?,?,?,?)");
		pstmt.setInt(1, bs.getAccountnum());
		pstmt.setString(2, bs.getAccountname());
		pstmt.setInt(3, bs.getAccountbal());
		pstmt.setString(4, bs.getPassword());

		pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	public boolean getCustomer(int accountnum) {
		try {
			
			PreparedStatement pstm=con.prepareStatement("select * from customer");
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
			{
				if(accountnum==rs.getInt(1))
				{
					return true;
				}
				
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return false ;
	}
		
	 public List<Customer> getDetailesdisplay(int accountnum)
	 {
		 List<Customer> list=new ArrayList<>();
		 try {
			 PreparedStatement ps=con.prepareStatement("select * from customer");
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				 if(accountnum==rs.getInt(1))
						{
							list.add(new Customer(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
						}
				}}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					return list;
			
	 }
	 public boolean findaccountnum(int accountnum) {
		 try
			{
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from customer");
				while(rs.next())
				{
					if(accountnum==rs.getInt(1))
					{
						return true;
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return false;
		}
		 
	 
	 public void UpdateCustomer(Customer s)
	 {System.out.println("detales going to updated");
		 
		 try {
			 PreparedStatement pt=con.prepareStatement("update customer set accountname=?,accountbal=?,password=? where accountnum=?");
			 
			 pt.setString(1,s.getAccountname());
			 pt.setInt(2, s.getAccountbal());
			 pt.setString(3, s.getPassword());
			 pt.setInt(4, s.getAccountnum());
			 
			 pt.executeUpdate();
			 System.out.println("updated");
			
		 }
		 catch(Exception e)
			{
				e.printStackTrace();
			}
		 }
	public List<Integer> getbalance(int accountnum)
	{System.out.println("balance enquery");
	List<Integer> list=new ArrayList<>();
	int flag=0;
	int accountbal=0;
	try {
		PreparedStatement pstmt1=con.prepareStatement("select * from customer");
		ResultSet ss=pstmt1.executeQuery();
		while(ss.next())
		{
			if(accountnum==ss.getInt(1)) {
				flag=1;
			}
		}
		if(flag==1) {
		PreparedStatement p= con.prepareStatement("select accountbal from customer where accountnum=?");
		p.setInt(1, accountnum);
		ResultSet r=p.executeQuery();
		while(r.next()) {
			accountbal=r.getInt(1);
			list.add(accountbal);
			System.out.println("balance particular ac");
		}
		
	}} catch (Exception e) {
e.printStackTrace();
	}
	return list;
		
		
	}

		public boolean removeacc(int accountnum)
		{ 
			int flag=0;
			try {			
				PreparedStatement pstmt1=con.prepareStatement("select * from customer");
				ResultSet ss=pstmt1.executeQuery();
				while(ss.next())
				{
					if(accountnum==ss.getInt(1)) {
						flag=1;
					}
				}
				if(flag==1)	
				{		
				PreparedStatement pstmt=con.prepareStatement("delete from customer where accountnum=?");
				pstmt.setInt(1, accountnum);
				pstmt.execute();
				return true;
				}
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
			return false;
		}
		 public List<Customer> getDetailesdisplay()
		 {
			 List<Customer> list=new ArrayList<>();
			 try {
				 PreparedStatement ps=con.prepareStatement("select * from customer");
				 ResultSet rs=ps.executeQuery();
				 while(rs.next()) 
							{
								list.add(new Customer(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
							}
					}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						return list;
				
		 }
	 }

		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


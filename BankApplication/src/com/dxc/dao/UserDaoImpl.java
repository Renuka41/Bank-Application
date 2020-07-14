package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Customer;
import com.dxc.pojos.Transection;


public class UserDaoImpl implements IUserDao {
	
	private static Connection con;
	int ab;
	int account,count,var,a;
	int accno;

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
	public int getAccountnum(String name,String password) {
		int accountnum=0;
		try {
			PreparedStatement p=con.prepareStatement("select accountnum from customer where accountname=?&&password=?");
p.setString(1,name);
p.setString(2, password);
ResultSet rset=p.executeQuery();
rset.next();
accountnum=rset.getInt(1);}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return accountnum;
	}

	public boolean loginuser(String name, String password) {
		boolean status=false;
		System.out.println("going to login");
		try {
			PreparedStatement p=con.prepareStatement("select * from customer");
			ResultSet rset=p.executeQuery();
			while(rset.next()) 
			{
				if(name.equals(rset.getString(2))&&password.equals(rset.getString(4))) 
				{
				 status=true;
				}
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	 public boolean checkAccountNum( int accountnum1) {
				boolean b=false;
				try {
					Statement stmt=con.createStatement();

					ResultSet rs=stmt.executeQuery("select accountnum from customer");

					while(rs.next())

					{

						if(accountnum1==rs.getInt(1))

						{

							b=true;

						}

					}

				} catch (SQLException e) {

					e.printStackTrace();

				}

				return b;

			}

	public boolean deposit(int accountnum,int accountbal) {
		
		int bal=0;
		try {
			PreparedStatement ps=con.prepareStatement("select accountbal from customer where accountnum=?");
			ps.setInt(1, accountnum);
			ResultSet rs=ps.executeQuery();
			rs.next();
			bal=rs.getInt(1);
			ps.close();
			bal=bal+accountbal;
			PreparedStatement pst=con.prepareStatement("update customer set accountbal=? where accountnum=?");
			pst.setInt(1, bal);
			pst.setInt(2, accountnum);
			pst.executeUpdate();
			pst.close();
			PreparedStatement pstmt2=con.prepareStatement("insert into transection values(?,?,?)");
			pstmt2.setString(1, "deposit");

			pstmt2.setInt(2, accountbal);
			pstmt2.setInt(3, accountnum);

			pstmt2.executeUpdate();
			pstmt2.close();
			return true;

			} 
		catch (Exception e) 
		{
			e.printStackTrace();	
		}
		
		return false;
		
	}
		
          public boolean withdraw(int accountnum2,int accountbal2)
			{
        	  System.out.println("going to withdraw");
				int bal=0;
				boolean insufficientbalance=false;

				try {
					PreparedStatement ps=con.prepareStatement("select accountbal from customer where accountnum=?");
					ps.setInt(1, accountnum2);
					ResultSet rs=ps.executeQuery();


    				while(rs.next())

    				{

    					bal=rs.getInt(1);
    					if(bal>accountbal2)
    					{
    						bal=bal-accountbal2;
    						System.out.println(bal);
    					}
    					else {

    						return insufficientbalance;
    					}

    				}
					PreparedStatement pst=con.prepareStatement("update customer set accountbal=? where accountnum=?");
					pst.setInt(1, bal);
					pst.setInt(2, accountnum2);
					pst.executeUpdate();
					pst.close();
					PreparedStatement pstmt2=con.prepareStatement("insert into transection values(?,?,?)");
					pstmt2.setString(1, "withdraw");

					pstmt2.setInt(2, accountbal2);
					pstmt2.setInt(3, accountnum2);

					pstmt2.executeUpdate();
					pstmt2.close();
					return true;

					
					} 
				catch (Exception e) 
				{
					e.printStackTrace();	
				}
				return false;
			}

          public boolean passwordChange(String password,String s2,String s)
          {      		
        	  int flag=0;
      		PreparedStatement pstmt,pstmt1;
      		try {
      			pstmt = con.prepareStatement("select * from customer");
      		ResultSet rset=pstmt.executeQuery();
      		while(rset.next())
      		{
      		if(password.equals(rset.getString(4))&&s.equals(rset.getString(2)))
      			flag=1;
      		}
      		pstmt1 = con.prepareStatement("update customer set password=? where accountname=?");
      		pstmt1.setString(1, s2);
      		pstmt1.setString(2, s);
      		pstmt1.execute();
      		} catch (SQLException e){e.printStackTrace();}
      		System.out.println("password changed");
      	if(flag==1)
      		return true;
      	else
      		return false;
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
    	 
    	 
    	 
    	 public String transferMoney(int accountnumber1,int accountnumber2,int balance)

    		{

    			int amount=0,amount1=0;

    			try {

    				PreparedStatement pstmt=con.prepareStatement("select * from customer where accountnum=?");

    				pstmt.setInt(1, accountnumber1);

    				ResultSet rs=pstmt.executeQuery();

    				while(rs.next())

    				{

    					amount=rs.getInt(3);
    					if(amount>balance)
    					{
    						amount=amount-balance;
    						System.out.println(amount);
    					}
    					else {

    						return "insufficiennt balance";
    					}

    				}

    				PreparedStatement pstmt1=con.prepareStatement("update customer set accountbal=? where accountnum=?");

    				pstmt1.setInt(1,amount);

    				pstmt1.setInt(2, accountnumber1);

    				pstmt1.executeUpdate();

    				System.out.println("deducting the from user1");

    				

    				PreparedStatement pstmt2=con.prepareStatement("select * from customer where accountnum=?");

    				pstmt2.setInt(1, accountnumber2);

    				ResultSet rs1=pstmt2.executeQuery();

    				while(rs1.next())

    				{

    					amount1=rs1.getInt(3);

    					amount1=amount1+balance;

    					System.out.println(amount1);

    				}

    				PreparedStatement pstmt3=con.prepareStatement("update customer set accountbal=? where accountnum=?");

    				pstmt3.setDouble(1,amount1);

    				pstmt3.setInt(2, accountnumber2);

    				pstmt3.executeUpdate();

    				System.out.println("updating the user2 balance");

    				PreparedStatement pstmt4=con.prepareStatement("insert into transection values(?,?,?)");


    				pstmt4.setString(1,"transfer");

    				pstmt4.setInt(2,balance);
    				pstmt4.setInt(3,accountnumber1);

    				pstmt4.execute();

    			}catch (SQLException e) 

    			{

    				e.printStackTrace();

    			}

    			return "Amount is transfered successfully!!!";

    		}
    	 public List<Transection> getTransectionDetailes(String cid) {
    			List<Transection> list =new ArrayList<Transection>();
    			try {
    				PreparedStatement p=con.prepareStatement("select accountnum from customer where accountname=?");
    				
					p.setString(1,cid);
					ResultSet s=p.executeQuery();
					
					s.next();
					accno=s.getInt(1);
				
				            PreparedStatement pstmt1=con.prepareStatement("select * from transection where accountnum=?");
				            pstmt1.setInt(1, accno);
				            ResultSet rs=pstmt1.executeQuery();
				            while(rs.next())
				            {
				                count=count+1;
				            }
				            PreparedStatement pstmt=con.prepareStatement("select * from transection where accountnum=?");
				            pstmt.setInt(1, accno);
				            ResultSet rset=pstmt.executeQuery();
				            var=count-5;
				            System.out.println(var);
				        while(rset.next())
				        {       
				        a=a+1;
				        System.out.println(a);
				            System.out.println(count);
				            if(a>var )
				            {
				            list.add(new Transection(rset.getString(1), rset.getInt(2), rset.getInt(3)));
				            }
				           
				           
				       
				        }
				        } catch (SQLException e) {
				            e.printStackTrace();
				        }
				       
				        return list;
				        }
					
				





    	 }
    			


















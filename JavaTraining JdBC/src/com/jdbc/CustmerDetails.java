package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class Custmer {
	int cid;
	String cname;
	String loc;
	int cstocks;

	public Custmer() {
		super();
		this.cid = cid;
		this.cname = cname;
		this.loc = loc;
		this.cstocks = cstocks;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public int getCstocks() {
		return cstocks;
	}

	public void setCstocks(int cstocks) {
		this.cstocks = cstocks;
	}

	@Override
	public String toString() {
		return "Custmer [cid=" + cid + ", cname=" + cname + ", loc=" + loc + ", cstocks=" + cstocks + "]";
	}

}

class JdBcMethod {
	public void insert(int cid, String cname, String Loc, int cstocks) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Custmer", "root", "P@ssw0rd");

			Statement st = con.createStatement();

			String Query = "INSERT INTO custmerdetails VALUES(" + cid + ",'" + cname + "','" + Loc + "','" + cstocks+ "')";

			System.out.println(Query);

			int i = st.executeUpdate(Query);

			if (i > 0) {
				System.out.println("Data  Inserted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getAllDetails() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Custmer", "root", "P@ssw0rd");
			Statement st = con.createStatement();

			String Query = "select * from custmerdetails";
			ResultSet rs = st.executeQuery(Query);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void Update(int cid,int id, String cname, String Loc, int cstocks) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Custmer", "root", "P@ssw0rd");

			Statement st = con.createStatement();

			String Query =("update custmerdetails set cid="+id+",cname='"+cname+"',Loc='"+Loc+"',cstocks="+cstocks+" where cid="+cid+";");
			int i = st.executeUpdate(Query);
			if (i > 0) {
				System.out.println("Row updated");

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void SelectOneRecord(int cid) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Custmer", "root", "P@ssw0rd");

			Statement st = con.createStatement();

			String Query = "select * from custmerdetails where cid=" +cid;
			ResultSet rs = st.executeQuery(Query);
			while (rs.next()) {
				System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void Delete(int cid) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Custmer", "root", "P@ssw0rd");

			Statement st = con.createStatement();
			String Query = "delete from custmerdetails where cid="+cid;
			st.execute(Query);

			System.out.println("Deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}

}
}

	public class CustmerDetails{



		public static void main(String[] args)throws Exception {
			Scanner sc = new Scanner(System.in);
			JdBcMethod j =new JdBcMethod();
			
			do {
				System.out.println("\n 1.insert");
				System.out.println(" 2.SelectAll");
				System.out.println(" 3.Update");
				System.out.println(" 4.SelectOne");
				System.out.println(" 5.Delete");

				System.out.println("Enter the Choice");
				int ch =sc.nextInt();
				switch(ch)
				{
				case 1:
					System.out.println("Enter Custmer Id");
					int cid=sc.nextInt();
					System.out.println("Enter Custmer Name");
					String cname=sc.next();
					System.out.println("Enter Custmer Location");
					String Loc=sc.next();
					System.out.println("Enter Custmer Stocks");
					int cstocks=sc.nextInt();
				
					HashMap<Integer,Custmer> l= new HashMap<>();

					j.insert(cid,cname,Loc,cstocks);

					break;
				case 2: System.out.println("Get All Custmer details ");
				     j.getAllDetails();
				break;

				case 3: System.out.println("\n Update records");
				System.out.println("Enter Custmer Id");
				 cid=sc.nextInt();
				System.out.println("Enter new id");
			     int id=sc.nextInt();
				System.out.println("Enter Custmer Name");
				cname=sc.next();
				System.out.println("Enter Custmer Location");    
				Loc=sc.next();
				System.out.println("Enter Custmer Stocks");
				cstocks=sc.nextInt();
				j.Update(cid,id, cname, Loc, cstocks);
				break;
				case 4:System.out.println("\n select one records");
				System.out.println(" Enter Custmer Id");
				cid=sc.nextInt();
				j.SelectOneRecord(cid);

				break;
				case 5 :System.out.println("\n Update records");
				System.out.println(" Enter Custmer Id");
				cid=sc.nextInt();
				j.Delete(cid);
				break;
				default :System.out.println("Option is available");
                           

				}
			}while (true);
		}
}


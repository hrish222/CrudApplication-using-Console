package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;

class Student{
	String sub;
	public Student(String sub, int id) {
		super();
		this.sub = sub;
		this.id = id;
	}


	int id;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student() {
		super();
		this.sub = sub;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}


	@Override
	public String toString() {
		return "Student [sub=" + sub + ", id=" + id + "]";
	}

}
class StudentDetails{
	public void addSub(String sub) {
		//ashSet<String> h=new HashSet<>();

		HashSet<String> h =new HashSet<>();
		h.add(sub);

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Student", "root", "P@ssw0rd");
			PreparedStatement ps=con.prepareStatement("insert into sub(sub)values(?)");

			ps.setString(1,sub);
			ps.executeUpdate();


			con.close();
		}catch(Exception ex){System.out.println(ex);}
		System.out.println("Row Insert");
		System.out.println(h);

	}


	public void AllofSub() {

		HashSet<String> h =new HashSet<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Student", "root", "P@ssw0rd");
			Statement st = con.createStatement();

			String Query = "select * from sub";
			ResultSet rs = st.executeQuery(Query);
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setSub(rs.getString(2));


				h.add(rs.getString(1)+" "+ rs.getString(2));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(h);

	}
	public void Delete(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Student", "root", "P@ssw0rd");

			Statement st = con.createStatement();
			String Query = "delete from sub where id="+id;
			st.execute(Query);

			System.out.println("Deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
public class HashsetU {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StudentDetails d= new StudentDetails();
		do {
			System.out.println(" 1.insert");
			System.out.println(" 2.SelectAll");
			System.out.println("3.Delete");
			System.out.println("Enter the Choice");
			int ch =sc.nextInt();
			switch(ch)
			{
			case 1:
				System.out.println("insert the data");
				System.out.println("Enter the Subject");
				String sub=sc.next();
				d.addSub(sub);
				break;
			case 2: System.out.println("List of Subject Getting");
			    d.AllofSub();
			break;
			case 3: System.out.println("Delete Records");
			     System.out.println("Enter Id");
			    int id =sc.nextInt();
			    d.Delete(id);
			break;
			default:System.out.println("Wrong option choose ");


			}
		}while (true); 

	}

}



package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class DBCode{
	Connection con;
	Scanner sc=new Scanner(System.in);
	String tname;
	int id,mark;
	String cls,name;
	public void dbConnection() {
		String dburl="jdbc:mysql://localhost:3306/Student?autoReconnect=true&ampuseSSL=false";
		String dbuser="root";
		String dbpwd="";
		try {
			con=DriverManager.getConnection(dburl,dbuser,dbpwd);
			System.out.println("DBconnected.....");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public void create() {
//		System.out.println("Enter your table name:");
//		tname=sc.next();
//		String q="Create table neero(ID int,Name varchar(25),Class varchar(25),Mark int)";
//		Statement smt;
//		try {
//			smt=(Statement) con.createStatement();
//			smt.execute(q);
//			System.out.println("Table created....");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	public void insert() {
		System.out.println("Enter Student id:");
		int id=sc.nextInt();
		System.out.println("Enter Student Name:");
		String name=sc.next();
		System.out.println("Enter Student class:");
		String cls=sc.next();
		System.out.println("Enter Student mark:");
		int mark=sc.nextInt();
		String q="insert into neero values(?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(q);
			pst.setInt(1,id);
			pst.setString(2, name);
			pst.setString(3, cls);
			pst.setInt(4, mark);
			pst.executeUpdate();
			System.out.println("insert success....");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void select() {
		String q="Select * from neero";
		try {
			PreparedStatement pst=con.prepareStatement(q);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				id=rs.getInt(1);
				name=rs.getString(2);
				cls=rs.getString(3);
				mark=rs.getInt(4);
				System.out.println(id+" "+name+" "+cls+" "+mark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void Update() {
		int id;
		while(true) {
		
		System.out.println("1.Update name\n2.Update class\n3.Update mark\n4.Exit");
		int no=sc.nextInt();
		if(no==1) {
			System.out.println("Enter your ID:");
			 id=sc.nextInt();
			System.out.println("Enter your Updatedname");
			String name=sc.next();
		String q="Update neero set name=?where id=?";
			try {
				PreparedStatement pst=con.prepareStatement(q);
				pst.setString(1, name);
				pst.setInt(2, id);
				pst.executeUpdate();
				System.out.println("Name update success...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			else if(no==2) {
			System.out.println("Enter your id");
			id=sc.nextInt();
			System.out.println("Enter your Updated Class:");
			String cls=sc.next();
			String q1="Update neero set class=? where id=?";
			try {
				PreparedStatement pst=con.prepareStatement(q1);
				pst.setString(1, cls);
				pst.setInt(2, id);
				pst.executeUpdate();
				System.out.println("class update success...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			else if(no==3) {
		System.out.println("Enter your id");
		id=sc.nextInt();
		System.out.println("Enter your UpdatedMark:");
		int mark=sc.nextInt();
		String q2="Update neero set mark=? where id=?";
		try {
			PreparedStatement pst=con.prepareStatement(q2);
			pst.setInt(1, mark);
			pst.setInt(2, id);
			pst.executeUpdate();
			System.out.println("Mark update success...");
		} catch (SQLException e) {
			e.printStackTrace();
	}
		}
		else if(no==4)
			System.out.println("Exiting...");
			break;
	}
	}
	public void delete() {
		System.out.println("Enter your id:");
		int id=sc.nextInt();
		String q="delete from neero where id=?";
		try {
			PreparedStatement pst=con.prepareStatement(q);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("Student data delete success....");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	}

public class Students_DBMS {

	public static void main(String[] args) {
		
     DBCode dc=new DBCode();
     while(true) {
    	 try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Admin:");
			 System.out.println("1.Insert\n2.View Table\n3.Update\n4.Delete\n5.Exit");
			 System.out.print("Enter your choice:");
			 int num=sc.nextInt();
			 if(num==1) {
				 dc.dbConnection();
				 dc.insert();
			 }
			 else if(num==2) {
				 dc.dbConnection();
				 dc.select();
			 }
			 else if(num==3) {
				 dc.dbConnection();
				 dc.Update();
			 }
			 else if(num==4) {
				 dc.dbConnection();
				 dc.delete();
			 }
			 else if(num==5) {
				 System.out.println("Exiting........");
				 break;
			 }
			 else
				 System.out.println("Invalid choice..");
		}
     }
	}
}

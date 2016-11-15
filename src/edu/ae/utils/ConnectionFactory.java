package edu.ae.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getconnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����سɹ�!");
		}
	   catch (ClassNotFoundException e) {
		   System.out.println("�����ʧ��!");
			e.printStackTrace();
		}
	   
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ninehouse","root","NineHouse2015");
			System.out.println("��ݿ����ӳɹ�");
		} catch (SQLException e) {
			System.out.println("��ݿ�����ʧ��");
		}
		
		return conn;
	}
}

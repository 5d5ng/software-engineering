package testPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
public class Test {

	public static void main(String[] args) {
//		System.out.println("�˻��� �л��� �й��� �Է��϶�");
//		System.out.println();
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�
//
//			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
//					"201512119"); // �����ͺ��̽� ����  
//
//			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
//			PreparedStatement pstmt = null;
//
//			ResultSet rs = stmt.executeQuery("select * from customer"); // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����
//
//			String sql1 = "insert into customer values ('�ƾ���','011222','B',300,22)";	
//			   stmt.executeUpdate(sql1);
//			
//			
//			while (rs.next()) { //���� �湮�� ���� �����ϴ� ���� ����
//				String cname = rs.getString("cname"); // getInt(1)
//				String phone = rs.getString("phone");
//				String grade = rs.getString("grade");
//				int saving = rs.getInt("saving");
//				int age =  rs.getInt("age");
//				System.out.format(" %s \n",phone);
//			}
//
//			rs.close();
//			stmt.close();
//			con.close();
//
//		} catch (SQLException se) {
//			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
//		} catch (ClassNotFoundException cnfe) {
//			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
//		} finally {
//
//		}
//
//	}
	int n=3;
	String s  = "sew"+n;
	System.out.println(s);
	}
	

}
package MarketPos;


import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.mysql.jdbc.PreparedStatement;

public class DBconnector {
	String url;
	BufferedWriter bw;//����
	BufferedReader br;//�б�
	String urlbooks,urlcustomer,urlproduct,urladministrator;
	//	 private static Connection con;
	//	 private static Statement stmt;
	//	 private static ResultSet rs;
	//txt ���Ϸ� ����
	public DBconnector() {
		urlbooks = "books.txt";
		urlcustomer = "customer.txt";
		urlproduct = "product.txt";
		urladministrator = "administrator.txt";
	}

	//�����ڰ� �´��� Ȯ���ϴ� �޼ҵ�
	public boolean checkPw(String inputPW) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  

			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select * from administrator"); // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����

			while (rs.next()) { //���� �湮�� ���� �����ϴ� ���� ����
				String pw = rs.getString("password"); // getInt(1)
				if(pw.equals(inputPW)) {
					System.out.println("�����ڸ�� �α��� ���� ");
					return true;
				}
			}

			rs.close();
			stmt.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}
		return false;

	}

	//��ǰ�� �߰��ϴ� �޼ҵ�
	public void addProduct(String barcode, String pname, String ptype, String pamount, String expiration,int price) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  

			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			String sql = " insert into product values(' "+barcode+" ',' "+pname+" ',' " + ptype +" ',' "+ pamount +" ',' "+expiration + "' ,"+price+ ")" ; 	
			stmt.executeUpdate(sql);

			stmt.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}		

		System.out.println( barcode + "\t" +pname+ "\t" +ptype+ "\t" +pamount + "\t" +expiration + "\t" + price + "��ǰ �߰� �Ϸ�");
	}

	//��ǰ ��ȸ�� ����ϴ� �޼ҵ�
	public void pdisplay() {
		System.out.println("��ǰ ��ȸ ���");
		System.out.println("���ڵ�	��ǰ�̸�	��ǰ����	��ǰ����	�������		����");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  
			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select * from product"); // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����

			while (rs.next()) { //���� �湮�� ���� �����ϴ� ���� ����
				String barcode = rs.getString("barcode");
				String pname = rs.getString("pname");
				String ptype = rs.getString("ptype");
				String pamount = rs.getString("pamount");
				String expiration = rs.getString("expiration");
				int price = rs.getInt("price");							
				System.out.println(barcode+"  "+pname+"  "+ptype+"  "+pamount+"  "+expiration+" "+price);
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}

	}

	//��ǰ�� �˻��ϴ� �޼ҵ�
	public void psearch(String key) {
		System.out.println("��ǰ �˻� ���");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  

			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select * from product where barcode = '"+key+"'"); // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����
			rs.next();		
			System.out.println("��ǰ�� ã�ҽ��ϴ�");
			System.out.println("���ڵ�	��ǰ�̸�	��ǰ����	��ǰ����	�������		����");

			String barcode = rs.getString("barcode");
			String pname = rs.getString("pname");
			String ptype = rs.getString("ptype");
			String pamount = rs.getString("pamount");
			String expiration = rs.getString("expiration");
			int price = rs.getInt("price");	
			String line =barcode+"\t"+pname+"\t"+ptype+"\t"+pamount+"\t"+expiration+"\t"+price;
			System.out.println(line);

			rs.close();
			stmt.close();

		} catch (SQLException se) {
			System.err.println("ã���ô� ��ǰ�� �����ϴ�." );
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}
	}
	//���� ��ȸ�� ����ϴ� �޼ҵ�
	public void cdisplay() {
		System.out.println("���� ��ȸ ���");
		System.out.println("�̸�	��ȭ��ȣ	ȸ�����	������	����");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  
			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select * from customer"); // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����
			while (rs.next()) { //���� �湮�� ���� �����ϴ� ���� ����

				String cname = rs.getString("cname");
				String phone = rs.getString("phone");
				String grade = rs.getString("grade");
				int saving = rs.getInt("saving");	
				int age = rs.getInt("age");	
				String line =cname+"\t"+phone+"\t"+grade+"\t"+saving+"\t"+age;
				System.out.println(line);

			}

			rs.close();
			stmt.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}

	}

	//������ �˻��ϴ� �޼ҵ�
	public void csearch(String key) {
		System.out.println("���� �˻� ���");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  
			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select * from customer where phone = '"+key+"'"); // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����
			rs.next();		
			System.out.println("������ ã�ҽ��ϴ�");
			System.out.println("�̸�	��ȭ��ȣ	ȸ�����	������	����");			
			String cname = rs.getString("cname");
			String phone = rs.getString("phone");
			String grade = rs.getString("grade");
			int saving = rs.getInt("saving");
			int age = rs.getInt("age");
			String line =cname+"\t"+phone+"\t"+grade+"\t"+saving+"\t"+age;
			System.out.println(line);

			rs.close();
			stmt.close();

		} catch (SQLException se) {
			System.err.println("ã���ô� ������ �����ϴ�." );
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}
	}


	public void bdisplay() {
		System.out.println("��� ���");
		System.out.println("���ڵ�	���Ű���	��ǰ�̸�	��������	����");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  

			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select * from books"); // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����
			while(rs.next()) {								
				String barcode = rs.getString("barcode");
				int amount = rs.getInt("amount");
				String pname = rs.getString("pname");
				String method = rs.getString("method");
				int price = rs.getInt("price");					
				String line =barcode+"\t"+amount+"\t"+pname+"\t"+method+"\t"+price;
				System.out.println(line);

			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}
	}

	//ȸ���� ����ϴ� �޼ҵ�
	public void addCustomer(String cname, String phone, char grade, int saving,int age) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  

			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			String sql = " insert into Customer values(' "+cname+" ',' "+phone+" ',' " + grade +" ', "+ saving +" , "+age +  ")" ; 	
			stmt.executeUpdate(sql);

			stmt.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}		

		System.out.println(cname + "/" + phone  + "/" + grade   + "/"
				+ saving  + "/" + age + "/ ȸ�� �߰� �Ϸ�");	
	}


	//���Ű����� ��ǰ���� Ȯ���ϴ� �޼ҵ�
	public boolean checkProduct(String item, int count) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  

			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select * from product where barcode = '"+item+"'"); // item ���� ���ڵ� Ȯ���ϰ� 
			rs.next();		

			String pamount = rs.getString("pamount");
			String exp = rs.getString("expiration");
			exp = exp.substring(0, exp.lastIndexOf('-')+3 );

			if(Integer.parseInt(pamount)<count) {
				System.out.println("����� �����մϴ�.");
			}
			else if(!isValidExp(exp)) {
				System.out.println("��������� �������ϴ�.");
			}
			else {
				return true;
			}				
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {
		}
		return false;
	}

	private boolean isValidExp(String exp) {
		Date today = new Date();
		SimpleDateFormat  format1 = new  SimpleDateFormat("yyyy-MM-dd");
		String present = format1.format(today);
		if(exp.compareTo(present)>=0 ) {
			return true;
		}

		return false;

	}

	public void addBooks(String barcode, Integer amount, String price, String pname, String method) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  

			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			String sql = " insert into books values(' "+barcode+" ',"+ amount +",' " + pname +" ',' "+ method +" ', "+Integer.parseInt(price) +  ")" ; 	
			stmt.executeUpdate(sql);
			stmt.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}		

		System.out.println(barcode + "/" + amount  + "/" + pname   + "/"
				+ method  + "/" + price + "��/ ��� �߰� �Ϸ�");	
	}


	//��ǰ������ �����ϴ� �޼ҵ�
	public void renew_pamount(String key,int count) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  

			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			String chk = "SELECT barcode from PRODUCT  ";
			ResultSet rs = stmt.executeQuery(chk);
			boolean flag = false;
			while(rs.next()) {
				String tempBarcode = rs.getString("barcode");
				if(tempBarcode.equals(key)) {
					flag = true;
					break;
				}
			}
			if(flag == true) {
				String sql = "UPDATE PRODUCT SET pamount = pamount +"+ count +"where barcode ='"+ key+"'"  ;
				stmt.executeUpdate(sql);
				System.out.println("����� �����Ǿ����ϴ�");
			}
			else {
				System.err.println("���ڵ�� ��ġ�ϴ� ��ǰ�� �����ϴ�.");
			}
			stmt.close();
			rs.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}		

	}

	//���ݵ����͸� �ѱ��  �޼ҵ�
	public ArrayList<String> pricecalculation(ArrayList<String> item_list,int count) {	 
		ArrayList<String> price_list = new ArrayList<String>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  

			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select barcode,price from product"); // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����
			while(rs.next()) {
				String barcode = rs.getString("barcode");
				int getPrice = rs.getInt("price");
				for(String target : item_list) {
					if(target.equals(barcode)) {
						int val = count*getPrice;
						price_list.add(Integer.toString(val));	
					}
				}				
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�. (�̸� ������ ����)" + se.getMessage() );
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}
		return price_list;

	}

	//��ǰ�̸������͸� �ѱ��  �޼ҵ�
	public ArrayList<String> pnamecalculation(ArrayList<String> item_list) {	 

		ArrayList<String> pname_list = new ArrayList<String>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  
			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select barcode, pname from product"); // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����
			while(rs.next()) {	
				String barcode = rs.getString("barcode");
				String pname = rs.getString("pname");	
				for(String target :item_list) {
					if(target.equals(barcode)) {
						pname_list.add(pname);
					}
				}

			}
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�. (�̸� ������ ����)" + se.getMessage() );
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {

		}
		return pname_list;

	}

	//ȸ������ Ȯ���ϴ� �޼ҵ�
	public boolean checkCustomer(String phone) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  
			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			ResultSet rs = stmt.executeQuery("select * from customer where phone = '"+phone+"'"); // item ���� ���ڵ� Ȯ���ϰ� 
			while(rs.next()) {			
				String target = rs.getString("phone");
				if(target.equals(phone)) {
					return true;
				}
			}
			rs.close();
			stmt.close();
			return false;
		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {
		}
		return false;
	}

	// ȸ���� ��� ��� 
	public void membercalculation(String phone,int fee) {
		String grade = "N";
		int saving = 0;	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //����̹� �ε�
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512119",
					"201512119"); // �����ͺ��̽� ����  
			Statement stmt = con.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����

			ResultSet rs = stmt.executeQuery("select grade,saving from customer where phone = '"+phone+"'"); // item ���� ���ڵ� Ȯ���ϰ� 

			rs.next();

			grade = rs.getString("grade");
			String prevGrade = grade;
			saving = rs.getInt("saving");

			switch (grade) {
			case "S":
				saving += fee * 0.05;
				break;
			case "A":
				saving += fee * 0.02;
				break;
			case "B":
				saving += fee * 0.01;
				break;
			default:
				System.out.println("�ý��� ����");
				break;
			}

			if(saving >= 1000.0)
				grade = "S";

			else if(saving>=500.0)
				grade = "A";

			else 	grade = "B";

			String sql = "UPDATE CUSTOMER SET grade ='"+ grade+"', saving = " +saving +"where phone = '"+phone+ "' ";
			stmt.executeUpdate(sql);
			if(!prevGrade.equals(grade))
				System.out.println("����� ���� �Ǿ����ϴ� ���ŵ� ���:"+ grade);

			System.out.println("�������� �����Ǿ����ϴ� ����������:"+saving);


			rs.close();
			stmt.close();


		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("����̹� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		} finally {
		}



	}




}




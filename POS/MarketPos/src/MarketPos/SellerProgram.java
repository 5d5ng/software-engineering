package MarketPos;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SellerProgram {
	Scanner scan;//���� �Է��ϴ� ��.
	DBconnector dbconnecter;//DBconnector class�� ���.
	String user_num = "";//���� ��ȣ

	
	public SellerProgram() {
		scan = new Scanner(System.in);//���� �Է��ϴ� ��ü ����
	}

	public void go() {//DBconnector class ����.
		System.out.println("�Ǹ��� ���α׷��� �����մϴ�.");
		dbconnecter = new DBconnector();//��ü ����
		start();//start �޼ҵ� ����
	}

   //�޴� �޼ҵ�
	public void start() {
		while (true) {
			System.out.println("����Ͻ÷��� ����� ������ �ּ���");

			System.out.println("\t 1. ȸ�� �߰�");
			System.out.println("\t 2. ���� ����");
			System.out.println("\t 3. ����");

			String result = scan.next();//6���� ��� �� 1�� �Է�

			switch (result) {
			case "1"://ȸ�� �߰�
			    customer_add();//�޼ҵ� �̵�
				break;
			case "2"://���� ����
				print_start();
				purchase();
				print_finish();
				break;
			case "3"://����
				System.out.println("�ý����� �����մϴ�.");
				System.exit(1);
			default:
				System.out.println("�Է��� �߸� �Ǿ����ϴ�.");
				break;
			}
		}
	}



	//���� ��� �޼ҵ�
	public void customer_add() {
		
		String cname; //�̸�
		String phone; //��ȭ��ȣ
		char grade ='B'; //������
		int saving = 0; //������
		int age; //����
		

		System.out.println("ȸ������� �����մϴ�.");
		System.out.println("�̸��� �Է��� �ּ���");
		cname = scan.next();
		System.out.println("��ȭ��ȣ�� �Է��� �ּ���");
		phone = scan.next();
		System.out.println("���� �Է��� �ּ���");
		age = scan.nextInt();
					
		dbconnecter.addCustomer(cname, phone, grade, saving,age);//�� ����.
		System.out.println("����� �Ϸ� �Ǿ����ϴ�.");
	}
	
	

	public void purchase() {
		
		String cardnum=null;
		int cash=0;
		int fee;
		int balance=0;
		
		System.out.println("���ݺ��� ��ǰ ���Ÿ� �����ϰڽ��ϴ�.");
		ArrayList<String> prod_item_list = new ArrayList<String>(); //��ǰ ���ڵ�
		ArrayList<Integer> prod_count_list = new ArrayList<Integer>();//��ǰ ����
		ArrayList<String> prod_price_list;   //��ǰ����   
		ArrayList<String> prod_pname_list;   //��ǰ�̸�
		int count = 0;
		while (true) {//���ѷ���(break �ɶ����� �ݺ�)
			System.out.println("���ڵ带 �Է����ּ���");
			String item = scan.next();
			System.out.println("��ǰ������ �Է����ּ���");
			 count = scan.nextInt();
			if(dbconnecter.checkProduct(item,count)) {
			prod_item_list.add(item);//�����Ϸ��� ��ǰ�� ���ڵ带 ��ǰ ��ȣ list�� ����
			prod_count_list.add(count);//�����Ϸ��� ��ǰ�� ������ list�� ����
			}
			System.out.println("��������� 1 �Է�/ �߰��� ���Ŵ� �ƹ�Ű �Է�.");
			if (scan.next().equals("1"))//1������ ���� �޼ҵ忡�� ����.
				break;
		}
		
		prod_price_list = dbconnecter.pricecalculation(prod_item_list,count); 
		prod_pname_list = dbconnecter.pnamecalculation(prod_item_list);
		fee = calculation(prod_price_list,prod_count_list);
			
		
		System.out.println("=======================");
		System.out.println("ȸ�����θ� ������ �ּ���");
		System.out.println("-----------------------");
		System.out.println("\t 1. ȸ������.");
		System.out.println("\t 2. ��ȸ������.");
		System.out.println("\t 3. ����.");
		System.out.println("=======================");
		

		String result = scan.next();

		switch (result) {
		case "1":
			System.out.println("ȸ�� ����ȣ�� �Է����ּ���");
			String phone = scan.next();
			if(dbconnecter.checkCustomer(phone)) dbconnecter.membercalculation(phone,fee);
			else checkRegister(phone,fee);
			
			
			break;
		case "2":
			break;
		case "3":
			System.out.println("�ý����� �����մϴ�.");
			System.exit(1);
		default:
			return;
		}
		
		System.out.println("=======================");
		System.out.println("���� ����� ������ �ֽʽÿ�.");
		System.out.println("-----------------------");
		System.out.println("\t1. ����");
		System.out.println("\t2. �ſ�ī��");
		System.out.println("\t3. ����");
		System.out.println("=======================");
		
	
		String result2 = scan.next();
		switch (result2) {
		case "1":
			System.out.println("�� �ݾ��� �Է��Ͻÿ�");
			cash = scan.nextInt();
			balance = cashcalculation(cash,fee);
			break;
		case "2": 
			System.out.println("ī���ȣ�� �Է��Ͻÿ�");
			cardnum = scan.next();
			break;
		case "3"://����
			System.out.println("�ý����� �����մϴ�.");
			System.exit(1);
		default:
			System.out.println("�߸��� �Է°��Դϴ�.");
			break;
	
		}
		
		books_add(prod_item_list,prod_count_list,prod_price_list,prod_pname_list,cash);
		
		//��� ���� ��� �ʿ�
		product_print(prod_pname_list,prod_count_list,prod_price_list,fee,cash);
	}

	private void product_print(ArrayList<String> pname_list, ArrayList<Integer> count_list,ArrayList<String> price_list, int fee, int cash) {
		
		
		for(int i=0;i<pname_list.size();i++) {
			System.out.println("��ǰ��: "+ pname_list.get(i) +"����"+ count_list.get(i));
			
		}
		System.out.println("�Ѿ�:"+fee);
		System.out.println("���� �ݾ�:"+cash);
		
	
	}

	private void books_add(ArrayList<String> item_list,ArrayList<Integer> count_list,ArrayList<String> price_list,ArrayList<String> pname_list,int cash) {
		for(int i = 0;i<item_list.size();i++) {
			if(cash != 0)
			dbconnecter.addBooks(item_list.get(i), count_list.get(i), price_list.get(i), pname_list.get(i),"cash");//�� ����.
			else dbconnecter.addBooks(item_list.get(i), count_list.get(i), price_list.get(i), pname_list.get(i),"card");
		}
		System.out.println("��� ����� �Ϸ�Ǿ����ϴ�.");
		
	}
	
	//fee�� ����ϴ� �޼ҵ�
	public int calculation(ArrayList<String> prod_count_price,ArrayList<Integer> count_list) {	//��ǰ ��ȣ,��ǰ ������ �̿��ؼ� ����� Ȯ�� 
		int fee =0;
		
		for (int i = 0; i < count_list.size(); i++) {
			fee += Integer.parseInt(prod_count_price.get(i))
					* count_list.get(i);
		}

		System.out.println(count_list.size() + "��/ �� �ݾ� ���Ϸ� =" + fee);// ���Թ�ǰ�� ����, �� �ݾ� ���
		return fee; //�ѱݾ� ȣ��
	}

	private int cashcalculation(int cash, int fee) {
		int balance = cash - fee;
		System.out.println("������ = "+ cash+"  �Ž����� = "+ balance);
		return cash-fee;	
	}

	private void checkRegister(String phone, int fee) {
		System.out.println("=======================");
		System.out.println("ȸ������� �Ͻðڽ��ϱ�");
		System.out.println("-----------------------");
		System.out.println("\t 1. ��.");
		System.out.println("\t 2. �ƴϿ�.");
		System.out.println("\t 3. ����.");
		System.out.println("=======================");
		
		String result = scan.next();

		switch (result) {
		case "1":
			customer_add();
			dbconnecter.membercalculation(phone,fee);
			break;
		case "2":
			break;
		case "3" :
			System.out.println("�ý����� �����մϴ�.");
			System.exit(1);
		}
		
	}
	
	private void print_finish() {
		System.out.println("�������� ����Ͽ����ϴ�.");
	}

	private void print_start() { 
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("receipt.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
//������� ���α׷�
package com.human.ex;
import java.util.Scanner;
class Account{                   //Account: �� 1�� ���� �ϴ� Ŭ���� 
	public String id;
	public String pw;
	public int money;
	public static int totalCount=0;   //static �� ����:  ��� ������ ���ٰ���,���������ϵ���
	
	public Account() {
		totalCount++;                //���� ���鶧���� �߰��ǵ���
	}
	public Account(String id, String pw) {
		this();                       // (!) �� Account()������ ȣ���� �����Ѵ�. (totalCount++ �����)
		this.id=id;
		this.pw=pw;
	}
	public void deposit(int money) {  //�Ա�
		this.money+=money;
	}
	public void withdraw(int money) { //���
		this.money-=money;
	}
	public void display() {
		System.out.println("------------------------------------");
		System.out.println("�� �������� "+Account.totalCount);   // (!) static�̱� ������ 'class��.' �����ֱ�.
		System.out.println("ID: "+ id);
		System.out.println("�ܾ�: "+ money);
		System.out.println("------------------------------------");
	}
	
	public void menu() {
		Scanner sc=new Scanner(System.in);
		String inputString;
		boolean flag=true;     //while ������ ���� flag�� ����
		
		while(flag) {  
			
			System.out.println(this.id+"��, �޴��� �����ϼ���.\n1.�Ա� 2.��� 3.����");
			inputString =sc.nextLine();
			switch(inputString) {
			case "1":
				System.out.print("�Ա��� �ݾ��Է�:");
				int money=Integer.parseInt(sc.nextLine());    //�Է��� string���̹Ƿ� int�� ����ȯ
				deposit(money);
				display();
				break;
			case "2":
				System.out.print("����� �ݾ��Է�:");
				money=Integer.parseInt(sc.nextLine());
				if(this.money<money) {
					System.out.println("�ܾ׺���");
				}else {
				    withdraw(money);
				    display();
				}
				;
				break;
			case "3":
				System.out.println("�����մϴ�.");
				flag=false;                                 //3 ������ ���̻� �ݺ����� �ʵ��� false�� ������ �ݺ��� �������ش�.
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
		
	}
	
}
class Bank{                             //Bank : �� �������� �����ϴ� Ŭ����. ������ account[]�� ����.
	
	public static int totalCount;       //���� �Ѱ���
	public int accountCount;            //���� �Ѱ���
	public Account[] account=new Account[10];  //Account �迭 ����
	public String bankName;
	public int accountCurrent=-1;              //�ƹ��͵� �α��ε��� ���� ����(-1) , �α��� �Ǹ� �ش� �ε����� �ǰԲ�.
	public Bank() {
		totalCount++;
	}
	public Bank(String bankName) {
		this();
		this.bankName=bankName;
	}
	public void displayAll() {
		System.out.println("---------------------------");
		System.out.println("�� �����: " + totalCount);
		System.out.println("���� �̸�: " + bankName);
		System.out.println("---------------------------");
		for(int i=0;i<accountCount;i++) {
			account[i].display();
		}
	}
	public boolean idCompare(String id) {         //���̵� �ߺ����� Ȯ�� �޼ҵ�
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].id.equals(id)) {
				returnValue=true;
			}
		}
		return returnValue;
	}
	public void createAccount() {                          //ȸ������
		Scanner sc=new Scanner(System.in);
		System.out.print("�����Ϸ��� id�� �Է��ϼ���:");
		String id=sc.nextLine();
		if(idCompare(id)) {                                //���̵� �ߺ����� Ȯ��
			System.out.println("�̹� �����ϴ� id�Դϴ�.");
			return;
		}
		System.out.print("�����Ϸ��� pw�� �Է��ϼ���:");
		String pw=sc.nextLine();
		account[accountCount++]=new Account(id,pw);  //�����迭�� �־��ֱ�: account[0],account[1],account[2] ���ʷ�
	}
	public boolean logIn(String id,String pw) {
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].id.equals(id)&&account[i].pw.equals(pw)) {      //�Է��� id,pw�� account[]�� �װ͵�� ��ġ�Ѵٸ� true
				returnValue=true;        //�α��� ��.
				accountCurrent=i;       //���� ������accountCurrent=-1 ���� �ش��ε����� �ٲ��ش�.
				break;                  //�ش��ε��� ã���� ������ �ݺ��ʿ� ��� break ���� for�� �����Ŵ. 
			}
		}
		return returnValue;
	}
	public void logOut() {                            //�α׾ƿ�
		accountCurrent=-1;
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
	}
	
	public void deleteAccount(String id, String pw) {   //�������� (�α��� ���� ����)
		boolean flag=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].id.equals(id)&&account[i].pw.equals(pw)) {
				accountCurrent=i;      //ã�´�.
				flag=true;
				break;		
			}
		}
		if(flag) {                  //ã���� ����
			for(int i=accountCurrent;i<accountCount;i++) {
				account[i]=account[i+1];
			}
			accountCount--;
			Account.totalCount--;
			accountCurrent=-1;                          //���þȵǰԲ� -1�� ��������.
			System.out.println("������ �����Ǿ����ϴ�.");
		}else {
			System.out.println("������ �����߽��ϴ�.");
		}
	}
	
	//�α��� �� ���¿����� ���������� �����ϰ� ������.(pw�� �ʿ�)
	public void deleteAccount(String pw) {
		deleteAccount(account[accountCurrent].id,pw);
	}
	public void useAccount() {
		if(accountCurrent!=-1) {
			account[accountCurrent].menu();
		}else {
			System.out.println("�߸��� �α����Դϴ�.");
		}
	}
	public void menu() {
		//0.��� ���� ��� 1.ȸ������ 2.�α��� 3.�α׾ƿ� 4.�������� 5.����
		boolean flag=true;
		Scanner sc=new Scanner(System.in);
		while(flag) {
			System.out.println("0.��� ���� ��� 1.ȸ������ 2.�α��� 3.�α׾ƿ� 4.�������� 5.����");
			String inputString=sc.nextLine();
			switch(inputString) {
			case "0":
				displayAll();
				break;
			case "1":
				createAccount();
				displayAll();
				break;
			case "2":
				//�α��� ¥�� ����
			//  1_����ڿ��� id,pw�Է¹ް�
			//  2_��������ڰ� �´��� Ȯ�� 
			//  3_�α����� ����ڰ� ����Ҽ� �ֵ��� ����. => account[] 10���� � account�� ����Ұ��� ����. =>index�̿�
		    //  4.�α��� ������� ���� ���.=> useAccount()
				
				System.out.println("�α���â�Դϴ�.");
				System.out.print("ID:");
				String id=sc.nextLine();
				System.out.print("PASSWORD:");
				String pw=sc.nextLine();
				
				if(logIn(id,pw)) {
					System.out.println("�α��� ����");
					useAccount();
				}else {
					System.out.println("ID Ȥ�� ��й�ȣ�� Ʋ���ϴ�.");
				}
				break;
			case "3":
				logOut();
				break;
			case "4":
				if(accountCurrent!=-1) {
					System.out.print("������ ������ �н����带 �Է��ϼ���:");
					pw=sc.nextLine();
					deleteAccount(pw);
				}else {
					System.out.println("�α��� �� �̿����ּ���.");
				}
				break;
			case "5":
				System.out.println("�����մϴ�.");
				flag=false;
				break;
			default:
				System.out.println("�Է¿���");
				break;
			}
		}
	}
}
class ATM{
	java.util.Scanner sc=new java.util.Scanner(System.in);
	public static int totalCount=0; //atm ����
	public int bankCount=0;
	public Bank[] bank=new Bank[10];
	public int bankCurrentIndex=-1;
	public String atmName="";
	
	
	public ATM() {
		totalCount++;
	}
	public ATM(String atmName) {
		totalCount++;
		this.atmName=atmName;
	}
	public void displayAll() {
		System.out.println("�������:"+ bankCount);
		System.out.println("atm���:"+ ATM.totalCount );
		for(int i=0;i<bankCount;i++) {
			bank[i].displayAll();
		}
	}
	public void addBank() {
		java.util.Scanner sc=new java.util.Scanner(System.in);
		System.out.print("�߰��� ������ �Է�>>");
		String bankstr=sc.nextLine();
		if(BankCompare(bankstr)) {
			System.out.println("�̹� ��ϵ� �����Դϴ�.");
			return;
		}
		System.out.println("�߰� ����");
		bank[bankCount++]=new Bank(bankstr);
		}
	
	
	public boolean BankCompare(String bankstr) {
		boolean returnValue=false;
		for(int i=0;i<bankCount;i++) {
			if(bank[i].bankName.equals(bankstr)) {
				returnValue=true;
				bankCurrentIndex=i;
			}
		}
		return returnValue;	
	}
	public void selectBank() {
		if(bankCurrentIndex!=-1) {
			bank[bankCurrentIndex].menu();
		}else {
			System.out.println("���� �����Դϴ�.");
		}
	}
	public void delete() {
		
		System.out.println("������ �����̸��� �Է��ϼ���");
		String bn=sc.nextLine();
		
		for(int i=0;i<bankCount;i++) {
			if(bank[i].bankName.equals(bn)) {
				bankCurrentIndex=i;
			for(int t=bankCurrentIndex;t+1<bankCount;t++) {
				bank[t]=bank[t+1];
					
			}
			System.out.println("������ �����Ǿ����ϴ�.");
			bankCurrentIndex=-1;
			bankCount--;
			Bank.totalCount--;
		 }
	  } 
  }
}
public class bankSelf {

	public static void main(String[] args) {
		Bank b1=new Bank("����");
		b1.menu();
	}

}

//������� ���α׷�
package com.human.ex;
class Account{                                   //Account : �� 1���� �����ϴ� Ŭ����
	public static int totalCount=0;              //static ����: ��� ������ ���ٰ���,���������ϵ���
	public int money=0;
	public String id=null;
	public String pw=null;
	
	//�ʿ��� �޼ҵ�
	public Account() {
		totalCount++;                            //���� ���鶧���� �߰��ǵ���
	}
	public Account(String id,String pw) {
		this();                                  // (!) �� Account()������ ȣ���� �����Ѵ�. (totalCount++ �����)
		this.id=id;
		this.pw=pw;
	}
	
	//����� �Է��� �޾� id pw �´��� Ȯ���ϴ� �޼ҵ�
	
	public boolean isLogin(String id,String pw) {
		boolean returnValue=false;
		
		if(this.id.equals(id)&&this.pw.equals(pw)) {
			returnValue=true;
		}
		return returnValue;                            //id,pw�� ���� �ߺ��Ǵ� ����� �����ϸ� true�� �����Ѵ�. 
	}
	
	public void display() {
		System.out.println("----------------");
		System.out.println("�� �������� "+Account.totalCount);   // (!) static�̱� ������ 'class��.' �����ֱ�.
	    System.out.println("id�� "+id);
	    System.out.println("�ܰ�� "+money);
	    System.out.println("----------------");
	}
	
	public void deposit(int money) {
		this.money=this.money+money;              //�Ա�
	}
	
	public void withdraw(int money) {
		this.money=this.money-money;              //���
	}
	
	public void menu() {
		java.util.Scanner sc=new java.util.Scanner(System.in);              //����� �Է� �ޱ�
		String inputString = null;
		boolean flag=true;                                                 //while ������ ���� flag�� ����
		
		while(flag) {
			System.out.println(this.id + "��,�޴��� �������ּ��� 1.�Ա� 2.��� 3.����");
			inputString= sc.nextLine();
			switch(inputString) {
			case "1":
				System.out.print("�Ա��� �ݾ��� �Է��ϼ���:");
				int money=Integer.parseInt(sc.nextLine());                //�Է��� string���̹Ƿ� int�� ����ȯ
				deposit(money);
				display();
				break;
			case "2":
				System.out.print("����� �ݾ��� �Է��ϼ���:");
				money=Integer.parseInt(sc.nextLine());
				if(this.money<money) {
					System.out.println("�ܰ� �����մϴ�.");
				}
				else {
				withdraw(money);
				display();
				}
				break;
			case "3":
				System.out.println("�����մϴ�.");
				flag=false;                                              //3 ������ ���̻� �ݺ����� �ʵ��� false�� ������ �ݺ��� �������ش�.
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�. �ʱ�ȭ������ ���ư��ϴ�.");
				break;
			}
		}
	}
}
class Bank{                                           //Bank : �� �������� �����ϴ� Ŭ����. ������ account[]�� ����. 
	public static int totalCount=0;                   //��� ������ �ִ��� 
	public String name = null;                        //�����̸�
	public Account [] account= new Account[10];       //Account �迭 ����
	public int accountCount=0;                        //����� ������ �ִ���
	public int accountCurrent=-1;                     //�ƹ��͵� �α��ε��� ���� ����(-1) , �α��� �Ǹ� �ش� �ε����� �ǰԲ�.
	
	public Bank() {
		totalCount++;
	}
	public Bank(String name) {
		totalCount++;
		this.name=name;
	}
	 
	public void accountAdd() {                                    //�������
		java.util.Scanner sc=new java.util.Scanner(System.in);
		System.out.print("���̵� : ");
		String id=sc.nextLine();
		if(isId(id)) {                                           //���̵� �ߺ����� Ȯ��
			System.out.println("�̹� �����ϴ� id�Դϴ�.");
			return;
		}
		System.out.print("��й�ȣ : ");
		String pw=sc.nextLine();
		account[accountCount++]=new Account(id,pw);   //�����迭�� �־��ֱ�: account[0],account[1],account[2] ���ʷ� �־�� �ϹǷ� accountCount++
	}
	
	private boolean isId(String id) {
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].id.equals(id)) {
				returnValue= true;
			}
		}
		return returnValue;
	}
	public boolean login(String id,String pw) {
		//isLogin(id,pw) �޼ҵ�
		//id,pw�� ���� ����� �����ϸ� true�� �����Ѵ�.->�α��ε�
		
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].isLogin(id,pw)) {
				returnValue= true;
				accountCurrent=i;                //�α��� �Ǿ����Ƿ� ���� ������accountCurrent=-1 ���� �ش��ε����� �ٲ��ش�.(3_�α����� ����ڰ� ����Ҽ� �ֵ��ϼ���.)
				break;                           //�ش��ε��� ã���� ������ �ݺ��ʿ� ��� break ���� for�� �����Ŵ. 
			}
		}                                        //true�̸� �α��� ��, false�̸� �α��� �ȵ�.
		return returnValue;
	}
	
	//�α׾ƿ�
	public void logout() {
		accountCurrent=-1;
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
	}
	
	//�߰��� ���� ��� ����ϴ� �޼ҵ�
	public void displayAll() {
		System.out.println("�� ���� �� :"+ Bank.totalCount);
		System.out.println("���� �̸�: "+ name);
		for(int i=0;i<accountCount;i++) {
			account[i].display();
		}
	}
	public void useAccount() {
		if(accountCurrent!=-1) {
		account[accountCurrent].menu();
		}else {
			System.out.println("�߸��� �α����Դϴ�.");
		}
	}
	
	public void menu(){
		//Bank�� �޴� �޼ҵ尡 �ʿ��ϴ�.
				//1.ȸ������ 2.��� ���� ��� 3.�α��� 4.�α׾ƿ� 5.����
				
	
		java.util.Scanner sc=new java.util.Scanner(System.in);
		boolean flag=true;
		while(flag) {
			System.out.println("1.ȸ������ 2.��� ���� ��� 3.�α��� 4.�α׾ƿ� 5.�������� 6.����");
			String StringInput=sc.nextLine();
			switch(StringInput) {
			case "1":
				accountAdd();
				displayAll();
				break;
			case "2":
				displayAll();
				break;
			case "3":
				
				//3. �α���
				//  1_����ڿ��� id,pw�Է¹ް�
				//  2_��������ڰ� �´��� Ȯ�� 
				//  3_�α����� ����ڰ� ����Ҽ� �ֵ��� ����. => account[] 10���� � account�� ����Ұ��� ����. =>index�̿�
				//  4.�α��� ������� ���� ���.=> useAccount() 
				
				System.out.print("���̵� : ");
				String id=sc.nextLine();
				System.out.print("��й�ȣ : ");
				String pw=sc.nextLine();
				
				if(login(id,pw)) {
					System.out.println("�α��� ����.");
					useAccount();                      //4.�α��� ������� ���� ���.
				}else {
					System.out.println("�α��� ����(id,pw�� ���� �ʽ��ϴ�.)");
				}
				break;
			case "4":
				logout();
				break;
			case "5":
				if(accountCurrent!=-1) {                                 //�α����� �Ǿ��ִٸ� ,
					System.out.println("������ �н����带 �Է��ϼ��� : ");
	//				System.out.print("���̵� : ");
	//				id=sc.nextLine();
					System.out.print("��й�ȣ : ");
					pw=sc.nextLine();
					delete(pw);
				}else {
					System.out.println("�α����� �ʿ��մϴ�.");
				}
				break;
			case "6":
				System.out.println("�����մϴ�.");
				flag=false;
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
			}
		}
}  
	//�α��� �� ���¿����� ���������� �����ϰ� ������.(pw�� �ʿ�)
	public void delete(String pw) {
		delete(account[accountCurrent].id,pw);                     //���� �ε���
		
	}
	public void delete(String id,String pw) {                   //��������_�α��� ���� ����(id,pw�� �ʿ�)
		boolean flag=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].isLogin(id, pw)) {
			accountCurrent=i;                 //ã�´�
			flag=true;
			break;
			}
		}
		if(flag) {          //ã���� ����
			for(int i=accountCurrent;i+1<accountCount;i++) {   //�迭�׷����� [1]�� ������ [2]�� �Ű����� �ȵǹǷ� .
				account[i]=account[i+1];
			}
			Account.totalCount--;                     //Account class������ ���������� ��Ÿ���� ���� -1����.
			accountCount--;                           //Bank class������ ���������� ��Ÿ���� ���� -1����.
			accountCurrent=-1;                        //���þȵǰԲ� -1�� ��������.
			System.out.println("������ �����Ǿ����ϴ�.");
			
		}else {
			System.out.println("���� �����Ͽ����ϴ�.");
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
			if(bank[i].name.equals(bankstr)) {
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
			if(bank[i].name.equals(bn)) {
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
	public void menu() {
		java.util.Scanner sc=new java.util.Scanner(System.in);
		boolean flag=true;
		while(flag) {
		System.out.println("�޴��� �����ϼ��� . 0.��� ���� ���� ��� 1.���� �߰� 2.���� ���� 3.���� ���� 4.����");
		String inputstr=sc.nextLine();
		switch(inputstr) {
		
		case "0":
			displayAll();
			break;
		case "1":
			addBank();
			break;
		case "2":
			selectBank();
			break;
		case "3":
			delete();
			break;
		case "4":
			System.out.println("�����մϴ�.");
			break;
		default:
			System.out.println("�߸��� �Է��Դϴ�.");
			break;
		}
	}
	
	
}
}

public class BankPr {

	public static void main(String[] args) {

//		Bank bk=new Bank("�ѱ�����");
//		bk.menu();
		ATM b1=new ATM();
	    b1.menu();
	
		
			}
		}
		
	

		
		

	

//Ÿ�ڿ���
/*MENU 
 * 0.�α��� 1.�α׾ƿ�2.ó�����ͽ��� 3.���ϴ� �ܰ�� �̵� 4.���� 5. ��������
 *  * �ܰ躰 �迭�� ���� ����
 * �迭 �� ���ҵ� ���Ƿ� ������
 * ����ڰ� ��Ȯ�� �Է��ϸ� ���� �� ����++
 * Ʋ���� ���� �� ���� ���� x
 * 10���� �� ���� �ܰ�� �̵����� ����
 * 
 * */


//�����Ұ� : �α��ι���(������ ȸ�������� ��� �������θ� �α��� �Ǵ°�, ���� ���� �� �������ϱ�

package com.human.ex;
import java.util.Scanner;

class User{
	public static String id;
	public static String pw;
	public static int accountCount=0;
	public static User[] user= new User[10]; 
	
//	public User() {
//		accountCount++;
//	}
	public User(String id,String pw) {
		accountCount++;
		this.id=id;
		this.pw=pw;
	}
}

class TypingGame{
	
	Scanner sc=new Scanner(System.in);
	public static int count=0;
	int pointIndex=0;
	public int accountCount;     
	public int accountCurrent=-1;  
	
	
	public String[][] allStage= {{"����","����ġ","����","��","����","���","ǥ��","�Ϸ�","����","���ͺ�"},
			{"��õ","����","�ϻ�","����ΰ�","�����ϴ�","�����̴�","����","Ȯ��","��","�����ϴ�"},
			{"����","������","����","�����ϴ�","�����Ӵ�","����","Ȱ���ϴ�","����","��","��°"},
			{"��½","������","���ŷӴ�","�ڷ�����","���Ǵ�","����ϴ�","�����ϴ�","����","�ʴ�","����"},
			{"�θ���","��������","������ϴ�","�����ε�","������","���߸���","�Ѹ�ġ��","Ƣ�����","����","��������"}
			};
	
	
	public TypingGame() {}
	
	public boolean isId(String id) {
		boolean returnValue=false;
		for(int i=0;i<User.accountCount;i++) {
			if(User.user[i].id.equals(id)) {
				returnValue=true;
			}
		}
		return returnValue;
	}
	public void register() {
		System.out.println("ȸ������������ �Դϴ�.���Ͻô� ID/PW�� �Է����ּ���.");
		System.out.print("��� ID:");
		Scanner sc=new Scanner(System.in);
		String id=sc.nextLine();
		if(isId(id)) {
			System.out.println("�̹� �����ϴ� id�Դϴ�.");
			return;
		}
		System.out.print("��� PW:");
		String pw=sc.nextLine();
		System.out.println("ȯ���մϴ�." + id+"��,ȸ������ �Ǿ����ϴ�.");
		User.user[accountCount++]=new User(id,pw);
		
	}
	
	public boolean logIn(String id, String pw) {
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			if(User.user[i].id.equals(id)&&User.user[i].pw.equals(pw)) {
				returnValue=true;
				accountCurrent=1;
				break;
			}
		}
		return returnValue;
	}
	public void logOut() {
		accountCurrent=-1;
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
	}
	public void useAccount() {
		if(accountCurrent!=-1) {
			menu();
		}else {
			System.out.println("�߸��� �α����Դϴ�.");
		}
	}
	
	public void gameStart(int input) {
		if(input>=1 && input<=5) {
		System.out.println("\n"+input+"�ܰ� Game Start!");
		System.out.println("--------���ôܾ�--------");	
		for(int j=0;j<allStage[0].length;j++) {
				
				System.out.println(allStage[input-1][j]);
				String type= sc.nextLine();
				if(type.equals(allStage[input-1][j])) {
					System.out.println("����!");
					TypingGame.count++;
				}else {
					System.out.println("����");
				}		
		}
		System.out.println("-----------------------------");
		System.out.println("���� ��!");
		displayPoint();
		
		if(input!=5) {
		System.out.print("����Ͻðڽ��ϱ�? 1: yes 2: no");
		String reply=sc.nextLine();
			switch(reply) {
			case "1":
			gameStart(++input);
			break;
			case "2":
			menu();
			break;
			}
		}else {
		System.out.println("�����մϴ�! �ְ�ܰ���� Ŭ���� �ϼ̽��ϴ�!");
		menu();
		
		}
	 }
	}
	public void displayPoint() {
		System.out.println("-----------------------------");
		System.out.println(User.id+"���� ����: "+TypingGame.count+"��");
		pointIndex++;
	}
	
	//���� ���� �� ranking
	public void ranking() {
	int[] point= new int[10];
	int [] rank = new int[] {1,1,1,1,1,1,1,1,1,1};

	for(int i=0;i<accountCount;i++) {
		point[i]=pointIndex;  //ù° player point = point[0]�� ���� 
	}
	//rank���ϱ�
	for(int i =0;i<rank.length;i++) {
		for(int j=0;j<point.length;j++) {
			if( point[i]< point[j]) {
				rank[i]++;
			}
		}
	}
	//���
	System.out.println("---����---");
	int[] index = new int[rank.length];
	for(int i =0;i<rank.length;i++) {
		index[rank[i]-1] = i;
	}
	for(int i =0;i<rank.length;i++) {
		int t= index[i];
		System.out.println(rank[t] + point[t]);
	}
	}
	
	
	public void displayAll() {
		System.out.println("========================");
		System.out.println("�� ���� �� :"+ accountCount);
		System.out.println("����: "+ count);
		System.out.println("========================");
	}
	public void menu() {
	boolean flag=true;
	while(flag) {
		System.out.println("\n�޴��� �����ϼ��� 0.ȸ������ 1.�α��� 2.�α׾ƿ� 3.1�ܰ���� ���� 4.���ϴ� �ܰ�� �̵�  5.�� �������� 6.���� 7.������ ��� 8.��������");
		String inputString=sc.nextLine();
		switch(inputString) {
		case "0":
			register();
			break;
		case "1":
			System.out.println("�α��� â�Դϴ�.");
			System.out.print("ID:");
			String id=sc.nextLine();
			System.out.print("PW:");
			String pw=sc.nextLine();
			
			if(logIn(id,pw)) {
				System.out.println("�α��� ����!");
				useAccount();
			}else {
				System.out.println("id/pw�� Ʋ���ϴ�.");
			}
			break;
		case "2":
			logOut();
			break;
		case "3":
			gameStart(1);
			break;
		case "4":
			System.out.print("���ϴ� �ܰ踦 �Է��ϼ���(1~5�ܰ�)");
			String input=sc.nextLine();
			switch(input) {
			case "1":
				gameStart(Integer.parseInt(input));
				break;
			case "2":
				gameStart(Integer.parseInt(input));
				break;
			case "3":
				gameStart(Integer.parseInt(input));
				break;
			case "4":
				gameStart(Integer.parseInt(input));
				break;
			case "5":
				gameStart(Integer.parseInt(input));
				break;
				default:
				System.out.println("�߸��� �Է��Դϴ�.(1~5�ܰ� ���� ����)");
				break;
			}
			gameStart(Integer.parseInt(input));
			break;
		case "5":
			displayPoint();
			break;
		case "6":
			System.out.println("�����մϴ�");
			flag=false;
			break;
		case "7":
			displayAll();
			break;
		case "8":
			ranking();
			break;
		default:
			System.out.println("�߸��� �Է��Դϴ�.");
			break;
			}
		}
	}
	
}

public class TypingGame {
	public static void main(String[] args) {
		 TypingGame p1=new TypingGame();
		 p1.menu();
		 

	}

	

}

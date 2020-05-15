//은행관리 프로그램
package com.human.ex;
import java.util.Scanner;
class Account{                   //Account: 고객 1명 관리 하는 클래스 
	public String id;
	public String pw;
	public int money;
	public static int totalCount=0;   //static 쓴 이유:  모든 변수가 접근가능,공유가능하도록
	
	public Account() {
		totalCount++;                //계정 만들때마다 추가되도록
	}
	public Account(String id, String pw) {
		this();                       // (!) 위 Account()계정을 호출해 실행한다. (totalCount++ 실행됨)
		this.id=id;
		this.pw=pw;
	}
	public void deposit(int money) {  //입금
		this.money+=money;
	}
	public void withdraw(int money) { //출금
		this.money-=money;
	}
	public void display() {
		System.out.println("------------------------------------");
		System.out.println("총 계정수는 "+Account.totalCount);   // (!) static이기 때문에 'class명.' 적어주기.
		System.out.println("ID: "+ id);
		System.out.println("잔액: "+ money);
		System.out.println("------------------------------------");
	}
	
	public void menu() {
		Scanner sc=new Scanner(System.in);
		String inputString;
		boolean flag=true;     //while 돌리기 위해 flag값 설정
		
		while(flag) {  
			
			System.out.println(this.id+"님, 메뉴를 선택하세요.\n1.입금 2.출금 3.종료");
			inputString =sc.nextLine();
			switch(inputString) {
			case "1":
				System.out.print("입금할 금액입력:");
				int money=Integer.parseInt(sc.nextLine());    //입력은 string형이므로 int로 형변환
				deposit(money);
				display();
				break;
			case "2":
				System.out.print("출금할 금액입력:");
				money=Integer.parseInt(sc.nextLine());
				if(this.money<money) {
					System.out.println("잔액부족");
				}else {
				    withdraw(money);
				    display();
				}
				;
				break;
			case "3":
				System.out.println("종료합니다.");
				flag=false;                                 //3 누르면 더이상 반복하지 않도록 false로 선언해 반복을 종료해준다.
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
				
			}
		}
		
	}
	
}
class Bank{                             //Bank : 고객 여러명을 관리하는 클래스. 때문에 account[]이 들어간다.
	
	public static int totalCount;       //은행 총갯수
	public int accountCount;            //계정 총갯수
	public Account[] account=new Account[10];  //Account 배열 생성
	public String bankName;
	public int accountCurrent=-1;              //아무것도 로그인되지 않은 상태(-1) , 로그인 되면 해당 인덱스가 되게끔.
	public Bank() {
		totalCount++;
	}
	public Bank(String bankName) {
		this();
		this.bankName=bankName;
	}
	public void displayAll() {
		System.out.println("---------------------------");
		System.out.println("총 은행수: " + totalCount);
		System.out.println("은행 이름: " + bankName);
		System.out.println("---------------------------");
		for(int i=0;i<accountCount;i++) {
			account[i].display();
		}
	}
	public boolean idCompare(String id) {         //아이디 중복여부 확인 메소드
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].id.equals(id)) {
				returnValue=true;
			}
		}
		return returnValue;
	}
	public void createAccount() {                          //회원가입
		Scanner sc=new Scanner(System.in);
		System.out.print("생성하려는 id를 입력하세요:");
		String id=sc.nextLine();
		if(idCompare(id)) {                                //아이디 중복여부 확인
			System.out.println("이미 존재하는 id입니다.");
			return;
		}
		System.out.print("생성하려는 pw를 입력하세요:");
		String pw=sc.nextLine();
		account[accountCount++]=new Account(id,pw);  //계정배열에 넣어주기: account[0],account[1],account[2] 차례로
	}
	public boolean logIn(String id,String pw) {
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].id.equals(id)&&account[i].pw.equals(pw)) {      //입력한 id,pw가 account[]의 그것들과 일치한다면 true
				returnValue=true;        //로그인 됨.
				accountCurrent=i;       //위에 설정한accountCurrent=-1 값을 해당인덱스로 바꿔준다.
				break;                  //해당인덱스 찾으면 끝까지 반복필요 없어서 break 통해 for문 종료시킴. 
			}
		}
		return returnValue;
	}
	public void logOut() {                            //로그아웃
		accountCurrent=-1;
		System.out.println("로그아웃 되었습니다.");
	}
	
	public void deleteAccount(String id, String pw) {   //계정삭제 (로그인 안한 상태)
		boolean flag=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].id.equals(id)&&account[i].pw.equals(pw)) {
				accountCurrent=i;      //찾는다.
				flag=true;
				break;		
			}
		}
		if(flag) {                  //찾으면 삭제
			for(int i=accountCurrent;i<accountCount;i++) {
				account[i]=account[i+1];
			}
			accountCount--;
			Account.totalCount--;
			accountCurrent=-1;                          //선택안되게끔 -1로 설정해줌.
			System.out.println("계정이 삭제되었습니다.");
		}else {
			System.out.println("삭제에 실패했습니다.");
		}
	}
	
	//로그인 한 상태에서도 계정삭제가 가능하게 만들자.(pw만 필요)
	public void deleteAccount(String pw) {
		deleteAccount(account[accountCurrent].id,pw);
	}
	public void useAccount() {
		if(accountCurrent!=-1) {
			account[accountCurrent].menu();
		}else {
			System.out.println("잘못된 로그인입니다.");
		}
	}
	public void menu() {
		//0.모든 계정 출력 1.회원가입 2.로그인 3.로그아웃 4.계정삭제 5.종료
		boolean flag=true;
		Scanner sc=new Scanner(System.in);
		while(flag) {
			System.out.println("0.모든 계정 출력 1.회원가입 2.로그인 3.로그아웃 4.계정삭제 5.종료");
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
				//로그인 짜는 과정
			//  1_사용자에게 id,pw입력받고
			//  2_기존사용자가 맞는지 확인 
			//  3_로그인한 사용자가 사용할수 있도록 설정. => account[] 10개중 어떤 account를 사용할건지 선택. =>index이용
		    //  4.로그인 사용자의 계정 사용.=> useAccount()
				
				System.out.println("로그인창입니다.");
				System.out.print("ID:");
				String id=sc.nextLine();
				System.out.print("PASSWORD:");
				String pw=sc.nextLine();
				
				if(logIn(id,pw)) {
					System.out.println("로그인 성공");
					useAccount();
				}else {
					System.out.println("ID 혹은 비밀번호가 틀립니다.");
				}
				break;
			case "3":
				logOut();
				break;
			case "4":
				if(accountCurrent!=-1) {
					System.out.print("삭제할 계정의 패스워드를 입력하세요:");
					pw=sc.nextLine();
					deleteAccount(pw);
				}else {
					System.out.println("로그인 후 이용해주세요.");
				}
				break;
			case "5":
				System.out.println("종료합니다.");
				flag=false;
				break;
			default:
				System.out.println("입력오류");
				break;
			}
		}
	}
}
class ATM{
	java.util.Scanner sc=new java.util.Scanner(System.in);
	public static int totalCount=0; //atm 개수
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
		System.out.println("총은행수:"+ bankCount);
		System.out.println("atm대수:"+ ATM.totalCount );
		for(int i=0;i<bankCount;i++) {
			bank[i].displayAll();
		}
	}
	public void addBank() {
		java.util.Scanner sc=new java.util.Scanner(System.in);
		System.out.print("추가할 은행을 입력>>");
		String bankstr=sc.nextLine();
		if(BankCompare(bankstr)) {
			System.out.println("이미 등록된 은행입니다.");
			return;
		}
		System.out.println("추가 성공");
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
			System.out.println("선택 오류입니다.");
		}
	}
	public void delete() {
		
		System.out.println("삭제할 은행이름을 입력하세요");
		String bn=sc.nextLine();
		
		for(int i=0;i<bankCount;i++) {
			if(bank[i].bankName.equals(bn)) {
				bankCurrentIndex=i;
			for(int t=bankCurrentIndex;t+1<bankCount;t++) {
				bank[t]=bank[t+1];
					
			}
			System.out.println("은행이 삭제되었습니다.");
			bankCurrentIndex=-1;
			bankCount--;
			Bank.totalCount--;
		 }
	  } 
  }
}
public class bankSelf {

	public static void main(String[] args) {
		Bank b1=new Bank("농협");
		b1.menu();
	}

}

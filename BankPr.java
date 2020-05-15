//은행관리 프로그램
package com.human.ex;
class Account{                                   //Account : 고객 1명을 관리하는 클래스
	public static int totalCount=0;              //static 이유: 모든 변수가 접근가능,공유가능하도록
	public int money=0;
	public String id=null;
	public String pw=null;
	
	//필요한 메소드
	public Account() {
		totalCount++;                            //계정 만들때마다 추가되도록
	}
	public Account(String id,String pw) {
		this();                                  // (!) 위 Account()계정을 호출해 실행한다. (totalCount++ 실행됨)
		this.id=id;
		this.pw=pw;
	}
	
	//사용자 입력을 받아 id pw 맞는지 확인하는 메소드
	
	public boolean isLogin(String id,String pw) {
		boolean returnValue=false;
		
		if(this.id.equals(id)&&this.pw.equals(pw)) {
			returnValue=true;
		}
		return returnValue;                            //id,pw가 서로 중복되는 사람이 존재하면 true를 리턴한다. 
	}
	
	public void display() {
		System.out.println("----------------");
		System.out.println("총 계정수는 "+Account.totalCount);   // (!) static이기 때문에 'class명.' 적어주기.
	    System.out.println("id는 "+id);
	    System.out.println("잔고는 "+money);
	    System.out.println("----------------");
	}
	
	public void deposit(int money) {
		this.money=this.money+money;              //입금
	}
	
	public void withdraw(int money) {
		this.money=this.money-money;              //출금
	}
	
	public void menu() {
		java.util.Scanner sc=new java.util.Scanner(System.in);              //사용자 입력 받기
		String inputString = null;
		boolean flag=true;                                                 //while 돌리기 위해 flag값 설정
		
		while(flag) {
			System.out.println(this.id + "님,메뉴를 선택해주세요 1.입금 2.출금 3.종료");
			inputString= sc.nextLine();
			switch(inputString) {
			case "1":
				System.out.print("입금할 금액을 입력하세요:");
				int money=Integer.parseInt(sc.nextLine());                //입력은 string형이므로 int로 형변환
				deposit(money);
				display();
				break;
			case "2":
				System.out.print("출금할 금액을 입력하세요:");
				money=Integer.parseInt(sc.nextLine());
				if(this.money<money) {
					System.out.println("잔고가 부족합니다.");
				}
				else {
				withdraw(money);
				display();
				}
				break;
			case "3":
				System.out.println("종료합니다.");
				flag=false;                                              //3 누르면 더이상 반복하지 않도록 false로 선언해 반복을 종료해준다.
				break;
			default:
				System.out.println("잘못된 입력입니다. 초기화면으로 돌아갑니다.");
				break;
			}
		}
	}
}
class Bank{                                           //Bank : 고객 여러명을 관리하는 클래스. 때문에 account[]이 들어간다. 
	public static int totalCount=0;                   //몇개의 은행이 있는지 
	public String name = null;                        //은행이름
	public Account [] account= new Account[10];       //Account 배열 생성
	public int accountCount=0;                        //몇명의 고객계정 있는지
	public int accountCurrent=-1;                     //아무것도 로그인되지 않은 상태(-1) , 로그인 되면 해당 인덱스가 되게끔.
	
	public Bank() {
		totalCount++;
	}
	public Bank(String name) {
		totalCount++;
		this.name=name;
	}
	 
	public void accountAdd() {                                    //계정등록
		java.util.Scanner sc=new java.util.Scanner(System.in);
		System.out.print("아이디 : ");
		String id=sc.nextLine();
		if(isId(id)) {                                           //아이디 중복여부 확인
			System.out.println("이미 존재하는 id입니다.");
			return;
		}
		System.out.print("비밀번호 : ");
		String pw=sc.nextLine();
		account[accountCount++]=new Account(id,pw);   //계정배열에 넣어주기: account[0],account[1],account[2] 차례로 넣어야 하므로 accountCount++
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
		//isLogin(id,pw) 메소드
		//id,pw가 같은 사람이 존재하면 true를 리턴한다.->로그인됨
		
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].isLogin(id,pw)) {
				returnValue= true;
				accountCurrent=i;                //로그인 되었으므로 위에 설정한accountCurrent=-1 값을 해당인덱스로 바꿔준다.(3_로그인한 사용자가 사용할수 있도록설정.)
				break;                           //해당인덱스 찾으면 끝까지 반복필요 없어서 break 통해 for문 종료시킴. 
			}
		}                                        //true이면 로그인 됨, false이면 로그인 안됨.
		return returnValue;
	}
	
	//로그아웃
	public void logout() {
		accountCurrent=-1;
		System.out.println("로그아웃 되었습니다.");
	}
	
	//추가된 계정 모두 출력하는 메소드
	public void displayAll() {
		System.out.println("총 은행 수 :"+ Bank.totalCount);
		System.out.println("은행 이름: "+ name);
		for(int i=0;i<accountCount;i++) {
			account[i].display();
		}
	}
	public void useAccount() {
		if(accountCurrent!=-1) {
		account[accountCurrent].menu();
		}else {
			System.out.println("잘못된 로그인입니다.");
		}
	}
	
	public void menu(){
		//Bank의 메뉴 메소드가 필요하다.
				//1.회원가입 2.모든 계정 출력 3.로그인 4.로그아웃 5.종료
				
	
		java.util.Scanner sc=new java.util.Scanner(System.in);
		boolean flag=true;
		while(flag) {
			System.out.println("1.회원가입 2.모든 계정 출력 3.로그인 4.로그아웃 5.계정삭제 6.종료");
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
				
				//3. 로그인
				//  1_사용자에게 id,pw입력받고
				//  2_기존사용자가 맞는지 확인 
				//  3_로그인한 사용자가 사용할수 있도록 설정. => account[] 10개중 어떤 account를 사용할건지 선택. =>index이용
				//  4.로그인 사용자의 계정 사용.=> useAccount() 
				
				System.out.print("아이디 : ");
				String id=sc.nextLine();
				System.out.print("비밀번호 : ");
				String pw=sc.nextLine();
				
				if(login(id,pw)) {
					System.out.println("로그인 성공.");
					useAccount();                      //4.로그인 사용자의 계정 사용.
				}else {
					System.out.println("로그인 실패(id,pw가 맞지 않습니다.)");
				}
				break;
			case "4":
				logout();
				break;
			case "5":
				if(accountCurrent!=-1) {                                 //로그인이 되어있다면 ,
					System.out.println("삭제할 패스워드를 입력하세요 : ");
	//				System.out.print("아이디 : ");
	//				id=sc.nextLine();
					System.out.print("비밀번호 : ");
					pw=sc.nextLine();
					delete(pw);
				}else {
					System.out.println("로그인이 필요합니다.");
				}
				break;
			case "6":
				System.out.println("종료합니다.");
				flag=false;
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}
}  
	//로그인 한 상태에서도 계정삭제가 가능하게 만들어보자.(pw만 필요)
	public void delete(String pw) {
		delete(account[accountCurrent].id,pw);                     //현재 인덱스
		
	}
	public void delete(String id,String pw) {                   //계정삭제_로그인 안한 상태(id,pw다 필요)
		boolean flag=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].isLogin(id, pw)) {
			accountCurrent=i;                 //찾는다
			flag=true;
			break;
			}
		}
		if(flag) {          //찾으면 삭제
			for(int i=accountCurrent;i+1<accountCount;i++) {   //배열그렸을때 [1]은 되지만 [2]는 옮겨지면 안되므로 .
				account[i]=account[i+1];
			}
			Account.totalCount--;                     //Account class에서의 계정개수를 나타내는 변수 -1해줌.
			accountCount--;                           //Bank class에서의 계정개수를 나타내는 변수 -1해줌.
			accountCurrent=-1;                        //선택안되게끔 -1로 설정해줌.
			System.out.println("계정이 삭제되었습니다.");
			
		}else {
			System.out.println("삭제 실패하였습니다.");
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
			System.out.println("선택 오류입니다.");
		}
	}
	public void delete() {
		
		System.out.println("삭제할 은행이름을 입력하세요");
		String bn=sc.nextLine();
		
		for(int i=0;i<bankCount;i++) {
			if(bank[i].name.equals(bn)) {
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
	public void menu() {
		java.util.Scanner sc=new java.util.Scanner(System.in);
		boolean flag=true;
		while(flag) {
		System.out.println("메뉴를 선택하세요 . 0.모든 은행 정보 출력 1.은행 추가 2.은행 선택 3.은행 삭제 4.종료");
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
			System.out.println("종료합니다.");
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			break;
		}
	}
	
	
}
}

public class BankPr {

	public static void main(String[] args) {

//		Bank bk=new Bank("한국은행");
//		bk.menu();
		ATM b1=new ATM();
	    b1.menu();
	
		
			}
		}
		
	

		
		

	

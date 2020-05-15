//타자연습
/*MENU 
 * 0.로그인 1.로그아웃2.처음부터시작 3.원하는 단계로 이동 4.종료 5. 점수보기
 *  * 단계별 배열에 글자 저장
 * 배열 속 원소들 임의로 보여줌
 * 사용자가 정확히 입력하면 정답 후 점수++
 * 틀리면 오답 후 점수 변동 x
 * 10문제 후 다음 단계로 이동여부 묻기
 * 
 * */


//보충할것 : 로그인문제(마지막 회원가입한 사람 계정으로만 로그인 되는것, 점수 저장 후 순위정하기

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
	
	
	public String[][] allStage= {{"가게","스위치","만남","돈","뉴스","기온","표면","하루","피자","인터뷰"},
			{"인천","공항","일상","산부인과","간편하다","덧붙이다","벌금","확인","꽃","딱딱하다"},
			{"베개","수요일","안팎","불편하다","자유롭다","통일","활발하다","뜻대로","뺨","번째"},
			{"슬쩍","낯설다","번거롭다","텔레비젼","향상되다","깔끔하다","마뜩하다","힘껏","않다","자율"},
			{"싸릿눈","붙잡히다","떠들썩하다","따따부따","앉히다","깨뜨리다","뿌리치다","튀어나오다","벌써","수도꼭지"}
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
		System.out.println("회원가입페이지 입니다.원하시는 ID/PW를 입력해주세요.");
		System.out.print("희망 ID:");
		Scanner sc=new Scanner(System.in);
		String id=sc.nextLine();
		if(isId(id)) {
			System.out.println("이미 존재하는 id입니다.");
			return;
		}
		System.out.print("희망 PW:");
		String pw=sc.nextLine();
		System.out.println("환영합니다." + id+"님,회원가입 되었습니다.");
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
		System.out.println("로그아웃 되었습니다.");
	}
	public void useAccount() {
		if(accountCurrent!=-1) {
			menu();
		}else {
			System.out.println("잘못된 로그인입니다.");
		}
	}
	
	public void gameStart(int input) {
		if(input>=1 && input<=5) {
		System.out.println("\n"+input+"단계 Game Start!");
		System.out.println("--------제시단어--------");	
		for(int j=0;j<allStage[0].length;j++) {
				
				System.out.println(allStage[input-1][j]);
				String type= sc.nextLine();
				if(type.equals(allStage[input-1][j])) {
					System.out.println("정답!");
					TypingGame.count++;
				}else {
					System.out.println("오답");
				}		
		}
		System.out.println("-----------------------------");
		System.out.println("게임 끝!");
		displayPoint();
		
		if(input!=5) {
		System.out.print("계속하시겠습니까? 1: yes 2: no");
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
		System.out.println("축하합니다! 최고단계까지 클리어 하셨습니다!");
		menu();
		
		}
	 }
	}
	public void displayPoint() {
		System.out.println("-----------------------------");
		System.out.println(User.id+"님의 점수: "+TypingGame.count+"점");
		pointIndex++;
	}
	
	//점수 저장 후 ranking
	public void ranking() {
	int[] point= new int[10];
	int [] rank = new int[] {1,1,1,1,1,1,1,1,1,1};

	for(int i=0;i<accountCount;i++) {
		point[i]=pointIndex;  //첫째 player point = point[0]에 저장 
	}
	//rank구하기
	for(int i =0;i<rank.length;i++) {
		for(int j=0;j<point.length;j++) {
			if( point[i]< point[j]) {
				rank[i]++;
			}
		}
	}
	//출력
	System.out.println("---순위---");
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
		System.out.println("총 계정 수 :"+ accountCount);
		System.out.println("점수: "+ count);
		System.out.println("========================");
	}
	public void menu() {
	boolean flag=true;
	while(flag) {
		System.out.println("\n메뉴를 선택하세요 0.회원가입 1.로그인 2.로그아웃 3.1단계부터 시작 4.원하는 단계로 이동  5.내 점수보기 6.종료 7.모든계정 출력 8.순위보기");
		String inputString=sc.nextLine();
		switch(inputString) {
		case "0":
			register();
			break;
		case "1":
			System.out.println("로그인 창입니다.");
			System.out.print("ID:");
			String id=sc.nextLine();
			System.out.print("PW:");
			String pw=sc.nextLine();
			
			if(logIn(id,pw)) {
				System.out.println("로그인 성공!");
				useAccount();
			}else {
				System.out.println("id/pw가 틀립니다.");
			}
			break;
		case "2":
			logOut();
			break;
		case "3":
			gameStart(1);
			break;
		case "4":
			System.out.print("원하는 단계를 입력하세요(1~5단계)");
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
				System.out.println("잘못된 입력입니다.(1~5단계 까지 가능)");
				break;
			}
			gameStart(Integer.parseInt(input));
			break;
		case "5":
			displayPoint();
			break;
		case "6":
			System.out.println("종료합니다");
			flag=false;
			break;
		case "7":
			displayAll();
			break;
		case "8":
			ranking();
			break;
		default:
			System.out.println("잘못된 입력입니다.");
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

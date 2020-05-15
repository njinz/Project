package com.human.play;
import java.util.Scanner;

import com.human.character.Character;
import com.human.character.Firee;
import com.human.character.Kkobuk;
import com.human.character.Pikachu;

public class PlayGame {
	private Character character;
	private boolean flag=true;
	
	
	public void play(Character c) {
		character=c;
		Scanner sc=new Scanner(System.in);
		
		while(flag) {
			character.printInfo();
			System.out.println("1.밥 먹이기 2.잠 재우기 3.놀아주기 4.운동 5.종료");
			System.out.print("입력>>");
			String select=sc.nextLine();
			switch(select) {
			case "1":
				character.eat();
				break;
			case "2":
				character.sleep();
				break;
			case "3":
				character.play();
				break;
			case "4":
				character.train();
				break;
			case "5":
				flag=false;
				System.out.println("종료합니다.");
				break;
			default:
				System.out.println("잘못된 숫자 입력입니다.");
				break;
			}
			character.levelUp();
			if(flag) {  //true 일때
				flag=character.endGame();
			}
		}
		
	}
}

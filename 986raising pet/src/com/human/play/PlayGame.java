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
			System.out.println("1.�� ���̱� 2.�� ���� 3.����ֱ� 4.� 5.����");
			System.out.print("�Է�>>");
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
				System.out.println("�����մϴ�.");
				break;
			default:
				System.out.println("�߸��� ���� �Է��Դϴ�.");
				break;
			}
			character.levelUp();
			if(flag) {  //true �϶�
				flag=character.endGame();
			}
		}
		
	}
}

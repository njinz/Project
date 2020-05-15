//'캐릭터 생성'에 관한것만을 모아두는 클래스
package com.human.play;

import java.util.Scanner;

import com.human.character.Firee;
import com.human.character.Kkobuk;
import com.human.character.Pikachu;
import com.human.character.Character;

public class MakeCharacter {
	
	public Character selectCharacter() {
		Character returnValue=null;
		Scanner sc=new Scanner(System.in);
		System.out.println("캐릭터를 입력하세요.");
		System.out.println("1.피카추 2.꼬부기 3.파이리 ");
		String input=sc.nextLine();
		switch(input) {
		case "1":
			returnValue=new Pikachu();  //마우스 Hover해서 : import 해주기 (패키지가 다르므로)
			break;
		case "2":
			returnValue=new Kkobuk();
			break;
		case "3":
			returnValue=new Firee();
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			break;
		}
		return returnValue;
	}
}

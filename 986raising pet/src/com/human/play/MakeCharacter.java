//'ĳ���� ����'�� ���Ѱ͸��� ��Ƶδ� Ŭ����
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
		System.out.println("ĳ���͸� �Է��ϼ���.");
		System.out.println("1.��ī�� 2.���α� 3.���̸� ");
		String input=sc.nextLine();
		switch(input) {
		case "1":
			returnValue=new Pikachu();  //���콺 Hover�ؼ� : import ���ֱ� (��Ű���� �ٸ��Ƿ�)
			break;
		case "2":
			returnValue=new Kkobuk();
			break;
		case "3":
			returnValue=new Firee();
			break;
		default:
			System.out.println("�߸��� �Է��Դϴ�.");
			break;
		}
		return returnValue;
	}
}

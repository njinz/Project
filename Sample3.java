package com.human.ex;
class ProductManager{
	public static java.util.Scanner sc=new java.util.Scanner(System.in);
	public int arrIndex=0;
	public Product[] arrProduct=new Product[10];
	
	public ProductManager() {}
	//0.�������α׷�
	public void play() {
		System.out.println("��ǰ ���� ���α׷� ����");
		int menu=-1;
		for(;menu!=0;) {
			System.out.println("�޴��� �������ּ���.\n1.��ǰ���\t2.��ǰ�԰�\t3.��ǰ���\t4.��ǰ�˻�\t5.��ǰ������\n0.����");
			menu=Integer.parseInt(sc.nextLine());
			switch(menu){
			case 1:registProduct();break;
			case 2:inputProduct();break;
			case 3:outputProduct();break;
			case 4:System.out.println("�˻��� ��ǰ���� �Է����ּ���.");searchProduct(sc.nextLine());break;
			case 5:displayProduct();
			case 0:System.out.println("�ý����� �����մϴ�.");break;
			}
			
		}
	}
	public void registTestData() {
		Product p1=new Product("�����",100);
		registProduct(p1);
		Product p2=new Product("����",100);
		registProduct(p2);
		Product p3=new Product("���찳",90);
		registProduct(p3);
		Product p4=new Product("���찳",70);
		registProduct(p4);
		Product p5=new Product("���찳",90);
		registProduct(p5);
	}
	//1.��ǰ���
	public void registProduct(Product p) {
		arrProduct[arrIndex++]=p;
	}
	public void registProduct() {
		System.out.println("��ǰ�� ��� ������");
		System.out.println("��ǰ��>>");
		String name=sc.nextLine();
		System.out.println("��ϰ���>>");
		int count=Integer.parseInt(sc.nextLine());
		arrProduct[arrIndex++]=new Product(name,count);
	}
	//2.�԰�
	public void inputProduct() {
		System.out.println("�԰��� ��ǰ �Է�");
		String name=sc.nextLine();
		System.out.println("�԰��� ��ǰ ���� �Է�");
		int count=Integer.parseInt(sc.nextLine());
		Product p=searchProduct(name);
		if(p==null) {
			System.out.println("���� ��ǰ�Դϴ�.");
		}else {
			p.count=p.count+count;
		}
	}
	//3.���
	public void outputProduct() {
		System.out.println("����� ��ǰ �Է�");
		String name=sc.nextLine();
		System.out.println("����� ��ǰ ���� �Է�");
		int count=Integer.parseInt(sc.nextLine());
		Product p=searchProduct(name);
		if(p==null) {
			System.out.println("���� ��ǰ�Դϴ�.");
		}else {
			p.count=p.count-count;
		}
	}
	//4.��ǰ�˻�
	public Product searchProduct(String name) {
		Product returnValue=null;
		for(int i=0;i<arrIndex;i++) {
			if(arrProduct[i].name.equals(name)) {
				returnValue=arrProduct[i];
				break;
			}
		}
		return returnValue;
	}
	//5.������
	public void displayProduct() {
		for(int i=0;i<Product.totalCount;i++) {
			arrProduct[i].display();
		}
	}
}
public class Sample3 {
	public static void main(String[] args) {
		ProductManager pm=new ProductManager();
		pm.play();
		
	}

}

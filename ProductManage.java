package com.human.ex;


	//����� ��ǰ����, ��ǰ��, ������,�԰�,���,�������� 
	//productManager:Ŭ���� ��ǰ���� �����ϴ� ���α׷� �� ������

	class Product{
		public static int totalCount=0; //����� ��ǰ������
		public String name="";	//��ǰ��
		public int count=0;	//������

		public Product() {
			Product.totalCount++;   //������ �����ϴ� product class. product()2���� �����ϸ� ���� 2���� �����.
		}
		public Product(String name) {
			this(name,0);
		}
		public Product(String name, int count) {
			this(); //this()�� ȣ���ϸ� line 10_Product()�� ���� �������� �ٽ� line 18�� ����.
			this.name=name;
			this.count=count;
		}
		//�޼ҵ� �����(�԰�,���,�����)
		public void add(int count) {
			this.count=this.count+count;
		}
		public void min(int count) {
			this.count=this.count-count;
		}
		public void display() {
			System.out.println("��ü ��ǰ ���� ����:"+Product.totalCount);
			System.out.println("��ǰ �̸�:"+name);
			System.out.println("��ǰ ����:"+count);
		}
	}
	public class ProductManage {

		public static void main(String[] args) {
			Product p1=new Product("�����",100);
			Product p2=new Product("����",100);
			Product p3=new Product("���찳",100);
//			Product p4=new Product();
//			Product p5=new Product();
			
			p1.min(4);    //����� 4�� �Ǹ�
			p3.add(10);   //���찳 10�� �԰�
			
			p1.display();
			p2.display();
			p3.display();
			
			
			Product[]arr=new Product[5];
			arr[0]=p1;
			arr[1]=p2; 
			arr[2]=p3;
			arr[3]=new Product("��Ʈ",10);
			
			
			//������� �Է��� �޾� �ش� ǰ�񳻿��� ȭ�鿡 ����غ���.
			
			System.out.println(" ------����� �Է¹޾� �ش� ��ǰ�� ���̱�-------");
			String input="����";
			
			for(Product p:arr) {
				if(p!=null) {
					if(p.name.equals(input)) {
						p.display();
					}
				}
			}
			//��� 20������ ��ǰ�� ����غ���. 
			System.out.println("---------��� 101�������� ��ǰ----------");
//			for(Product p:arr) {
//				if(p!=null)
//				if(p.count<=101) {
//					p.display();
//				}
//			}
			int number=101;
			for(int i=0;i<Product.totalCount;i++) {
				if(arr[i].count<number){
					arr[i].display();
				}
			}
			
		//��ǰ�������α׷�
		//1.��ǰ��� 2.��ǰ�԰� 3.��ǰ��� 4 ��ǰ�˻� 5.��ǰ ��� Ȯ��
			
			
			
			
		}

	}


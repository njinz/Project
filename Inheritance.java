package com.human.ex;

 	class Customer{
		protected int customerID=0;
		protected String customerName="";
		public String customerGrade="";
		public int bonusPoint=0;
		public int bonusRatio=0;
		
		public Customer() {
			this.customerGrade="SILVER";
			this.bonusRatio=1;
		}
		public int calcPrice(Customer c,Product p) {
			c.bonusPoint+=p.price*c.bonusRatio/100;
			int totalPrice=p.price;
			return totalPrice;
		}
		public void showCustomerInfor() {
			System.out.println("��ID:"+customerID);
			System.out.println("�� �̸�:"+customerName);
			System.out.println("�����:"+customerGrade);
			System.out.println("���ʽ�����Ʈ:"+bonusPoint);
		}
		public int getCustomerID() {
			return customerID;
		}
		public void setCustomerID(int customerID) {
			this.customerID=customerID;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName=customerName;
		}
	}


	//VIP customer
	class VIPcustomer extends Customer{
		public int agentID;
		public int salesRatio;
		
		public VIPcustomer() {
			this.customerGrade="VIP";
			this.bonusRatio=5;
			this.salesRatio=10;
		}
		@Override
		public int calcPrice(Customer c,Product p) {
			c.bonusPoint+=p.price*c.bonusRatio/100;
			int totalPrice=p.price*(100-this.salesRatio)/100;
			return totalPrice;
		}
	}
	//Gold customer
	class GoldCustomer extends Customer{
		public int salesRatio;
		
		public GoldCustomer() {
			this.customerGrade="GOLD";
			this.bonusRatio=3;
			this.salesRatio=5;
		}
		@Override
		public int calcPrice(Customer c,Product p) {
			c.bonusPoint+=p.price*c.bonusRatio/100;
			int totalPrice=p.price*(100-this.salesRatio)/100;
			return totalPrice;
		}
	}
	//product
	class Product{
		public String name;
		public int price;
		
		public Product() {}
		public Product(String name,int price) {
			this.name=name;
			this.price=price;
		}
	}
	class TV extends Product{
		public TV() {}
		public TV(String name,int price) {
			super(name,price);
		}
	}
	class Hoover extends Product{
		public Hoover() {}
		public Hoover(String name,int price) {
			super(name,price);
		}
	}
	class Phone extends Product{
		public Phone() {}
		public Phone(String name,int price) {
			super(name,price);
		}
	}

	public class Inheritance {

		public static void main(String[] args) {
		Customer customerLee=new Customer();
		customerLee.setCustomerID(10010);
		customerLee.setCustomerName("�̼���");
		customerLee.bonusPoint=1000;
		
		VIPcustomer customerKim=new VIPcustomer();
		customerKim.setCustomerID(10020);
		customerKim.setCustomerName("������");
		customerKim.bonusPoint=10000;
		
		GoldCustomer customerPark=new GoldCustomer();
		customerPark.setCustomerID(10030);
		customerPark.setCustomerName("�ڹ���");
		customerPark.bonusPoint=5000;
		
		Customer[] cArr=new Customer[3];
		cArr[0]=customerLee;
		cArr[1]=customerKim;
		cArr[2]=customerPark;
		
		Product[] pArr=new Product[3];
		pArr[0]=new TV("LG-1",1000000);
		pArr[1]=new Hoover("Daison",200000);
		pArr[2]=new Phone("Samsung-1",3000000);
		
		for(Customer c:cArr) {
			System.out.print(c.customerName+"���� �����"+c.customerGrade+"�̸�,");
		for(Product p:pArr) {
			System.out.print(p.name+"�� ������"+c.calcPrice(c, p)+"�Դϴ�.");
		}
		System.out.println(c.customerName+"���� ���ʽ�����Ʈ��"+c.bonusPoint+"�Դϴ�");
		}
		}

}

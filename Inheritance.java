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
			System.out.println("고객ID:"+customerID);
			System.out.println("고객 이름:"+customerName);
			System.out.println("고객등금:"+customerGrade);
			System.out.println("보너스포인트:"+bonusPoint);
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
		customerLee.setCustomerName("이순신");
		customerLee.bonusPoint=1000;
		
		VIPcustomer customerKim=new VIPcustomer();
		customerKim.setCustomerID(10020);
		customerKim.setCustomerName("김유신");
		customerKim.bonusPoint=10000;
		
		GoldCustomer customerPark=new GoldCustomer();
		customerPark.setCustomerID(10030);
		customerPark.setCustomerName("박문수");
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
			System.out.print(c.customerName+"님의 등급은"+c.customerGrade+"이며,");
		for(Product p:pArr) {
			System.out.print(p.name+"의 가격은"+c.calcPrice(c, p)+"입니다.");
		}
		System.out.println(c.customerName+"님의 보너스포인트는"+c.bonusPoint+"입니다");
		}
		}

}

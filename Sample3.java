package com.human.ex;
class ProductManager{
	public static java.util.Scanner sc=new java.util.Scanner(System.in);
	public int arrIndex=0;
	public Product[] arrProduct=new Product[10];
	
	public ProductManager() {}
	//0.실행프로그램
	public void play() {
		System.out.println("상품 관리 프로그램 시작");
		int menu=-1;
		for(;menu!=0;) {
			System.out.println("메뉴를 선택해주세요.\n1.상품등록\t2.상품입고\t3.상품출고\t4.상품검색\t5.상품재고관리\n0.종료");
			menu=Integer.parseInt(sc.nextLine());
			switch(menu){
			case 1:registProduct();break;
			case 2:inputProduct();break;
			case 3:outputProduct();break;
			case 4:System.out.println("검색할 상품명을 입력해주세요.");searchProduct(sc.nextLine());break;
			case 5:displayProduct();
			case 0:System.out.println("시스템을 종료합니다.");break;
			}
			
		}
	}
	public void registTestData() {
		Product p1=new Product("새우깡",100);
		registProduct(p1);
		Product p2=new Product("연필",100);
		registProduct(p2);
		Product p3=new Product("지우개",90);
		registProduct(p3);
		Product p4=new Product("지우개",70);
		registProduct(p4);
		Product p5=new Product("지우개",90);
		registProduct(p5);
	}
	//1.상품등록
	public void registProduct(Product p) {
		arrProduct[arrIndex++]=p;
	}
	public void registProduct() {
		System.out.println("상품명 등록 페이지");
		System.out.println("상품명>>");
		String name=sc.nextLine();
		System.out.println("등록개수>>");
		int count=Integer.parseInt(sc.nextLine());
		arrProduct[arrIndex++]=new Product(name,count);
	}
	//2.입고
	public void inputProduct() {
		System.out.println("입고할 상품 입력");
		String name=sc.nextLine();
		System.out.println("입고할 상품 개수 입력");
		int count=Integer.parseInt(sc.nextLine());
		Product p=searchProduct(name);
		if(p==null) {
			System.out.println("없는 상품입니다.");
		}else {
			p.count=p.count+count;
		}
	}
	//3.출고
	public void outputProduct() {
		System.out.println("출고할 상품 입력");
		String name=sc.nextLine();
		System.out.println("출고할 상품 개수 입력");
		int count=Integer.parseInt(sc.nextLine());
		Product p=searchProduct(name);
		if(p==null) {
			System.out.println("없는 상품입니다.");
		}else {
			p.count=p.count-count;
		}
	}
	//4.상품검색
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
	//5.재고관리
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

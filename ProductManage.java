package com.human.ex;


	//등록한 상품개수, 상품명, 재고수량,입고,출고,재고내용출력 
	//productManager:클래스 상품들을 관리하는 프로그램 을 만들어보자

	class Product{
		public static int totalCount=0; //등록한 상품종류수
		public String name="";	//상품명
		public int count=0;	//재고수량

		public Product() {
			Product.totalCount++;   //물건을 저장하는 product class. product()2개를 생성하면 물건 2개가 저장됨.
		}
		public Product(String name) {
			this(name,0);
		}
		public Product(String name, int count) {
			this(); //this()를 호출하면 line 10_Product()로 가서 실행한후 다시 line 18로 간다.
			this.name=name;
			this.count=count;
		}
		//메소드 만들기(입고,출고,재고내용)
		public void add(int count) {
			this.count=this.count+count;
		}
		public void min(int count) {
			this.count=this.count-count;
		}
		public void display() {
			System.out.println("전체 상품 종류 개수:"+Product.totalCount);
			System.out.println("상품 이름:"+name);
			System.out.println("상품 개수:"+count);
		}
	}
	public class ProductManage {

		public static void main(String[] args) {
			Product p1=new Product("새우깡",100);
			Product p2=new Product("연필",100);
			Product p3=new Product("지우개",100);
//			Product p4=new Product();
//			Product p5=new Product();
			
			p1.min(4);    //새우깡 4개 판매
			p3.add(10);   //지우개 10개 입고
			
			p1.display();
			p2.display();
			p3.display();
			
			
			Product[]arr=new Product[5];
			arr[0]=p1;
			arr[1]=p2; 
			arr[2]=p3;
			arr[3]=new Product("노트",10);
			
			
			//사용자의 입력을 받아 해당 품목내용을 화면에 출력해보자.
			
			System.out.println(" ------사용자 입력받아 해당 상품을 보이기-------");
			String input="연필";
			
			for(Product p:arr) {
				if(p!=null) {
					if(p.name.equals(input)) {
						p.display();
					}
				}
			}
			//재고가 20이하인 제품을 출력해보자. 
			System.out.println("---------재고가 101개이하인 제품----------");
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
			
		//상품관리프로그램
		//1.상품등록 2.상품입고 3.상품출고 4 상품검색 5.상품 재고 확인
			
			
			
			
		}

	}


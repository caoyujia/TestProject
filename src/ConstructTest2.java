
public class ConstructTest2 extends ConstructTest{
	ConstructTest2(){
		this("1");
		System.out.println("this is test 2");
	}
	ConstructTest2(String a){
		this();
		System.out.println("this is test 3");
	}

	@Override
	public String toString() {
		return "ConstructTest2 [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	public static void main(String[] arg){
		ConstructTest2 constructTest2= new ConstructTest2(); 
	}
}

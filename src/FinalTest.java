import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalTest {

	public String aaa;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final StringBuffer s= new StringBuffer();
		final int a=2;
		String m="456";
		s.append("45");
		System.out.println(s);
		if(1==1){
			String ddd="3232";
		}
		int num = a >> 1; 
		int num2 = a << 3; 
		System.out.println(num);
		System.out.println(num2);
		
		
		String [] s1={"1","2"};
		String [] s2=new String[s1.length];
		
		Table [] t ;
//		t.
		
		System.arraycopy (s1,0,s2,0,s1.length);
		System.out.println(Arrays.toString(s2));
		System.out.println(s2.length);
		
		Object o ;
		String am;
		Son[] son = new Son[3];
		Father[] father = son;
		
		Son ss=new Son();
		Father ff=new Father();
		
		System.out.println(son.getClass().getName());
		System.out.println(son.getClass().getSuperclass().getName());
		
		System.out.println(ss.getClass().getSuperclass().getName());
		System.out.println(ff);
		
		List<Son> sonlist= new ArrayList<Son>(10);
		List<Son> sonlist2= new ArrayList<Son>();
		int i=0;
		System.out.println(sonlist.size());
		for (int j = 0; j < 10; j++) {
			sonlist.add(new Son());
		}
		System.out.println(sonlist.size());
		for(Son sons : sonlist){
			sons.a=String.valueOf(i);
			i++;
		}
		for (Son son11 : sonlist) {
			System.out.println(son11.toString());
		}
		Son sss = null;
		for (int j = 0; j < sonlist.size(); j++) {
			sss=sonlist.get(j);
			sonlist2.add(sss);
		}
		for (Son son11 : sonlist2) {
			System.out.println(son11.toString());
		}
		
		Son dd= new Son<Table>();
		Son mm= new Son();
		System.out.println(dd.getClass().getName());
		System.out.println(mm.getClass().getName());
		System.out.println(dd.getClass().getSuperclass().getName());
		System.out.println(mm.getClass().getSuperclass().getName());
		
		
		int[] a1 = { 1, 2, 3, 4, 5 };
	    int[] a2;
	    a2 = a1;
	    for(int j = 0; j < a2.length; j++)
	      a2[j] = a2[j] + 1;
	    a2=null;
	    for(int j = 0; j < a1.length; j++)
	    	System.out.println("a1[" + j + "] = " + a1[j]);
	    
	    Son aaa=new Son();
	    System.out.println(aaa.a);
	    Father bbb = (Father)aaa;
	    System.out.println(bbb.a);

	}

}

package Unitn.sectest;


import org.junit.*;
import net.sourceforge.jwebunit.junit.*;
  
 
   

        
 
public class First {
	 private WebTester test ;
@Before
	
	public void prepare(){
        test = new WebTester() ; 
        test.setBaseUrl("http://127.0.0.1/schoolmate/");
        
}
@Test 
public void test(){
	test.beginAt("/index.php");
	test.setTextField("username", "schoolmate");
	test.setTextField("password", "schoolmate");
	test.submit();
	//test.assertTitleEquals("joel");
	
	
	
	
	
}	

	
}





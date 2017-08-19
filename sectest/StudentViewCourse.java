package Unitn.sectest;

 import java.awt.Button;
import org.junit.*;
import org.w3c.dom.Document;
import org.xml.sax.helpers.AttributesImpl;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;
import com.gargoylesoftware.htmlunit.javascript.host.file.FileRequest;
import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.*;

public class StudentViewCourse {
	
	private WebTester test ;
	 
	
	 
	@Before
		
	public void prepare(){
	       test = new WebTester() ; 
	       test.setBaseUrl("http://127.0.0.1/schoolmate/");
	       test.beginAt("/index.php");
	       test.setTextField("username", "ngaha");
	       test.setTextField("password", "ngaha");
	       test.submit();
	       test.assertMatch("ngaha justice's Classes");
			
	}
	
	@Test
	public void test1(){
		 test.setHiddenField("page","4'> <a href=www.yahoo.fr>malicious link</a><br'");
			test.clickElementByXPath("//option [@value='2']");
		    test.assertMatch("ngaha justice's Classes");
			test.assertLinkNotPresentWithExactText("malicious link");
		
	}

	@Test
	public void test2(){
		
		test.clickLinkWithExactText("security testing");
		
	    test.getElementByXPath("//input[@type='hidden' and @value='1']").setAttribute("value", "1 --'> <a href=www.yahoo.fr>malicious link</a><br'");
        test.clickLinkWithExactText("Settings");
	    test.assertMatch("Settings");
		test.assertLinkNotPresentWithExactText("malicious link");
		
	}


}

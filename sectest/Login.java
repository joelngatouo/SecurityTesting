package Unitn.sectest;

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
public class Login {
	private WebTester test ;
	 
	@Before
		
	public void prepare(){
	       test = new WebTester() ; 
	       test.setBaseUrl("http://127.0.0.1/schoolmate/");
	       test.beginAt("/index.php");
	       test.setTextField("username", "joel");
	       test.setTextField("password", "joel");
	       test.submit();
			
	}

	@Test
	public void test1(){
 		 test.setHiddenField("page","1'> <a href=www.yahoo.fr>malicious link</a><br'");
 		 test.clickLinkWithExactText("Log Out");
 		test.assertMatch("Username");
 		test.assertLinkNotPresentWithExactText("malicious link");

	}
	@Test
	public void test2(){
		
		
		
		
	}
	
}

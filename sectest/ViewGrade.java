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

public class ViewGrade {
	
private WebTester test ;
	
	@Before
	
	public void prepare(){
	       test = new WebTester() ; 
	       test.setBaseUrl("http://127.0.0.1/schoolmate/");
	       test.beginAt("/index.php");
	        
			
	}
	@Test
	public void test1(){
		test.setTextField("username", "test");
	       test.setTextField("password", "test");
	       test.submit();
	       test.clickLinkWithExactText("Ngatouo joel");
 	       test.clickLinkWithExactText("N");
		   test.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a><br'");
		   test.clickLinkWithExactText("Grades");
 	       test.assertLinkNotPresentWithExactText("malicious link"); 
	}

	@Test
	public void test2(){
		
		   test.setTextField("username", "test");
	       test.setTextField("password", "test");
	       test.submit();
           test.assertMatch("Students of Ngatouo Jeremie");
	       test.clickLinkWithExactText("Ngatouo joel");
 	       test.clickLinkWithExactText("N");
		test.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a><br'");
		test.clickLinkWithExactText("Grades");
 	    test.assertLinkNotPresentWithExactText("malicious link"); 
		
	}

	@Test
	public void test3(){
		
		test.setTextField("username", "test");
	       test.setTextField("password", "test");
	       test.submit();
	       test.clickLinkWithExactText("Ngatouo joel");
 	       test.clickLinkWithExactText("N");
		   test.setHiddenField("selectclass","1'> <a href=www.unitn.it>nalicious link</a><br'");
 		   test.clickLinkWithExactText("Grades");
	       test.assertMatch("View Grades");
	       test.assertLinkNotPresentWithExactText("malicious link"); 
	}
	@Test
	public void test4(){
		
		 test.setTextField("username", "test");
	       test.setTextField("password", "test");
	       test.submit();
         test.assertMatch("Students of Ngatouo Jeremie");
	       test.clickLinkWithExactText("Ngatouo joel");
	       test.clickLinkWithExactText("N");
		test.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a><br'");
		test.clickLinkWithExactText("Grades");
 	    test.assertLinkNotPresentWithExactText("malicious link"); 
		
	}
	

private void addSubmitButton(String fromXpath){	
 	IElement element = test.getElementByXPath(fromXpath);
 		DomElement form = ((HtmlUnitElementImpl)element).getHtmlElement();
 		InputElementFactory factory = InputElementFactory.instance;
 		AttributesImpl attributes = new AttributesImpl();
 		attributes.addAttribute("","","type","","submit");
 		HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
 		form.appendChild(submit);
}

}

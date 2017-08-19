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

public class AddAttendance {
	
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
	//fix
	@Test
	public void test1(){
		
		
		test.assertMatch("Manage Classes");
		String text = test.getElementByXPath("html//a[@href='javascript: attendance();']").getTextContent();
	 	System.out.println(text);
	 	test.clickLinkWithText(text);
	 	test.assertMatch("Attendance");
	    test.setHiddenField("page","1'> <a href=www.yahoo.fr>malicious link</a><br'");
	 	test.clickButtonWithText("Add");
 	    test.assertLinkNotPresentWithExactText("malicious link");

	}

	@Test
	public void test2(){
		test.assertMatch("Manage Classes");
		test.assertMatch("Manage Classes");
		String text = test.getElementByXPath("html//a[@href='javascript: attendance();']").getTextContent();
	 	System.out.println(text);
	 	test.clickLinkWithText(text);
	 	test.assertMatch("Attendance");
	 	test.setWorkingForm("registration");
	    test.setHiddenField("page2","30'> <a href=www.yahoo.fr>malicious link</a><br'");

	 	addSubmitButton("html//form[@name='registration']");
	 	test.submit();
	 	test.assertMatch("Attendance");
 	    test.assertLinkNotPresentWithExactText("malicious link");


	}
//student
	@Test
	public void test3(){
		test.clickLinkWithExactText("Attendance");
 	 	test.clickButtonWithText("Add");
 	 	
  	    test.setHiddenField("student","1'> <a href=www.yahoo.fr>malicious link</a><br'");
         test.assertMatch("Date");
        
 	    test.assertLinkNotPresentWithExactText("malicious link");

	 	

	}
	//semester

	@Test
	public void test4(){
		 
		test.clickLinkWithExactText("Attendance");
 	 	test.clickButtonWithText("Add");
  	    test.setHiddenField("semester","1'> <a href=www.yahoo.fr>malicious link</a><br'");    
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

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



public class AddUser {
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
		
		test.assertMatch("Manage Classes");
		String text = test.getElementByXPath("html//a[@href='javascript: users();']").getTextContent();
	    test.setHiddenField("page","1'> <a href=www.yahoo.fr>malicious link</a><br'");
		System.out.println(text);
		test.clickLinkWithExactText(text);
  		test.assertMatch("Manage Users");
 	    test.assertLinkNotPresentWithExactText("malicious link");

		

	}

	@Test
	public void test2(){
		
		test.assertMatch("Manage Classes");
		test.assertMatch("Manage Classes");
		String text = test.getElementByXPath("html//a[@href='javascript: users();']").getTextContent();
	 	System.out.println(text);
		test.clickLinkWithExactText(text);
	 	test.setWorkingForm("users");
	    test.setHiddenField("page2","10'> <a href=www.yahoo.fr>malicious link</a><br'");

	 	addSubmitButton("html//form[@name='users']");
	 	test.submit();
  
		test.assertMatch("Manage Users");
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

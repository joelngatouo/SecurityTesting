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
 


public class AddAssignment {

	 private WebTester test ;
	 
	
	 
@Before
	
	public void prepare(){
    test = new WebTester() ; 
    test.setBaseUrl("http://127.0.0.1/schoolmate/");
    test.beginAt("/index.php");
   	test.setTextField("username", "sotto");
   	test.setTextField("password", "sotto");
   	test.submit();
		
}
@Test
public void test(){
	
	 
 	test.assertMatch("sotto patrick's Classes");
 	String text = test.getElementByXPath("html//a[@class='items']").getTextContent();
 	System.out.println(text);
 	test.clickLinkWithExactText(text);
   	test.assertMatch("Update");
   	String text2 = test.getElementByXPath("html//a[@href='javascript: assignments();']").getTextContent();
 	System.out.println(text2);
 	test.clickLinkWithExactText(text2);
   	test.setWorkingForm("assignments");
    test.setHiddenField("page","2'> <a href=www.yahoo.fr>malicious link</a><br'");
    addSubmitButton("html//form[@name='assignments']");
    test.submit();
    test.assertLinkNotPresentWithExactText("malicious link");
 	
 	
}
 @Test
public void test2(){
	 	test.assertMatch("sotto patrick's Classes");
	 	String text = test.getElementByXPath("html//a[@href='JavaScript:document.classes.selectclass.value=1;document.classes.page2.value=1;document.classes.submit();']").getTextContent();
	 	System.out.println(text);
	 	test.clickLinkWithExactText(text);
	   	test.assertMatch("Update");
	   	test.clickLinkWithExactText("Assignments");
	    test.setTextField("selectclass","1'> <a href=www.yahoo.fr>malicious link</a><br'");
        test.clickButtonWithText("Add");	    
 	    test.assertLinkNotPresentWithExactText("malicious link");
	
} 
 //a litle problem on this test case
 @Test
 public void test3(){
	
	test.assertMatch("sotto patrick's Classes");
      String text = test.getElementByXPath("html//a[@href='JavaScript:document.classes.selectclass.value=1;document.classes.page2.value=1;document.classes.submit();']").getTextContent();
	 System.out.println(text);
	 test.clickLinkWithExactText(text);
	 test.assertMatch("Update");
	   	test.clickLinkWithExactText("Assignments");
     test.setTextField("page2","1'> <a href=www.yahoo.fr>malicious link</a><br'");
     test.clickButtonWithText("Add");
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
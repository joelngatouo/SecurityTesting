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



public class EditAnnoucements {

private WebTester test ;
	 
	
	 
@Before
	
public void prepare(){
       test = new WebTester() ; 
       test.setBaseUrl("http://127.0.0.1/schoolmate/");
       test.beginAt("/index.php");
       test.setTextField("username", "joel");
       test.setTextField("password", "joel");
       test.submit();
       test.clickLinkWithExactText("Announcements");
       test.assertMatch("Manage Announcements");

		
}


@Test
public void test1(){
 	test.assertCheckboxPresent("delete[]");
 	test.clickElementByXPath("//input[@onClick='updateboxes(1);']");
    test.setHiddenField("page","1'> <a href=www.yahoo.fr>malicious link</a><br'");
   	test.clickElementByXPath("//input[@onClick='document.announcements.page2.value=19;checkboxes();']");
     test.assertLinkNotPresentWithExactText("malicious link");   
 
}

@Test
public void test2(){
 	test.assertCheckboxPresent("delete[]");
   	test.clickElementByXPath("html//input[@onClick='updateboxes(1);']");
  	String text = test.getElementByXPath("//input[@value=4]").getAttribute("name");
 	System.out.println(text);
	test.getElementByXPath("//input[@value='4']").setAttribute("value", "4 --'> <a href=www.yahoo.fr>malicious link</a><br'");
	test.clickElementByXPath("//input[@onClick='document.announcements.page2.value=19;checkboxes();']");
     test.assertLinkNotPresentWithExactText("malicious link");   
 
}
 

@Test
public void test3(){
	test.assertCheckboxPresent("delete[]");
 	test.clickElementByXPath("//input[@onClick='updateboxes(1);']");
	test.getElementByXPath("//input[@type='checkbox' and @value='2']").setAttribute("value", "2 --'> <a href=www.yahoo.fr>malicious link</a><br'");
   	test.clickElementByXPath("//input[@onClick='document.announcements.page2.value=19;checkboxes();']");
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

package Unitn.sectest;
import java.awt.Button;
import java.awt.Checkbox;

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

public class EditAssignment {


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
public void test1(){ 
 	test.assertMatch("sotto patrick's Classes");

 	String text = test.getElementByXPath("html//a[@href='JavaScript:document.classes.selectclass.value=1;document.classes.page2.value=1;document.classes.submit();']").getTextContent();
 	System.out.println(text);
 	test.clickLinkWithExactText(text);
   	test.assertMatch("Update");
 
 	test.setHiddenField("page","2'> <a href=www.yahoo.fr>malicious link</a><br'");

 	test.clickLinkWithExactText("Assignments");
    test.assertMatch("Add");

    test.assertLinkNotPresentWithExactText("malicious link");   
  	
	
}

@Test
public void test2(){
 	test.assertMatch("sotto patrick's Classes");


 	String text = test.getElementByXPath("html//a[@href='JavaScript:document.classes.selectclass.value=1;document.classes.page2.value=1;document.classes.submit();']").getTextContent();
 	System.out.println(text);
 	test.clickLinkWithExactText(text);
   	test.assertMatch("Class Settings");
 	test.setHiddenField("selectclass","1'> <a href=www.yahoo.fr>Malicious link</a><br'");
 	test.clickLinkWithExactText("Assignments");
    test.assertLinkNotPresentWithExactText("malicious link");   


 	
}
//big issue on it
@Test
public void test3(){
 	test.assertMatch("sotto patrick's Classes");
	String text = test.getElementByXPath("html//a[@href='JavaScript:document.classes.selectclass.value=1;document.classes.page2.value=1;document.classes.submit();']").getTextContent();
 	System.out.println(text);
 	test.clickLinkWithExactText(text);
 	test.clickLinkWithExactText("Assignments");
   	test.assertMatch("Manage Assignments");
  	test.assertCheckboxPresent("delete[]");
  	test.clickElementByXPath("//input[@onClick='updateboxes(1);']");
	test.getElementByXPath("//input[@type='hidden' and @value='1']").setAttribute("value", "1 1 -- <a href=www.yahoo.fr>malicious link</a>");
	test.clickElementByXPath("//input[@onClick='document.assignments.page2.value=5;checkboxes();']");
     test.assertLinkNotPresentWithExactText("malicious  link");   
    
     


}

@Test
public void test4(){
 	test.assertMatch("sotto patrick's Classes");

 	String text = test.getElementByXPath("html//a[@href='JavaScript:document.classes.selectclass.value=1;document.classes.page2.value=1;document.classes.submit();']").getTextContent();
 	System.out.println(text);
 	test.clickLinkWithExactText(text);
   	test.assertMatch("Class Settings");
   	test.clickLinkWithExactText("Assignments");
   	test.assertCheckboxPresent("delete[]");
   	test.clickElementByXPath("html//input[@value = 3]");
   	test.assertMatch("Title");
   	test.getElementByXPath("//input[@type='checkbox' and @value='3']").setAttribute("value", "31 -- <a href=www.yahoo.fr>malicious link</a>");
    test.clickElementByXPath("html//input[@onClick='document.assignments.page2.value=5;checkboxes();']");
    test.assertLinkNotPresentWithExactText("malicious link");   

    

}


private void addSubmitButton(String fromXpath){	
	 	IElement element = test.getElementByXPath(fromXpath);
	 		DomElement form = ((HtmlUnitElementImpl)element).getHtmlElement();
	 		InputElementFactory factory = InputElementFactory.instance;
	 		AttributesImpl attributes = new AttributesImpl();
	 		attributes.addAttribute("","","","","submit");
	 		HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
	 		form.appendChild(submit);
	}
}

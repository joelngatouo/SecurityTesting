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


public class EditClass {
	

private WebTester test ;
	 
	@Before
	
	public void prepare(){
	       test = new WebTester() ; 
	       test.setBaseUrl("http://127.0.0.1/schoolmate/");
	       test.beginAt("/index.php");
	       test.setTextField("username", "joel");
	       test.setTextField("password", "joel");
	       test.submit();
	       test.clickLinkWithExactText("Classes");
			
	}

	

@Test
public void test1(){
	
	test.assertCheckboxPresent("delete[]");
 	test.clickElementByXPath("//input[@onClick='updateboxes(1);']");
    test.setHiddenField("page","1'> <a href=www.yahoo.fr>malicious link</a><br'");
   	test.clickElementByXPath("//input[@onClick='document.classes.page2.value=11;checkboxes();']");
    test.assertMatch("Edit Class");
    test.assertLinkNotPresentWithExactText("malicious link");
}

@Test
public void test2(){
	test.assertCheckboxPresent("delete[]");
	test.clickElementByXPath("//input[@onClick='updateboxes(1);']");
    test.getElementByXPath("//input[@type='hidden' and @name='page2']").setAttribute("value", "11 --'> <a href=www.yahoo.fr>malicious link</a><br'");
	test.clickElementByXPath("//input[@onClick='document.classes.page2.value=11;checkboxes();']");
     test.assertLinkNotPresentWithExactText("malicious link");
	
}

@Test
public void test3(){
	
	test.assertCheckboxPresent("delete[]");
	test.clickElementByXPath("//input[@onClick='updateboxes(1);']");
    test.getElementByXPath("//input[@type='checkbox' and @value='1']").setAttribute("value", "1 -- <a href=www.yahoo.fr>malicious link</a>");
    test.clickButtonWithText("Edit");
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

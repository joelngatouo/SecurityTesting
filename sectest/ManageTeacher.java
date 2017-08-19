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

public class ManageTeacher {
	

	private WebTester test ;
	@Before
	
	public void prepare(){
	       test = new WebTester() ; 
	       test.setBaseUrl("http://127.0.0.1/schoolmate/");
	       test.beginAt("/index.php");
	       test.setTextField("username", "joel");
	       test.setTextField("password", "joel");
	       test.submit();	   
	       test.assertMatch("Manage Classes");
			
	}
	@Test
	public void test1(){
		
		test.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a><br'");
		test.clickLinkWithExactText("Teachers");
		test.assertLinkNotPresentWithExactText("malicious link"); 
		
	}

	@Test
	public void test2(){
		test.clickLinkWithExactText("Teachers");
		test.setWorkingForm("teachers");
		test.setHiddenField("page2","17'><a href=www.unitn.it>malicious link</a> <br ");
		addSubmitButton("//form[@name='teachers']");
		test.submit();    
  	 	test.assertLinkNotPresentWithText("malicious link");
	}

	@Test
	public void test3(){	
		test.clickLinkWithExactText("Teachers"); 
		test.setWorkingForm("teachers");
		test.setHiddenField("onpage","1'><a href=www.unitn.it>malicious link</a> <br ");
		addSubmitButton("//form[@name='teachers']");
		test.submit();    
		test.assertMatch("Manage Teachers");
	 	test.assertLinkNotPresentWithText("malicious link");
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

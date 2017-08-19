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

public class AddParent {
	
	private WebTester tester ;
	 
	
	 
	@Before
		
	public void prepare(){
	       tester = new WebTester() ; 
	       tester.setBaseUrl("http://127.0.0.1/schoolmate/");
	       tester.beginAt("/index.php");
	       tester.setTextField("username", "joel");
	       tester.setTextField("password", "joel");
	       tester.submit();
		   tester.clickLinkWithExactText("Parents");

	}

@Test
public void test1(){
	
	
	tester.assertMatch("Manage Parents");
    tester.setHiddenField("page","1'> <a href=www.unitn.it>malicious link</a><br'");
    tester.clickButtonWithText("Add");
    tester.assertMatch("Add New Parent");
    tester.assertLinkNotPresentWithText("malicious link");
}

@Test
public void test2(){
	
	
	tester.assertMatch("Manage Parents");
	tester.setWorkingForm("parents");
    tester.setHiddenField("page2","23'><a href=www.unitn.it>malicious link</a> <br ");
    addSubmitButton("//form[@name='parents']");
    tester.submit();     
    tester.assertMatch("Add New Parent");
    tester.assertLinkNotPresentWithText("malicious link");
}

private void addSubmitButton(String fromXpath){	
 	IElement element = tester.getElementByXPath(fromXpath);
 		DomElement form = ((HtmlUnitElementImpl)element).getHtmlElement();
 		InputElementFactory factory = InputElementFactory.instance;
 		AttributesImpl attributes = new AttributesImpl();
 		attributes.addAttribute("","","type","","submit");
 		HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
 		form.appendChild(submit);
}


}

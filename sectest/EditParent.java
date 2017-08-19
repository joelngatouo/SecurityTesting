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

public class EditParent {
	
	private WebTester test ;
	 
	@Before
	
	public void prepare(){
	       test = new WebTester() ; 
	       test.setBaseUrl("http://127.0.0.1/schoolmate/");
	       test.beginAt("/index.php");
	       test.setTextField("username", "joel");
	       test.setTextField("password", "joel");
	       test.submit();
	       test.clickLinkWithExactText("Parents");
			
	}
	

@Test
public void test1(){
	

	test.assertCheckboxPresent("delete[]");
 	test.clickElementByXPath("//input[@onClick='updateboxes(1);document.parents.studentid.value=1;']");
    test.setHiddenField("page","1'> <a href=www.yahoo.fr>malicious link</a><br'");
 	test.clickElementByXPath("//input[@onClick='document.parents.page2.value=24;checkboxes();']");
    test.assertMatch("Edit Parent");
    test.assertLinkNotPresentWithExactText("malicious link");
}

@Test
public void test2(){
	
	test.assertCheckboxPresent("delete[]");
	test.clickElementByXPath("//input[@onClick='updateboxes(1);document.parents.studentid.value=1;']");
	test.getElementByXPath("//input[@type='hidden' and @value='22']").setAttribute("value", "22 --'> <a href=www.yahoo.fr>malicious link</a><br'");
	test.clickElementByXPath("//input[@onClick='document.parents.page2.value=24;checkboxes();']");
	test.assertMatch("Edit Parent");
	test.assertLinkNotPresentWithExactText("malicious link");
}

@Test
public void test3(){
	

	test.assertCheckboxPresent("delete[]");
	test.clickElementByXPath("//input[@onClick='updateboxes(1);document.parents.studentid.value=1;']");
	test.getElementByXPath("//input[@type='checkbox' and @value='1']").setAttribute("value", "1  -- <a href=www.yahoo.fr>malicious link</a>'");
	test.clickElementByXPath("//input[@onClick='document.parents.page2.value=24;checkboxes();']");
	test.assertMatch("Edit Parent");
	test.assertLinkNotPresentWithExactText("malicious link");
	
}

}

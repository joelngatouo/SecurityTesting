
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
public void test1(){}

@Test
public void test2(){}

@Test
public void test3(){}

@Test
public void test4(){}


@Test
public void test5(){}

 private void addSubmitButton(String fromXpath){	
	 	IElement element = test.getElementByXPath(fromXpath);
	 		DomElement form = ((HtmlUnitElementImpl)element).getHtmlElement();
	 		InputElementFactory factory = InputElementFactory.instance;
	 		AttributesImpl attributes = new AttributesImpl();
	 		attributes.addAttribute("","","type","","submit");
	 		HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
	 		form.appendChild(submit);
	}



@Test
	public void test1(){
		
		
		test.assertMatch("sotto patrick's Classes");
		String text = test.getElementByXPath("html//a[@href='JavaScript:document.classes.selectclass.value=1;document.classes.page2.value=1;document.classes.submit();']").getTextContent();
	 	System.out.println(text);
	 	test.clickLinkWithExactText(text);
	   	test.assertMatch("Update");
	     test.setHiddenField("page","2'> <a href=www.yahoo.fr>malicious link</a><br'");
		test.clickLinkWithExactText("Grades");
		test.assertMatch("Grades");
		test.assertLinkNotPresentWithExactText("malicious link");

	}

	@Test
	public void test2(){
		

		test.assertMatch("sotto patrick's Classes");
		 
	 	test.setWorkingForm("teacher");
	    test.setHiddenField("page2","3'> <a href=www.yahoo.fr>malicious link</a><br'");

	 	addSubmitButton("html//form[@name='teacher']");
	 	test.submit();

		test.assertMatch("Grades");
		test.assertLinkNotPresentWithExactText("malicious link");
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
   	//test.clickButtonWithText("Delete");
   	//test.setWorkingForm("assignments");
   	test.getElementByXPath("//input[@type='checkbox' and @value='3']").setAttribute("value", "3 --'> <a href=www.yahoo.fr>malicious link</a><br'");
    test.clickElementByXPath("html//input[@onClick='document.announcements.page2.value=19;checkboxes();']");
    test.assertMatch("Cancel");
  	test.assertMatch("Assigned Task");
    test.assertLinkNotPresentWithExactText("malicious link");   

    

}

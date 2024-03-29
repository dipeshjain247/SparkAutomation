package ScriptHelper;

import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.Refreshable;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.Page;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;

import Driver.DriverHelper;
import Driver.XMLReader;
import Reporter.ExtentTestManager;
import org.apache.log4j.Logger;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.apache.poi.xssf.streaming.examples.SavePasswordProtectedXlsx;

public class InFlightOrderHelper extends DriverHelper {

	
	
	
	public InFlightOrderHelper(WebDriver dr) {
		super(dr);

	}

	WebElement el;
	
	
	XMLReader xml = new XMLReader("src\\Locators\\InFlightOrder.xml");
	
	public void ServiceTabInFlight(Object[] Inputdata) throws Exception {
		String Product_Name="Number Hosting";//Inputdata[2].toString();		
		
		        Thread.sleep(15000);
				//Service Tab Click
				try 
				{
					Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Tab");
				} 
				catch (Exception e) 
				{
					try 
					{
						safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
				}
				waitforPagetobeenable();
				waitForpageload();
				//******
				
				//Search Service Order
				waitandForElementDisplay(xml.getlocator("//locators/InputServiceOrder"), 5);
				Clickon(getwebelement(xml.getlocator("//locators/InputServiceOrder")));
				SendKeys(getwebelement(xml.getlocator("//locators/InputServiceOrder")),ServiceOrder.get().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Send Service Order Number");
				Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Go");
				waitforPagetobeenable();
				Thread.sleep(20000);
				//*******
				
				//Open Reference No
				Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ServiceOrderReferenceNo");
				waitforPagetobeenable();
				//******
				
				//Open Worflow Tab
				WaitforElementtobeclickable(xml.getlocator("//locators/ClickLink").replace("Value", "Workflows"));
				Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value","Workflows")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Workflows");
				waitforPagetobeenable();
				//Click  Plus Sign, select dropdown and select option 
				Clickon(getwebelement(xml.getlocator("//locators/InflightPlusSign")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on InFightPlusSign");
				waitforPagetobeenable();
				Clickon(getwebelement(xml.getlocator("//locators/WorkflowDropdown")));
				Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "In-Flight Change")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Value from Dropdown");
				Thread.sleep(3000);
				Clickon(getwebelement(xml.getlocator("//locators/InflightVoiceSelection")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Voice");
				waitforPagetobeenable();
				//Workflow save
				Clickon(getwebelement(xml.getlocator("//locators/WorkflowSave")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on WorkflowsSave");
				waitforPagetobeenable();
				Thread.sleep(2000);
				waitforPagetobeenable();
				switch(Product_Name)
				{
				case "Number Hosting":
				{
					NumberHostingInflight(Inputdata);
					break;
				}
				case "SIP Trunking":
				{
					SIPTrunkingInflight(Inputdata);
					break;
				}
				case "Inter Connect":
				{
					NumberHostingInflight(Inputdata);
					break;
				}
				case "Voice LineV":
				{
					VoiceLineVInflight(Inputdata);
					break;
				}
				default:
					System.out.println("Above product is not exist in current list");
					break;
				}	
	   }
	
	public void NumberHostingInflight( Object[] Inputdata) throws InterruptedException, DocumentException, IOException
	{
		//Open Voice Tab
		WaitforElementtobeclickable(xml.getlocator("//locators/ClickLink").replace("Value", "Voice Config"));
		Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value","Voice Config")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Voice Config");
		waitforPagetobeenable();
		//********
		
		//Update Trunk Name and verified icon 
		
		String Fieldtitle1=Getattribute(getwebelement(xml.getlocator("//locators/FieldLabel").replace("ChangeFieldName","Trunk Name")), "Title");
		Assert.assertTrue("Before Change In- Flight apprear for control", !Fieldtitle1.contains("flight"));
		Clickon(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Trunk Name")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Trunk Name");
		Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Trunk Name")));
		
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Trunk Name")), "abcd");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Send Trunk Name");
		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		Clickon(getwebelement(xml.getlocator("//lotors/ClickheretoSaveAccess")));
		waitforPagetobeenable();
		
		Fieldtitle1=Getattribute(getwebelement(xml.getlocator("//locators/FieldLabel").replace("ChangeFieldName","Trunk Name")), "Title");
		Assert.assertTrue("After Change to In- Flight title not appear for field", Fieldtitle1.contains("flight"));
	}
	

	public void SIPTrunkingInflight( Object[] Inputdata) throws InterruptedException, DocumentException, IOException
	{
		String Fieldtitle1=Getattribute(getwebelement(xml.getlocator("//locators/FieldLabel").replace("ChangeFieldName","Call Admission Control (Number of Channels)")), "Title");
		Assert.assertTrue("Before Change In- Flight apprear for control", !Fieldtitle1.contains("flight"));
		WaitforElementtobeclickable(xml.getlocator("//locators/CallAdmissionControl"));	
		Clickon(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallAdmissionControl");
		Clear(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
		SendKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")),"12" );
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Send value for CallAdmissionControl");
		
		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		Clickon(getwebelement(xml.getlocator("//lotors/ClickheretoSaveAccess")));
		waitforPagetobeenable();
		
		Fieldtitle1=Getattribute(getwebelement(xml.getlocator("//locators/FieldLabel").replace("ChangeFieldName","Call Admission Control (Number of Channels)")), "Title");
		Assert.assertTrue("After Change to In- Flight title not appear for field", Fieldtitle1.contains("flight"));
		
		 
		
	}
	public void VoiceLineVInflight( Object[] Inputdata) throws InterruptedException, DocumentException, IOException
	{
		String Fieldtitle1=Getattribute(getwebelement(xml.getlocator("//locators/FieldLabel").replace("ChangeFieldName","Total Number of DDIs")), "Title");
		Assert.assertTrue("Before Change In- Flight apprear for control", !Fieldtitle1.contains("flight"));
		WaitforElementtobeclickable(xml.getlocator("//locators/TotalDDi"));
		Clickon(getwebelement(xml.getlocator("//locators/TotalDDi")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on TotalDDi");
		SendKeys(getwebelement(xml.getlocator("//locators/TotalDDi")),"6");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Send  TotalDDi");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/TotalDDi")),Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Total DDI");
		Thread.sleep(5000);
		
		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		Clickon(getwebelement(xml.getlocator("//lotors/ClickheretoSaveAccess")));
		waitforPagetobeenable();
		
		Fieldtitle1=Getattribute(getwebelement(xml.getlocator("//locators/FieldLabel").replace("ChangeFieldName","Total Number of DDIs")), "Title");
		Assert.assertTrue("After Change to In- Flight title not appear for field", Fieldtitle1.contains("flight"));
	}
}

	

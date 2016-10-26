package rentpack;



import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.ArrayList;

public class DomainAuto {
	
	private static WebDriver driver;
	private static Actions actions;
	private static List<AptDatabase> database = new ArrayList<AptDatabase>(); //note here the right hand side is a class that implements interface List, can't instantiate a List
	
	//define a constructor that initializes everything
	DomainAuto() throws Exception{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jconstan\\Documents\\programming\\java\\WebDriverWork\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.domain.com.au//");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		actions = new Actions(driver);
		//Thread.sleep(5000);
	}
	
	//constructor that specifies the suburb in the correct format
	DomainAuto(String suburb){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jconstan\\Documents\\programming\\java\\WebDriverWork\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.domain.com.au/rent/"+suburb+"/??ssubs=1");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		actions = new Actions(driver);
	}
	
	//constructor for bypassing init steps
	DomainAuto(int x){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jconstan\\Documents\\programming\\java\\WebDriverWork\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.domain.com.au/rent/melbourne-vic-3000/?ssubs=1&price=100-300&features=furnished");
		//driver.get("file:///C:/Users/jconstan/Documents/programming/java/offline%20web%20pages/Rental%20Properties%20in%20Melbourne,%20VIC%203000.html");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		actions = new Actions(driver);
	}
	
	public static void pickPrice(String id1, String id2, String val){
		WebElement minPriceBtn = driver.findElement(By.xpath("//span[@id='" +id1+ "']"));
		minPriceBtn.click();
		WebElement minPrice100 = driver.findElement(By.xpath("//ul[@id='" +id2+ "']/li[text()='" +val+ "']"));
		minPrice100.click();
	}
	
	//function for clicking using action, to avoid not clickable error due to none visible element
	public static void clickButton(String htmlClass, String attribute, String value){
		WebElement elementTemp = driver.findElement(By.xpath("//"+htmlClass+"[@"+attribute+"='"+value+"']"));
		actions.moveToElement(elementTemp).click().perform();
	}
	
	//function for getting a specific information text from a webelement
	//will work if an only if 1 element is found
	//need to include some finding/detection mechanism...
	//well they said using exceptions can be slow... so better use find Elements...
	//note, this is a very good check for the program flow, must apply this for everything...
	public static void getInfoText(String xpathIn){
		List <WebElement> tempListElement = driver.findElements(By.xpath(xpathIn));
		if(tempListElement.size() == 1) System.out.println(tempListElement.get(0).getText());
		else System.out.println("----");
	}
	
	//this version stores the text in a string, through a return value because, java is always pass by value
	public static String saveInfoText(String xpathIn){
		List <WebElement> tempListElement = driver.findElements(By.xpath(xpathIn));
		if(tempListElement.size() == 1) return (tempListElement.get(0).getText());
		else return ("----");
	}
	
	public static void filterOptions() throws InterruptedException{
		//now initialize the choices
		//set min and max price
		pickPrice("MinPrice-button", "MinPrice-menu", "$100");
		pickPrice("MaxPrice-button", "MaxPrice-menu", "$300");
		
		//allow scroll down
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		//click the furnished filter
		clickButton("a","data-eventlabel","More options open");
		clickButton("a","data-filter-field","PropertyFeatures");
		clickButton("input","value","Furnished");
		
		//now update the search using submit - weird that the click does not work here
		Thread.sleep(3000);
		WebElement elementTemp = driver.findElement(By.xpath("//input[@id='searchRefinerControlBtnSearch']"));
		elementTemp.submit();
	}
	
	public static void getAptInfo(){

		//get the following and store in database
		//common: price, address, bond, description
		//not so common: internal area and features
		//need to include some finding/detection mechanism...
		//refer to getInfoText method that checks first if the element is present
		
		AptDatabase tempAptData = new AptDatabase();
		String tempUrl = driver.getCurrentUrl();
		String tempPrice = saveInfoText("//div[@class='left-wrap']/span[@class='h4 pricedisplay truncate-single']");
		String tempAddress = saveInfoText("//div[@class='left-wrap']/h1");	
		String tempAvailability = saveInfoText("//li[text()='Available from ']/em");
		String tempBond = 	saveInfoText("//li[text()='Bond ']/em");
		String tempArea = saveInfoText("//li[text()='Internal area ']/em");
		String tempBed = saveInfoText("//span[@class='icon domain-icon-ic_beds']/following-sibling::span/descendant::em");
		String tempBath = saveInfoText("//span[@class='icon domain-icon-ic_baths']/following-sibling::span/descendant::em");
		String tempCar = saveInfoText("//span[@class='icon domain-icon-ic_cars']/following-sibling::span/descendant::em");
		
		//now  the other info is embedded in paragraph text
		//i want to quickly find for keywords such as heating and laundry.
		String tempString = driver.findElement(By.xpath("//div[@id='description']/descendant::pre")).getText();
		String tempHeating = String.valueOf(tempString.toLowerCase().contains("heating"));
		String tempLaundry = String.valueOf(tempString.toLowerCase().contains("laundry"));
		
		//store it inside the class
		tempAptData.setData(tempUrl, tempPrice, tempAddress, tempAvailability, tempBond, tempArea, tempBed, tempBath, tempCar, tempHeating, tempLaundry);
		//tempAptData.printData();
		database.add(tempAptData);
		
		
		
	}
	

	public static void main(String[] args) throws Exception  {
		
		//initialize
		DomainAuto test = new DomainAuto("melbourne-vic-3000"); //input here the suburb
		filterOptions();
		
		/* //now let us cheat and avoid this prelim steps...
		int x=0;
		DomainAuto test = new DomainAuto(x); //go straight to filtered input
											 //better yet, can go offline	
		*/
		
		//next major thing is to be able to iterate on all links in the page -> see the webpage, this involves traversing to each li in the main search results ul
		//also note that the hyperlink element <a> is a some grandhcildren of li, each entry having different depth -> use decsendants to select all children and grandchildren
		//for this, we use driver.findElements->('s') (instead of findElement) that can actually return all elements pointed to by xpaths into a list
		
		List<WebElement> allElements3 = driver.findElements(By.xpath("//ul[@id='searchresults']/li/descendant::a[@class='listing-result__listing']")); 
		List<String> urlList = new ArrayList<String> ();
		
		for (WebElement element: allElements3) {
		      System.out.println(element.getAttribute("href"));
		      urlList.add(element.getAttribute("href"));
		}
		
		//now that we have the list of links we can do this iteretively
		for(int i=0; i<urlList.size(); ++i){	
			driver.get(urlList.get(i)); //after first iteration, it seems that allelements is no longer valid... so use this instead
			//actions.moveToElement(allElements3.get(i)).click().perform(); //go to each of the hyper link, more robust
			Thread.sleep(1000);
			getAptInfo();					//get apartment information
			driver.navigate().back();		//next step, return to list of the webpage
		}
		
		//print current content of the struct, and at same time export it to CSV
		
		//save the file path here and char set and open a writer buffer
		Path p = Paths.get("C:\\Users\\jconstan\\Documents\\programming\\java\\WebDriverWork\\RentScrape\\data.txt");
		Charset charset = Charset.forName("US-ASCII");
		BufferedWriter writer = Files.newBufferedWriter(p,charset,CREATE,APPEND);
		
		
		for(int i=0; i<urlList.size(); ++i){ 
			database.get(i).saveData(writer);
			database.get(i).printData();
		}
		
		//wait for a few seconds before exit
		 try {
			 Thread.sleep(3000);
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		}
		 
		writer.close();
		driver.close();
		driver.quit(); //this is needed to release the resource of chromedriver

	}
	
	public static void sampleScripts()  throws InterruptedException{
		//copy paste these scripts to main
		
		//next major thing is to be able to iterate on all Li of a ul
		//as an example, this code uses findElements that returns all possible li in ul search results and store it in a List (one interface implemented by ArrayList)
		//here I was able to print all data list id
		
		List<WebElement> allElements = driver.findElements(By.xpath("//ul[@id='searchresults']/li")); 

		for (WebElement element: allElements) {
		      System.out.println(element.getAttribute("data-listing-id"));
		}
		
		//the next question is, how to access elements that are way deep down the hierarchy
		//here see the use of descendants, which selects all children and grandchildren of the node of type h2, which should only be one
		//this allows me to retrieve all price as stated in the list...
		List<WebElement> allElements2 = driver.findElements(By.xpath("//ul[@id='searchresults']/li/descendant::h2")); 

		for (WebElement element: allElements2) {
		      System.out.println(element.getText());
		}
	
		//now for the real thing that i want to do... is i want to click on the links...
		//question, can a web element access its child nodes
		//right now it allows me to print the url, so theoretically, they should be clickable
		List<WebElement> allElements3 = driver.findElements(By.xpath("//ul[@id='searchresults']/li/descendant::a[@class='listing-result__listing']")); 

		for (WebElement element: allElements3) {
		      System.out.println(element.getAttribute("href"));
		}
		
		/*
		getInfoText("//div[@class='left-wrap']/span[@class='h4 pricedisplay truncate-single']"); //price
		getInfoText("//div[@class='left-wrap']/h1");	//address
		
		getInfoText("//li[text()='Available from ']/em");
		getInfoText("//li[text()='Bond ']/em");
		getInfoText("//li[text()='Internal area ']/em");
		
		//bed, bath, parking
		getInfoText("//span[@class='icon domain-icon-ic_beds']/following-sibling::span/descendant::em");
		getInfoText("//span[@class='icon domain-icon-ic_baths']/following-sibling::span/descendant::em");
		getInfoText("//span[@class='icon domain-icon-ic_cars']/following-sibling::span/descendant::em");
		*/
	
		//Thread.sleep(3000);
		allElements3.get(0).click(); //done, was able to go to this link...
		Thread.sleep(1000);
		//next step, how to navigate through web pages
		driver.navigate().back();
	}

}

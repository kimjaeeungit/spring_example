//package com.kimjaeeun.service;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class SeleniumTest {
//	private final String WEB_DRIVER_ID="webdriver.chrome.driver";
//	private final String WEB_DRIVER_PATH="C:\\tools\\chromedriver\\chromedriver.exe";
//	//private String url="https://www.naver.com";
//	//private String url="http://localhost:8080/customLogin";
//	private String url="http://localhost:8080/board/list";
//	
//	private WebDriver driver;
//	
//	public SeleniumTest() {
//		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//		driver = new ChromeDriver();
//	}
//	
//	public void crawl() throws InterruptedException{
//		driver.get(url);
//		//List<WebElement> list = driver.findElements(By.cssSelector(".form-group"));
//		List<WebElement> list = driver.findElements(By.cssSelector("#replyUL li"));
//		//list.get(0).findElement(By.tagName("input")).sendKeys("admin98");
//		//list.get(1).findElement(By.tagName("input")).sendKeys("pw98");
//		
//		
//		//driver.findElement(By.className("btn-primary")).submit();
//	
//		//Thread.sleep(10000);
//		
//		List<WebElement> boardList=driver.findElements(By.cssSelector("table tr"));
//		System.out.println();
//		for(int i=0;i<boardList.size();i++){
//			WebElement tr =  boardList.get(i);
//			System.out.println(tr.getText());
//			tr.findElements(By.cssSelector("td")).get(1).findElement(select("a")).click();
//			try {
//				Thread.sleep(500);
//				list.forEach(li->{
//					//댓글 읽어오는 부분
//					List<WebElement> divs = li.findElements(By.cssSelector("div"));
//					String title = divs.get(0).findElements(By.cssSelector("div")).get(0).getText();
//					String content = divs.get(1).getText();
//					
//					System.out.println(title + "::"+content);
//				});
//				driver.findElement(select("form a")).click();
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		
//		//System.out.println(driver.getPageSource());
//	}
//	
//	static By select(String selector){
//		return By.cssSelector(selector);
//	}
//	
//	public static void main(String[] args) throws InterruptedException {
//		SeleniumTest test= new SeleniumTest();
//		test.crawl();
//		
//		
//	}
//}

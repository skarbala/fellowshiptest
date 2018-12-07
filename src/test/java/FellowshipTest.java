import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FellowshipTest {
  WebDriver driver;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("http://localhost:8888");
  }

  @Test
  public void shouldShowAllFellowshipMembers() {
    List<String> fellowMembers = driver.findElements(By.cssSelector("ul.list-of-fellows div.col h1"))
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
    Assert.assertEquals(9, fellowMembers.size());
    Assert.assertTrue(fellowMembers.contains("Frodo"));
    Assert.assertTrue(fellowMembers.contains("Samwise"));
    Assert.assertTrue(fellowMembers.contains("Gandalf"));
    Assert.assertTrue(fellowMembers.contains("Legolas"));
    Assert.assertTrue(fellowMembers.contains("Gimli"));
    Assert.assertTrue(fellowMembers.contains("Aragron"));
    Assert.assertTrue(fellowMembers.contains("Boromir"));
    Assert.assertTrue(fellowMembers.contains("Meriadoc"));
    Assert.assertTrue(fellowMembers.contains("Peregrin"));
  }
}

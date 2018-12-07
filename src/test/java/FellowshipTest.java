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
  private WebDriver driver;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("http://localhost:8888");
  }

  @Test
  public void shouldContainInitialPointsValue() {
    String expectedInitialValue = "25";
    String actualInitialValue = driver.findElement(By.cssSelector("div.points-left h2")).getText();

    Assert.assertEquals(expectedInitialValue, actualInitialValue);
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

  @Test
  public void shouldContainAgeForEachFellow() {
    List<String> fellowMembersAgeList = driver.findElements(By.cssSelector("ul.list-of-fellows h2:nth-child(3)"))
        .stream()
        .map(WebElement::getText)
        .map(fellowAge -> fellowAge.replace("Age:", "").trim())
        .collect(Collectors.toList());

    Assert.assertFalse(fellowMembersAgeList.isEmpty());
    fellowMembersAgeList.forEach(fellowMembersAge -> {
      Assert.assertFalse(fellowMembersAge.equals(""));
      Assert.assertFalse(fellowMembersAge.isEmpty());
    });
  }

  @Test
  public void shouldContainOnlySpecificHobbits() {
    String[] hobbits = {"Frodo", "Samwise", "Meriadoc", "Peregrin"};

    List<String> actualHobbits = driver.findElements(By.cssSelector("ul.list-of-fellows li"))
        .stream()
        .filter(webElement -> webElement.findElement(By.cssSelector("h2:nth-child(2)")).getText().contains("Hobbit"))
        .map(webElement -> webElement.findElement(By.cssSelector("h1")).getText())
        .collect(Collectors.toList());

    for (String hobbit : hobbits) {
      Assert.assertTrue(actualHobbits.contains(hobbit));
    }
  }
}

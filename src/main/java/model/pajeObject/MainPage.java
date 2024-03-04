package model.pajeObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

  // адрес главной страницы:
  private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

  // время для ожидания загрузки элемента:
  public static final int SHORT_TIMEOUT_MS = 3000;

  // кнопка куки "Да все привыкли":
  private static final By cookieButton = By.id("rcc-confirm-button");

  // вопросы в секции "Вопросы о важном":
  private static final By questionsHeader = By.xpath(".//div[@class='accordion__heading']");

  // ответы на вопросы в секции "Вопросы о важном":
  private static final By  answersText = By.cssSelector("div.accordion__panel > p");

  // кнопка "Заказать" в шапке:
  private static final By orderButtonHeader = By.cssSelector(".Header_Nav__AGCXC>button.Button_Button__ra12g");

  // кнопка "Заказать" в блоке "Как это работает":
  private static final By orderButtonHow = By.xpath(".//div[@class='Home_RoadMap__2tal_']//button[text()='Заказать']");


  private WebDriver driver;

  public MainPage(WebDriver driver) {
    this.driver = driver;
  }

  public MainPage open() {
    driver.get(PAGE_URL);
    return this;
  }

  public static WebDriver getWebDriver(boolean useFirefox) {
    if (useFirefox) {
      return new FirefoxDriver();
    } else {
      return new ChromeDriver();
    }
  }


  public void clickCookieButton() {
    driver.findElement(cookieButton).click();
  }

  public void clickQuestionHeader(int number) {
    driver.findElements(questionsHeader).get(number).click();
  }

  public void waitForAnswerText(int number) {
    new WebDriverWait(driver, SHORT_TIMEOUT_MS)
        .until(ExpectedConditions.visibilityOf(driver.findElements(answersText).get(number)));
  }

  public String getQuestionHeader(int number) {
    return driver.findElements(questionsHeader).get(number).getText();
  }

  public String getAnswerText(int number) {
    return driver.findElements(answersText).get(number).getText();
  }

  public void clickOrderButtonHeader() {
    driver.findElement(orderButtonHeader).click();
  }

  public void clickOrderButtonHow() {
    driver.findElement(orderButtonHow).click();
  }

}

package model.pajeObject;

import static model.pajeObject.MainPage.SHORT_TIMEOUT_MS;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

  // Форма заказа:
  private static final By orderForm = By.xpath(".//div[@class='Order_Form__17u6u']");

  // поле ввода имени:
  private static final By nameField = By.xpath(".//input[contains(@placeholder,'Имя')]");

  // поле ввода фамилии:
  private static final By surnameField = By.xpath(".//input[contains(@placeholder,'Фамилия')]");

  // поле ввода адреса:
  private static final By addressField = By.xpath(".//input[contains(@placeholder,'Адрес')]");

  // поле ввода метро:
  private static final By metroField = By.xpath(".//input[contains(@placeholder,'метро')]");

  // выпадающий список станций метро:
  private static final By metroList = By.className("select-search__select");

  // перечисление всех станций из списка метро:
  private static final By metroListStations =
      By.xpath(".//div[@class='Order_Text__2broi']");

  // поле ввода телефона:
  private static final By phoneField = By.xpath(".//input[contains(@placeholder,'Телефон')]");

  // кнопка "Далее" в форме заказа:
  private static final By nextButton = By.cssSelector(".Button_Middle__1CSJM");

  // поле выбора даты доставки:
  private static final By dayField = By.xpath(".//input[contains(@placeholder,'Когда')]");

  // выпадающий календарь даты доставки:
  private static final By dayChoose = By.cssSelector(".react-datepicker__day--selected");

  // поле выбора сроков аренды:
  private static final By rentField = By.className("Dropdown-root");

  // выпадающий список сроков аренды:
  private static final By rentList = By.className("Dropdown-option");

  // список выбора цветов самоката:
  private static final By colorList = By.xpath(".//label");

  // поле ввода комментария:
  private static final By commentField = By.xpath(".//input[contains(@placeholder,'Комментарий')]");

  // кнопка "Заказать" в финале формы заказа:
  private static final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(),'Заказать')]");

  // кнопка "Да" - подтверждение заказа:
  private static final By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(),'Да')]");

  // сообщение "Заказ оформлен"
  private static final By doneMessage = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and contains(text(),'Заказ оформлен')]");


  private WebDriver driver;

  public OrderPage(WebDriver driver) {
    this.driver = driver;
  }

  public void waitForLoadForm() {
    new WebDriverWait(driver, SHORT_TIMEOUT_MS)
        .until(ExpectedConditions.visibilityOf(driver.findElement(orderForm)));
  }

  public void enterName(String name) {
    driver.findElement(nameField).sendKeys(name);
  }

  public void enterSurname(String surname) {
    driver.findElement(surnameField).sendKeys(surname);
  }

  public void enterAddress(String address) {
    driver.findElement(addressField).sendKeys(address);
  }

  public void waitForLoadList(By LoadList) {
    new WebDriverWait(driver, SHORT_TIMEOUT_MS)
        .until(ExpectedConditions.visibilityOf(driver.findElement(LoadList)));
  }

  public void chooseListItem(By listItems, String itemChoose) {
    List<WebElement> elements = driver.findElements(listItems);
    for (WebElement element : elements) {
      if (element.getText().equals(itemChoose)) {
        element.click();
        break;
      }
    }
  }

  public void enterMetro(String metro) {
    driver.findElement(metroField).sendKeys(metro);
    waitForLoadList(metroList);
    chooseListItem(metroListStations, metro);
  }

  public void enterPhone(String phone) {
    driver.findElement(phoneField).sendKeys(phone);
  }

  public void clickNextButton() {
    driver.findElement(nextButton).click();
  }

  public void enterDay(String day) {
    driver.findElement(dayField).sendKeys(day);
    waitForLoadList(dayChoose);
    clickDayChoose();
  }

  public void clickDayChoose() {
    driver.findElement(dayChoose).click();
  }

  public void clickRentField() {
    driver.findElement(rentField).click();
  }

  public void enterRent(String rentDays) {
    this.clickRentField();
    this.chooseListItem(rentList, rentDays);
  }

  public void chooseColor(String color) {
    this.chooseListItem(colorList, color);
  }

  public void enterComment(String comment) {
    driver.findElement(commentField).sendKeys(comment);
  }

  public void clickOrderButton() {
    driver.findElement(orderButton).click();
  }

  public void order() {
    this.clickOrderButton();
    this.waitForLoadList(yesButton);
    this.clickYesButton();
  }

  public void clickYesButton() {
    driver.findElement(yesButton).click();
  }

  public String orderDoneMessage() {
    return driver.findElement(doneMessage).getText();
  }

  public void makeOrder(String name, String surname, String address, String metro, String phone, String date, String rentDays, String color, String comment) {
    this.waitForLoadForm();

    enterName(name);
    enterSurname(surname);
    enterAddress(address);
    enterMetro(metro);
    enterPhone(phone);
    clickNextButton();
    enterDay(date);
    enterRent(rentDays);
    chooseColor(color);
    enterComment(comment);
    order();
  }



}
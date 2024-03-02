import static model.constants.OrderConstants.*;
import static org.hamcrest.CoreMatchers.containsString;

import model.pajeObject.MainPage;
import model.pajeObject.OrderPage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class OrderTest {
  private WebDriver driver;
  private final String name;
  private final String surname;
  private final String address;
  private final String metro;
  private final String phone;
  private final String date;
  private final String rentDays;
  private final String color;
  private final String comment;
  private static final String EXPECTED_DONE_ORDER_TEXT = "Заказ оформлен";

  public OrderTest(String name, String surname, String address, String metro, String phone,
      String date, String rentDays, String color, String comment) {
    this.name = name;
    this.surname = surname;
    this.address = address;
    this.metro = metro;
    this.phone = phone;
    this.date = date;
    this.rentDays = rentDays;
    this.color = color;
    this.comment = comment;
  }

  @Parameterized.Parameters
  public static Object[][] enterOrderData() {
    return new Object[][] {
        {NAME_USER_ONE, SURNAME_USER_ONE, ADDRESS_USER_ONE, METRO_USER_ONE, PHONE_USER_ONE, DATE_USER_ONE, RENT_DAYS_USER_ONE, COLOR_USER_ONE, COMMENT_USER_ONE},
        {NAME_USER_TWO, SURNAME_USER_TWO, ADDRESS_USER_TWO, METRO_USER_TWO, PHONE_USER_TWO, DATE_USER_TWO, RENT_DAYS_USER_TWO, COLOR_USER_TWO, COMMENT_USER_TWO},
    };
  }

  @Before
  public void setUp() {
    driver = new ChromeDriver();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  //тест с кнопкой "Заказать" вверху страницы:
  @Test
  public void checkDoneOrderButtonHeader() {
    MainPage mainPage = new MainPage(driver);
    OrderPage orderPage = new OrderPage(driver);

    mainPage.open();
    mainPage.clickCookieButton();
    mainPage.clickOrderButtonHeader();
    orderPage.makeOrder(name, surname, address, metro, phone, date, rentDays, color, comment);

    MatcherAssert.assertThat("Неправильный текст готового заказа:", orderPage.orderDoneMessage(), containsString(EXPECTED_DONE_ORDER_TEXT));
  }

  //тест с кнопкой "Заказать" в центре страницы:
  @Test
  public void checkDoneOrderButtonHow() {
    MainPage mainPage = new MainPage(driver);
    OrderPage orderPage = new OrderPage(driver);

    mainPage.open();
    mainPage.clickCookieButton();
    mainPage.clickOrderButtonHow();
    orderPage.makeOrder(name, surname, address, metro, phone, date, rentDays, color, comment);

    MatcherAssert.assertThat("Неправильный текст готового заказа:", orderPage.orderDoneMessage(), containsString(EXPECTED_DONE_ORDER_TEXT));
  }



}

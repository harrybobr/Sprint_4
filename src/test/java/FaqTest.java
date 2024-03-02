
import static model.constants.FaqConstants.*;
import static org.hamcrest.CoreMatchers.equalTo;

import model.pajeObject.MainPage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class FaqTest {

  private WebDriver driver;
  private final int questionNumber;
  private final String expectedQuestionText;
  private final String expectedAnswerText;
  public FaqTest(int questionNumber, String expectedQuestionText, String expectedAnswerText) {
    this.questionNumber = questionNumber;
    this.expectedQuestionText = expectedQuestionText;
    this.expectedAnswerText = expectedAnswerText;
  }

  @Parameterized.Parameters
  public static Object[][] getTestData() {
    return new Object[][] {
        {0, QUESTION_ONE, ANSWER_ONE},
        {1, QUESTION_TWO, ANSWER_TWO},
        {2, QUESTION_THREE, ANSWER_THREE},
        {3, QUESTION_FOUR, ANSWER_FOUR},
        {4, QUESTION_FIVE, ANSWER_FIVE},
        {5, QUESTION_SIX, ANSWER_SIX},
        {6, QUESTION_SEVEN, ANSWER_SEVEN},
        {7, QUESTION_EIGHT, ANSWER_EIGHT},
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

  @Test
  public void checkFaqText() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickCookieButton();
    mainPage.clickQuestionHeader(questionNumber);
    mainPage.waitForAnswerText(questionNumber);
    MatcherAssert.assertThat(
        "Ошибка в тексте вопроса " + questionNumber,
        mainPage.getQuestionHeader(questionNumber),
        equalTo(expectedQuestionText));
    MatcherAssert.assertThat(
        "Ошибка в тексте ответа " + questionNumber,
        mainPage.getAnswerText(questionNumber),
        equalTo(expectedAnswerText));
  }
}

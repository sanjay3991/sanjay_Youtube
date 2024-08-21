package demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        System.out.println("Setup: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterMethod(enabled = true)
    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    @Test(enabled = true)
    public void testCase01() {

        System.out.println("Start Test case: testCase01");
        driver.get("https://www.youtube.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/");

        HomePage HP = new HomePage(driver);
        HP.ClickonAbout();
        String Message = HP.AboutMessage();
        System.out.println(Message);
        System.out.println("end Test case: testCase01");
        System.out.println();
    }

    @Test(enabled = true)
    public void testCase02() {

        System.out.println("Start Test case: testCase02");
        driver.get("https://www.youtube.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/");
        HomePage HP = new HomePage(driver);
        HP.ClickonFilmsTab();
        HP.ScrollToRight();
        String Genre = HP.GetGenres();
        SoftAssert SA = new SoftAssert();
        SA.assertTrue(Genre.contains("Comedy") || Genre.contains("Animation"),
                "The genre text does not contain 'Comedy' or 'Animation'.");
        System.out.println("end Test case: testCase02");
        System.out.println();
    }

    @Test(enabled = true)
    public void testCase03() throws InterruptedException {

        System.out.println("Start Test case: testCase03");
        driver.get("https://www.youtube.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/");
        HomePage HP = new HomePage(driver);
        HP.ClickonMusicTab();
        HP.ScrollToRight();
        HP.PrintPlayList();
        String noOfTracks = HP.NoOfTracks();
        String noOfTracks1 = noOfTracks.replaceAll("\\D+", "");
        int noOfTracksInt = Integer.parseInt(noOfTracks1);
        SoftAssert SA = new SoftAssert();
        SA.assertTrue(noOfTracksInt <= 50, "Number of tracks is greater than 50");
        System.out.println(noOfTracks);
        System.out.println("end Test case: testCase03");
    }

    @Test(enabled = true)
    public void testCase04() throws InterruptedException {

        System.out.println("Start Test case: testCase04");
        driver.get("https://www.youtube.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/");
        HomePage HP = new HomePage(driver);
        HP.ClickonNewsTab();
        HP.Top3LatestNews();
        System.out.println("end Test case: testCase04");
    }

}

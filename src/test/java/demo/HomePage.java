package demo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class HomePage {
    private WebDriver driver;
    
    @FindBy(xpath = "//a[normalize-space()='About']")
    WebElement About;

    @FindBy(xpath = "(//p[contains(@class , 'lb')])[1]")
    WebElement About_Message;

    @FindBy(xpath = "//yt-formatted-string[text() = 'Films']")
    WebElement FilmsTab;

    @FindBy(xpath = "//span[contains(@class , 'grid-movie-renderer-')]")
    List<WebElement> Genre;

    @FindBy(xpath = "(//div[@class='yt-spec-touch-feedback-shape yt-spec-touch-feedback-shape--touch-response'])[7]")
    WebElement ScrollTopFilmsRight_btn;

    @FindBy(xpath = "//yt-formatted-string[text() = 'Music']")
    WebElement MusicTab;

    @FindBy(xpath = "(//div[contains(@class , 'flex-c')])[11]/descendant::h3")
    WebElement PlayListName;

    @FindBy(xpath = "(//p[@id='video-count-text'])[11]")
    WebElement NoofTracks;

    @FindBy(xpath = "//yt-formatted-string[text() = 'News']")
    WebElement NewsTab;

    @FindBy(xpath = "(//div[@id='content'])[16]/descendant::a[@id='author-text']/span")
    List<WebElement> LatestNewsHeader;

    @FindBy(xpath = "//span[@id='vote-count-middle']")
    List<WebElement> LatestNewsLikes;

    WrapperClass WC = new WrapperClass();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void ClickonAbout(){
        WC.clickElement(driver, About);
    }
    public String AboutMessage(){
        return WC.getElementText(driver,About_Message);
    }
    public void ClickonFilmsTab(){
        WC.clickElement(driver, FilmsTab);
    }
    public void ScrollToRight(){
        for (int i = 0; i < 3; i++) {
            WC.clickElement(driver, ScrollTopFilmsRight_btn);
        }
    }
    public String GetGenres(){
        return Genre.get(Genre.size()-1).getText();
    }    

    public void ClickonMusicTab(){
        WC.clickElement(driver, MusicTab);
    }
    public void PrintPlayList() throws InterruptedException{
        Thread.sleep(2000);
        System.out.println(PlayListName.getText());
    }
    public String NoOfTracks() throws InterruptedException{
        Thread.sleep(3000);
        return (WC.getElementText(driver,NoofTracks));
    }

    public void ClickonNewsTab(){
        WC.clickElement(driver, NewsTab);
    }

    public void Top3LatestNews() throws InterruptedException{
        int count =0;
        for (int index = 0; index < 3; index++) {
            String Title = WC.getTextFromElementList(driver, LatestNewsHeader , index);
            System.out.println(Title);
            String Likes = WC.getTextFromElementList(driver, LatestNewsLikes , index);
            Thread.sleep(1000);
            if (Likes != null && !Likes.trim().isEmpty()){
                count+= Integer.valueOf(Likes);
            }
            
        }
        System.out.println("Likes : "+count);
        
    }
    


}

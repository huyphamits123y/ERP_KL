package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    By username = By.xpath("//input[@id='login']");
    By password = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("//button[normalize-space()='Đăng nhập']");
    public LoginPage(){}
    public void login(String user, String pass){
        BaseTest.fill(username, user);
        BaseTest.fill(password, pass);
        BaseTest.click(loginButton);
    }
    public void open(){
        BaseTest.visit("http://192.168.1.172:3000/auth/login");
    }
    public Boolean isLoginSuccess(String user, String pass){
        BaseTest.clear(username);
        BaseTest.clear(password);
        login(user, pass);
        BaseTest.waitForUrl("/module");
        String currentUrl = BaseTest.driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        return currentUrl.contains("http://192.168.1.172:3000/module");
    }
    public Boolean isLoginFailedWithEmptyUsername(String user, String pass){
        BaseTest.clear(username);
        BaseTest.clear(password);
        login(user, pass);
        return BaseTest.getText(By.xpath("//div[@id='login-error']")).contains("Vui lòng nhập email") ;
    }
    public Boolean isLoginFailedWithEmptyPassword(String user, String pass){
        BaseTest.clear(username);
        BaseTest.clear(password);
        BaseTest.fill(username, user);
        BaseTest.fill(password, pass);
        BaseTest.click(loginButton);
        return  BaseTest.getText(By.xpath("//div[@id='password-error']")).contains("Vui lòng nhập mật khẩu");

    }
    public Boolean isLoginFailedInvalid(String user, String pass){
        BaseTest.clear(username);
        BaseTest.clear(password);
        login(user, pass);
        BaseTest.waitForPageLoaded();
        System.out.println("Text :" + BaseTest.getText(By.xpath("//section[@aria-label='Notifications alt+T']//p")));
        return BaseTest.getText(By.xpath("//section[@aria-label='Notifications alt+T']//p")).contains("Thông tin đăng nhập không hợp lệ");
    }


}

package com.pt.iade.ml.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CookieAccept {

    /*
     * This is a main method that launch a single threah, in this case we are running tests.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo");
        //Adicionar um Cookie a tua local storage
        //Cookie cookie = new Cookie("cookieName", "cookieValue");
        //driver.manage().addCookie(cookie);

        //Esperar pelo elemento, carregar sobre
        Thread.sleep(10000);
        driver.quit();
    }

    //@Test
    void testHomePage() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        Thread.sleep(5000);
        driver.quit();
    }
}

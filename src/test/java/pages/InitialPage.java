package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import storages.CookieStorage;
import storages.OpenPicUrlStorage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class InitialPage {
    @Step("Запустить UI для авторизованного пользователя")
    public InitialPage initializeUI() {
        if (CookieStorage.getCookies() != null) {
            open(OpenPicUrlStorage.getUrlPicture());
            for (Map.Entry<String, String> entry : CookieStorage.getCookies().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                getWebDriver().manage().addCookie(new Cookie(key, value));
            }
        }

        return this;
    }
}

package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import storages.TestDataStorage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DogFoodPage extends InitialPage {

    private final SelenideElement buttonAddItemToCart = $("a[href=\"/emarket/basket/put/element/" + TestDataStorage.getItemCartPrimaryId() + "/\"]");

    @Step("Открыть страницу авторизованным пользователем")
    public DogFoodPage openPageWithAuthorizedUser() {

        open("/razdely_i_tovary/molina_krabov_palochki_s_kur_dsobak_80g/korma_dlya_sobak/");

        return this;
    }

    @Step("Добавить товар в корзину")
    public DogFoodPage addItemToCart() {

        buttonAddItemToCart.click();

        return this;
    }
}

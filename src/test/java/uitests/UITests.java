package uitests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.*;
import storages.AuthDataStorage;
import storages.TestDataStorage;
import uiapisteps.CommonAPISteps;

@Tag("FullTest")
public class UITests extends UIBaseTest {

    final MainPage mainPage = new MainPage();
    final SearchPage searchPage = new SearchPage();
    final PurchasePage purchasePage = new PurchasePage();
    final CartPage cartPage = new CartPage();
    final DogFoodPage dogFoodPage = new DogFoodPage();
    final CommonAPISteps commonAPISteps = new CommonAPISteps();

    @Test
    public void loginWithCorrectCredentialsMustGreetUserTest() {

        mainPage
                .openMainPage()
                .login(AuthDataStorage.getAuthDataContainer())
                .checkLogin();
    }

    @Test
    @Tag("CartTests")
    public void addingItemToCartMustAddItemToCartTest() {

        commonAPISteps
                .receiveCookies(AuthDataStorage.getAuthDataContainer())
                .prepareCart();

        dogFoodPage.initializeUI();
        dogFoodPage.openPageWithAuthorizedUser();
        dogFoodPage.addItemToCart();

        commonAPISteps
                .checkCartItem(TestDataStorage.getItemCartPrimaryDescription());
    }

    @Test
    @Tag("CartTests")
    public void addingTwoItemsToCartMustDisplayNumberOfItemsInCartTest() {
        int quantity = (int) (Math.random() * 3) + 2;

        commonAPISteps
                .receiveCookies(AuthDataStorage.getAuthDataContainer())
                .prepareCart()
                .putItemToCart(TestDataStorage.getItemCartPrimaryId(), quantity);

        cartPage.initializeUI();
        cartPage.openCartPageWithAuthorizedUser();
        cartPage.checkFirstItemQuantity(quantity);

        commonAPISteps
                .prepareCart();
    }

    @Test
    @Tag("CartTests")
    public void deletingItemFromCartMustEmptyCartTest() {

        commonAPISteps
                .receiveCookies(AuthDataStorage.getAuthDataContainer())
                .prepareCart()
                .putItemToCart(TestDataStorage.getItemCartPrimaryId());

        cartPage.initializeUI();
        cartPage.openCartPageWithAuthorizedUser();
        cartPage.deleteFirstItem();

        commonAPISteps
                .checkCartEmpty();
    }

    @Test
    @Tag("CartTests")
    public void clearCartMustEmptyCartTest() {

        commonAPISteps
                .receiveCookies(AuthDataStorage.getAuthDataContainer())
                .prepareCart()
                .putItemToCart(TestDataStorage.getItemCartPrimaryId())
                .putItemToCart(TestDataStorage.getItemCartSecondaryId());

        cartPage.initializeUI();
        cartPage.openCartPageWithAuthorizedUser();
        cartPage.clearCart();

        commonAPISteps
                .checkCartEmpty();
    }

    @Test
    public void openingPurchasePageMustShowPurchaseFormTest() {

        commonAPISteps
                .receiveCookies(AuthDataStorage.getAuthDataContainer())
                .prepareCart()
                .putItemToCart(TestDataStorage.getItemCartPrimaryId());

        purchasePage.initializeUI();
        purchasePage.openPurchasePageWithAuthorizedUser();
        purchasePage.checkPurchaseForm();
    }

    @SuppressWarnings("TestFailedLine")
    @Test
    public void searchItemMustShowListOfItemsTest() {

        mainPage
                .openMainPage()
                .search(TestDataStorage.getSearchWord());

        searchPage
                .successfulSearchResultsCheck();
    }
}

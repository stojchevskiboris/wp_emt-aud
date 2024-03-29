package mk.ukim.finki.wpaud.selenium;
import lombok.Getter;
import mk.ukim.finki.wpaud.model.ShoppingCart;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class ShoppingCartPage extends AbstractPage {

    @FindBy(css = "tr[class=cart-item]")
    private List<WebElement> cartRows;


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public static ShoppingCartPage init(WebDriver driver) {
        return PageFactory.initElements(driver, ShoppingCartPage.class);
    }


    public void assertElements(int cartItemsNumber) {
        Assert.assertEquals("rows do not match", cartItemsNumber, this.getCartRows().size());
    }
}

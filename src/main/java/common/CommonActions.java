package common;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.switchTo;

public class CommonActions {
    public static void sleep(int mseconds) {
        try {
            Thread.sleep(mseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void switchToIFrame(SelenideElement iframe) {
        switchTo().frame(iframe);
    }

    public static void scrollToPixels(int pixels) {
        actions().scrollByAmount(0, pixels).build().perform();
    }
}

package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class TranslationPage {

    private final SelenideElement selectorAmountInput = $("[data-test-id='amount'] input");
    private final SelenideElement selectorFromInput = $("[data-test-id='from'] input");
    private final SelenideElement selectorTransferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement selectorErrorMessage = $("[data-test-id='error-notification'] .notification__content");

    public TranslationPage() {
        selectorAmountInput.shouldBe(Condition.visible, Duration.ofSeconds(15)); //ассерт проверки видимости (ожидание 15 сек)
    }

    //Заполнение полей формы для перевода средств
    private void translation(int amount, String card) {
        selectorAmountInput.setValue(String.valueOf(amount));
        selectorFromInput.setValue(card);
        selectorTransferButton.click();
    }

    //Проверка сообщения ошибки перевода
    private String errorMessage() {
        return selectorErrorMessage.shouldBe(Condition.visible, Duration.ofSeconds(15)).text();
    }

    //Успешный перевод
    public DashboardPage depositTranslation(int amount, String card) {
        translation(amount, card);
        return new DashboardPage();
    }

    //Недействительный перевод
    public String invalidDepositTranslation(int amount, String card) {
        translation(amount, card);
        return errorMessage();
    }
}

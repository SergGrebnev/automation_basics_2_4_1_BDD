package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class TranslationPage {
    public TranslationPage() {
        $("[data-test-id='amount'] input")
                .shouldBe(Condition.visible, Duration.ofSeconds(15)); //ассерт проверки видимости (ожидание 15 сек)
    }

    //Успешный перевод
    public DashboardPage DepositTranslation(int amount, String card) {
        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));
        $("[data-test-id='from'] input").setValue(card);
        $("[data-test-id='action-transfer']").click();

        return new DashboardPage();
    }

    //Недействительный перевод
    public String InvalidDepositTranslation(int amount, String card) {
        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));
        $("[data-test-id='from'] input").setValue(card);
        $("[data-test-id='action-transfer']").click();

        return $("[data-test-id='error-notification'] .notification__content").text();
    }
}

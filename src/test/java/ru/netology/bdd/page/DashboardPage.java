package ru.netology.bdd.page;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        $("[data-test-id='action-deposit']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15)); //ассерт проверки видимости (ожидание 15 сек)
    }

    //баланс на счёте карты
    public int getCardBalance(int index) {
        val text = cards.get(index).text(); //текст элемента из коллекции cards
        return extractBalance(text);
    }

    //Выбор счёта (карты) для пополнения
    public TranslationPage selectDeposit(int index) {
        cards.get(index).$("[data-test-id='action-deposit']").click();

        return new TranslationPage();
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}

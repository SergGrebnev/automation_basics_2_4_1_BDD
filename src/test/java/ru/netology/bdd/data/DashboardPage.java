package ru.netology.bdd.data;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private static ElementsCollection cards = $$(".list__item");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public DashboardPage() {
    }

    public static int getFirstCardBalance() {
        val text = cards.get(0).text(); //текст первого элемента из коллекции cards
        return extractBalance(text);
    }

    public static int getSecondCardBalance() {
        val text = cards.get(1).text(); //текст второго элемента из коллекции cards
        return extractBalance(text);
    }

    //Выбор счёта (карты) для пополнения
    public static void selectDeposit(int index) {
        cards.get(index).$("[data-test-id='action-deposit']").click();

        //поверка перехода на страницу пополнения
        $("h1")
                .shouldHave(Condition.text("Пополнение карты"), Duration.ofSeconds(15)) //ассерт проверки текста (ожидание 15 сек)
                .shouldBe(Condition.visible); //ассерт проверки видимости
    }

    //Пополнение карты
    public static void DepositReplenishment(int amount, String card) {
        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));
        $("[data-test-id='from'] input").setValue(card);
        $("[data-test-id='action-transfer']").click();

        //поверка перехода на страницу Ваши карты
        $("h1")
                .shouldHave(Condition.text("Ваши карты"), Duration.ofSeconds(15)) //ассерт проверки текста (ожидание 15 сек)
                .shouldBe(Condition.visible); //ассерт проверки видимости
    }


    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}

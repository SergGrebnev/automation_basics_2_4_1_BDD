package ru.netology.bdd.data;


import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {


    //Авторизация пользователя user
    public static void authorizationOnLoginPage(DataHelper.User user) {
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();

        //поверка успешной авторизации
        $("h2")
                .shouldHave(Condition.text("Интернет Банк"), Duration.ofSeconds(15)) //ассерт проверки текста (ожидание 15 сек)
                .shouldBe(Condition.visible); //ассерт проверки видимости
    }


}

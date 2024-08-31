package ru.netology.bdd.data;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class Verification {

    //Верификация пользователя
    public static void verificationCode(DataHelper.VerificationCode code) {
        $("[data-test-id='code'] input").setValue(code.getCode());
        $("[data-test-id='action-verify']").click();

        //поверка успешной верификации
        $("h1")
                .shouldHave(Condition.text("Ваши карты"), Duration.ofSeconds(15)) //ассерт проверки текста (ожидание 15 сек)
                .shouldBe(Condition.visible); //ассерт проверки видимости
    }
}

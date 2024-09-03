package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;
import ru.netology.bdd.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class Verification {
    public Verification() {
        $("[data-test-id='code'] input")
                .shouldBe(Condition.visible, Duration.ofSeconds(15)); //ассерт проверки видимости (ожидание 15 сек)
    }


    //Верификация пользователя
    public DashboardPage verificationCode(DataHelper.VerificationCode code) {
        $("[data-test-id='code'] input").setValue(code.getCode());
        $("[data-test-id='action-verify']").click();

        return new DashboardPage();
    }
}

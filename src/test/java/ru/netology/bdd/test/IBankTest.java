//Для запуска тестируемого приложения, находясь в корне проекта,
// выполните в терминале команду: java -jar ./artifacts/app-ibank-build-for-testers.jar

// Используется selenide


package ru.netology.bdd.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.data.DashboardPage;
import ru.netology.bdd.data.DataHelper;
import ru.netology.bdd.data.LoginPage;
import ru.netology.bdd.data.Verification;

import static com.codeborne.selenide.Selenide.open;

public class IBankTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");

    }

    //перевод с первой карты на вторую
    @Test
    @DisplayName("Should successfully transfer funds")
    void shouldSuccessfullyTransferFunds() {
        var user = DataHelper.getUser(); //Получение данных пользователя
        LoginPage.authorizationOnLoginPage(user); //Авторизация пользователя в сервисе

        var code = DataHelper.getVerificationCode(user); //Получение кода верификации
        Verification.verificationCode(code); //Верификация пользователя в сервисе

        var balanceFirstCard = DashboardPage.getFirstCardBalance(); //Баланс на первой карте
        var balanceSecondCard = DashboardPage.getSecondCardBalance(); //Баланс на второй карте

        DashboardPage.selectDeposit(1); //Выбор счёта (карты) для пополнения

        //Перевести 1000 р. с первой карты (0 - певая, 1 - вторая)
        int amount = 1000;
        DashboardPage.DepositReplenishment(amount, DataHelper.getNumberCard(0));

        int expectedFirst = balanceFirstCard - amount;//баланс на первой карте уменьшился
        int expectedSecond = balanceSecondCard + amount;//баланс на второй карте увеличился

        Assertions.assertEquals(expectedFirst, DashboardPage.getFirstCardBalance());
        Assertions.assertEquals(expectedSecond, DashboardPage.getSecondCardBalance());


    }
}

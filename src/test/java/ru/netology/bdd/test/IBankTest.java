//Для запуска тестируемого приложения, находясь в корне проекта,
// выполните в терминале команду: java -jar ./artifacts/app-ibank-build-for-testers.jar

// Используется selenide


package ru.netology.bdd.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.data.DataHelper;
import ru.netology.bdd.page.DashboardPage;
import ru.netology.bdd.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class IBankTest {

    DashboardPage dashboardPage;
    int balanceFirstCard;
    int balanceSecondCard;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");

        var user = DataHelper.getUser(); //Получение данных пользователя
        var loginPage = new LoginPage();
        var verification = loginPage.authorizationOnLoginPage(user); //Авторизация пользователя в сервисе

        var code = DataHelper.getVerificationCode(user); //Получение кода верификации
        dashboardPage = verification.verificationCode(code); //Верификация пользователя в сервисе

        balanceFirstCard = dashboardPage.getCardBalance(0); //Баланс на первой карте до перевода
        balanceSecondCard = dashboardPage.getCardBalance(1); //Баланс на второй карте до перевода


    }

    //перевод с первой карты на вторую
    @Test
    @DisplayName("Should successfully transfer funds")
    void shouldSuccessfullyTransferFunds() {

        var translationPage = dashboardPage.selectDeposit(1); //Выбор счёта (карты) для пополнения (0 - певая, 1 - вторая)

        //Перевести валидную сумму с первой карты (0 - певая, 1 - вторая)
        int amount = DataHelper.getValidAmount(balanceFirstCard);
        dashboardPage = translationPage.DepositTranslation(amount, DataHelper.getNumberCard(0));

        int expectedFirst = balanceFirstCard - amount;//баланс на первой карте уменьшился
        int expectedSecond = balanceSecondCard + amount;//баланс на второй карте увеличился

        assertAll(
                () -> assertEquals(expectedFirst, dashboardPage.getCardBalance(0)), //проверка баланса на первой карте
                () -> assertEquals(expectedSecond, dashboardPage.getCardBalance(1))); //проверка баланса на второй карте


    }


//    @Test
//    @DisplayName("Transfer amount more balance")
//        //сумма перевода больше баланса
//    void transferAmountMoreBalance() {
//
//        var translationPage = dashboardPage.selectDeposit(1); //Выбор счёта (карты) для пополнения (0 - певая, 1 - вторая)
//
//        //Перевести невалидную сумму с первой карты (0 - певая, 1 - вторая)
//        int amount = DataHelper.getInvalidAmount(balanceFirstCard);
//        String errorMessage = translationPage.InvalidDepositTranslation(amount, DataHelper.getNumberCard(0));
//
//        assertEquals("Недостаточно средств на карте", errorMessage);
//
//    }
}

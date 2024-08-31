package ru.netology.bdd.data;

import lombok.Value;

public class DataHelper {

    /*    * login: 'vasya'
     * password: 'qwerty123'
     * verification code (hardcoded): '12345'
     * cards:
     * first:
     * number: '5559 0000 0000 0001'
     * balance: 10 000 RUB
     * second:
     * number: '5559 0000 0000 0002'
     * balance: 10 000 RUB
     */

    private DataHelper() {
    }

    @Value
    public static class User { //шаблон для пользователя
        private String login;
        private String password;

    }

    public static User getUser() { //метод получения (создания) пользовательских данных
        return new User("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode { //Шаблон для кода верификации
        private String code;
    }

    public static VerificationCode getVerificationCode(User user) { //метод получения (создания) кода верификации
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        private String[] card;
    }

    public static String getNumberCard(int index) {
        var cards = new Card(new String[]{"5559 0000 0000 0001", "5559 0000 0000 0002"});
        return cards.card[index];
    }

}

package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // проверка оплаты с карты
    @Test
    public void shouldPlayAboveTheMinimum() { //ошибка лимита списания, баланс уходит в минус
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(8_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPlayWithinTheLimit() {   //ошибки нет
        SavingAccount account = new SavingAccount(
                9_000,
                1_000,
                10_000,
                5
        );

        account.pay(9_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    // тесты на пополнение баланса
    @Test
    public void shouldAddLessThanMaxBalance() { // Не добавляет сумму пополнения
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddGreaterThanMaxBalance() { // Не добавляет сумму пополнения: попытка выйти за MAXлимит
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(9_000);

        Assertions.assertEquals(2_000 + 9_000, account.getBalance());
    }

    @Test
    public void shouldAddEqualThanMaxBalance() { // Не добавляет сумму пополнения в рамках верхнего лимита
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    //тест исключения IllegalArgumentException
    @Test
    public void shouldAddExceptionThanMaxBalance() { //умирает выводя ошибку.
        SavingAccount account = new SavingAccount(
                10_000,
                1_000,
                10_000,
                -1
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.setRate(-1));

    }

    //Операция расчёта процентов на остаток счёта
    @Test
    public void percenrageShouldBeCalculated() { //тест проходит верно
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 15);
        
        account.yearChange();
        int actual = account.yearChange();
        int expected = 300;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void percenrageShouldBeCalculated1() { //тест проходит верно
        SavingAccount account = new SavingAccount(10_000, 1_000, 10_000, 1);

        account.yearChange();
        int actual = account.yearChange();
        int expected = 100;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void percenrageShouldBeCalculated2() {
        SavingAccount account = new SavingAccount(10_000, 1_000, 10_000, 0);

        account.yearChange();
        int actual = account.yearChange();
        int expected = 0;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void percenrageShouldBeCalculated3() {
        SavingAccount account = new SavingAccount(7_643, 1_000, 10_000, 10);

        account.yearChange();
        int actual = account.yearChange();
        int expected = 760;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void percenrageShouldBeCalculated4() {
        SavingAccount account = new SavingAccount(0, 1_000, 10_000, 10);

        account.yearChange();
        int actual = account.yearChange();
        int expected = 0;

        Assertions.assertEquals(expected, actual);
    }
}

package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // проверка оплаты с карты
    @Test
    public void shouldPlayAboveTheMinimum() { //ошибка лимита списания, баланс уходит в минус
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.pay(8_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPlayWithinTheLimit() { // ошибка, происходит списание превышающее minBalance
        SavingAccount account = new SavingAccount(9_000, 1_000, 10_000, 5);

        account.pay(9_000);

        Assertions.assertEquals(9_000, account.getBalance());
    }

    @Test
    public void shouldPlayWithinTheLimitTest() {
        SavingAccount account = new SavingAccount(9_000, 1_000, 10_000, 5);

        account.pay(6_750);

        Assertions.assertEquals(2_250, account.getBalance());
    }


    @Test
    public void shouldPlayWithinNegativeLimit() {
        SavingAccount account = new SavingAccount(9_000, 1_000, 10_000, 5);

        account.pay(-9);

        Assertions.assertEquals(9_000, account.getBalance());
    }

    // тесты на пополнение баланса
    @Test
    public void shouldAddLessThanMaxBalance() { // Добавляет сумму пополнения, но не учитывает первоначальный баланс
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());

    }

    @Test
    public void shouldAddEqualThanMaxBalance() { // Не добавляет сумму пополнения не превышающую рамки верхнего лимита
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test
    public void shouldAddGreaterThanMaxBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(9_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddGreaterZeroBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddGreaterNegativeBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(-123);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    //тест исключения IllegalArgumentException
    @Test //Проверка исключения с процентной ставкой <0
    public void SavingAccount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    3_000,
                    1_000,
                    10_000,
                    -5
            );
        });
    }

    @Test //Проверка исключения с начальным балансом > макс баланса
    public void balanceGreaterThanMaxTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    13_000,
                    1_000,
                    10_000,
                    15
            );
        });
    }

    @Test //Проверка исключения с отрицательным минимальным балансом
    public void balancenegativeMinTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    500,
                    -1_000,
                    10_000,
                    5
            );
        });
    }

    @Test //Проверка исключения с отрицательным максимальным балансом
    public void balanceLessThanMaxTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    500,
                    1_000,
                    -10_000,
                    5
            );
        });
    }

    @Test //Проверка исключения с минимальным балансом > макс баланса
    public void balanceMinGreatMaxTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    3_000,
                    10_000,
                    1_000,
                    15
            );
        });
    }

    @Test //Проверка исключения с начальным балансом < минимального баланса
    public void initialBalanceMinNegativeTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -3,
                    0,
                    10_000,
                    15
            );
        });
    }

    @Test //Проверка исключения с начальным балансом > максимального баланса
    public void initialBalanceGreatMaxBalanceTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    30_000,
                    1_000,
                    10_000,
                    15
            );
        });
    }

    //Операция расчёта процентов на остаток счёта
    @Test
    public void percenrageShouldBeCalculated() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 15);

        account.yearChange();
        int actual = account.yearChange();
        int expected = 300;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void percenrageShouldBeCalculated1() {
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
}

package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {
    CreditAccount account = new CreditAccount(
            0,
            5_000,
            15
    );
    Account account2 = new SavingAccount(
            10_000,
            1_000,
            10_000,
            5

    );

    @Test
    public void shouldAddToPositiveBalance() {
        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void rateLessThanZero() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                -1
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.getRate();
        });
    }

    @Test
    public void rateChangedLessThanZeroCheckInMethod() {
        account.setRate(-1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.yearChange();
        });
    }


    @Test
    public void purchaseValidBorderLimit2() {
        account.pay(4_999);

        Assertions.assertEquals(-4_999, account.getBalance());
    }

    @Test
    public void purchaseValidBorderLimit1() {
        account.pay(5_000);

        Assertions.assertEquals(-5_000, account.getBalance());
    }

    @Test
    public void purchaseInvalidBorderLimit1() {
        account.pay(5_001);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void purchaseInvalidBorderLimit2() {
        account.pay(5_002);

        Assertions.assertEquals(0, account.getBalance());
    }


    @Test
    public void addTransferToCredit() {
        Bank bank = new Bank();
        account.pay(5_000);

        bank.transfer(account2, account, 10_000);

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertEquals(0, account2.getBalance());
    }

    @Test
    public void yearZeroBalance() {

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void yearOneNegativeBalance() {
        account.pay(200);

        Assertions.assertEquals(-30, account.yearChange());

    }

    @Test
    public void yearOneAflerTheCreditLimit() {
        account.pay(5000);

        Assertions.assertEquals(-750, account.yearChange());

    }
    @Test
    public void testYearChangeWithPositiveBalance() {

        CreditAccount account = new CreditAccount(200, 5000, 15);
        int result = account.yearChange();
        Assertions.assertEquals(30, result);
    }

    @Test
    public void testYearChangeWithZeroBalance() {

        CreditAccount account = new CreditAccount(0, 5000, 15);
        int result = account.yearChange();
        Assertions.assertEquals(0, result);
    }
}

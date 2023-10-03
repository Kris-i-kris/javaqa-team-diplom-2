package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                -10,
                5_000,
                15
        );

        account.add(100_000);

        Assertions.assertEquals(100_000, account.getBalance());
    }
}

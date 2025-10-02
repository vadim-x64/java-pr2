package com.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest3 {

    @Test
    public void testFindTopExpenses() {
        Transaction t1 = new Transaction("01-01-2024", -500.0, "Комунальні");
        Transaction t2 = new Transaction("02-01-2024", -1500.0, "Оренда");
        Transaction t3 = new Transaction("03-01-2024", 2000.0, "Зарплата"); // дохід
        Transaction t4 = new Transaction("04-01-2024", -300.0, "Продукти");
        Transaction t5 = new Transaction("05-01-2024", -100.0, "Транспорт");
        Transaction t6 = new Transaction("06-01-2024", -2000.0, "Велика покупка");

        List<Transaction> transactions = Arrays.asList(t1, t2, t3, t4, t5, t6);
        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        List<Transaction> topExpenses = analyzer.findTopExpenses();

        for (Transaction expense : topExpenses) {
            Assertions.assertTrue(expense.getAmount() < 0, "У топ витратах повинні бути тільки негативні суми.");
        }

        Assertions.assertEquals(-2000.0, topExpenses.get(0).getAmount(), "Перша витрата повинна бути найбільшою.");
        Assertions.assertTrue(topExpenses.size() <= 10, "Повинно бути не більше 10 витрат.");
    }
}
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class AccountDynamicTest {
    Account account = new Account(2000);
    CreditCard creditCard = account.createCreditCard(CreditCard.CardType.NORMAL_CARD);

    double price, expectedBalance;

    @TestFactory
    public List<DynamicTest> dynamicPurchaseTest(){
        List<Double> prices = new ArrayList<>(Arrays.asList(800.0, 400.0, 50.0));
        List<Double> expectedBalances = new ArrayList<>(Arrays.asList(1320.0, 1660.0, 1957.5));
        List<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < prices.size(); i++){
            price = prices.get(i);
            expectedBalance = expectedBalances.get(i);

            account.setBalance(2000);
            creditCard.purchase(price);

            Executable exec = () -> assertThat(creditCard.getBalance(), closeTo(expectedBalance, 0.001));
            String testName = "dynamicPurchaseTest" + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(testName, exec);
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }

}

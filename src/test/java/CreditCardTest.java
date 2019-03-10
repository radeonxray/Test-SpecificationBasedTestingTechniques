import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("ParameterizedTests")
public class CreditCardTest {

    @DisplayName("getDiscount")
    @ParameterizedTest(name = "Calculate discount from different cardTypes")
    @MethodSource("creditCardTypeProvider")
    void getDiscount(CreditCard.CardType cardType, double discount)
    {
        Account account = new Account(8585);
        CreditCard creditCard = account.createCreditCard(cardType);
        assertThat(creditCard.getDiscount(), closeTo(discount, 0.001));
    }

    private static Stream creditCardTypeProvider(){
        return Stream.of(
            Arguments.of(CreditCard.CardType.LOYALTY_CARD, 0.1),
            Arguments.of(CreditCard.CardType.COUPON_CARD, 0.2),
            Arguments.of(CreditCard.CardType.NORMAL_CARD, 0.15)
        );
    }




    @DisplayName("purchase")
    @ParameterizedTest(name = "Check discount gets applied to purchases")
    @ValueSource(strings = {"800,1320", "400,1660", "50,1957.5"})
    void purchase(String values){
        double price = Double.parseDouble(values.split(",")[0]);
        double expectedBalance = Double.parseDouble(values.split(",")[1]);

        Account account = new Account(2000);
        CreditCard creditCard = account.createCreditCard(CreditCard.CardType.NORMAL_CARD);
        creditCard.purchase(price);
        assertThat(creditCard.getBalance(), closeTo(expectedBalance, 0.001));
    }




    @DisplayName("purchaseCSV")
    @ParameterizedTest(name = "CSV + Check discount gets applied to purchases")
    @CsvSource({"800,1320", "400,1660", "50,1957.5"})
    void purchaseCSV(String strPrice, String strExpectedBalance){
        double price = Double.parseDouble(strPrice);
        double expectedBalance = Double.parseDouble(strExpectedBalance);

        Account account = new Account(2000);
        CreditCard creditCard = account.createCreditCard(CreditCard.CardType.NORMAL_CARD);
        creditCard.purchase(price);
        assertThat(creditCard.getBalance(), closeTo(expectedBalance, 0.001));
    }



    @DisplayName("purchaseCSVFile")
    @ParameterizedTest(name = "CSV File + Check discount gets applied to purchases")
    @CsvFileSource(resources = "file.csv")
    void purchaseCSVFile(String strPrice, String strExpectedBalance){
        double price = Double.parseDouble(strPrice);
        double expectedBalance = Double.parseDouble(strExpectedBalance);

        Account account = new Account(2000);
        CreditCard creditCard = account.createCreditCard(CreditCard.CardType.NORMAL_CARD);
        creditCard.purchase(price);
        assertThat(creditCard.getBalance(), closeTo(expectedBalance, 0.001));
    }




    @DisplayName("RepeatedTest")
    @RepeatedTest(value = 3)
    void repeatedTest(){
        Account account = new Account(2000);
        CreditCard creditCard = account.createCreditCard(CreditCard.CardType.NORMAL_CARD);
        assertThat(creditCard.getBalance(), equalTo(2000));
    }



}
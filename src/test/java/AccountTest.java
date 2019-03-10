import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class AccountTest {

    @Test
    void getMonthlyInterestRate_low() {
        Account account = new Account(85);
        double interest = account.getMonthlyInterest();
        assertThat(interest, closeTo(2.55, 0.001));
    }

    @Test
    void getMonthlyInterestRate_lowEdge() {
        Account account = new Account(100);
        double interest = account.getMonthlyInterest();
        assertThat(interest, closeTo(3, 0.001));
    }

    @Test
    void getMonthlyInterestRate_medium() {
        Account account = new Account(885);
        double interest = account.getMonthlyInterest();
        assertThat(interest, closeTo(44.25, 0.001));
    }

    @Test
    void getMonthlyInterestRate_mediumEdge() {
        Account account = new Account(1000);
        double interest = account.getMonthlyInterest();
        assertThat(interest, closeTo(50, 0.001));
    }

    @Test
    void getMonthlyInterestRate_maxEdge() {
        Account account = new Account(1001);
        double interest = account.getMonthlyInterest();
        assertThat(interest, closeTo(70.07, 0.001));
    }

    @Test
    void getMonthlyInterestRate_max() {
        Account account = new Account(8585);
        double interest = account.getMonthlyInterest();
        assertThat(interest, closeTo(600.95, 0.001));
    }

    @Test
    void getMonthlyInterestRate_negative() {
        Account account = new Account(-85);
        double interest = account.getMonthlyInterest();
        assertThat(interest, closeTo(-2.55, 0.001));
    }


    // CalculateInterestRate
    @Test
    void calculateInterestRate_low() {
        Account account = new Account(85);
        double interest = account.calculateInterestRate();
        assertThat(interest, closeTo(1.03, 0.001));
    }

    @Test
    void calculateInterestRate_lowEdge() {
        Account account = new Account(100);
        double interest = account.calculateInterestRate();
        assertThat(interest, closeTo(1.03, 0.001));
    }

    @Test
    void calculateInterestRate_medium() {
        Account account = new Account(885);
        double interest = account.calculateInterestRate();
        assertThat(interest, closeTo(1.05, 0.001));
    }

    @Test
    void calculateInterestRate_mediumEdge() {
        Account account = new Account(1000);
        double interest = account.calculateInterestRate();
        assertThat(interest, closeTo(1.05, 0.001));
    }

    @Test
    void calculateInterestRate_maxEdge() {
        Account account = new Account(1001);
        double interest = account.calculateInterestRate();
        assertThat(interest, closeTo(1.07, 0.001));
    }

    @Test
    void calculateInterestRate_max() {
        Account account = new Account(8585);
        double interest = account.calculateInterestRate();
        assertThat(interest, closeTo(1.07, 0.001));
    }

    @Test
    void calculateInterestRate_negative() {
        Account account = new Account(-85);
        double interest = account.calculateInterestRate();
        assertThat(interest, closeTo(1.03, 0.001));
    }

    @DisplayName("Transfer Between two accounts")
    @Test
    void TransferBetweenAccountsTest(){
        Account account = new Account(2000);
        Account savingsAccount = new Account(0);

        account.transferBetweenAccounts(account,savingsAccount,1250);

        assertThat(account.getBalance(), is(750.0));
        assertThat(savingsAccount.getBalance(), is(1250.0));
    }

    @DisplayName("Transfer from Account - Check amountLeft")
    @ParameterizedTest(name = "Check that the remaining amount is correct when transffered from account")
    @ValueSource(strings = {"800,1200", "100,1900", "1500,500", "2000,0"})
    void AmountLeftAfterTransferTest(String values){
        double amountWithDrawn = Double.parseDouble(values.split(",")[0]);
        double amountLeft = Double.parseDouble(values.split(",")[1]);

        Account account = new Account(2000);
        Account savingsAccount = new Account(0);

        account.transferBetweenAccounts(account,savingsAccount,amountWithDrawn);

        assertThat(account.getBalance(), is(amountLeft));
    }




}
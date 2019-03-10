import java.util.ArrayList;

public class Account {
    private ArrayList<CreditCard> creditCards;
    private double balance;

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public Account(double balance) {
        this.creditCards = new ArrayList<CreditCard>();
        this.balance = balance;
    }

    public double calculateInterestRate() {
        if (balance <= 100){
            return 1.03;
        }
        else if (balance <= 1000){
            return 1.05;
        }
        return 1.07;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getMonthlyInterest(){
        return (balance * calculateInterestRate()) - balance;
    }

    //Simplified :-)
    public double getYearlyInterest(){
        return ((balance * calculateInterestRate()) - balance) * 12;
    }

    public CreditCard createCreditCard(CreditCard.CardType cardType){
        CreditCard newCreditCard = new CreditCard(cardType, this);
        creditCards.add(newCreditCard);
        return newCreditCard;
    }

    public double transferBetweenAccounts(Account fromAccount, Account toAccount, double amountToTransfer){
        if(fromAccount.getBalance() >= amountToTransfer){
            fromAccount.withdraw(amountToTransfer);
            toAccount.deposit(amountToTransfer);
        }

        return fromAccount.getBalance();
    }
}

public class ATM {
    private CreditCard creditCardInserted;

    public void insertCreditCard(CreditCard newCreditCard) throws Exception {
        if (creditCardInserted != null){
            throw new Exception("You push the card into the ATM, but a card is already inserted...");
        }
        this.creditCardInserted = newCreditCard;
    }

    public void extractCreditCard(){
        this.creditCardInserted = null;
    }

    public void depositAmount(double amount){
        this.creditCardInserted.deposit(amount);
    }

    public void withdrawAmount(double amount){
        this.creditCardInserted.withdraw(amount);
    }

    public double showBalance(){
        return this.creditCardInserted.getBalance();
    }

    public double showMonthlyInterest(){
        return this.creditCardInserted.getMonthlyInterest();
    }

    public double showYearlyInterestRate(){
        return this.creditCardInserted.getYearlyInterest();
    }

    public double transferBetweenAccounts(Account fromAccount, Account toAccount, double amountToTransfer){
        return this.transferBetweenAccounts(fromAccount,toAccount,amountToTransfer);
    }


}

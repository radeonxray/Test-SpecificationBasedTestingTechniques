import java.util.Calendar;

public class CreditCard {
    public static enum CardType{ LOYALTY_CARD, NORMAL_CARD, COUPON_CARD};
    private CardType ownCardType;
    private Calendar creationDate;
    private double discount;
    private Account attachedAccount;

    public CreditCard(CardType cardType, Account attachedAccount) {
        this.creationDate = Calendar.getInstance();
        this.ownCardType = cardType;
        this.attachedAccount = attachedAccount;
        this.discount = 0.1;

        if (cardType.equals(CardType.NORMAL_CARD)){
            this.discount = 0.15;
        } else if(cardType.equals(CardType.COUPON_CARD)){
            this.discount = 0.2;
        }
    }

    public double getDiscount() {
        if(ownCardType.equals(CardType.LOYALTY_CARD)){
            return discount;
        }
        Calendar today = Calendar.getInstance();
        boolean sameDay = creationDate.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && creationDate.get(Calendar.YEAR) == today.get(Calendar.YEAR);
        return (sameDay ? this.discount : 0);
    }

    public void purchase(double price){
        double discountedPrice = (1 - getDiscount()) * price;
        attachedAccount.withdraw(discountedPrice);
    }

    public void deposit(double amount){
        this.attachedAccount.deposit(amount);
    }

    public void withdraw(double amount){
        this.attachedAccount.withdraw(amount);
    }

    public double getBalance(){
        return this.attachedAccount.getBalance();
    }

    public double getMonthlyInterest(){
        return this.attachedAccount.getMonthlyInterest();
    }

    public double getYearlyInterest(){
        return this.attachedAccount.getYearlyInterest();
    }


}

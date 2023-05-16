import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * bank: the bank class stores a balance of USD and is capable of updating its balance.
 */
public class bank {
    public bank(String name, BigDecimal balance){
        this.name = name;
        this.setBalance(balance);
    }
    /**
     * balance: a BigDecimal that stores the balance within th bank class.
     */
    private BigDecimal balance;
    private String name;
    public String getName(){return name;}

    /**
     * setBalance: sets the balance to the passed big decimal amount and sets the scale of the balance.
     * @param amount a bigDecimal that is to be assigned to the balance.
     */
    public void setBalance(BigDecimal amount) {
        balance = amount.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * getBalance: returns the balance member variable.
     * @return returns the BigDecimal balance member variable.
     */
    public BigDecimal getBalance(){
        return balance;
    }

    /**
     * updateBalance: updates the balance member variable depending on the passed parameters.
     * @param sign an int that determines what kind of transaction it is(withdrawal,deposit)
     * @param amount a Big decimal that represents that amount to be added or subtracted from balance
     * @return returns 0 or 1 depending on if the transaction exceeds the balance
     */
    public int updateBalance(int sign,BigDecimal amount){
        if(amount.compareTo(balance) == -1 && sign == 0){
            setBalance(balance.subtract(amount));
            return 1;
        }else if(sign == 1){
            setBalance(balance.add(amount));
            return 1;
        }
        return 0;
    }
}

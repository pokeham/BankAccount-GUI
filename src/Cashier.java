import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Cashier: The Cashier Class uses the bank class and manipulates the total amount stored in the bank class
 * It sets the exchange rate for USD to SWD and  calculates change.
 */
public class Cashier {
    public Cashier(String rate){
        exchangeRate = Float.parseFloat(rate);
    }
    /**
     * exchangeRate: the exchange rate between usd and swd.
     */
    private static float exchangeRate;

    /**
     * setExchangeRate: sets the member variable exchangeRate to the passed parameter.
     * @param exchangeRate2 a float to be assigned to the member variable exchangeRate.
     */
    public static void setExchangeRate(float exchangeRate2) {
        exchangeRate = exchangeRate2;
    }

    /**
     * getExchangeRate: returns the member variable exchange rate.
     * @return returns the member variable exchange rate.
     */
    public static float getExchangeRate() {
        return exchangeRate;
    }

    /**
     * calculateChange: creates a hashmap to store change then uses the parameter SWD to determine if the user wants to withdrawal SWD or USD
     * then adds the respective Keys and Values. it then checks if the Key at a given entry subtracting the amount is greater than 0.00.
     * If so the value at the given key is incremented by 1. If the given entry key is subracting the amount is less than 0.00 the next key is tried until it satisfies teh conditional.
     * @param amount a BigDecimal which represents the amount to be broken into change
     * @param SWD a boolean that determines if the user wants to withdrawal SWD or USD
     * @return returns a Map of the calculated change.
     */
    public Map<BigDecimal,Integer> calculateChange (BigDecimal amount, boolean SWD) {
        amount.setScale(2, RoundingMode.HALF_UP);
        Map<BigDecimal,Integer> map = new LinkedHashMap<BigDecimal,Integer>();
        if(SWD){
            map.put(new BigDecimal("25.00").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("10.00").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("5.00").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("1.00").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("0.20").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("0.08").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("0.05").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("0.01").setScale(2,RoundingMode.HALF_UP), 0);
            for(Map.Entry<BigDecimal,Integer> money : map.entrySet()){
                BigDecimal key = money.getKey();

                while((amount.subtract(key)).compareTo(new BigDecimal(0.00)) >= 0){
                    money.setValue(money.getValue()+1);
                    amount = amount.subtract(key).setScale(2,RoundingMode.HALF_UP);
                }
            }

        }else{
            map.put(new BigDecimal("20.00").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("10.00").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("5.00").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("1.00").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("0.25").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("0.10").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("0.05").setScale(2,RoundingMode.HALF_UP), 0);
            map.put(new BigDecimal("0.01").setScale(2,RoundingMode.HALF_UP), 0);
            for(Map.Entry<BigDecimal,Integer> money : map.entrySet()){
                BigDecimal key = money.getKey();
                while((amount.subtract(key)).compareTo(new BigDecimal(0.00)) >= 0){
                    money.setValue(money.getValue()+1);
                    amount = amount.subtract(key).setScale(2,RoundingMode.HALF_UP);;
                }

            }
        }
        return map;
    }

    /**
     * transaction: updates the amount in the passed bank dependent on the parameters.
     * @param amount a float of the desired amount to be added or subtracted from the total stored in the bank.
     * @param account a bank class that is to be deposited to or withdrawn from.
     * @param SWD a boolean that determines the type of currency being deposited or withdrawn
     * @param sign an int that determines if this transaction is a withdrawal or a deposit.
     * @return returns a 0 or 1 depending on if the withdrawal is too large.
     */
    public int transaction(float amount, bank account, boolean SWD,int sign){
        if(SWD){
            amount = amount/exchangeRate;
        }
        BigDecimal transaction_amount = new BigDecimal(amount);
        transaction_amount.setScale(2,RoundingMode.HALF_UP);
        if(account.updateBalance(sign,transaction_amount) == 1) return 1;
        else return 0;
    }

}



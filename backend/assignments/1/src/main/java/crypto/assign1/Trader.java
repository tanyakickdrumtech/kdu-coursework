package crypto.assign1;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a trader participating in a cryptocurrency trading system.
 */
public class Trader {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String walletAddress;
    private Map<String, Pair<Long, Double>> portfolio;

    /**
     * Constructs a new Trader with the specified details.
     *
     * @param firstName     The first name of the trader.
     * @param lastName      The last name of the trader.
     * @param phoneNumber   The phone number of the trader.
     * @param walletAddress The wallet address of the trader.
     */
    public Trader(String firstName, String lastName, String phoneNumber, String walletAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.walletAddress = walletAddress;
        this.portfolio = new HashMap<>();
    }

    /**
     * Gets the first name of the trader.
     *
     * @return The first name of the trader.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the trader.
     *
     * @return The last name of the trader.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the phone number of the trader.
     *
     * @return The phone number of the trader.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the wallet address of the trader.
     *
     * @return The wallet address of the trader.
     */
    public String getWalletAddress() {
        return walletAddress;
    }

    /**
     * Sets the portfolio of the trader.
     *
     * @param portfolio The portfolio to set.
     */
    public void setPortfolio(Map<String, Pair<Long, Double>> portfolio) {
        this.portfolio = portfolio;
    }

    /**
     * Gets the portfolio of the trader.
     *
     * @return The portfolio of the trader.
     */
    public Map<String, Pair<Long, Double>> getPortfolio() {
        return portfolio;
    }

    /**
     * Updates the portfolio of the trader with the given symbol and quantity.
     * If the symbol is already in the portfolio, the quantity is added to the existing value.
     * If the symbol is not in the portfolio, a new entry is created.
     *
     * @param symbol   The symbol of the cryptocurrency.
     * @param quantity The quantity to update in the portfolio.
     */
    public void updatePortFolio(String symbol, long quantity) {
        if (this.portfolio.containsKey(symbol)) {
            Pair<Long, Double> pair = this.portfolio.get(symbol);
            pair.setFirst(pair.getFirst() + quantity);
            this.portfolio.put(symbol, pair);
        } else {
            this.portfolio.put(symbol, new Pair<>(quantity, 0D));
        }
    }

    /**
     * Returns a string representation of the trader.
     *
     * @return A string representation of the trader.
     */
    @Override
    public String toString() {
        return "Trader{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                '}';
    }
}

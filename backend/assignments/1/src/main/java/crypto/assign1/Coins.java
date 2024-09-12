package crypto.assign1;

/**
 * Represents information about a cryptocurrency coin, including rank, name, symbol, price, and circulating supply.
 */
public class Coins {
    private int rank;
    private String name;
    private String symbol;
    private double price;
    private long circulatingSupply;

    /**
     * Constructor for creating a new Coin object.
     *
     * @param rank              The rank of the coin.
     * @param name              The name of the coin.
     * @param symbol            The symbol of the coin.
     * @param price             The price of the coin.
     * @param circulatingSupply The circulating supply of the coin.
     */
    public Coins(int rank, String name, String symbol, double price, long circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
    }

    /**
     * Retrieves the rank of the coin.
     *
     * @return The rank of the coin.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Retrieves the name of the coin.
     *
     * @return The name of the coin.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the coin.
     *
     * @return The price of the coin.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Retrieves the symbol of the coin.
     *
     * @return The symbol of the coin.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Retrieves the circulating supply of the coin.
     *
     * @return The circulating supply of the coin.
     */
    public long getCirculatingSupply() {
        return circulatingSupply;
    }

    /**
     * Sets the price of the coin.
     *
     * @param price The new price of the coin.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the circulating supply of the coin.
     *
     * @param circulatingSupply The new circulating supply of the coin.
     */
    public void setCirculatingSupply(long circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    /**
     * Updates the circulating supply of the coin by adding a specified quantity.
     *
     * @param quantity The quantity to be added to the circulating supply.
     */
    public void updateCS(long quantity) {
        this.circulatingSupply += quantity;
    }

    /**
     * Generates a string representation of the Coin object.
     *
     * @return A string representation of the Coin object.
     */
    @Override
    public String toString() {
        return "Coin{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", circulatingSupply=" + circulatingSupply +
                '}';
    }

    public String getCoinName() {
        return this.getName();
    }

    public String getCoinSymbol() {
        return this.getSymbol();
    }

}

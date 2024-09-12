package crypto.assign1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Represents a cryptocurrency trading system with various operations.
 */

public class CryptoTradingSystem {

    private static final Logger logger = LoggerFactory.getLogger(CryptoTradingSystem.class);
    protected static Map<String, Coins> symbolToCoin = new HashMap<>();
    protected static Map<String, String> coinNameToSymbol = new HashMap<>();
    protected static Map<String, Trader> walletToTrader = new HashMap<>();
    protected static Map<String, Map<String, List<Pair<Double, Long>>>> walletCoinsTrading = new HashMap<>();
    protected static Set<Coins> coinsSet = new TreeSet<>(Comparator.comparing(Coins::getPrice).reversed());


    /**
     * Executes a buy transaction for a specified symbol, quantity, and wallet address.
     *
     * @param symbol        The symbol of the cryptocurrency.
     * @param quantity      The quantity to buy.
     * @param walletAddress The wallet address for the transaction.
     */
    public static void executeBuy(String symbol, long quantity, String walletAddress) {


        Coins coins = symbolToCoin.get(symbol);
        Trader trader = walletToTrader.get(walletAddress);

        if(coins != null && trader != null) {
            synchronized (coins) {
                if(coins.getCirculatingSupply() >= quantity) {
                    synchronized (trader) {
                        double price = coins.getPrice();
                        coins.updateCS(-quantity);
                        walletCoinsTrading.putIfAbsent(walletAddress, new HashMap<>());
                        walletCoinsTrading.get(walletAddress).putIfAbsent(symbol, new ArrayList<>());
                        walletCoinsTrading.get(walletAddress).get(symbol).add(new Pair<>(-price,quantity));
                        trader.updatePortFolio(symbol, quantity);
                    }
                }
            }
        }
    }

    /**
     * Executes a sell transaction for a specified symbol, quantity, and wallet address.
     *
     * @param symbol        The symbol of the cryptocurrency.
     * @param quantity      The quantity to sell.
     * @param walletAddress The wallet address for the transaction.
     */

    public static synchronized void executeSell(String symbol, long quantity, String walletAddress) {

        Coins coins = symbolToCoin.get(symbol);
        Trader trader = walletToTrader.get(walletAddress);

        if(coins != null && trader != null && trader.getPortfolio().containsKey(symbol)) {
            synchronized (coins) {
                if(trader.getPortfolio().get(symbol).getFirst() >= quantity) {
                    synchronized (trader) {
                        trader.updatePortFolio(symbol, -quantity);
                        double price = coins.getPrice();
                        coins.updateCS(quantity);
                        walletCoinsTrading.putIfAbsent(walletAddress, new HashMap<>());
                        walletCoinsTrading.get(walletAddress).putIfAbsent(symbol, new ArrayList<>());
                        walletCoinsTrading.get(walletAddress).get(symbol).add(new Pair<>(price,quantity));
                    }
                }
            }
        }
    }

    /**
     * Executes an update of the cryptocurrency's price for a specified symbol.
     *
     * @param symbol The symbol of the cryptocurrency.
     * @param price  The new price of the cryptocurrency.
     */

    public static synchronized void executeUpdatePrice(String symbol, double price) {
        Coins coins = symbolToCoin.get(symbol);

        if(coins !=null) {
            synchronized (coins) {
                coinsSet.remove(coins);
                coins.setPrice(price);
                coinsSet.add(coins);
            }
        }
    }

    /**
     * Executes an addition of volume (circulating supply) for a specified symbol.
     *
     * @param symbol The symbol of the cryptocurrency.
     * @param volume The volume (circulating supply) to add.
     */

    public static synchronized void executeAddVolume(String symbol, long volume) {

        Coins coins = symbolToCoin.get(symbol);

        if(coins != null) {
            synchronized (coins) {
                coins.updateCS(volume);
            }
        }
    }

    /**
     * Generates a random block hash.
     *
     * @return The generated block hash.
     */

    private String getBlockHash() {
        String saltchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();
/**
 * Introducing delay mimicking complex
 * calculation being performed.
 */

        while (transactionHash.length() < 128) {
            int index = (rnd.nextInt() * saltchars.length());
            transactionHash.append(saltchars.charAt(index)); }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase(); }

    /**
     * Displays details of a cryptocurrency coin based on either its code name or symbol.
     *
     * @param codeName The code name or symbol of the cryptocurrency.
     */

    public static void displayCoinDetails(String codeName) {
        if(symbolToCoin.containsKey(codeName)) {
            Coins coins = symbolToCoin.get(codeName);
            logger.info("Displaying coin details for codeName: {}", coins);
        }
        else {
            String symbol = coinNameToSymbol.get(codeName);
            Coins coins = symbolToCoin.get(symbol);
            logger.info("Displaying coin details for codeName: {}", coins);
        }
    }

    /**
     * Displays details of the top N coins in the set, ordered by price.
     *
     * @param n The number of top coins to display.
     */

    public static void displayTopNCoins(int n) {
        int i = 0;
        for (Coins coins : coinsSet) {
            if (i < n) {
                logger.info("Coin :: {} ", coins);
            }
            i++;
            if (i == n) {
                break;
            }
        }
    }


    /**
     * Displays the portfolio of a trader based on their wallet address.
     *
     * @param walletAddress The wallet address of the trader.
     */

    public static void displayPortfolio(String walletAddress) {
        Trader trader = walletToTrader.get(walletAddress);
        if(trader != null) {
            for (String symbol : trader.getPortfolio().keySet()) {
                logger.info(symbol , " :: {} " , trader.getPortfolio().get(symbol));
            }
        }
    }

    /**
     * Displays profit or loss for a trader based on their wallet address.
     *
     * @param walletAddress The wallet address of the trader.
     */
    public static void displayProfitLossForTrader(String walletAddress) {
        if(walletCoinsTrading.containsKey(walletAddress)) {
            for (String symbol : walletCoinsTrading.get(walletAddress).keySet()) {
                double buyAmount = 0.0D;
                double sellAmount = 0.0D;
                long remainingQuantity = 0;
                for (Pair<Double, Long> pair : walletCoinsTrading.get(walletAddress).get(symbol)) {
                    if(pair.getFirst() < 0) {
                        buyAmount += pair.getFirst() * pair.getSecond();
                        remainingQuantity += pair.getSecond();
                    }
                    else {
                        sellAmount += pair.getFirst() * pair.getSecond();
                        remainingQuantity -= pair.getSecond();
                    }
                }
                if(Double.compare(sellAmount, 0.0D) == 0) {
                    logger.info("No sell order placed yet for coin ::{}", symbol);

                }
                else
                    logger.info("Coin :: {} and Profile/Loss :: {}" , symbol, ((sellAmount + buyAmount) + (remainingQuantity * symbolToCoin.get(symbol).getPrice())));
            }
        }
    }

}

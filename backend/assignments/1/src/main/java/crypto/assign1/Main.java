package crypto.assign1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        loadFromCSV();
        JsonNode jsonTransactions = loadFromJson();
        if(jsonTransactions != null) {
            CountDownLatch latch = new CountDownLatch(jsonTransactions.size());

            executeTransactions(jsonTransactions, latch);

            try {
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error(String.valueOf(e));
            }
        }
    }

    private static JsonNode loadFromJson() {
        JsonNode jsonNode = null;
        try {
            jsonNode = JSONLoader.loadJSONFromResources("small_transaction.json");
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
        return jsonNode;
    }

    private static void loadFromCSV() {
        List<Coins> coins = loadCoins();
        for (Coins coin : coins) {
            CryptoTradingSystem.symbolToCoin.put(coin.getSymbol(), coin);
            CryptoTradingSystem.coinNameToSymbol.put(coin.getName(), coin.getSymbol());
            CryptoTradingSystem.coinsSet.add(coin);
        }
        List<Trader> traders = loadTraders();
        for (Trader trader : traders) {
            CryptoTradingSystem.walletToTrader.put(trader.getWalletAddress(), trader);
        }
    }

    private static List<Trader> loadTraders() {
        List<Trader> traders = new ArrayList<>();
        try {
            List<CSVRecord> csvRecords = CSVLoader.loadCSVFromResources("traders.csv");
            for (CSVRecord csvRecord : csvRecords) {
                String firstName = csvRecord.get("first_name");
                String lastName = csvRecord.get("last_name");
                String phone = csvRecord.get("phone");
                String walAddr = csvRecord.get("Wallet Address");
                Trader trader = new Trader(firstName, lastName, phone, walAddr);
                traders.add(trader);
            }
        } catch (Exception e) {
            logger.error("unable to load traders {}", e.getMessage());
        }
        return traders;
    }

    private static List<Coins> loadCoins() {
        List<Coins> coins = new ArrayList<>();
        try {
            List<CSVRecord> csvRecords = CSVLoader.loadCSVFromResources("coins.csv");
            for (CSVRecord csvRecord : csvRecords) {
                int rank = Integer.parseInt(csvRecord.get("Rank"));
                String name = csvRecord.get("Name");
                String symbol = csvRecord.get("Symbol");
                double price = Double.parseDouble(csvRecord.get("Price"));
                long cirSup = Long.parseLong(csvRecord.get("Circulating Supply"));
                Coins coin = new Coins(rank, name, symbol, price, cirSup);
                coins.add(coin);
            }
        } catch (Exception e) {
            logger.error("unable to load coins {}" ,e.getMessage());
        }
        return coins;
    }

    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) {
        for (JsonNode transaction : jsonTransactions) {
            ExecuteTransaction executeTransaction = new ExecuteTransaction(transaction, latch);
            executeTransaction.run();
        }
        CryptoTradingSystem.displayTopNCoins(5);
        CryptoTradingSystem.displayCoinDetails("SOL");
        CryptoTradingSystem.displayCoinDetails("Solana");
        String walletAddress = "0x1397ffcfbd2badb81a0734035a957ef1";
        CryptoTradingSystem.displayPortfolio(walletAddress);
        logger.info(CryptoTradingSystem.walletCoinsTrading.getOrDefault(walletAddress, new HashMap<>()).toString());
        CryptoTradingSystem.displayProfitLossForTrader(walletAddress);
    }

    public static ArrayList<String[]> parseCSV(Path filePath) {
        ArrayList<String[]> csvData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath.toString()))) {
            reader.readNext();
            List<String[]> records = reader.readAll();
            csvData.addAll(records);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
        return csvData;
    }
    public static JsonNode parseJsonFile(String filePath) throws IOException {
        JsonNode jsonNode = null;
        Path file = Path.of(filePath);

        ObjectMapper objectMapper = new ObjectMapper();
        jsonNode = objectMapper.readTree(file.toFile());
        return jsonNode;
    }
}
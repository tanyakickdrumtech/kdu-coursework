package assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Logger logger= LoggerFactory.getLogger(Main.class);
        Tournament tournament = new Tournament();
        tournament.loadFromCSV("/Users/tanyaagarwal/Downloads/kdu.backend.assessment1/src/main/resources/IPL_2021-data.csv");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            logger.info("IPL 2021 Menu:");
            logger.info("1. Bowlers with at least 40 wickets for a team");
            logger.info("2. Highest wicket-taker and highest run-scorer for a team");
            logger.info("3. Top 3 Run-scorers and Top 3 Wicket-takers");
            logger.info("4. Generate Match Fixtures");
            logger.info("5. Exit");

            logger.info("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    logger.info("Enter team name: ");
                    String teamName1 = scanner.nextLine();
                    List<String> bowlers = tournament.getBowlersWith40Wickets(teamName1);
                    logger.info("Bowlers with at least 40 wickets for {} : {}" , teamName1 , bowlers);
                    break;
                case 2:
                    logger.info("Enter team name: ");
                    String teamName2 = scanner.nextLine();
                    tournament.displayHighestWicketTakerAndRunScorer(teamName2);
                    break;
                case 3:
                    tournament.displayTop3RunScorersAndWicketTakers();
                    break;
                case 4:
                    tournament.generateMatchFixtures();
                    tournament.writeMatchFixturesToCSV("/Users/tanyaagarwal/Downloads/kdu.backend.assessment1/src/main/resources/match_fixtures.csv");
                    logger.info("Match fixtures generated and written to 'matchfixtures.csv'.");
                    break;
                case 5:
                    logger.info("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    logger.info("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

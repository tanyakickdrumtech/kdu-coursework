package assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Tournament {
    final Logger logger= LoggerFactory.getLogger(Tournament.class);
    private List<Team> teams = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public void loadFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                Player player = new Player(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]),
                        Double.parseDouble(data[5]), Double.parseDouble(data[6]), Integer.parseInt(data[7]));
                players.add(player);

                Team team = getTeamByName(data[1]);
                if (team == null) {
                    team = new Team(data[1]);
                    teams.add(team);
                }
                team.addPlayer(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Team getTeamByName(String name) {
        return teams.stream()
                .filter(team -> team.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<String> getBowlersWith40Wickets(String teamName) {
        return players.stream()
                .filter(player -> player.getTeam().equals(teamName) && player.getWickets() >= 40)
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public void displayHighestWicketTakerAndRunScorer(String teamName) {
        Team team = getTeamByName(teamName);
        if (team != null) {
            Player highestWicketTaker = Collections.max(team.getPlayers(), Comparator.comparingInt(Player::getWickets));
            Player highestRunScorer = Collections.max(team.getPlayers(), Comparator.comparingInt(Player::getRuns));

            logger.info("Highest Wicket-taker:{}, Wickets {} " + highestWicketTaker.getName() + ", Wickets: " + highestWicketTaker.getWickets());
            logger.info("Highest Run-scorer: " + highestRunScorer.getName() + ", Runs: " + highestRunScorer.getRuns());
        } else {
            logger.info("Team not found!");
        }
    }

    public void displayTop3RunScorersAndWicketTakers() {
        List<Player> topRunScorers = new ArrayList<>(this.getPlayers());
        List<Player> topWicketTakers = new ArrayList<>(this.getPlayers());

        topRunScorers.sort(Comparator.comparingInt(Player::getRuns).reversed());
        topWicketTakers.sort(Comparator.comparingInt(Player::getWickets).reversed());

        logger.info("Top 3 Run-scorers:");
        topRunScorers.stream()
                .limit(3)
                .forEach(player -> logger.info(player.getName() + ", Team: " + player.getTeam() + ", Runs: " + player.getRuns()));

        logger.info("Top 3 Wicket-takers:");
        topWicketTakers.stream()
                .limit(3)
                .forEach(player -> logger.info(player.getName() + ", Team: " + player.getTeam() + ", Wickets: " + player.getWickets()));
    }

    public void generateMatchFixtures() {
        int matchNumber = 1;
        List<Match> matchFixtures = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i+1; j < teams.size(); j++){
                Team team = teams.get(i);
                Team otherTeam = teams.get(j);
                matchFixtures.add(new Match(formatDate(LocalDateTime.now().plusDays(matchNumber)), matchNumber++,
                        team.getName(), otherTeam.getName(), team.getName() + "_home"));
                matchFixtures.add(new Match(formatDate(LocalDateTime.now().plusDays(matchNumber)), matchNumber++,
                        otherTeam.getName(), team.getName(), otherTeam.getName() + "_home"));
            }
        }
        matches = matchFixtures;
    }

    private String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy h:mm a");
        return date.format(formatter);
    }

    public void writeMatchFixturesToCSV(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Date,Match number,Team home,Team away,Ground\n");

            for (Match match : matches) {
                writer.write(match.getDateTime() + "," + match.getMatchNumber() + ","
                        + match.getTeamHome() + "," + match.getTeamAway() + ","
                        + match.getGround() + "\n");
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

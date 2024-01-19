package assignment;

public class Match {
    private String dateTime;
    private int matchNumber;
    private String teamHome;
    private String teamAway;
    private String ground;

    public Match(String dateTime, int matchNumber, String teamHome, String teamAway, String ground) {
        this.dateTime = dateTime;
        this.matchNumber = matchNumber;
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.ground = ground;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public String getTeamHome() {
        return teamHome;
    }

    public String getTeamAway() {
        return teamAway;
    }

    public String getGround() {
        return ground;
    }
}

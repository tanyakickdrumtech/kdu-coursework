package kdu.backend.assessment1;

import java.util.Date;

public class Match {
    private Date dateTime;
    private int matchNumber;
    private String teamHome;
    private String teamAway;
    private String ground;

    public Match(Date dateTime, int matchNumber, String teamHome, String teamAway, String ground) {
        this.dateTime = dateTime;
        this.matchNumber = matchNumber;
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.ground = ground;
    }

    public Date getDateTime() {
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

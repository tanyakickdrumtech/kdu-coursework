package assignment;

public class Player {
    private String name;
    private String team;
    private String role;
    private int matches;
    private int runs;
    private double average;
    private double sr;
    private int wickets;

    public Player(String name, String team, String role, int matches, int runs, double average, double sr, int wickets) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.matches = matches;
        this.runs = runs;
        this.average = average;
        this.sr = sr;
        this.wickets = wickets;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getRole() {
        return role;
    }

    public int getMatches() {
        return matches;
    }

    public int getRuns() {
        return runs;
    }

    public double getAverage() {
        return average;
    }

    public double getSr() {
        return sr;
    }

    public int getWickets() {
        return wickets;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", role='" + role + '\'' +
                ", matches=" + matches +
                ", runs=" + runs +
                ", average=" + average +
                ", sr=" + sr +
                ", wickets=" + wickets +
                '}';
    }
}

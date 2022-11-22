package game;

import resurces.Player;

public class StatsCommands {
    public void getTotalGamesPlayed(Player p1, Player p2) {
        System.out.println(p1.getWins()+ p2.getWins());
    }
    public void getPlayerWins(Player player) {
        System.out.println(player.getWins());
    }
}

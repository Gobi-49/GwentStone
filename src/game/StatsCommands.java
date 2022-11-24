package game;

import resurces.Player;

public class StatsCommands {
    public int getTotalGamesPlayed(Player p1, Player p2) {
        return p1.getWins()+ p2.getWins();
    }
    public int getPlayerWins(Player player) {
        return player.getWins();
    }
}
